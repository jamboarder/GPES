package com.uobia.gpes.environment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.actor.Actor;
import com.uobia.gpes.event.Event;

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
