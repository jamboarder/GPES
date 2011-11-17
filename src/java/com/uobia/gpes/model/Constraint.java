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
	 * m1, MATCH_O, c2
	 * c2, COMPARE_USING, COMPARE_EQUALS
	 * c2, COMPARE_WITH_S_FROM, m2
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
	
	public boolean isValid() {
		boolean comparatorExists = false;
		boolean compareWithExists = false;
		for (Info info: infoCollection) {
			if (info.getPredicate() == InfoPredicate.COMPARE_USING.getValue() &&
				InfoComparator.has(info.getObject()) &&
				info.getObject() != InfoComparator.NULL.getValue()) {
				comparatorExists = true;
			}
			if (info.getPredicate() == InfoPredicate.COMPARE_WITH_FIXED.getValue() ||
				InfoCompareTarget.has(info.getPredicate()) &&
				info.getPredicate() != InfoCompareTarget.NULL.getValue()) {
				compareWithExists = true;
			}
		}
		return (comparatorExists && compareWithExists);
	}
	
	public InfoComparator getComparator() {
		for (Info info: infoCollection) {
			if (info.getPredicate() == InfoPredicate.COMPARE_USING.getValue()) {
				return InfoComparator.get(info.getObject());
			}
		}
		return InfoComparator.NULL;
	}

	public boolean isFixed() {
		for (Info info: infoCollection) {
			if (info.getPredicate() == InfoPredicate.COMPARE_WITH_FIXED.getValue()) {
				return true;
			}
		}
		return false;
	}

	public int getFixedValue() {
		for (Info info: infoCollection) {
			if (info.getPredicate() == InfoPredicate.COMPARE_WITH_FIXED.getValue()) {
				return info.getObject();
			}
		}
		return 0;
	}

	public InfoCompareTarget getCompareTarget() {
		for (Info info: infoCollection) {
			if (info.getPredicate() == InfoPredicate.COMPARE_WITH_S_FROM.getValue() ||
				info.getPredicate() == InfoPredicate.COMPARE_WITH_P_FROM.getValue() ||
				info.getPredicate() == InfoPredicate.COMPARE_WITH_O_FROM.getValue()) {
				return InfoCompareTarget.get(info.getPredicate());
			}
		}
		return InfoCompareTarget.NULL;
	}

	public int getTargetMatchRule() {
		for (Info info: infoCollection) {
			if (info.getPredicate() == InfoPredicate.COMPARE_WITH_S_FROM.getValue() ||
				info.getPredicate() == InfoPredicate.COMPARE_WITH_P_FROM.getValue() ||
				info.getPredicate() == InfoPredicate.COMPARE_WITH_O_FROM.getValue()) {
				return info.getObject();
			}
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
