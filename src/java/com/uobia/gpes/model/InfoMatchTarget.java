package com.uobia.gpes.model;

public enum InfoMatchTarget {
	MATCH_S(InfoPredicate.MATCH_S.getValue()),
	MATCH_O(InfoPredicate.MATCH_O.getValue()),
	MATCH_P(InfoPredicate.MATCH_P.getValue());
	
	private final int value;
	private InfoMatchTarget(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
