package com.uobia.gpes.model;

import java.io.Serializable;

public class Info implements Serializable {
 	private static final long serialVersionUID = 1L;
	private final int subject;
    private final int predicate;
    private final int object;
    
    private Info(int subject, int predicate, int object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }
    
    public static Info create(int subject, int predicate, int object) {
        Info info = new Info(subject, predicate, object);
        return info;
    }
   public int getSubject() {
    	return subject;
    }
    
    public int getPredicate() {
    	return predicate;
    }
    
    public int getObject() {
    	return object;
    }
    
    public boolean isRelated(Info info) {
        if (subject == info.getObject() || object == info.getSubject()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + object;
		result = prime * result + predicate;
		result = prime * result + subject;
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
		Info other = (Info) obj;
		if (object != other.object)
			return false;
		if (predicate != other.predicate)
			return false;
		if (subject != other.subject)
			return false;
		return true;
	}
}
