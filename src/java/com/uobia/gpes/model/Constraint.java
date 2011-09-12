package com.uobia.gpes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Constraint implements Serializable {
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

	public void addComparator(final InfoComparator comparator, final int value) {
		infoCollection.add(Info.create(id, InfoPredicate.COMPARE_USING.getId(), comparator.getId()));
		infoCollection.add(Info.create(id, InfoPredicate.COMPARE_WITH_FIXED.getId(), value));
	}

	public void addComparator(final InfoComparator comparator, final CompareTarget target, final int matchId) {
		infoCollection.add(Info.create(id,InfoPredicate.COMPARE_USING.getId(), comparator.getId()));
		infoCollection.add(Info.create(id, infoForTarget(target), matchId));
	}
	
	public List<Info> getInfo() {
		return Collections.unmodifiableList(infoCollection);
	}

	private int infoForTarget(CompareTarget target) {
		//TODO: Change this to map
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Constraint other = (Constraint) obj;
		if (id != other.id)
			return false;
		if (infoCollection == null) {
			if (other.infoCollection != null)
				return false;
		} else if (!infoCollection.equals(other.infoCollection))
			return false;
		return true;
	}
	
	
}
