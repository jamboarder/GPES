package com.uobia.gpes.actor;
import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoPredicate;
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
	
	
	private List<Info> actionRule;
	private List<MatchRule> matchRules;
	private List<Info> ruleInfo;
	private final ActorRuleType ruleType;
	private final int id;
	
	private ActorRule(int id, ActorRuleType ruleType) {
		setActionRule(new ArrayList<Info>());
		matchRules = new ArrayList<MatchRule>();
		ruleInfo = new ArrayList<Info>();
		
		this.id = id;
		this.ruleType = ruleType;
		ruleInfo.add(Info.create(id, InfoPredicate.IS_TYPE.getValue(), ruleType.getValue()));
	}
	
	/*public final static ActorRule create() {
		ActorRule rule = ActorRule.create(0, RuleType.SensorRule);
		return rule;
	}*/
	
	public final static ActorRule create(int id, ActorRuleType ruleType) {
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
		ruleInfo.add(Info.create(id, InfoPredicate.HAS_PART.getValue(), matchRule.getId()));
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

	public ActorRuleType getRuleType() {
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

}
