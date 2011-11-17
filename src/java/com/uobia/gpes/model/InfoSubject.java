package com.uobia.gpes.model;

public enum InfoSubject {
    NULL(-1),
	SELF(0);
	private final int value;
	private InfoSubject(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
