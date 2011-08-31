package com.uobia.gpes.bridge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.actor.Actor;
import com.uobia.gpes.environment.Environment;
import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class BridgeTest {
	@Test
	public void shouldReturnEnvironment() {
		Environment e = Environment.create();
		Actor actor = Actor.create();
		actor.infoStore().add(Info.create(1, 2, 3));
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(actor);
		e.addActors(actors);
		Event event = Event.create();
		event.infoStore().add(Info.create(4, 5, 6));
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		e.addEvents(events);
		Bridge bridge = Bridge.create(e);
		Assert.assertTrue("Environment should be correctly set and returned", bridge.getEnvironment().equals(e));		
	}
	
	@Test
	public void shouldSetAndReturnMinInterval() {
		Bridge bridge = Bridge.create(Environment.create());
		int minInterval = bridge.getMinInterval() + 1000;
		bridge.setMinInterval(minInterval);
		Assert.assertTrue("Should set minInterval", bridge.getMinInterval() == minInterval);
	}

	@Test
	public void shouldSetAndReturnMaxStepCount() {
		Bridge bridge = Bridge.create(Environment.create());
		long maxStepCount = bridge.getMaxStepCount() + 1000;
		bridge.setMaxStepCount(maxStepCount);
		Assert.assertTrue("Should set maxStepCount", bridge.getMaxStepCount() == maxStepCount);
	}
}
