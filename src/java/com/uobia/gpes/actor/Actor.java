package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.model.InfoStore;


public class Actor {
	private InfoStore infoStore;
	
	private Actor(int id) {
		infoStore = InfoStore.create();
		setId(id);
	}

	public static Actor create(int id) {
		Actor actor = new Actor(id);
		return actor;
	}

	public static Actor create() {
		int id = 0;
		Actor actor = Actor.create(id);
		return actor;
	}
	
	public int getId() {
		return 0;
//		Info dummyIdInfo = Info.create(InfoSubject.SELF.getValue(), InfoPredicate.HAS_ID.getValue(), 0);
//		List<Integer> idIndex = infoStore.indexesForInfo(dummyIdInfo, EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate));
//		if (!idIndex.isEmpty()) {
//			Info idInfo = infoStore.get(idIndex.get(0));
//			return idInfo.getObject();
//		} else {
//			return -1;
//		}
	}

	public boolean equals(Object object) {
    	if ( this == object ) return true;
    	if ( !(object instanceof Actor) ) return false;
    	Actor actor = (Actor)object;
		return this.infoStore.equals(actor.infoStore());
	}
	
	public InfoStore infoStore() {
		return infoStore;
	}

	public void setInfoStore(InfoStore infoStore) {
		this.infoStore = infoStore;		
	}

	public List<ActorRule> getSensorRules() {
		List<ActorRule> rules = new ArrayList<ActorRule>();
		//TODO: Finish implementation
		return rules;
	}
	
	public List<ActorRule> getInductorRules() {
		List<ActorRule> rules = new ArrayList<ActorRule>();
		//TODO: Finish implementation
		return rules;
	}
	
	public List<ActorRule> getDeductorRules() {
		List<ActorRule> rules = new ArrayList<ActorRule>();
		//TODO: Finish implementation
		return rules;
	}
	

	public List<ActorRule> getActuatorRules() {
		List<ActorRule> rules = new ArrayList<ActorRule>();
		//TODO: Finish implementation
		return rules;
	}

	public List<ActorRule> getMutationRules() {
		List<ActorRule> rules = new ArrayList<ActorRule>();
		//TODO: Finish implementation
		return rules;
	}
	
	public void addRule(ActorRule rule) {
		// TODO Finish Implementation
	}
	
	private void setId(int id) {
//		Info idInfo = Info.create(Info.THIS, Info.HAS_ID, id);
//		List<Integer> idIndex = infoStore.indexesForInfo(idInfo, EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate));
//		infoStore.removeAll(idIndex);
//		infoStore.add(idInfo);
	}
}