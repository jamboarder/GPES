package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.MatchRuleFixture;

public class ActorRuleFixture {
	public static ActorRule createValidRule(ActorRuleType ruleType) {
		ActorRule rule = ActorRule.create(0, ruleType);
		rule.addMatchRule(MatchRuleFixture.createValidMatchRule());
		rule.setActionRule(ActorRuleTest.createValidTestActionRule());
		return rule;
	}
	
	public static ActorRule createMatchingActuatorRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_ACTUATOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createNoMatchActuatorRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_ACTUATOR_RULE);
		rule.addMatchRule(MatchRuleFixture.createNoMatch(infos));
		return rule;
	}

	public static ActorRule createMatchingDeductorRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_DEDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createNoMatchDeductorRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_DEDUCTOR_RULE);
		rule.addMatchRule(MatchRuleFixture.createNoMatch(infos));
		return rule;
	}

	public static ActorRule createDeductorAddRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_DEDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createDeductorModifyRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_DEDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createDeductorRemoveRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_DEDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createMatchingInductorRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_INDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createNoMatchInductorRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_INDUCTOR_RULE);
		rule.addMatchRule(MatchRuleFixture.createNoMatch(infos));
		return rule;
	}

	public static ActorRule createInductorAddRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_INDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createInductorModifyRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_INDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule createInductorRemoveRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_INDUCTOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule detectsAllSensorTestRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_SENSOR_RULE);
		List<Integer> subjectMatches = new ArrayList<Integer>();
		for (Info info: infos) {
			subjectMatches.add(info.getSubject());
		}
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule detectsSubjectsSensorRule(
			List<Integer> subjectMatches) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_SENSOR_RULE);
		rule.addMatchRule(MatchRuleFixture.createMatchSubjects(subjectMatches));
		// TODO create test action rule
		return rule;
	}

	public static ActorRule detectsNothingSensorTestRule(List<Info> infos) {
		ActorRule rule = ActorRule.create(0, ActorRuleType.TYPE_SENSOR_RULE);
		rule.addMatchRule(MatchRuleFixture.createNoMatch(infos));
		return rule;
	}

}
