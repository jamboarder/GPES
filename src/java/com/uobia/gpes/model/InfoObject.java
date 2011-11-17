package com.uobia.gpes.model;

public enum InfoObject {
    NULL(-1),
    TYPE_EVENT(0),
    TYPE_REPRODUCTION_EVENT(1),
    TYPE_MUTATION_RULE(2),
    TYPE_SENSOR_RULE(3),
    TYPE_ACTUATOR_RULE(4),
    TYPE_INDUCTOR_RULE(5),
    TYPE_DEDUCTOR_RULE(6),
    TYPE_MATCH_RULE(7),
    TYPE_ACTION_RULE(8),
    TYPE_CONSTRAINT(9),
    COMPARATOR_EQUALS(100),
    COMPARATOR_GREATER_THAN(101),
    COMPARATOR_LESS_THAN(102),
    COMPARATOR_NOT_EQUALS(103);
 
	private final int value;
	private InfoObject(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
