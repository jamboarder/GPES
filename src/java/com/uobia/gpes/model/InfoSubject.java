package com.uobia.gpes.model;

public enum InfoSubject {
	SELF(0);
	private final int id;
	private InfoSubject(final int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
