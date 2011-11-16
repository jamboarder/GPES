package com.uobia.gpes.eventspace;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class EventSpaceTest {
	@Test
	public void shouldAddEvent() {
		EventSpace eventSpace = EventSpace.create();
		List<Event> events = generateTestEvents();
		eventSpace.setMaxSize(events.size() + 1);
		eventSpace.addEvents(generateTestEvents());
        boolean addSuccessful = eventSpace.getEvents().equals(generateTestEvents());
        Assert.assertTrue("Events should be correctly added", addSuccessful);
	}

	@Test
	public void shouldNotAddEvent() {
		EventSpace eventSpace = EventSpace.create();
		List<Event> events = generateTestEvents();
		eventSpace.setMaxSize(events.size() - 1);
		Assert.assertEquals("Events should not be added", false, eventSpace.addEvents(events));
        Assert.assertEquals("Event space size should not change", 0, eventSpace.getEvents().size());
	}
	
	@Test
	public void shouldRemoveExpiredEvents() {
		EventSpace eventSpace = EventSpace.create();
		List<Event> events = generateExpiredEvents();
		eventSpace.setMaxSize(events.size() +1);
		eventSpace.addEvents(events);
		eventSpace.removeExpiredEvents();
		Assert.assertEquals("Expired events should be removed", 0, eventSpace.getEvents().size());
	}

	public static List<Event> generateTestEvents() {
		Event event1 = Event.create(0);
		event1.infoStore().add(Info.create(1, 2, 3));
		List<Event> events = new ArrayList<Event>();
		events.add(event1);
		return events;
	}
	
	public static List<Event> generateExpiredEvents() {
		//TODO: Create event that matches expiration criteria
		Event event1 = Event.create(0);
		event1.infoStore().add(Info.create(1, 2, 3));
		List<Event> events = new ArrayList<Event>();
		events.add(event1);
		return events;
	}
}
