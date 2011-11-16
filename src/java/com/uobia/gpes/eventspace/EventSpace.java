package com.uobia.gpes.eventspace;

import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.event.Event;
import com.uobia.gpes.event.EventStateChecker;

public class EventSpace {
	private List<Event> events;
	private int maxSize;
	
	private EventSpace() {
		this.events = new ArrayList<Event>();
		setMaxSize(100);
	}

	public static EventSpace create() {
		return new EventSpace();
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(final int maxSize) {
		this.maxSize = maxSize;
	}

	public List<Event> getEvents() {
		return events;
	}

	public boolean addEvents(final List<Event> events) {
		boolean maxSizeExceeded = ((this.events.size() + events.size()) > this.maxSize);
		if (!maxSizeExceeded) {
			this.events.addAll(events);
		}
		return !maxSizeExceeded;
	}

	public void removeExpiredEvents() {
		for (int i = 0; i < this.events.size(); i++) {
			if (EventStateChecker.isExpired(events.get(i))) {
				this.events.remove(i);
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + maxSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventSpace other = (EventSpace) obj;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (maxSize != other.maxSize)
			return false;
		return true;
	}
}
