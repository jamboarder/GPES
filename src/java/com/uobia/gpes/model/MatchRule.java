package com.uobia.gpes.model;

import java.util.ArrayList;
import java.util.List;

public class MatchRule {
	/**
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
	
	public enum MatchTarget{MatchS, MatchP, MatchO};
	private final int id;
	private List<Info> infoCollection;
	private List<Constraint> constraints;
	
	private MatchRule(int id) {
		this.id = id;
		infoCollection = new ArrayList<Info>();
		infoCollection.add(Info.create(this.id, Info.IS_TYPE, Info.TYPE_CONSTRAINT));
	}
	
	public static MatchRule create(int id) {
		return new MatchRule(id);
	}
	
	public boolean equals(Object object) {
    	if ( this == object ) return true;
    	if ( !(object instanceof MatchRule) ) return false;
    	MatchRule matchRule = (MatchRule)object;
		return this.infoCollection.equals(matchRule.getInfo());
	}

	public int getId() {
		return id;
	}
	
	public List<Info> getInfo() {
		List<Info> allInfo = new ArrayList<Info>();
		allInfo.addAll(infoCollection);
		for (int i = 0; i < constraints.size(); i++) {
			allInfo.addAll(constraints.get(i).getInfo());
		}
		return allInfo;
	}
	
	public List<Constraint> getConstraints() {
		return constraints;
	}
	
	public void addConstraint(MatchTarget target, Constraint constraint) {
		infoCollection.add(Info.create(id, infoForTarget(target), constraint.getId()));
	}

	private int infoForTarget(MatchTarget target) {
		switch (target) {
		case MatchS:
			return Info.MATCH_S;
		case MatchP:
			return Info.MATCH_P;
		case MatchO:
			return Info.MATCH_O;
		}
		return 0;
	}

}
