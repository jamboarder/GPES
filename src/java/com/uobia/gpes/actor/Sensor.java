package com.uobia.gpes.actor;

import java.util.List;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoStore;

public class Sensor {

	public static List<Info> detect(Event event, Rule sensorRule) {
		InfoStore eventStore = event.infoStore();
		List<Info> sensorMatchRule = sensorRule.getMatchRule();
		List<Info> eventMatches = eventStore.matchInfo(sensorMatchRule);
		//TODO Modify raw matches based on sensor action rule
		return eventMatches;
	}

	public static void detectAndRecord(Event event, Actor actor) {
		List<Rule> sensorRules = actor.getSensorRules();
		for (int i = 0; i < sensorRules.size(); i++) {
			List<Info> eventMatches = detect(event, sensorRules.get(i));
			//TODO Add sensor rule semantics to matched info
			actor.infoStore().addAll(eventMatches);
		}
	}

	public static void detectAllAndRecord(List<Event> events, Actor actor) {
		for (int i = 0; i < events.size(); i++) {
			detectAndRecord(events.get(i), actor);
		}
	}
}
