package com.uobia.gpes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MatchRule implements Serializable {
 	private static final long serialVersionUID = 1L;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((constraints == null) ? 0 : constraints.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((infoCollection == null) ? 0 : infoCollection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchRule other = (MatchRule) obj;
		if (constraints == null) {
			if (other.constraints != null)
				return false;
		} else if (!constraints.equals(other.constraints))
			return false;
		if (id != other.id)
			return false;
		if (infoCollection == null) {
			if (other.infoCollection != null)
				return false;
		} else if (!infoCollection.equals(other.infoCollection))
			return false;
		return true;
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
