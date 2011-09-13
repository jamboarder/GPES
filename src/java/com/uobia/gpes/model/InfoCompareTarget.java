package com.uobia.gpes.model;

public enum InfoCompareTarget {
	COMPARE_WITH_S_FROM(InfoPredicate.COMPARE_WITH_S_FROM.getValue()),
	COMPARE_WITH_O_FROM(InfoPredicate.COMPARE_WITH_O_FROM.getValue()),
	COMPARE_WITH_P_FROM(InfoPredicate.COMPARE_WITH_P_FROM.getValue());
	
	private final int value;
	private InfoCompareTarget(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
