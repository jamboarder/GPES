package com.uobia.gpes.model;

import java.util.ArrayList;
import java.util.List;

public class Constraint {
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
	public enum Comparator{LessThan, Equals, GreaterThan};
	public enum CompareTarget{SFrom, PFrom, OFrom};
	
	private final int id;
	private List<Info> infoCollection;

	private Constraint(int id) {
		this.id = id;
		infoCollection = new ArrayList<Info>();
		infoCollection.add(Info.create(this.id, Info.IS_TYPE, Info.TYPE_CONSTRAINT));
	}
	
	public static Constraint create(int id) {
		return new Constraint(id);
	}
	
	public int getId() {
		return id;
	}

	public void addConstraint(Comparator comparator, int value) {
		infoCollection.add(Info.create(id, Info.COMPARE_USING, infoForComparator(comparator)));
		infoCollection.add(Info.create(id, Info.COMPARE_WITH_FIXED, value));
	}

	public void addConstraint(Comparator comparator, CompareTarget target, int matchId) {
		infoCollection.add(Info.create(id, Info.COMPARE_USING, infoForComparator(comparator)));
		infoCollection.add(Info.create(id, infoForTarget(target), matchId));
	}
	
	public List<Info> getInfo() {
		return infoCollection;
	}

	private int infoForComparator(Comparator comparator) {
		switch (comparator) {
		case LessThan:
			return Info.COMPARATOR_LESS_THAN;
		case Equals:
			return Info.COMPARATOR_EQUALS;
		case GreaterThan:
			return Info.COMPARATOR_GREATER_THAN;
		}
		return 0;
	}

	private int infoForTarget(CompareTarget target) {
		switch (target) {
		case SFrom:
			return Info.COMPARE_WITH_S_FROM;
		case PFrom:
			return Info.COMPARE_WITH_P_FROM;
		case OFrom:
			return Info.COMPARE_WITH_O_FROM;
		}
		return 0;
	}
}
