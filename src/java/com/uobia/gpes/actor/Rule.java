package com.uobia.gpes.actor;
import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.model.Info;


public class Rule {
	public enum RuleType{NullRule, SensorRule, ActuatorRule, InductorRule, DeductorRule, MutationRule};
	private List<Info> matchRule;
	private List<Info> actionRule;
	private RuleType ruleType;
	
	private Rule() {
		setMatchRule(new ArrayList<Info>());
		setActionRule(new ArrayList<Info>());
		setRuleType(RuleType.NullRule);
	}
	
	public final static Rule create() {
		Rule rule = new Rule();
		return rule;
	}
	
	public List<Info> getMatchRule() {
		return matchRule;
	}

	public void setMatchRule(List<Info> matchRule) {
		this.matchRule = matchRule;
	}

	public List<Info> getActionRule() {
		return actionRule;
	}

	public void setActionRule(List<Info> actionRule) {
		this.actionRule = actionRule;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public boolean isValidMatchRule() {
		// TODO Finish implementation
		return false;
	}

	public boolean isValidActionRule() {
		// TODO Finish implementation
		return false;
	}
}
