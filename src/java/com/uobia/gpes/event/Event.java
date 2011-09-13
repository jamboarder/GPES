package com.uobia.gpes.event;
import com.uobia.gpes.model.InfoStore;


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
		return 0;
//		Info dummyIdInfo = Info.create(InfoSubject.SELF.getValue(), InfoPredicate.CREATED_BY.getValue(), 0);
//		List<Integer> idIndex = infoStore.indexesForInfo(dummyIdInfo, EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate));
//		if (!idIndex.isEmpty()) {
//			Info idInfo = infoStore.get(idIndex.get(0));
//			return idInfo.getObject();
//		} else {
//			return -1;
//		}
	}

	public InfoStore infoStore() {
		return infoStore;
	}
	
	public void setInfoStore(InfoStore infoStore) {
		this.infoStore = infoStore;
	}
	
	public boolean equals(Object object) {
    	if ( this == object ) return true;
    	if ( !(object instanceof Event) ) return false;
    	Event event = (Event)object;
		return this.infoStore.equals(event.infoStore());
	}
	private void setCreatorId(int creatorId) {
//		Info idInfo = Info.create(Info.THIS, Info.CREATED_BY, creatorId);
//		List<Integer> idIndex = infoStore.indexesForInfo(idInfo, EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate));
//		infoStore.removeAll(idIndex);
//		infoStore.add(idInfo);
	}
}
