package com.uobia.gpes.model;

public enum InfoComparator {
	COMPARATOR_GREATER_THAN(InfoObject.COMPARATOR_GREATER_THAN.getId()),
	COMPARE_WITH_FIXED(InfoPredicate.COMPARE_WITH_FIXED.getId());
	private final int id;
	private InfoComparator(final int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
