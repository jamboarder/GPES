package com.uobia.gpes.model;

public enum InfoPredicate {
	COMPARE_USING(103),
	COMPARE_WITH_FIXED(104);
	private final int id;
	private InfoPredicate(final int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
