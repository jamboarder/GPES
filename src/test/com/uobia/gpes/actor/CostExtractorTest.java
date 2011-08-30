package com.uobia.gpes.actor;

import org.junit.Assert;
import org.junit.Test;

public class CostExtractorTest {
	@Test
	public void shouldExtractCost() {
		Actor actor = createTestActor();
		int cost = 10;
		CostExtractor.extract(actor, cost);
		//TODO Determine requirements for correctly extracting cost
		Assert.assertTrue("Cost should be correctly extracted from the actor", false);
	}

	private static Actor createTestActor() {
		// TODO Auto-generated method stub
		return Actor.create();
	}
}
