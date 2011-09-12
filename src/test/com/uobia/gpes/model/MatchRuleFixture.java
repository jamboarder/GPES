package com.uobia.gpes.model;

import java.util.List;

public class MatchRuleFixture {

	public static MatchRule createNoMatch(List<Info> infos) {
		int maxS = Integer.MIN_VALUE;
		for (Info info: infos) {
			maxS = (maxS < info.getS()) ? info.getS() : maxS;
		}
		MatchRule m = MatchRule.create(0);
		Constraint constraint = Constraint.create(m.getId()+1);
		constraint.addComparator(InfoComparator.COMPARATOR_GREATER_THAN, maxS);
		m.addConstraint(MatchRule.MatchTarget.MatchS, constraint);
		return m;
	}

}
