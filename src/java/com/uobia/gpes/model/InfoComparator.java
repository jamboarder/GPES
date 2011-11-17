package com.uobia.gpes.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum InfoComparator {
	NULL(InfoObject.NULL.getValue()),
	COMPARATOR_GREATER_THAN(InfoObject.COMPARATOR_GREATER_THAN.getValue()),
	COMPARATOR_EQUALS(InfoObject.COMPARATOR_EQUALS.getValue()),
	COMPARATOR_LESS_THAN(InfoObject.COMPARATOR_LESS_THAN.getValue()),
	COMPARATOR_NOT_EQUALS(InfoObject.COMPARATOR_NOT_EQUALS.getValue());

    private final int value;
    
	private InfoComparator(final int value) {
		this.value = value;
	}
	
	private static final Map<Integer, InfoComparator> lookup = new HashMap<Integer,InfoComparator>();
    static {
    	for(InfoComparator c : EnumSet.allOf(InfoComparator.class)) {
    		lookup.put(c.getValue(), c);
    	}
    }
    
	public int getValue() {
		return value;
	}
	
	public static InfoComparator get(int value) {
		return lookup.get(value);
	}
	
	public static boolean has(int value) {
		return lookup.containsKey(value);
	}
}
