package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.uobia.gpes.matcher.Matcher;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoPredicate;
import com.uobia.gpes.model.InfoStore;
import com.uobia.gpes.model.InfoSubject;


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
		Info searchIdInfo = Info.create(InfoSubject.SELF.getValue(), InfoPredicate.HAS_ID.getValue(), 0);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate);
		List<Integer> idIndex = Matcher.indexesForInfo(searchIdInfo, matchOn, infoStore);
		if (!idIndex.isEmpty()) {
			Info idInfo = infoStore.get(idIndex.get(0));
			return idInfo.getObject();
		} else {
			return 0;
		}
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
		Info idInfo = Info.create(InfoSubject.SELF.getValue(), InfoPredicate.HAS_ID.getValue(), id);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate);
		List<Integer> idIndex = Matcher.indexesForInfo(idInfo, matchOn, infoStore);
		infoStore.removeAll(idIndex);
		infoStore.add(idInfo);
	}
}