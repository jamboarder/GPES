package com.uobia.gpes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uobia.gpes.matcher.Matcher;


public class InfoStore implements Serializable {
 	private static final long serialVersionUID = 1L;
	private List<Info> infoCollection;
	private List<Integer> subjectCache;
	private List<Integer> predicateCache;
	private List<Integer> objectCache;
	public int maxSize;
	
	private InfoStore() {
		infoCollection = new ArrayList<Info>();
		subjectCache = new ArrayList<Integer>();
		predicateCache = new ArrayList<Integer>();
		objectCache = new ArrayList<Integer>();
		maxSize = 100;
	}
	
	public static InfoStore create() {
		InfoStore infoStore = new InfoStore();
		return infoStore;
	}

	public int size() {
		return infoCollection.size();
	}
	
	public boolean isEmpty() {
		return infoCollection.isEmpty();
	}

	public void setMaxSize(int maxSize) {
		//NOTE: Setting maxSize < infoCollection.size() is purposefully nonfatal.
		//      It will simply be not possible to add Info to the store until
		//      infoCollection.size() < maxSize.
		this.maxSize = maxSize;
	}

	public Info get(int index) {
		return infoCollection.get(index);
	}
	
	public void clear() {
		infoCollection.clear();
		subjectCache.clear();
		predicateCache.clear();
		objectCache.clear();
	}

	public boolean add(Info info) {
		if (infoCollection.size() >= maxSize) {
			return false;
		}
		infoCollection.add(info);
		subjectCache.add(info.getSubject());
		predicateCache.add(info.getPredicate());
		objectCache.add(info.getObject());
		return true;
	}

	public boolean addAll(List<Info> infos) {
		if (infoCollection.size() >= maxSize) {
			return false;
		}
		for (int i = 0; i < infos.size(); i++) {
			add(infos.get(i));
		}
		return true;
	}
	
	public boolean remove(int index) {
		if (index < 0 || index >= infoCollection.size()) {
			return false;
		}
		infoCollection.remove(index);
		subjectCache.remove(index);
		predicateCache.remove(index);
		objectCache.remove(index);
		return true;
	}
	
	public void removeAll(List<Integer> indexes) {
		for (int i = 0; i < indexes.size(); i++) {
			remove(indexes.get(i));
		}
	}

	public List<Info> getInfo() {
		return Collections.unmodifiableList(infoCollection);
	}
	
	public List<Info> matchInfo(MatchRule matchRule) {
		List<Info> matches = Matcher.match(matchRule, this);
		return matches;
	}
	
	public List<Integer> matchIndexes(List<MatchRule> matchRule) {
		// TODO: Finish implementation
		return new ArrayList<Integer>();
	}
	
	public List<Integer> getSubjectCache() {
		return subjectCache;
	}
	
	public List<Integer> getPredicateCache() {
		return predicateCache;
	}
	
	public List<Integer> getObjectCache() {
		return objectCache;
	}
	
	public MatchRule getMatchRule(int matchRuleId) {
		MatchRule matchRule = MatchRule.create(matchRuleId);
		List<Integer> mInfoIndexes = Matcher.indexesForElement(matchRuleId, subjectCache);
		List<Info> mInfos = Matcher.infoForIndexes(mInfoIndexes, this);
		for (Info mInfo: mInfos) {
			if (InfoMatchTarget.has(mInfo.getPredicate())) {
				int constraintId = mInfo.getObject();
				List<Integer> cInfoIndexes = Matcher.indexesForElement(constraintId, subjectCache);
				List<Info> cInfos = Matcher.infoForIndexes(cInfoIndexes, this);
				InfoComparator comparator = InfoComparator.NULL;
				Info targetInfo = Info.create(constraintId, InfoPredicate.COMPARE_WITH_FIXED.getValue(), 0);
				for (Info cInfo: cInfos) {
					if (InfoComparator.has(cInfo.getObject())) {
						comparator = InfoComparator.get(cInfo.getObject());
					}
					if (cInfo.getPredicate() == InfoPredicate.COMPARE_WITH_FIXED.getValue() ||
						InfoCompareTarget.has(cInfo.getPredicate())) {
						targetInfo = cInfo;
					}
				}
				if (comparator == InfoComparator.NULL) {
					continue;
				}
				if (targetInfo.getPredicate() == InfoPredicate.COMPARE_WITH_FIXED.getValue()) {
					Constraint constraint = Constraint.create(constraintId);
					constraint.addComparator(comparator, targetInfo.getObject());
					matchRule.addConstraint(InfoMatchTarget.get(mInfo.getPredicate()), constraint);
				} else {
					Constraint constraint = Constraint.create(constraintId);
					constraint.addComparator(comparator, InfoCompareTarget.get(targetInfo.getPredicate()), targetInfo.getObject());
					matchRule.addConstraint(InfoMatchTarget.get(mInfo.getPredicate()), constraint);
				}
			}
		}
		return matchRule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((infoCollection == null) ? 0 : infoCollection.hashCode());
		result = prime * result + ((objectCache == null) ? 0 : objectCache.hashCode());
		result = prime * result + ((predicateCache == null) ? 0 : predicateCache.hashCode());
		result = prime * result + ((subjectCache == null) ? 0 : subjectCache.hashCode());
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
		InfoStore other = (InfoStore) obj;
		if (infoCollection == null) {
			if (other.infoCollection != null)
				return false;
		} else if (!infoCollection.equals(other.infoCollection))
			return false;
		if (objectCache == null) {
			if (other.objectCache != null)
				return false;
		} else if (!objectCache.equals(other.objectCache))
			return false;
		if (predicateCache == null) {
			if (other.predicateCache != null)
				return false;
		} else if (!predicateCache.equals(other.predicateCache))
			return false;
		if (subjectCache == null) {
			if (other.subjectCache != null)
				return false;
		} else if (!subjectCache.equals(other.subjectCache))
			return false;
		return true;
	}

}
