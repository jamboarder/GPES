package com.uobia.gpes.matcher;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.MatchRule;

public class Matcher {

	public static List<Info> match(MatchRule m, List<Info> infos) {
		//TODO: Finish Implementation
		return new ArrayList<Info>();
	}
	
	//TODO: Integrate below functions into matcher
	/*public List<Info> infoForIndexes(List<Integer> indexes) {
		List<Info> subList = new ArrayList<Info>();
		for(int i = 0; i < indexes.size(); i++) {
			subList.add(infoCollection.get(i));
		}
		return subList;
	}
	
	public List<Integer> indexesForInfo(Info info, EnumSet<MatchElement> matchOn) {
		if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject))) {
			return indexesForSubject(info);
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchPredicate))) {
			return indexesForPredicate(info);
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchObject))) {
			return indexesForObject(info);
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject, MatchElement.MatchPredicate))) {
			List<Integer> indexesForInfo = new ArrayList<Integer>();
			List<Integer> sIndexes = indexesForSubject(info);
			for (int i = 0; i < sIndexes.size(); i++) {
				int index = sIndexes.get(i);
				if (index < 0  || index >= infoCollection.size()) {
					continue;
				}
				Info sInfo = infoCollection.get(index);
				if (sInfo.getP() == info.getP()) {
					indexesForInfo.add(index);
				}
			}
			return indexesForInfo;
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject, MatchElement.MatchObject))) {
			List<Integer> indexesForInfo = new ArrayList<Integer>();
			List<Integer> sIndexes = indexesForSubject(info);
			for (int i = 0; i < sIndexes.size(); i++) {
				int index = sIndexes.get(i);
				if (index < 0  || index >= infoCollection.size()) {
					continue;
				}
				Info sInfo = infoCollection.get(index);
				if (sInfo.getO() == info.getO()) {
					indexesForInfo.add(index);
				}
			}
			return indexesForInfo;
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchPredicate, MatchElement.MatchObject))) {
			List<Integer> indexesForInfo = new ArrayList<Integer>();
			List<Integer> pIndexes = indexesForPredicate(info);
			for (int i = 0; i < pIndexes.size(); i++) {
				int index = pIndexes.get(i);
				if (index < 0  || index >= infoCollection.size()) {
					continue;
				}
				Info pInfo = infoCollection.get(index);
				if (pInfo.getO() == info.getO()) {
					indexesForInfo.add(index);
				}
			}
			return indexesForInfo;
		} else if (matchOn.equals(EnumSet.of(MatchElement.MatchSubject, MatchElement.MatchPredicate, MatchElement.MatchObject))) {
			List<Integer> indexesForInfo = new ArrayList<Integer>();
			List<Integer> sIndexes = indexesForSubject(info);
			for (int i = 0; i < sIndexes.size(); i++) {
				int index = sIndexes.get(i);
				if (index < 0  || index >= infoCollection.size()) {
					continue;
				}
				Info sInfo = infoCollection.get(index);
				if (sInfo.getP() == info.getP() && sInfo.getO() == info.getO()) {
					indexesForInfo.add(index);
				}
			}
			return indexesForInfo;
		} else {
			return new ArrayList<Integer>();
		}
	}
	
	private List<Integer> indexesForSubject(Info info) {
		
		return indexesForElement(info.getS(), sCache);
	}
	
	private List<Integer> indexesForPredicate(Info info) {
		return indexesForElement(info.getP(), pCache);
	}

	private List<Integer> indexesForObject(Info info) {
		return indexesForElement(info.getO(), oCache);
	}
	
	@SuppressWarnings("unused")
	private List<Integer> subjectsForIndexes(List<Integer> indexes) {
		return elementsForIndexes(indexes, sCache);
	}

	@SuppressWarnings("unused")
	private List<Integer> predicatesForIndexes(List<Integer> indexes) {
		return elementsForIndexes(indexes, pCache);
	}

	@SuppressWarnings("unused")
	private List<Integer> objectsForIndexes(List<Integer> indexes) {
		return elementsForIndexes(indexes, oCache);
	}

	private List<Integer> indexesForElement(int element, List<Integer> elementCache) {
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
	
	private List<Integer> elementsForIndexes(List<Integer> indexes, List<Integer> elementCache) {
		List<Integer> subList = new ArrayList<Integer>();
		for(int i = 0; i < indexes.size(); i++) {
			subList.add(elementCache.get(i));
		}
		return subList;
	}*/


}
