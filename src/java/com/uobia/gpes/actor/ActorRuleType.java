package com.uobia.gpes.actor;

import com.uobia.gpes.model.InfoObject;

public enum ActorRuleType {
	TYPE_SENSOR_RULE(InfoObject.TYPE_SENSOR_RULE.getValue()),
	TYPE_ACTUATOR_RULE(InfoObject.TYPE_ACTUATOR_RULE.getValue()),
	TYPE_INDUCTOR_RULE(InfoObject.TYPE_INDUCTOR_RULE.getValue()),
	TYPE_DEDUCTOR_RULE(InfoObject.TYPE_DEDUCTOR_RULE.getValue());
	
	private final int value;
	private ActorRuleType(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
