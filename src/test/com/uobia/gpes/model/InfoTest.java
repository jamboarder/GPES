package com.uobia.gpes.model;

import org.junit.Assert;
import org.junit.Test;

public class InfoTest {
    
    @Test
    public void shouldBeEqual() {
        Info info1 = Info.create(1, 2, 3);
        Info info2 = Info.create(1, 2, 3);
        Assert.assertTrue("Should be equal if all elements are equal", info1.equals(info2));
    }

    @Test
    public void shouldNotBeEqual() {
        Info info1 = Info.create(1, 2, 3);
        Info info2 = Info.create(2, 2, 3);
        Assert.assertFalse("Should not be equal (s1 != s2)", info1.equals(info2));

        Info info3 = Info.create(1, -3, 3);
        Assert.assertFalse("Should not be equal (p1 != p2)", info1.equals(info3));
        
        Info info4 = Info.create(1, 2, 4);
        Assert.assertFalse("Should not be equal (o1!=o2)", info1.equals(info4));
    }

    @Test
    public void shouldBeRelated() {
        Info info1 = Info.create(1, 2, 3);
        Info info2 = Info.create(3, 4, 5);
        Assert.assertTrue("Should be related (o = s)", info1.isRelated(info2));

        Info info3 = Info.create(6, 7, 1);
        Assert.assertTrue("Should be related (s = o)", info1.isRelated(info3));
    }
    
    @Test
    public void shouldNotBeRelated() {
        Info info1 = Info.create(1, 2, 3);
        Info info2 = Info.create(4, 5, 6);
        Assert.assertFalse("Should be unrelated if neither s nor o are equal", info1.isRelated(info2));
    }
}
