package com.uobia.gpes.environment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.actor.Actor;
import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class EnvironmentTest {
	@Test
	public void shouldAddActors() {
		Environment environment = Environment.create();
		environment.clear();
		environment.addActors(generateTestActors());
		boolean addSuccessful = environment.getActors().equals(generateTestActors());
		Assert.assertTrue("Actors should be correctly added", addSuccessful);
	}
	
    @Test
    public void shouldAddEvents() {
        Environment environment = Environment.create();
		environment.clear();
        environment.addEvents(generateTestEvents());
        boolean addSuccessful = environment.getEvents().equals(generateTestEvents());
        Assert.assertTrue("Events should be correctly added", addSuccessful);
    }
    
	@Test
	public void shouldClear() {
		Environment environment = Environment.create();
		environment.addActors(generateTestActors());
		environment.addEvents(generateTestEvents());
		environment.clear();
		Assert.assertTrue("Environment should be cleared", environment.getActors().isEmpty() && environment.getEvents().isEmpty());
	}
	
	@Test
	public void shouldBeEqual() {
		Environment e1 = Environment.create();
		Environment e2 = Environment.create();
		Actor actor = Actor.create();
		actor.infoStore().add(Info.create(1, 2, 3));
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(actor);
		e1.addActors(actors);
		e2.addActors(actors);
		Event event = Event.create();
		event.infoStore().add(Info.create(4, 5, 6));
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		e1.addEvents(events);
		e2.addEvents(events);
		Assert.assertTrue("Environments with the same actors and events should be equal", e1.equals(e2));
	}
	
	@Test
	public void shouldNotBeEqual() {
		Environment e1 = Environment.create();
		Environment e2 = Environment.create();
		Actor actor1 = Actor.create();
		actor1.infoStore().add(Info.create(1, 2, 3));
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(actor1);
		e1.addActors(actors);
		Actor actor2 = Actor.create();
		actor2.infoStore().add(Info.create(7, 8, 9));
		actors.add(actor2);
		e2.addActors(actors);
		Event event = Event.create();
		event.infoStore().add(Info.create(4, 5, 6));
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		e1.addEvents(events);
		e2.addEvents(events);
		Assert.assertFalse("Environments with different actors or events should not be equal", e1.equals(e2));
	}
	@Test
	public void shouldBeDead() {
		Environment environment = Environment.create();
		environment.clear();
		Assert.assertTrue("Empty environment should be dead", environment.isDead());
		
		environment.addEvents(generateTestEvents());
		Assert.assertTrue("Environment with events but no actors should be dead", environment.isDead());
	}
	
	@Test
	public void shouldNotBeDead() {
		Environment environment = Environment.create();
		environment.addActors(generateTestActors());
		Assert.assertFalse("Environment with actors should not be dead", environment.isDead());
	}
	
	@Test
	public void shouldStepCorrectly() {
		//TODO Create test for correctly executing one step
	}

	private static List<Actor> generateTestActors() {
		// TODO Auto-generated method stub
		return new ArrayList<Actor>();
	}
	
	private static List<Event> generateTestEvents() {
		// TODO Auto-generated method stub
		return new ArrayList<Event>();
	}
}
