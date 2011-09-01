package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class Sensor {

	public static List<Info> detect(Event event, ActorRule sensorRule) {
		//TODO finish implementation
		List<Info> eventMatches = new ArrayList<Info>();
		return eventMatches;
	}

	public static void detectAndRecord(Event event, Actor actor) {
		List<ActorRule> sensorRules = actor.getSensorRules();
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
