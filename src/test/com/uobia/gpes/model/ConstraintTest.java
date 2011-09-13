package com.uobia.gpes.model;

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
		Info correctIdInfo = Info.create(id, InfoPredicate.IS_TYPE.getValue(), InfoObject.TYPE_CONSTRAINT.getValue());;
		Assert.assertEquals("Triple should set the id type to Constraint", idInfo, correctIdInfo);
	}
	
	@Test
	public void shouldAddFixedConstraint() {
		int id = 42;
		Constraint constraint = Constraint.create(id);
		int value = 987;
		constraint.addComparator(InfoComparator.COMPARATOR_EQUALS, value);
		List<Info> cInfo = constraint.getInfo();
		boolean compareUsingCorrect = false;
		boolean compareWithCorrect = false;
		Info compareUsingInfo = Info.create(id, InfoPredicate.COMPARE_USING.getValue(), InfoObject.COMPARATOR_EQUALS.getValue());
		Info compareWithInfo = Info.create(id, InfoPredicate.COMPARE_WITH_FIXED.getValue(), value);
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
		Assert.assertTrue("Compare With Fixed Info triple should be correctly created", compareWithCorrect);
	}
	
	@Test
	public void shouldAddVariableConstraint() {
		int id = 42;
		Constraint constraint = Constraint.create(id);
		int matchRuleId = 987;
		constraint.addComparator(InfoComparator.COMPARATOR_EQUALS, InfoCompareTarget.COMPARE_WITH_S_FROM, matchRuleId) ;
		List<Info> cInfo = constraint.getInfo();
		boolean compareUsingCorrect = false;
		boolean compareWithCorrect = false;
		Info compareUsingInfo = Info.create(id, InfoPredicate.COMPARE_USING.getValue(), InfoObject.COMPARATOR_EQUALS.getValue());
		Info compareWithInfo = Info.create(id, InfoPredicate.COMPARE_WITH_S_FROM.getValue(), matchRuleId);
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
		Assert.assertTrue("Compare With Target Info triple should be correctly created", compareWithCorrect);
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void shouldNotAlterInfoCollection() {
		Constraint c = Constraint.create(0);
		c.getInfo().clear();		
	}
}
