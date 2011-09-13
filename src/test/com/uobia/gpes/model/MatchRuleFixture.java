package com.uobia.gpes.model;

import java.util.ArrayList;
import java.util.List;

public class MatchRuleFixture {

	public static MatchRule createNoMatch(List<Info> infos) {
		int maxS = Integer.MIN_VALUE;
		for (Info info: infos) {
			maxS = (maxS < info.getSubject()) ? info.getSubject() : maxS;
		}
		MatchRule m = MatchRule.create(0);
		Constraint constraint = Constraint.create(m.getId()+1);
		constraint.addComparator(InfoComparator.COMPARATOR_GREATER_THAN, maxS);
		m.addConstraint(InfoMatchTarget.MATCH_S, constraint);
		return m;
	}

	public static MatchRule createValidMatchRule() {
		//TODO: Define requirements for rule validation
		MatchRule matchRule = MatchRule.create(0);
		return matchRule;
	}
	
	public static MatchRule createInvalidMatchRule() {
		//TODO: Define requirements for rule validation
		MatchRule matchRule = MatchRule.create(0);
		return matchRule;
	}

	public static MatchRule createMatchSubjects(List<Integer> values) {
		MatchRule m = MatchRule.create(0);
		Constraint constraint = Constraint.create(m.getId()+1);
		List<Integer> uniqueValues = new ArrayList<Integer>();
 		for (int value: values) {
 			if (!uniqueValues.contains(value)) {
 				constraint.addComparator(InfoComparator.COMPARATOR_GREATER_THAN, value);
 				uniqueValues.add(value);
 			}
 		}
		m.addConstraint(InfoMatchTarget.MATCH_S, constraint);
		return m;
	}
}
