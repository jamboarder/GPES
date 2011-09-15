package com.uobia.gpes.event;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;

public class EventTest {
	@Test
	public void shouldBeEqual() {
		Event e1 = Event.create();
		e1.infoStore().add(Info.create(1, 2, 3));
		Event e2 = Event.create();
		e2.infoStore().add(Info.create(1, 2, 3));
		Assert.assertTrue("Events with same info should be equal", e1.equals(e2));
	}
	
	@Test
	public void shouldNotBeEqual() {
		Event e1 = Event.create();
		e1.infoStore().add(Info.create(1, 2, 3));
		Event e2 = Event.create();
		e2.infoStore().add(Info.create(3, 4, 5));
		Assert.assertFalse("Events with different info should not be equal", e1.equals(e2));
	}
	
	@Test
	public void shouldReturnCreatorId() {
		int creatorId = 1234;
		Event event = Event.create(creatorId);
		Assert.assertTrue("Should correctly return event creator id", event.getCreatorId() == creatorId);
	}
}
