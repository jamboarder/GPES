package com.uobia.gpes.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum InfoCompareTarget {
	NULL(InfoPredicate.NULL.getValue()),
	COMPARE_WITH_S_FROM(InfoPredicate.COMPARE_WITH_S_FROM.getValue()),
	COMPARE_WITH_O_FROM(InfoPredicate.COMPARE_WITH_O_FROM.getValue()),
	COMPARE_WITH_P_FROM(InfoPredicate.COMPARE_WITH_P_FROM.getValue());
	
	private final int value;
	private InfoCompareTarget(final int value) {
		this.value = value;
	}
	private static final Map<Integer, InfoCompareTarget> lookup = new HashMap<Integer,InfoCompareTarget>();
    static {
    	for(InfoCompareTarget c : EnumSet.allOf(InfoCompareTarget.class)) {
    		lookup.put(c.getValue(), c);
    	}
    }
	public int getValue() {
		return value;
	}
	public static InfoCompareTarget get(int value) {
		return lookup.get(value);
	}
	public static boolean has(int value) {
		return lookup.containsKey(value);
	}
}
