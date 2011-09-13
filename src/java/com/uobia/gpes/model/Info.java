package com.uobia.gpes.model;

import java.io.Serializable;

public class Info implements Serializable {
 	private static final long serialVersionUID = 1L;
	private final int s;
    private final int p;
    private final int o;
    
    private Info(int i, int j, int k) {
        s = i;
        p = j;
        o = k;
    }
    
    public int getSubject() {
    	return s;
    }
    
    public int getPredicate() {
    	return p;
    }
    
    public int getObject() {
    	return o;
    }
    
    public boolean isRelated(Info info) {
        if (s == info.getObject() || o == info.getSubject()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + o;
		result = prime * result + p;
		result = prime * result + s;
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
		if (o != other.o)
			return false;
		if (p != other.p)
			return false;
		if (s != other.s)
			return false;
		return true;
	}

    public static Info create(int i, int j, int k) {
        Info info = new Info(i, j, k);
        return info;
    }
}
