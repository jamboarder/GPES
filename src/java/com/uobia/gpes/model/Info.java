package com.uobia.gpes.model;

public class Info {
    private final int s;
    private final int p;
    private final int o;
    
    public static final int THIS = 0;
    
    public static final int HAS_ID = 0;
    public static final int IS_TYPE = 1;
    public static final int CREATED_BY = 2;    

    public static final int TYPE_EVENT = 0;
    public static final int TYPE_REPRODUCTION_EVENT = 1;
    public static final int TYPE_MUTATION_RULE = 2;
    public static final int TYPE_SENSOR_RULE = 3;
    public static final int TYPE_ACTUATOR_RULE = 4;
    public static final int TYPE_INDUCTOR_RULE = 5;
    public static final int TYPE_DEDUCTOR_RULE = 6;
    
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
    
    public boolean equals(Object object) {
    	if ( this == object ) return true;
    	if ( !(object instanceof Info) ) return false;
    	Info info = (Info)object;
        if (s == info.getS() && p == info.getP() && o == info.getO()) {
            return true;
        } else {
            return false;
        }
    }

    public static Info create(int i, int j, int k) {
        Info info = new Info(i, j, k);
        return info;
    }
}
