package com.uobia.gpes.event;

import junit.framework.Assert;

import org.junit.Test;

public class EventStateCheckerTest {
	@Test
	public void shouldBeReproductionEvent() {
		//TODO: Finish Reproduction event creation
		Event event = Event.create();
		Assert.assertTrue("Should be reproduction event if...", EventStateChecker.isReproductionEvent(event));
	}

	@Test
	public void shouldNotBeReproductionEvent() {
		//TODO: Finish Reproduction event creation
		Event event = Event.create();
		Assert.assertFalse("Should not be reproduction event if...", EventStateChecker.isReproductionEvent(event));
	}

	@Test
	public void shouldBeExpired() {
		//TODO: Finish Reproduction event creation
		Event event = Event.create();
		Assert.assertTrue("Should be expired if...", EventStateChecker.isExpired(event));
	}

	@Test
	public void shouldNotBeExpired() {
		//TODO: Finish Reproduction event creation
		Event event = Event.create();
		Assert.assertFalse("Should not be reproduction event if...", EventStateChecker.isExpired(event));
	}
}
