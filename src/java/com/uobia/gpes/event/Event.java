package com.uobia.gpes.event;
import java.util.EnumSet;
import java.util.List;

import com.uobia.gpes.matcher.Matcher;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoPredicate;
import com.uobia.gpes.model.InfoStore;
import com.uobia.gpes.model.InfoSubject;


public class Event {
	private InfoStore infoStore;
	
	private Event(int creatorId) {
		infoStore = InfoStore.create();
		setCreatorId(creatorId);
	}
	
	public static Event create(int creatorId) {
		Event event = new Event(creatorId);
		return event;
	}

	public static Event create() {
		Event event = new Event(0);
		return event;
	}
	
	public int getCreatorId() {
		Info searchIdInfo = Info.create(InfoSubject.SELF.getValue(), InfoPredicate.CREATED_BY.getValue(), 0);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate);
		List<Integer> idIndex = Matcher.indexesForInfo(searchIdInfo, matchOn, infoStore);
		if (!idIndex.isEmpty()) {
			Info idInfo = infoStore.get(idIndex.get(0));
			return idInfo.getObject();
		} else {
			return 0;
		}
	}

	private void setCreatorId(int creatorId) {
		Info idInfo = Info.create(InfoSubject.SELF.getValue(), InfoPredicate.CREATED_BY.getValue(), creatorId);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate);
		List<Integer> idIndex = Matcher.indexesForInfo(idInfo, matchOn, infoStore);
		infoStore.removeAll(idIndex);
		infoStore.add(idInfo);
	}

	public InfoStore infoStore() {
		return infoStore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((infoStore == null) ? 0 : infoStore.hashCode());
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
		Event other = (Event) obj;
		if (infoStore == null) {
			if (other.infoStore != null)
				return false;
		} else if (!infoStore.equals(other.infoStore))
			return false;
		return true;
	}
}
