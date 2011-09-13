package com.uobia.gpes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uobia.gpes.matcher.Matcher;


public class InfoStore implements Serializable {
 	private static final long serialVersionUID = 1L;
	private List<Info> infoCollection;
	private List<Integer> sCache;
	private List<Integer> pCache;
	private List<Integer> oCache;
	public int maxSize;
	//public enum MatchElement{MatchSubject, MatchPredicate, MatchObject};
	
	private InfoStore() {
		infoCollection = new ArrayList<Info>();
		sCache = new ArrayList<Integer>();
		pCache = new ArrayList<Integer>();
		oCache = new ArrayList<Integer>();
		maxSize = 100;
	}
	
	public static InfoStore create() {
		InfoStore infoStore = new InfoStore();
		return infoStore;
	}

	public boolean isEmpty() {
		return infoCollection.isEmpty();
	}

	public void addAll(List<Info> infos) {
		infoCollection.addAll(infos);
		for (int i = 0; i < infos.size(); i++) {
			sCache.add(infos.get(i).getSubject());
			pCache.add(infos.get(i).getPredicate());
			oCache.add(infos.get(i).getObject());
		}
	}
	
	public int size() {
		return infoCollection.size();
	}
	
	public Info get(int index) {
		return infoCollection.get(index);
	}
	
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
		//TODO: resolve how to handle if info collection is bigger than specified
		//TODO: Look at ArrayBlockingQueue to implement this
	}
	
	public boolean add(Info info) {
		if (infoCollection.size() < maxSize) {
			infoCollection.add(info);
			sCache.add(info.getSubject());
			pCache.add(info.getPredicate());
			oCache.add(info.getObject());
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		infoCollection.clear();
		sCache.clear();
		pCache.clear();
		oCache.clear();
	}

	public boolean remove(int index) {
		if (index < 0 || index >= infoCollection.size()) {
			return false;
		} else {
			infoCollection.remove(index);
			sCache.remove(index);
			pCache.remove(index);
			oCache.remove(index);
			return true;
		}
	}
	
	public void removeAll(List<Integer> indexes) {
		for (int i = 0; i < indexes.size(); i++) {
			remove(indexes.get(i));
		}
	}

	public boolean equals(InfoStore infoStore) {
		return (this.infoCollection.equals(infoStore.getInfo()));
	}
	
	public List<Info> getInfo() {
		return Collections.unmodifiableList(infoCollection);
	}
	
	public List<Info> matchInfo(MatchRule matchRule) {
		List<Info> matches = Matcher.match(matchRule, infoCollection);
		//TODO: Finish implementation
		return matches;
	}
	
	public List<Integer> matchIndexes(List<MatchRule> matchRule) {
		// TODO: Finish implementation
		return new ArrayList<Integer>();
	}
	
	public List<Info> infoForIndexes(List<Integer> indexes) {
		List<Info> subList = new ArrayList<Info>();
		for(int i = 0; i < indexes.size(); i++) {
			subList.add(infoCollection.get(i));
		}
		return subList;
	}
	
	/*public List<Integer> indexesForInfo(Info info, EnumSet<MatchElement> matchOn) {
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
