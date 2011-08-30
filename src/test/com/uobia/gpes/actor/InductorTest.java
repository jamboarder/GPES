package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.actor.Rule.RuleType;
import com.uobia.gpes.model.Info;

public class InductorTest {
	@Test
	public void shouldFindInfo() {
		Actor actor = Actor.create();
		actor.addRule(inductorTestRule());
		actor.infoStore().addAll(matchableTestInfo());
		List<Integer> matchedIndexes = Inductor.find(actor, inductorTestRule());
		List<Info> matchedInfo = actor.infoStore().infoForIndexes(matchedIndexes);
		Assert.assertTrue("Info should be found", matchedInfo.equals(matchableTestInfo()));
	}

	@Test
	public void shouldNotFindInfo() {
		Actor actor = Actor.create();
		actor.addRule(inductorTestRule());
		actor.infoStore().addAll(unMatchableTestInfo());
		List<Integer> matchedIndexes = Inductor.find(actor, inductorTestRule());
		List<Info> matchedInfo = actor.infoStore().infoForIndexes(matchedIndexes);
		Assert.assertFalse("Info should not be found", matchedInfo.equals(matchableTestInfo()));
	}

	@Test
	public void shouldAddInfo() {
		Actor actor = Actor.create();
		actor.addRule(inductorAddTestRule());
		actor.infoStore().addAll(matchableTestInfo());
		Inductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and add info", false);
	}

	@Test
	public void shouldModifyInfo() {
		Actor actor = Actor.create();
		actor.addRule(inductorModifyTestRule());
		actor.infoStore().addAll(matchableTestInfo());
		Inductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and modify info", false);
	}

	@Test
	public void shouldRemoveInfo() {
		Actor actor = Actor.create();
		actor.addRule(inductorRemoveTestRule());
		actor.infoStore().addAll(matchableTestInfo());
		Inductor.findAndAct(actor);
		//TODO: Define requirements for added info and how to compare with expectation
		Assert.assertTrue("Should find and remove info", false);
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

	private static Rule inductorTestRule() {
		Rule rule = Rule.create();
		rule.setRuleType(RuleType.InductorRule);
		// TODO create test inductor rule
		return rule;
	}
	
	private static Rule inductorAddTestRule() {
		Rule rule = Rule.create();
		rule.setRuleType(RuleType.InductorRule);
		// TODO create test inductor add rule
		return rule;
	}

	private static Rule inductorModifyTestRule() {
		Rule rule = Rule.create();
		rule.setRuleType(RuleType.InductorRule);
		// TODO create test inductor add rule
		return rule;
	}
	private static Rule inductorRemoveTestRule() {
		Rule rule = Rule.create();
		rule.setRuleType(RuleType.InductorRule);
		// TODO create test inductor add rule
		return rule;
	}
}
