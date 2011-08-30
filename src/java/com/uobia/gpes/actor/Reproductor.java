package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.event.Event;

public class Reproductor {

    public static Actor reproduceFromOne(Actor parent) {
        Actor offspring = Actor.create();
        //TODO copy and mutate per mutation rules in parent
        return offspring;
    }

    public static Actor reproduceFromMultiple(List<Actor> parents) {
        Actor offspring = Actor.create();
        //TODO copy and mutate(recombine) per mutation rules in parents
        return offspring;
    }
    
    public static List< ArrayList<Actor>> parentsOfPartials(List<Actor> candidateActors, List<Event> partialReproductionEvents) {
    	List< ArrayList<Actor>> parentSets = new ArrayList< ArrayList<Actor>>();
    	for (int i = 0; i < partialReproductionEvents.size(); i++) {
    		//TODO find matching sets of partialReproductionEvents and corresponding parents
    	}
    	return parentSets;
    }
}
