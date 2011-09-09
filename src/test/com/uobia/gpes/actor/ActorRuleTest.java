package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.MatchRule;

public class ActorRuleTest {
	@Test
	public void shouldSetRuleType() {
		ActorRule rule = ActorRule.create();
		rule.setRuleType(ActorRule.RuleType.SensorRule);
		Assert.assertTrue("Should set rule type to sensor", rule.getRuleType() == ActorRule.RuleType.SensorRule);
		rule.setRuleType(ActorRule.RuleType.ActuatorRule);
		Assert.assertTrue("Should set rule type to actuator", rule.getRuleType() == ActorRule.RuleType.ActuatorRule);
		rule.setRuleType(ActorRule.RuleType.InductorRule);
		Assert.assertTrue("Should set rule type to inductor", rule.getRuleType() == ActorRule.RuleType.InductorRule);
		rule.setRuleType(ActorRule.RuleType.DeductorRule);
		Assert.assertTrue("Should set rule type to deductor", rule.getRuleType() == ActorRule.RuleType.DeductorRule);
		rule.setRuleType(ActorRule.RuleType.MutationRule);
		Assert.assertTrue("Should set rule type to deductor", rule.getRuleType() == ActorRule.RuleType.MutationRule);
	}
	
	@Test
	public void shouldSetMatchRule() {
		ActorRule rule = ActorRule.create();
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
		Assert.assertTrue("Should be valid Action rule", rule.isValidActionRule());
	}
	
	@Test
	public void shouldBeInValidActionRule() {
		ActorRule rule = ActorRule.create();
		List<Info> actionRule = ActorRuleTest.createInvalidTestActionRule();
		rule.setActionRule(actionRule);
		Assert.assertFalse("Should be invalid match rule", rule.isValidMatchRule());
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