package com.uobia.gpes.bridge;
import java.util.ArrayList;
import java.util.List;


public class Bridge {
	private List<EventTranslator> eventTranslators;
	private List<BehaviorTranslator> behaviorTranslators;
	
	private Bridge() {
		setEventTranslators(new ArrayList<EventTranslator>());
		setBehaviorTranslators(new ArrayList<BehaviorTranslator>());
	}
	
	public static Bridge create() {
		return new Bridge();
	}

	public List<EventTranslator> getEventTranslators() {
		return eventTranslators;
	}

	public void setEventTranslators(List<EventTranslator> eventTranslators) {
		this.eventTranslators = eventTranslators;
	}

	public List<BehaviorTranslator> getBehaviorTranslators() {
		return behaviorTranslators;
	}

	public void setBehaviorTranslators(List<BehaviorTranslator> behaviorTranslators) {
		this.behaviorTranslators = behaviorTranslators;
	}
	
}
