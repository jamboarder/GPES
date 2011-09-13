package com.uobia.gpes.model;

public enum InfoComparator {
	COMPARATOR_GREATER_THAN(InfoObject.COMPARATOR_GREATER_THAN.getValue()),
	COMPARATOR_EQUALS(InfoObject.COMPARATOR_EQUALS.getValue()),
	COMPARATOR_LESS_THAN(InfoObject.COMPARATOR_LESS_THAN.getValue());
	private final int value;
	private InfoComparator(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
