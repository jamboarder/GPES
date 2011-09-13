package com.uobia.gpes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private final int id;
	private List<Info> infoCollection;

	private Constraint(int id) {
		this.id = id;
		infoCollection = new ArrayList<Info>();
		infoCollection.add(Info.create(this.id, 
				                       InfoPredicate.IS_TYPE.getValue(), 
				                       InfoObject.TYPE_CONSTRAINT.getValue()));
	}
	
	public static Constraint create(int id) {
		return new Constraint(id);
	}
	
	public int getId() {
		return id;
	}

	public void addComparator(final InfoComparator comparator, final int value) {
		infoCollection.add(Info.create(id, InfoPredicate.COMPARE_USING.getValue(), comparator.getValue()));
		infoCollection.add(Info.create(id, InfoPredicate.COMPARE_WITH_FIXED.getValue(), value));
	}

	public void addComparator(final InfoComparator comparator, final InfoCompareTarget target, final int matchId) {
		infoCollection.add(Info.create(id, InfoPredicate.COMPARE_USING.getValue(), comparator.getValue()));
		infoCollection.add(Info.create(id, target.getValue(), matchId));
	}
	
	public List<Info> getInfo() {
		return Collections.unmodifiableList(infoCollection);
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
