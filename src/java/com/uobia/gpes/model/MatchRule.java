package com.uobia.gpes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.uobia.gpes.matcher.Matcher;

public class MatchRule implements Serializable {
 	private static final long serialVersionUID = 1L;
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
	
	private final int id;
	private List<Info> infoCollection;
	private List<Constraint> constraints;
	
	private MatchRule(int id) {
		this.id = id;
		infoCollection = new ArrayList<Info>();
		infoCollection.add(Info.create(this.id, 
				                       InfoPredicate.IS_TYPE.getValue(), 
				                       InfoObject.TYPE_CONSTRAINT.getValue()));
		constraints = new ArrayList<Constraint>();
	}
	
	public static MatchRule create(int id) {
		return new MatchRule(id);
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
	
	public void addConstraint(final InfoMatchTarget target, Constraint constraint) {
		infoCollection.add(Info.create(id, target.getValue(), constraint.getId()));
		constraints.add(constraint);
	}

	public List<Constraint> getSubjectConstraints() {
		InfoStore infoStore = InfoStore.create();
		infoStore.addAll(infoCollection);
		Info searchInfo = Info.create(getId(), InfoMatchTarget.MATCH_S.getValue(), 0);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, 
				                                     Matcher.MatchElement.MatchPredicate);
		List<Integer> subjectInfo = Matcher.indexesForInfo(searchInfo, matchOn, infoStore);
		List<Constraint> subjectConstraints = new ArrayList<Constraint>();
		for (int index: subjectInfo) {
			int subjectConstraintId = infoStore.get(index).getObject();
			for (Constraint constraint: constraints) {
				if (constraint.getId() == subjectConstraintId) {
					subjectConstraints.add(constraint);
				}
			}
		}
		return subjectConstraints;
	}

	public List<Constraint> getPredicateConstraints() {
		InfoStore infoStore = InfoStore.create();
		infoStore.addAll(infoCollection);
		Info searchInfo = Info.create(getId(), InfoMatchTarget.MATCH_P.getValue(), 0);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, 
				                                     Matcher.MatchElement.MatchPredicate);
		List<Integer> predicateInfo = Matcher.indexesForInfo(searchInfo, matchOn, infoStore);
		List<Constraint> predicateConstraints = new ArrayList<Constraint>();
		for (int index: predicateInfo) {
			int predicateConstraintId = infoStore.get(index).getObject();
			for (Constraint constraint: constraints) {
				if (constraint.getId() == predicateConstraintId) {
					predicateConstraints.add(constraint);
				}
			}
		}
		return predicateConstraints;
	}
	
	public List<Constraint> getObjectConstraints() {
		InfoStore infoStore = InfoStore.create();
		infoStore.addAll(infoCollection);
		Info searchInfo = Info.create(getId(), InfoMatchTarget.MATCH_O.getValue(), 0);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, 
				                                     Matcher.MatchElement.MatchPredicate);
		List<Integer> objectInfo = Matcher.indexesForInfo(searchInfo, matchOn, infoStore);
		List<Constraint> objectConstraints = new ArrayList<Constraint>();
		for (int index: objectInfo) {
			int objectConstraintId = infoStore.get(index).getObject();
			for (Constraint constraint: constraints) {
				if (constraint.getId() == objectConstraintId) {
					objectConstraints.add(constraint);
				}
			}
		}
		return objectConstraints;
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
}
