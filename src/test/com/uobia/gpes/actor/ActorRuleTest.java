package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.MatchRule;

public class ActorRuleTest {
	@Test
	public void shouldSetRuleType() {
		final ActorRule.RuleType[] rType = {
			ActorRule.RuleType.ActuatorRule,
			ActorRule.RuleType.DeductorRule,
			ActorRule.RuleType.InductorRule,
			ActorRule.RuleType.SensorRule
		};
		
		for (ActorRule.RuleType r: rType) {
			int id = new Random().nextInt();
			ActorRule rule = ActorRule.create(id, r);
			Assert.assertEquals("Should set rule type", r, rule.getRuleType());
			Assert.assertEquals("Should set id", id, rule.getId());
		}
	}
	
	@Test
	public void shouldSetMatchRule() {
		ActorRule rule = ActorRule.create(new Random().nextInt(), ActorRule.RuleType.SensorRule);
		MatchRule matchRule = ActorRuleTest.createValidTestMatchRule();
		rule.addMatchRule(matchRule);
		List<MatchRule> storedMatchRules = rule.getMatchRules();
		boolean ruleAdded = (storedMatchRules.size() == 1);
		boolean correctRule = (storedMatchRules.get(0).equals(matchRule));
		Assert.assertTrue("Should correctly set match rule", ruleAdded && correctRule);
	}
	
	@Test
	public void shouldSetActionRule() {
		ActorRule rule = ActorRule.create();
		List<Info> actionRule = ActorRuleTest.createValidTestActionRule();
		rule.setActionRule(actionRule);
		List<Info> storedMatchRule = rule.getActionRule();
		boolean ruleAdded = (storedMatchRule.size() == actionRule.size());
		boolean correctRule = (storedMatchRule.get(0).equals(actionRule.get(0)) &&
				               storedMatchRule.get(1).equals(actionRule.get(1)));
		Assert.assertTrue("Should correctly set match rule", ruleAdded && correctRule);
	}
	
	@Test
	public void shouldBeValidMatchRule() {
		ActorRule rule = ActorRule.create();
		MatchRule matchRule = ActorRuleTest.createValidTestMatchRule();
		rule.addMatchRule(matchRule);
		Assert.assertTrue("Should be valid match rule", rule.isValidMatchRule());
	}
	
	@Test
	public void shouldBeInValidMatchRule() {
		ActorRule rule = ActorRule.create();
		MatchRule matchRule = ActorRuleTest.createInvalidTestMatchRule();
		rule.addMatchRule(matchRule);
		Assert.assertFalse("Should be invalid match rule", rule.isValidMatchRule());
	}
	
	@Test
	public void shouldBeValidActionRule() {
		ActorRule rule = ActorRule.create();
		List<Info> actionRule = ActorRuleTest.createValidTestActionRule();
		rule.setActionRule(actionRule);
		Assert.assertTrue("Should be valid action rule", rule.isValidActionRule());
	}
	
	@Test
	public void shouldBeInValidActionRule() {
		ActorRule rule = ActorRule.create();
		List<Info> actionRule = ActorRuleTest.createInvalidTestActionRule();
		rule.setActionRule(actionRule);
		Assert.assertFalse("Should be invalid action rule", rule.isValidMatchRule());
	}
	
	public static MatchRule createValidTestMatchRule() {
		//TODO: Define requirements for rule validation
		MatchRule matchRule = MatchRule.create(0);
		return matchRule;
	}
	
	public static MatchRule createInvalidTestMatchRule() {
		//TODO: Define requirements for rule validation
		MatchRule matchRule = MatchRule.create(0);
		return matchRule;
	}

	public static List<Info> createValidTestActionRule() {
		//TODO: Define requirements for rule validation
		List<Info> matchRule = new ArrayList<Info>();
		matchRule.add(Info.create(1, 2, 3));
		matchRule.add(Info.create(3, 4, 5));
		return matchRule;
	}
	
	public static List<Info> createInvalidTestActionRule() {
		//TODO: Define requirements for rule validation
		List<Info> matchRule = new ArrayList<Info>();
		matchRule.add(Info.create(1, 2, 3));
		matchRule.add(Info.create(3, 4, 5));
		return matchRule;
	}
	
	public static ActorRule createValidRule(ActorRule.RuleType ruleType) {
		ActorRule rule = ActorRule.create();
		rule.setRuleType(ruleType);
		rule.addMatchRule(ActorRuleTest.createValidTestMatchRule());
		rule.setActionRule(ActorRuleTest.createValidTestActionRule());
		return rule;
	}
}
