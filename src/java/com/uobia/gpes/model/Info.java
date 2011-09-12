package com.uobia.gpes.model;

import java.io.Serializable;

public class Info implements Serializable {
 	private static final long serialVersionUID = 1L;
	private final int s;
    private final int p;
    private final int o;
    
    //Subject
    public static final int THIS = 0;
    
    //Predicates
    public static final int HAS_ID = 0;
    public static final int IS_TYPE = 1;
    public static final int CREATED_BY = 2;
    public static final int HAS_PART = 3; 
    public static final int MATCH_S = 100;
    public static final int MATCH_P = 101;
    public static final int MATCH_O = 102;
    public static final int COMPARE_USING = 103;
    public static final int COMPARE_WITH_FIXED = 104;
    public static final int COMPARE_WITH_S_FROM = 105;
    public static final int COMPARE_WITH_P_FROM = 106;
    public static final int COMPARE_WITH_O_FROM = 107;

    //Objects
    public static final int TYPE_EVENT = 0;
    public static final int TYPE_REPRODUCTION_EVENT = 1;
    public static final int TYPE_MUTATION_RULE = 2;
    public static final int TYPE_SENSOR_RULE = 3;
    public static final int TYPE_ACTUATOR_RULE = 4;
    public static final int TYPE_INDUCTOR_RULE = 5;
    public static final int TYPE_DEDUCTOR_RULE = 6;
    public static final int TYPE_MATCH_RULE = 7;
    public static final int TYPE_ACTION_RULE = 8;
    public static final int TYPE_CONSTRAINT = 9;
    public static final int COMPARATOR_GREATER_THAN = 100;
    public static final int COMPARATOR_LESS_THAN = 101;
    public static final int COMPARATOR_EQUALS = 101;
    
    
    private Info(int i, int j, int k) {
        s = i;
        p = j;
        o = k;
    }
    
    public int getS() {
    	return s;
    }
    
    public int getP() {
    	return p;
    }
    
    public int getO() {
    	return o;
    }
    
    public boolean isRelated(Info info) {
        if (s == info.getO() || o == info.getS()) {
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
