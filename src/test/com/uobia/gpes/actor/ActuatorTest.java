package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;

public class ActuatorTest {
	@Test
	public void shouldMatch() {
		Actor actor = Actor.create();
		actor.addRule(actuatorTestRule());
		actor.infoStore().addAll(matchableTestInfo());
		List<Integer> matchedIndexes = Actuator.find(actor, actuatorTestRule());
		List<Info> matchedInfo = actor.infoStore().infoForIndexes(matchedIndexes);
		Assert.assertTrue("Info should be matched", matchedInfo.equals(matchableTestInfo()));
	}

	@Test
	public void shouldNotMatch() {
		Actor actor = Actor.create();
		actor.addRule(ActuatorTest.actuatorTestRule());
		actor.infoStore().addAll(unMatchableTestInfo());
		List<Integer> matchedIndexes = Actuator.find(actor, actuatorTestRule());
		List<Info> matchedInfo = actor.infoStore().infoForIndexes(matchedIndexes);
		Assert.assertFalse("Info should not be matched", matchedInfo.equals(matchableTestInfo()));
	}

	@Test
	public void shouldMatchAndAct() {
		Actor actor = Actor.create();
		actor.addRule(ActuatorTest.actuatorTestRule());
		actor.infoStore().addAll(matchableTestInfo());
		@SuppressWarnings("unused")
        List<Event> events = Actuator.createEvents(actor);
		//TODO: Define requirements for created events and how to compare with expectation
		Assert.assertTrue("Should match and act", false);
	}

	private static ActorRule actuatorTestRule() {
		ActorRule rule = ActorRule.create();
		rule.setRuleType(ActorRule.RuleType.ActuatorRule);
		// TODO create test actuator rule
		return rule;
	}
	
	private static List<Info> matchableTestInfo() {
		// TODO Auto-generated method stub
		List<Info> testInfo = new ArrayList<Info>();
		return testInfo;
	}

	private static List<Info> unMatchableTestInfo() {
		// TODO Auto-generated method stub
		List<Info> testInfo = new ArrayList<Info>();
		return testInfo;
	}
}
