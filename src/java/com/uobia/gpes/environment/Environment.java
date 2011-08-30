package com.uobia.gpes.environment;
import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.actor.*;
import com.uobia.gpes.event.*;


public class Environment {
	public List<Actor> actors = new ArrayList<Actor>();
	public List<Event> events = new ArrayList<Event>();
	public int maxSize;
	public int stepCount = 0;
	private List<Actor> partialCandidates = new ArrayList<Actor>();
	private List<Event> partialReproductionEvents = new ArrayList<Event>();
	
	private Environment() {
		for (int i = 0; i < 50; i++) {
			actors.add(Actor.create());
		}
	}

    public static Environment create() {
        return new Environment();
    }

	public void clear() {
		actors.clear();
		events.clear();
	}
	
	public boolean isDead() {
		if (actors.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
    public void addActors(List<Actor> actors) {
        // TODO Check against max allowable environment size before adding
        this.actors.addAll(actors);
        
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void addEvents(List<Event> events) {
        // TODO Check against max allowable environment size before adding
        this.events.addAll(events);
        
    }

    public List<Event> getEvents() {
        return events;
    }

	public List<Event> step(int stepCost) {
		List<Event> newEvents = new ArrayList<Event>();
		List< ArrayList<Actor> > parentActorSets = new ArrayList< ArrayList<Actor> >();

		//Extract cost to execute this step
		ExtractCost(stepCost);

		//Actor death check
		RemoveDeadActors();

		//Event expiration check
		RemoveExpiredEvents();
		
		//Return if there are no actors
		if (this.isDead()) {
			return newEvents;
		}

		//Actor sensing
		Sense();
		
		//Actor induction
		Induct();
		
		//Actor deduction
		Deduct();
		
		//Actor actuation
		Actuate(newEvents, parentActorSets);
		
		//Actor reproduction
		Reproduce(parentActorSets);

		stepCount++;
		
		return newEvents;
	}

	private void ExtractCost(int stepCost) {
		for (int i = 0; i < actors.size(); i++) {
			CostExtractor.extract(actors.get(i), stepCost);
		}
	}

	private void RemoveDeadActors() {
		for (int i = 0; i < actors.size(); i++) {
			if (ActorStateChecker.isDead(actors.get(i))) {
				System.out.println("Removing dead actor...");
				actors.remove(i);
			}
		}
	}

	private void RemoveExpiredEvents() {
		for (int i = 0; i < events.size(); i++) {
			if (EventStateChecker.isExpired(events.get(i))) {
				events.remove(i);
			}
		}
	}

	private void Sense() {
		System.out.println("Actors sensing...");
		for (int i = 0; i < actors.size(); i++) {
			Sensor.detectAllAndRecord(events, actors.get(i));
			actors.get(i);
		}
	}

	private void Induct() {
		System.out.println("Actors induction...");
		for (int i = 0; i < actors.size(); i++) {
			Inductor.findAndAct(actors.get(i));
		}
	}

	private void Deduct() {
		System.out.println("Actors deduction...");
		for (int i = 0; i < actors.size(); i++) {
			Deductor.findAndAct(actors.get(i));
		}
	}

	private void Actuate(List<Event> newEvents, List< ArrayList<Actor> > parentActorSets) {
		System.out.println("Actors actuation...");
		
		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			List<Event> createdEvents = Actuator.createEvents(actor);
			newEvents.addAll(createdEvents);
			
			//Capture parents of reproduction events
			for (int j = 0; j < createdEvents.size(); j++) {
				Event createdEvent = createdEvents.get(j);
				if (EventStateChecker.isReproductionEvent(createdEvent)) {
					ArrayList<Actor> parentActor = new ArrayList<Actor>();
					parentActor.add(actor);
					parentActorSets.add(parentActor);
				} else if (EventStateChecker.isPartialReproductionEvent(createdEvent)) {
					partialCandidates.add(actor);
					partialReproductionEvents.add(createdEvent);
				}
			}
		}
		
	}
	
	private void Reproduce(List<ArrayList<Actor>> parentActorSets) {
		//Determine parents of completed partial reproduction events
		if (!partialCandidates.isEmpty()) {
			List< ArrayList<Actor>> partialParents = new ArrayList< ArrayList<Actor>>();
			partialParents = Reproductor.parentsOfPartials(partialCandidates, partialReproductionEvents);
			parentActorSets.addAll(partialParents);
		    //FIXME Clean up partialCandidates and partialReporductionEvents of identified parents and events
			//      OR Just find a better way of handling recombination.
		}

		for (int i = 0; i < parentActorSets.size(); i++) {
			System.out.println("Reproducing actor...");
			List<Actor> parents = parentActorSets.get(i);
			if (parents.size() == 1) {
				Actor newActor = Reproductor.reproduceFromOne(actors.get(i));
				actors.add(newActor);
			} else if (parents.size() > 1) {
				Actor newActor = Reproductor.reproduceFromMultiple(parents);
				actors.add(newActor);
			}
			//TODO Handle multiple parents
		}
		parentActorSets.clear();
	}
}
