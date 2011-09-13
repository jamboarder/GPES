package com.uobia.gpes.model;

public enum InfoPredicate {
    HAS_ID(0),
    IS_TYPE(1),
    CREATED_BY(2),
    HAS_PART(3), 
    MATCH_S(100),
    MATCH_P(101),
    MATCH_O(102),
    COMPARE_USING(103),
    COMPARE_WITH_FIXED(104),
    COMPARE_WITH_S_FROM(105),
    COMPARE_WITH_P_FROM(106),
    COMPARE_WITH_O_FROM(107);

    private final int value;
	private InfoPredicate(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
