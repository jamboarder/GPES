package com.uobia.gpes.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum InfoMatchTarget {
	NULL(InfoPredicate.NULL.getValue()),
	MATCH_S(InfoPredicate.MATCH_S.getValue()),
	MATCH_O(InfoPredicate.MATCH_O.getValue()),
	MATCH_P(InfoPredicate.MATCH_P.getValue());
	
	private final int value;
	private InfoMatchTarget(final int value) {
		this.value = value;
	}
	private static final Map<Integer, InfoMatchTarget> lookup = new HashMap<Integer,InfoMatchTarget>();
    static {
    	for(InfoMatchTarget c : EnumSet.allOf(InfoMatchTarget.class)) {
    		lookup.put(c.getValue(), c);
    	}
    }
	public int getValue() {
		return value;
	}
	public static InfoMatchTarget get(int value) {
		return lookup.get(value);
	}
	public static boolean has(int value) {
		return lookup.containsKey(value);
	}
}
