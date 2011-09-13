package com.uobia.gpes.actor;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoFixture;

public class DeductorTest {
	@Test
	public void shouldFindMatches() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule deductorRule = ActorRuleFixture.createMatchingDeductorRule(infos);
		actor.addRule(deductorRule);
		List<Integer> matchedIndexes = Actuator.find(actor, deductorRule);
		List<Integer> correctMatchedIndexes = actor.infoStore().matchIndexes(deductorRule.getMatchRules());
		Assert.assertTrue("Info should be matched", matchedIndexes.equals(correctMatchedIndexes));
	}

	@Test
	public void shouldNotMatch() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.infoStore().addAll(infos);
		ActorRule deductorRule = ActorRuleFixture.createNoMatchDeductorRule(infos);
		actor.addRule(deductorRule);
		List<Integer> matchedIndexes = Actuator.find(actor, deductorRule);
		List<Integer> correctMatchedIndexes = actor.infoStore().matchIndexes(deductorRule.getMatchRules());
		Assert.assertFalse("Info should not be matched", matchedIndexes.equals(correctMatchedIndexes));
	}

	@Test
	public void shouldAddInfo() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.addRule(ActorRuleFixture.createDeductorAddRule(infos));
		actor.infoStore().addAll(infos);
		Deductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and add info", false);
	}

	@Test
	public void shouldModifyInfo() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.addRule(ActorRuleFixture.createDeductorModifyRule(infos));
		actor.infoStore().addAll(infos);
		Deductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and modify info", false);
	}

	@Test
	public void shouldRemoveInfo() {
		Actor actor = Actor.create();
		List<Info> infos = InfoFixture.createInfo();
		actor.addRule(ActorRuleFixture.createDeductorRemoveRule(infos));
		actor.infoStore().addAll(infos);
		Deductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and remove info", false);
	}
}
