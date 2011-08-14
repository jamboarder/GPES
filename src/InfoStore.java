import java.util.ArrayList;
import java.util.List;

public class InfoStore {
	private List<Integer> s = new ArrayList<Integer>();
	private List<Integer> p = new ArrayList<Integer>();
	private List<Integer> o = new ArrayList<Integer>();
	public int maxSize = 100;
	
	public int size() {
		return s.size();
	}
	
	public boolean add(List<Integer> triple) {
		if (triple.size() == 3 && s.size() < maxSize) {
			s.add(triple.get(0));
			p.add(triple.get(1));
			o.add(triple.get(2));
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		s.clear();
		p.clear();
		o.clear();
	}

	public boolean remove(List<Integer> triple) {
		if (triple.size() != 3) {
			return false;
		}
		int sFoundIndex = s.indexOf(triple.get(0));
		if (sFoundIndex == -1) {
			return false;
		}
		int pFoundIndex = p.indexOf(triple.get(1));
		if (pFoundIndex == -1) {
			return false;
		}
		int oFoundIndex = o.indexOf(triple.get(2));
		if (oFoundIndex == -1) {
			return false;
		}
		//TODO Finish impementation
		return true;
	}
	
	public List<Integer> indexesForSubject(int subject) {
		return indexesForElement(subject, s);
	}
	
	public List<Integer> indexesForPredicate(int predicate) {
		return indexesForElement(predicate, p);
	}

	public List<Integer> indexesForObject(int object) {
		return indexesForElement(object, o);
	}
	
	public List<Integer> subjectsForIndexes(List<Integer> indexes) {
		return subListForIndexes(indexes, s);
	}

	public List<Integer> predicatesForIndexes(List<Integer> indexes) {
		return subListForIndexes(indexes, p);
	}

	public List<Integer> objectsForIndexes(List<Integer> indexes) {
		return subListForIndexes(indexes, o);
	}

	private List<Integer> indexesForElement(int element, List<Integer> l) {
		List<Integer> indexes = new ArrayList<Integer>();
		int index = l.indexOf(element);
	    int lastIndex = l.lastIndexOf(element);
		indexes.add(index);
		while (index < lastIndex) {
			List<Integer> subList = l.subList(index + 1, lastIndex);
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
	
	private List<Integer> subListForIndexes(List<Integer> indexes, List<Integer> l) {
		List<Integer> subList = new ArrayList<Integer>();
		for(int i = 0; i < indexes.size(); i++) {
			subList.add(l.get(i));
		}
		return subList;
	}
}
