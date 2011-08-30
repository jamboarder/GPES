package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class Actuator {

	public static List<Integer> find(Actor actor, Rule actuatorRule) {
		List<Info> matchRule = actuatorRule.getMatchRule();
		List<Integer> matchedIndexes = actor.infoStore().matchIndexes(matchRule);
		return matchedIndexes;
	}

	public static List<Event> createEvents(Actor actor) {
	    List<Event> events = new ArrayList<Event>();
		List<Rule> actuatorRules = actor.getActuatorRules();
		for (int i =0; i < actuatorRules.size(); i++) {
			Rule actuatorRule = actuatorRules.get(i);
			List<Integer> matchedIndexes = find(actor, actuatorRule);
			List<Info> actionRule = actuatorRule.getActionRule();
			Event newEvent = createEvent(actor, actionRule, matchedIndexes);
			events.add(newEvent);
		}
	    return events;
	}

	private static Event createEvent(Actor actor, List<Info> actionRule,
			List<Integer> matchedIndexes) {
		// TODO Implement event creation
		Event event = Event.create();
		return event;
	}

}
