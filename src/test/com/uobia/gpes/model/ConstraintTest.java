package com.uobia.gpes.model;

import java.lang.instrument.UnmodifiableClassException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ConstraintTest {
	@Test
	public void shouldCreate() {
		int id = 42;
		Constraint constraint = Constraint.create(id);
		List<Info> cInfo = constraint.getInfo();
		Assert.assertTrue("One triple should be created.", cInfo.size() == 1);
		Info idInfo = cInfo.get(0);
		boolean idIsSet = (idInfo.getS() == id);
		boolean typeIsSet = (idInfo.getP() == Info.IS_TYPE && idInfo.getO() == Info.TYPE_CONSTRAINT);
		Assert.assertTrue("Triple should set the id type to Constraint", idIsSet && typeIsSet);
	}
	
	@Test
	public void shouldAddFixedConstraint() {
		int id = 42;
		Constraint constraint = Constraint.create(id);
		int value = 987;
		Constraint.Comparator comp = Constraint.Comparator.Equals;
		constraint.addComparator(comp, value);
		List<Info> cInfo = constraint.getInfo();
		boolean compareUsingCorrect = false;
		boolean compareWithCorrect = false;
		Info compareUsingInfo = Info.create(id, Info.COMPARE_USING, Info.COMPARATOR_EQUALS);
		Info compareWithInfo = Info.create(id, Info.COMPARE_WITH_FIXED, value);
		for (int i = 0; i < cInfo.size(); i++) {
			Info info = cInfo.get(i);
			if (info.equals(compareUsingInfo)) {
				compareUsingCorrect = true;
			}
			if (info.equals(compareWithInfo)) {
				compareWithCorrect = true;
			}
		}
		Assert.assertTrue("Compare Using Info triple should be correctly created", compareUsingCorrect);
		Assert.assertTrue("Compare With Info triple should be correctly created", compareWithCorrect);
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void shouldNotAlterInfoCollection() {
		Constraint c = Constraint.create(0);
		c.getInfo().clear();		
	}
}
