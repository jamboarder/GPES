package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoFixture;

public class SensorTest {
	@Test
	public void shouldDetectEntireEvent() {
		List<Info> infos = InfoFixture.createInfo();
		Event event = Event.create();
		event.infoStore().addAll(infos);
		List<Info> detectedInfo = Sensor.detect(event, ActorRuleFixture.detectsAllSensorTestRule(infos));
		Assert.assertTrue("Entire event should be detected", detectedInfo.equals(event.infoStore().getInfo()));
	}
	
	@Test
	public void shouldDetectSomeOfEvent() {
		List<Info> infos = InfoFixture.createInfo();
		Event event = Event.create();
		event.infoStore().addAll(infos);
		Info targetInfo = infos.get(0);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		subjectMatches.add(targetInfo.getSubject());
		List<Info> detectedInfo = Sensor.detect(event, ActorRuleFixture.detectsSubjectsSensorRule(subjectMatches));
		Assert.assertTrue("Some of event should be detected", detectedInfo.contains(targetInfo));
	}
	
	@Test
	public void shouldNotDetectEvent() {
		List<Info> infos = InfoFixture.createInfo();
		Event event = Event.create();
		event.infoStore().addAll(infos);
		List<Info> detectedInfo = Sensor.detect(event, 
				ActorRuleFixture.detectsNothingSensorTestRule(infos));
		Assert.assertTrue("None of event should be detected", detectedInfo.isEmpty());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void shouldDetectAndRecordEvent() {
		List<Info> infos = InfoFixture.createInfo();
		ActorRule sensorRule = ActorRuleFixture.detectsAllSensorTestRule(infos);
		Actor actor = Actor.create();
		actor.addRule(sensorRule);
		Event event = Event.create();
		event.infoStore().addAll(infos);
		List<Info> detectedInfo = Sensor.detect(event, ActorRuleFixture.detectsAllSensorTestRule(infos));
		Sensor.detectAndRecord(event, actor);
		//TODO: Define requirements for recorded info and how to compare with detected info
		Assert.assertTrue("Should detect and record event", false);
	}
	
}
