package com.uobia.gpes.actor;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoFixture;

public class ActuatorTest {
	@Test
	public void shouldFindMatches() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule actuatorRule = ActorRuleFixture.createMatchingActuatorRule(infos);
		actor.addRule(actuatorRule);
		List<Integer> matchedIndexes = Actuator.find(actor, actuatorRule);
		List<Integer> correctMatchedIndexes = actor.infoStore().matchIndexes(actuatorRule.getMatchRules());
		Assert.assertTrue("Info should be matched", matchedIndexes.equals(correctMatchedIndexes));
	}

	@Test
	public void shouldNotMatch() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule actuatorRule = ActorRuleFixture.createNoMatchActuatorRule(infos);
		actor.addRule(actuatorRule);
		List<Integer> matchedIndexes = Actuator.find(actor, actuatorRule);
		List<Integer> correctMatchedIndexes = actor.infoStore().matchIndexes(actuatorRule.getMatchRules());
		Assert.assertFalse("Info should not be matched", matchedIndexes.equals(correctMatchedIndexes));
	}

	@Test
	public void shouldCreateEvent() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule actuatorRule = ActorRuleFixture.createMatchingActuatorRule(infos);
		actor.addRule(actuatorRule);
        List<Event> events = Actuator.createEvents(actor);
		//TODO: Define requirements for created events and how to compare with expectation
		Assert.assertTrue("Should match and act", events.size() > 0);
	}

}
