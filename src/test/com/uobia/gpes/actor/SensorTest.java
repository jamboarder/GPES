package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.actor.ActorRule.RuleType;
import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class SensorTest {
	@Test
	public void shouldDetectEntireEvent() {
		Event event = sensableTestEvent();
		List<Info> detectedInfo = Sensor.detect(event, detectsAllSensorTestRule());
		Assert.assertTrue("Entire event should be detected", detectedInfo.equals(event.infoStore().allInfo()));
	}
	
	@Test
	public void shouldDetectSomeOfEvent() {
		Event event = sensableTestEvent();
		//TODO: Define what the partially detected info should look like
		List<Info> targetInfo = new ArrayList<Info>();
		List<Info> detectedInfo = Sensor.detect(event, detectsSomeSensorTestRule());
		Assert.assertTrue("Some of event should be detected", detectedInfo.equals(targetInfo));
	}
	
	@Test
	public void shouldNotDetectEvent() {
		List<Info> detectedInfo = Sensor.detect(sensableTestEvent(), 
				SensorTest.detectsNothingSensorTestRule());
		Assert.assertTrue("Some of event should be detected", detectedInfo.isEmpty());
	}
	
	@Test
	public void shouldDetectAndRecordEvent() {
		Actor actor = Actor.create();
		actor.addRule(detectsAllSensorTestRule());
		Event event = sensableTestEvent();
		@SuppressWarnings("unused")
		List<Info> detectedInfo = Sensor.detect(event, detectsAllSensorTestRule());
		Sensor.detectAndRecord(event, actor);
		//TODO: Define requirements for recorded info and how to compare with detected info
		Assert.assertTrue("Should detect and record event", false);
	}
	
	@Test
	public void shouldDetectAndRecordAllEvents() {
		Actor actor = Actor.create();
		actor.addRule(detectsAllSensorTestRule());
		List<Event> events = sensableTestEvents();
		Sensor.detectAllAndRecord(events, actor);
		//TODO: Define requirements for recorded info and how to compare with detected info
		Assert.assertTrue("Should detect and record event", false);
	}
	
	public static final Event sensableTestEvent() {
		Event event = Event.create();
		//TODO: Add event data
		return event;
	}
	
	private static List<Event> sensableTestEvents() {
		List<Event> events = new ArrayList<Event>();
		events.add(sensableTestEvent());
		// TODO Add more event data
		return events;
	}

	public static final ActorRule detectsAllSensorTestRule() {
		ActorRule rule = ActorRule.create();
		rule.setRuleType(RuleType.SensorRule);
		//TODO: Add Sensor match and action rules
		return rule;
	}

	public static final ActorRule detectsSomeSensorTestRule() {
		ActorRule rule = ActorRule.create();
		rule.setRuleType(RuleType.SensorRule);
		//TODO: Add Sensor match and action rules
		return rule;
	}
	public static final ActorRule detectsNothingSensorTestRule() {
		ActorRule rule = ActorRule.create();
		rule.setRuleType(RuleType.SensorRule);
		//TODO: Add Sensor match and action rules
		return rule;
	}
}
