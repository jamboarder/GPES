package com.uobia.gpes.model;

public enum InfoObject {
	COMPARATOR_GREATER_THAN(100),
	COMPARATOR_LESS_THAN(101),
	COMPARATOR_EQUALS(102);
	private final int id;
	private InfoObject(final int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
