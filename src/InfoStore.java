import java.util.ArrayList;
import java.util.List;

public class InfoStore {
	private List<Info> infoCollection = new ArrayList<Info>();
	private List<Integer> subjectCache;
	private List<Integer> predicateCache;
	private List<Integer> objectCache;
	
	public int maxSize = 100;
	
	public int size() {
		return infoCollection.size();
	}
	
	public boolean add(Info info) {
		if (infoCollection.size() < maxSize) {
			infoCollection.add(info);
			subjectCache.add(info.s);
			predicateCache.add(info.p);
			objectCache.add(info.o);
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		infoCollection.clear();
	}

	public boolean remove(Info info) {
		int foundIndex = infoCollection.indexOf(info);
		if (foundIndex == -1) {
			return false;
		} else {
			infoCollection.remove(foundIndex);
			subjectCache.remove(foundIndex);
			predicateCache.remove(foundIndex);
			objectCache.remove(foundIndex);
			return true;
		}
	}
	
	public List<Info> matchInfo(List<Info> matchRules) {
		List<Info> matches = new ArrayList<Info>();
		//TODO: Finish implementation
		return matches;
	}
	
	
	public List<Integer> indexesForSubject(Info info) {
		
		return indexesForElement(info.s, subjectCache);
	}
	
	public List<Integer> indexesForPredicate(Info info) {
		return indexesForElement(info.p, predicateCache);
	}

	public List<Integer> indexesForObject(Info info) {
		return indexesForElement(info.o, objectCache);
	}
	
	public List<Integer> subjectsForIndexes(List<Integer> indexes) {
		return elementsForIndexes(indexes, subjectCache);
	}

	public List<Integer> predicatesForIndexes(List<Integer> indexes) {
		return elementsForIndexes(indexes, predicateCache);
	}

	public List<Integer> objectsForIndexes(List<Integer> indexes) {
		return elementsForIndexes(indexes, objectCache);
	}

	private List<Integer> indexesForElement(int element, List<Integer> elementCache) {
		List<Integer> indexes = new ArrayList<Integer>();
		int index = elementCache.indexOf(element);
	    int lastIndex = elementCache.lastIndexOf(element);
		indexes.add(index);
		while (index < lastIndex) {
			List<Integer> subList = elementCache.subList(index + 1, lastIndex);
			int subIndex = subList.indexOf(element);
			if (subIndex != -1) {
				index = index + 1 + subIndex;
			} else {
				index = lastIndex;
			}
			indexes.add(index);
		}
		
		return indexes;
	}
	
	private List<Integer> elementsForIndexes(List<Integer> indexes, List<Integer> elementCache) {
		List<Integer> subList = new ArrayList<Integer>();
		for(int i = 0; i < indexes.size(); i++) {
			subList.add(elementCache.get(i));
		}
		return subList;
	}
}
