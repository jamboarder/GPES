package com.uobia.gpes.actor;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoFixture;

public class InductorTest {
	@Test
	public void shouldFindMatches() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule inductorRule = ActorRuleFixture.createMatchingInductorRule(infos);
		actor.addRule(inductorRule);
		List<Integer> matchedIndexes = Actuator.find(actor, inductorRule);
		List<Integer> correctMatchedIndexes = actor.infoStore().matchIndexes(inductorRule.getMatchRules());
		Assert.assertTrue("Info should be matched", matchedIndexes.equals(correctMatchedIndexes));
	}

	@Test
	public void shouldNotMatch() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule inductorRule = ActorRuleFixture.createNoMatchInductorRule(infos);
		actor.addRule(inductorRule);
		List<Integer> matchedIndexes = Actuator.find(actor, inductorRule);
		List<Integer> correctMatchedIndexes = actor.infoStore().matchIndexes(inductorRule.getMatchRules());
		Assert.assertFalse("Info should not be matched", matchedIndexes.equals(correctMatchedIndexes));
	}

	@Test
	public void shouldAddInfo() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.addRule(ActorRuleFixture.createInductorAddRule(infos));
		actor.infoStore().addAll(infos);
		Inductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and add info", false);
	}

	@Test
	public void shouldModifyInfo() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.addRule(ActorRuleFixture.createInductorModifyRule(infos));
		actor.infoStore().addAll(infos);
		Inductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and modify info", false);
	}

	@Test
	public void shouldRemoveInfo() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.addRule(ActorRuleFixture.createInductorRemoveRule(infos));
		actor.infoStore().addAll(infos);
		Inductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and remove info", false);
	}
}
