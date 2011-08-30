package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;

public class RuleTest {
	@Test
	public void shouldSetRuleType() {
		Rule rule = Rule.create();
		rule.setRuleType(Rule.RuleType.SensorRule);
		Assert.assertTrue("Should set rule type to sensor", rule.getRuleType() == Rule.RuleType.SensorRule);
		rule.setRuleType(Rule.RuleType.ActuatorRule);
		Assert.assertTrue("Should set rule type to actuator", rule.getRuleType() == Rule.RuleType.ActuatorRule);
		rule.setRuleType(Rule.RuleType.InductorRule);
		Assert.assertTrue("Should set rule type to inductor", rule.getRuleType() == Rule.RuleType.InductorRule);
		rule.setRuleType(Rule.RuleType.DeductorRule);
		Assert.assertTrue("Should set rule type to deductor", rule.getRuleType() == Rule.RuleType.DeductorRule);
		rule.setRuleType(Rule.RuleType.MutationRule);
		Assert.assertTrue("Should set rule type to deductor", rule.getRuleType() == Rule.RuleType.MutationRule);
		rule.setRuleType(Rule.RuleType.NullRule);
		Assert.assertTrue("Should set rule type to Null", rule.getRuleType() == Rule.RuleType.NullRule);
	}
	
	@Test
	public void shouldSetMatchRule() {
		Rule rule = Rule.create();
		List<Info> matchRule = RuleTest.createValidTestMatchRule();
		rule.setMatchRule(matchRule);
		List<Info> storedMatchRule = rule.getMatchRule();
		boolean ruleAdded = (storedMatchRule.size() == matchRule.size());
		boolean correctRule = (storedMatchRule.get(0).equals(matchRule.get(0)) &&
				               storedMatchRule.get(1).equals(matchRule.get(1)));
		Assert.assertTrue("Should correctly set match rule", ruleAdded && correctRule);
	}
	
	@Test
	public void shouldSetActionRule() {
		Rule rule = Rule.create();
		List<Info> actionRule = RuleTest.createValidTestActionRule();
		rule.setMatchRule(actionRule);
		List<Info> storedMatchRule = rule.getMatchRule();
		boolean ruleAdded = (storedMatchRule.size() == actionRule.size());
		boolean correctRule = (storedMatchRule.get(0).equals(actionRule.get(0)) &&
				               storedMatchRule.get(1).equals(actionRule.get(1)));
		Assert.assertTrue("Should correctly set match rule", ruleAdded && correctRule);
	}
	
	@Test
	public void shouldBeValidMatchRule() {
		Rule rule = Rule.create();
		List<Info> matchRule = RuleTest.createValidTestMatchRule();
		rule.setMatchRule(matchRule);
		Assert.assertTrue("Should be valid match rule", rule.isValidMatchRule());
	}
	
	@Test
	public void shouldBeInValidMatchRule() {
		Rule rule = Rule.create();
		List<Info> matchRule = RuleTest.createInvalidTestMatchRule();
		rule.setMatchRule(matchRule);
		Assert.assertFalse("Should be invalid match rule", rule.isValidMatchRule());
	}
	
	@Test
	public void shouldBeValidActionRule() {
		Rule rule = Rule.create();
		List<Info> actionRule = RuleTest.createValidTestActionRule();
		rule.setMatchRule(actionRule);
		Assert.assertTrue("Should be valid Action rule", rule.isValidActionRule());
	}
	
	@Test
	public void shouldBeInValidActionRule() {
		Rule rule = Rule.create();
		List<Info> actionRule = RuleTest.createInvalidTestActionRule();
		rule.setMatchRule(actionRule);
		Assert.assertFalse("Should be invalid match rule", rule.isValidMatchRule());
	}
	
	public static List<Info> createValidTestMatchRule() {
		//TODO: Define requirements for rule validation
		List<Info> matchRule = new ArrayList<Info>();
		matchRule.add(Info.create(1, 2, 3));
		matchRule.add(Info.create(3, 4, 5));
		return matchRule;
	}
	
	public static List<Info> createInvalidTestMatchRule() {
		//TODO: Define requirements for rule validation
		List<Info> matchRule = new ArrayList<Info>();
		matchRule.add(Info.create(1, 2, 3));
		matchRule.add(Info.create(3, 4, 5));
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
	
	public static Rule createValidRule(Rule.RuleType ruleType) {
		Rule rule = Rule.create();
		rule.setRuleType(ruleType);
		rule.setMatchRule(RuleTest.createValidTestMatchRule());
		rule.setActionRule(RuleTest.createValidTestActionRule());
		return rule;
	}
}
