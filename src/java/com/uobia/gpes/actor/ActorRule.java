package com.uobia.gpes.actor;
import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.MatchRule;


public class ActorRule {
	/*
	 * Example sensor rule
	 * s1, IS_TYPE, TYPE_SENSOR_RULE
	 * s1, HAS_PART, m1
	 * m1, IS_TYPE, TYPE_MATCH_RULE
	 * m1, MATCH_S, c1
	 * c1, COMPARE_USING, COMPARE_GREATER_THAN
	 * c1, COMPARE_WITH_FIXED, 4
	 * s1, HAS_PART, a1
	 * a1, IS_TYPE, TYPE_ACTION_RULE
	 * ...
	 */
	
	
	public enum RuleType{SensorRule, ActuatorRule, InductorRule, DeductorRule, MutationRule};
	private List<Info> actionRule;
	private List<MatchRule> matchRules;
	private List<Info> ruleInfo;
	private final RuleType ruleType;
	private final int id;
	
	private ActorRule(int id, RuleType ruleType) {
		setActionRule(new ArrayList<Info>());
		matchRules = new ArrayList<MatchRule>();
		ruleInfo = new ArrayList<Info>();
		
		this.id = id;
		this.ruleType = ruleType;
		ruleInfo.add(Info.create(id, Info.IS_TYPE, infoForRuleType(ruleType)));
	}
	
	/*public final static ActorRule create() {
		ActorRule rule = ActorRule.create(0, RuleType.SensorRule);
		return rule;
	}*/
	
	public final static ActorRule create(int id, RuleType ruleType) {
		ActorRule rule = new ActorRule(id, ruleType);
		return rule;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Info> getInfo() {
		List<Info> allInfo = new ArrayList<Info>();
		allInfo.addAll(ruleInfo);
		for (int i = 0; i < matchRules.size(); i++) {
			allInfo.addAll(matchRules.get(i).getInfo());
		}
		//TODO: return action rules info
		return allInfo;
	}
	
	public void addMatchRule(MatchRule matchRule) {
		ruleInfo.add(Info.create(id, Info.HAS_PART, matchRule.getId()));
		matchRules.add(matchRule);
	}

	public List<MatchRule> getMatchRules() {
		return matchRules;
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

	public boolean isValidMatchRule() {
		// TODO Finish implementation
		return false;
	}

	public boolean isValidActionRule() {
		// TODO Finish implementation
		return false;
	}

	private int infoForRuleType(RuleType ruleType) {
		switch (ruleType) {
		case SensorRule:
			return Info.TYPE_SENSOR_RULE;
		case ActuatorRule:
			return Info.TYPE_ACTUATOR_RULE;
		case InductorRule:
			return Info.TYPE_INDUCTOR_RULE;
		case DeductorRule:
			return Info.TYPE_DEDUCTOR_RULE;
		}
		return 0;
	}


}
