import java.util.ArrayList;
import java.util.List;


public class Environment {
	private List<Actor> actors = new ArrayList<Actor>();
	private List<Event> events = new ArrayList<Event>();
	public int maxSize;
	public int stepCount = 0;
	
	public void clear() {
		actors.clear();
		events.clear();
	}
	
	public boolean addActor(Actor newActor) {
		//TODO: Check maxSize before adding actor
		actors.add(newActor);
		return true;
	}
	
	public boolean addEvent(Event newEvent) {
		//TODO: Check maxSize before adding event
		events.add(newEvent);
		return true;
	}
	
	public List<Event> step(int stepCost) {
		//Extract cost to execute this step
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).extractCost(stepCost);
		}

		//Actor death check
		for (int i = 0; i < actors.size(); i++) {
			if (actors.get(i).isDead()) {
				actors.remove(i);
			}
		}

		//Event expiration check
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).isExpired()) {
				events.remove(i);
			}
		}

		//Actor sensing
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).sense(events);
		}
		
		//Actor induction
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).induct();
		}
		
		//Actor deduction
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).deduct();
		}
		
		//Actor actuation
		List<Event> allCreatedEvents = new ArrayList<Event>();
		for (int i = 0; i < actors.size(); i++) {
			List<Event> createdEvents = actors.get(i).actuate();
			allCreatedEvents.addAll(createdEvents);
			events.addAll(createdEvents);
		}
		
		//Actor reproduction
		for (int i = 0; i < allCreatedEvents.size(); i++) {
			Event createdEvent = allCreatedEvents.get(i);
			if (createdEvent.isReproductionEvent()) {
				Actor newActor = actors.get(i).reproduce(createdEvent);
				actors.add(newActor);
			}
		}

		stepCount++;
		
		return allCreatedEvents;
	}
}
