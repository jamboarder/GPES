package com.uobia.gpes.matcher;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.uobia.gpes.model.Constraint;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoComparator;
import com.uobia.gpes.model.InfoCompareTarget;
import com.uobia.gpes.model.InfoStore;
import com.uobia.gpes.model.MatchRule;

public class Matcher {
	/*
	 * Example sensor rule
	 * s1, IS_TYPE, TYPE_SENSOR_RULE
	 * s1, HAS_PART, m1
	 * m1, IS_TYPE, TYPE_MATCH_RULE
	 * m1, MATCH_S, c1
	 * c1, COMPARE_USING, COMPARE_GREATER_THAN
	 * c1, COMPARE_WITH_FIXED, 4
	 * ...
	 */

	public enum MatchElement{MatchSubject, MatchPredicate, MatchObject};
	
	public static List<Info> match(final MatchRule matchRule, final InfoStore infoStore) {
		List<Info> matches = new ArrayList<Info>();
		getSubjectMatches(matchRule, infoStore, matches);
		getPredicateMatches(matchRule, infoStore, matches);
		getObjectMatches(matchRule, infoStore, matches);
		return matches;
	}
	
	public static List<Info> infoForIndexes(final List<Integer> indexes, final InfoStore infoStore) {
		List<Info> subList = new ArrayList<Info>();
		if (infoStore.isEmpty()) {
			return subList;
		}
		for(int i = 0; i < indexes.size(); i++) {
			int currentIndex = indexes.get(i);
			if (currentIndex >= 0 && currentIndex < infoStore.size()) {
				subList.add(infoStore.get(currentIndex));
			}
		}
		return subList;
	}
	
	public static List<Integer> indexesForInfo(final Info info, 
			                                   final EnumSet<MatchElement> matchOn, 
			                                   final InfoStore infoStore) {
		if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject))) {
			return indexesForSubject(info, infoStore);
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchPredicate))) {
			return indexesForPredicate(info, infoStore);
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchObject))) {
			return indexesForObject(info, infoStore);
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject, 
				                             MatchElement.MatchPredicate))) {
			List<Integer> sIndexes = indexesForSubject(info, infoStore);
			List<Integer> pIndexes = indexesForPredicate(info, infoStore);
			sIndexes.retainAll(pIndexes);
			return sIndexes;
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject, 
				                             MatchElement.MatchObject))) {
			List<Integer> sIndexes = indexesForSubject(info, infoStore);
			List<Integer> oIndexes = indexesForObject(info, infoStore);
			sIndexes.retainAll(oIndexes);
			return sIndexes;
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchPredicate, 
				                             MatchElement.MatchObject))) {
			List<Integer> pIndexes = indexesForPredicate(info, infoStore);
			List<Integer> oIndexes = indexesForObject(info, infoStore);
			pIndexes.retainAll(oIndexes);
			return pIndexes;
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject, 
				                             MatchElement.MatchPredicate, 
				                             MatchElement.MatchObject))) {
			List<Integer> sIndexes = indexesForSubject(info, infoStore);
			List<Integer> pIndexes = indexesForPredicate(info, infoStore);
			sIndexes.retainAll(pIndexes);
			List<Integer> oIndexes = indexesForObject(info, infoStore);
			sIndexes.retainAll(oIndexes);
			return sIndexes;
		} else {
			return new ArrayList<Integer>();
		}
	}
	
	public static List<Integer> indexesForSubject(final Info info, final InfoStore infoStore) {
		return indexesForElement(info.getSubject(), infoStore.getSubjectCache());
	}
	
	public static List<Integer> indexesForPredicate(final Info info, final InfoStore infoStore) {
		return indexesForElement(info.getPredicate(), infoStore.getPredicateCache());
	}

	public static List<Integer> indexesForObject(final Info info, final InfoStore infoStore) {
		return indexesForElement(info.getObject(), infoStore.getObjectCache());
	}
	
	@SuppressWarnings("unused")
	private static List<Integer> subjectsForIndexes(final List<Integer> indexes, final InfoStore infoStore) {
		return elementsForIndexes(indexes, infoStore.getSubjectCache());
	}

	@SuppressWarnings("unused")
	private static List<Integer> predicatesForIndexes(final List<Integer> indexes, final InfoStore infoStore) {
		return elementsForIndexes(indexes, infoStore.getPredicateCache());
	}

	@SuppressWarnings("unused")
	private static List<Integer> objectsForIndexes(final List<Integer> indexes, final InfoStore infoStore) {
		return elementsForIndexes(indexes, infoStore.getObjectCache());
	}

	public static List<Integer> indexesForElement(int element, List<Integer> elementCache) {
		List<Integer> indexes = new ArrayList<Integer>();
		int index = elementCache.indexOf(element);
	    int lastIndex = elementCache.lastIndexOf(element);
		if (index != -1) {
			indexes.add(index);
		}
		while (index < lastIndex) {
			List<Integer> subList = elementCache.subList(index + 1, lastIndex);
			int subIndex = subList.indexOf(element);
			if (subIndex != -1) {
				index = index + 1 + subIndex;
			} else {
				index = lastIndex;
			}
			if (index != -1) {
				indexes.add(index);
			}
		}
		return indexes;
	}
	
	private static List<Integer> elementsForIndexes(List<Integer> indexes, List<Integer> elementCache) {
		List<Integer> subList = new ArrayList<Integer>();
		for(int i = 0; i < indexes.size(); i++) {
			subList.add(elementCache.get(i));
		}
		return subList;
	}
	
	private static List<Integer> indexesForElementsGreaterThan(int value, List<Integer> elementCache) {
		List<Integer> indexes = new ArrayList<Integer>();
		
		for (int i = 0; i < elementCache.size(); i++) {
			int element = elementCache.get(i);
			if (element > value) {
				indexes.add(i);
			}
		}
		return indexes;
	}

	private static List<Integer> indexesForElementsLessThan(int value, List<Integer> elementCache) {
		List<Integer> indexes = new ArrayList<Integer>();
		
		for (int i = 0; i < elementCache.size(); i++) {
			int element = elementCache.get(i);
			if (element < value) {
				indexes.add(i);
			}
		}
		return indexes;
	}

	private static List<Integer> indexesForElementsNotEqual(int value, List<Integer> elementCache) {
		List<Integer> indexes = new ArrayList<Integer>();
		
		for (int i = 0; i < elementCache.size(); i++) {
			int element = elementCache.get(i);
			if (element != value) {
				indexes.add(i);
			}
		}
		return indexes;
	}
	
	private static void getSubjectMatches(final MatchRule matchRule, final InfoStore infoStore, 
			List<Info> matches) {
		List<Constraint> subjectConstraints = matchRule.getSubjectConstraints();
		for (Constraint constraint: subjectConstraints) {
			if (!constraint.isValid()) {
				continue;
			}
			if (constraint.isFixed()) {
				int value = constraint.getFixedValue();
				InfoComparator comparator = constraint.getComparator();
				List<Info> subjectMatches = Matcher.subjectMatches(comparator, value, infoStore);
				subjectMatches.removeAll(matches);
				matches.addAll(subjectMatches);
			} else {
				int targetMatchRuleId = constraint.getTargetMatchRule();
				if (targetMatchRuleId == matchRule.getId()) {
					//Ignore circular matches
					continue;
				}
				InfoCompareTarget target = constraint.getCompareTarget();
				MatchRule targetMatchRule = infoStore.getMatchRule(targetMatchRuleId);
				List<Info> subRuleMatches = Matcher.match(targetMatchRule, infoStore);
				for (Info subMatch: subRuleMatches) {
					if (target.equals(InfoCompareTarget.NULL)) {
						continue;
					}
					int value;
					if (target.equals(InfoCompareTarget.COMPARE_WITH_S_FROM)) {
						value = subMatch.getSubject();
					} else if (target.equals(InfoCompareTarget.COMPARE_WITH_P_FROM)) {
						value = subMatch.getPredicate();
					} else {
						value = subMatch.getObject();
					}
					InfoComparator comparator = constraint.getComparator();
					List<Info> subjectMatches = Matcher.subjectMatches(comparator, value, infoStore);
					subjectMatches.removeAll(matches);
					matches.addAll(subjectMatches);
				}
			}
		}
	}

	private static void getPredicateMatches(final MatchRule matchRule, final InfoStore infoStore, 
			List<Info> matches) {
		List<Constraint> predicateConstraints = matchRule.getPredicateConstraints();
		for (Constraint constraint: predicateConstraints) {
			if (!constraint.isValid()) {
				continue;
			}
			if (constraint.isFixed()) {
				int value = constraint.getFixedValue();
				InfoComparator comparator = constraint.getComparator();
				List<Info> predicateMatches = Matcher.predicateMatches(comparator, value, infoStore);
				predicateMatches.removeAll(matches);
				matches.addAll(predicateMatches);
			} else {
				int targetMatchRuleId = constraint.getTargetMatchRule();
				if (targetMatchRuleId == matchRule.getId()) {
					//Ignore circular matches
					continue;
				}
				InfoCompareTarget target = constraint.getCompareTarget();
				MatchRule targetMatchRule = infoStore.getMatchRule(targetMatchRuleId);
				List<Info> subRuleMatches = Matcher.match(targetMatchRule, infoStore);
				for (Info subMatch: subRuleMatches) {
					if (target.equals(InfoCompareTarget.NULL)) {
						continue;
					}
					int value;
					if (target.equals(InfoCompareTarget.COMPARE_WITH_S_FROM)) {
						value = subMatch.getSubject();
					} else if (target.equals(InfoCompareTarget.COMPARE_WITH_P_FROM)) {
						value = subMatch.getPredicate();
					} else {
						value = subMatch.getObject();
					}
					InfoComparator comparator = constraint.getComparator();
					List<Info> predicateMatches = Matcher.predicateMatches(comparator, value, infoStore);
					predicateMatches.removeAll(matches);
					matches.addAll(predicateMatches);
				}
			}
		}
	}

	private static void getObjectMatches(final MatchRule matchRule, final InfoStore infoStore, 
			List<Info> matches) {
		List<Constraint> objectConstraints = matchRule.getObjectConstraints();
		for (Constraint constraint: objectConstraints) {
			if (!constraint.isValid()) {
				continue;
			}
			if (constraint.isFixed()) {
				int value = constraint.getFixedValue();
				InfoComparator comparator = constraint.getComparator();
				List<Info> objectMatches = Matcher.objectMatches(comparator, value, infoStore);
				objectMatches.removeAll(matches);
				matches.addAll(objectMatches);
			} else {
				int targetMatchRuleId = constraint.getTargetMatchRule();
				if (targetMatchRuleId == matchRule.getId()) {
					//Ignore circular matches
					continue;
				}
				InfoCompareTarget target = constraint.getCompareTarget();
				MatchRule targetMatchRule = infoStore.getMatchRule(targetMatchRuleId);
				List<Info> subRuleMatches = Matcher.match(targetMatchRule, infoStore);
				for (Info subMatch: subRuleMatches) {
					if (target.equals(InfoCompareTarget.NULL)) {
						continue;
					}
					int value;
					if (target.equals(InfoCompareTarget.COMPARE_WITH_S_FROM)) {
						value = subMatch.getSubject();
					} else if (target.equals(InfoCompareTarget.COMPARE_WITH_P_FROM)) {
						value = subMatch.getPredicate();
					} else {
						value = subMatch.getObject();
					}
					InfoComparator comparator = constraint.getComparator();
					List<Info> objectMatches = Matcher.objectMatches(comparator, value, infoStore);
					objectMatches.removeAll(matches);
					matches.addAll(objectMatches);
				}
			}
		}
	}
	
	private static List<Info> subjectMatches(InfoComparator comparator, int value, InfoStore infoStore) {
		List<Integer> subjectIndexes = new ArrayList<Integer>();
		if (comparator.equals(InfoComparator.COMPARATOR_EQUALS)) {
			subjectIndexes = Matcher.indexesForElement(value, infoStore.getSubjectCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_GREATER_THAN)) {
			subjectIndexes = Matcher.indexesForElementsGreaterThan(value, infoStore.getSubjectCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_LESS_THAN)) {
			subjectIndexes = Matcher.indexesForElementsLessThan(value, infoStore.getSubjectCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_NOT_EQUALS)) {
			subjectIndexes = Matcher.indexesForElementsNotEqual(value, infoStore.getSubjectCache());
		}
		return infoForIndexes(subjectIndexes, infoStore);
	}

	private static List<Info> predicateMatches(InfoComparator comparator, int value, InfoStore infoStore) {
		List<Integer> predicateIndexes = new ArrayList<Integer>();
		if (comparator.equals(InfoComparator.COMPARATOR_EQUALS)) {
			predicateIndexes = Matcher.indexesForElement(value, infoStore.getPredicateCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_GREATER_THAN)) {
			predicateIndexes = Matcher.indexesForElementsGreaterThan(value, infoStore.getPredicateCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_LESS_THAN)) {
			predicateIndexes = Matcher.indexesForElementsLessThan(value, infoStore.getPredicateCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_NOT_EQUALS)) {
			predicateIndexes = Matcher.indexesForElementsNotEqual(value, infoStore.getPredicateCache());
		}
		return infoForIndexes(predicateIndexes, infoStore);
	}
	
	private static List<Info> objectMatches(InfoComparator comparator, int value, InfoStore infoStore) {
		List<Integer> objectIndexes = new ArrayList<Integer>();
		if (comparator.equals(InfoComparator.COMPARATOR_EQUALS)) {
			objectIndexes = Matcher.indexesForElement(value, infoStore.getObjectCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_GREATER_THAN)) {
			objectIndexes = Matcher.indexesForElementsGreaterThan(value, infoStore.getObjectCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_LESS_THAN)) {
			objectIndexes = Matcher.indexesForElementsLessThan(value, infoStore.getObjectCache());
		} else if (comparator.equals(InfoComparator.COMPARATOR_NOT_EQUALS)) {
			objectIndexes = Matcher.indexesForElementsNotEqual(value, infoStore.getObjectCache());
		}
		return infoForIndexes(objectIndexes, infoStore);
	}
}
