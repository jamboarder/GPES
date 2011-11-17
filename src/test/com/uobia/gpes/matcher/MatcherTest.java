package com.uobia.gpes.matcher;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.uobia.gpes.model.Constraint;
import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoComparator;
import com.uobia.gpes.model.InfoCompareTarget;
import com.uobia.gpes.model.InfoFixture;
import com.uobia.gpes.model.InfoMatchTarget;
import com.uobia.gpes.model.InfoStore;
import com.uobia.gpes.model.MatchRule;
import com.uobia.gpes.model.MatchRuleFixture;

public class MatcherTest {

	/*
	 * Example sensor rule
	 * s1, IS_TYPE, TYPE_SENSOR_RULE
	 * s1, HAS_PART, m1
	 * m1, IS_TYPE, TYPE_MATCH_RULE
	 * m1, MATCH_S, c1
	 * c1, COMPARE_USING, COMPARE_GREATER_THAN
	 * c1, COMPARE_WITH_FIXED, 4
	 * s1, HAS_PART, a1
	 * a1, IS_TYPE, TYPE_ACTION_RULE
	 * a1, DELETE, d1
	 * d1, DELETE_TYPE, DELETE_ALL
	 * s1, HAS_PART, a2
	 * a2, MODIFY, mr1
	 * mr1, MODIFY_S, mod1
	 * mod1, METHOD, MODIFY_ADD
	 * mod1, WITH_FIXED, 4
	 * ...
	 * s1, HAS_PART, a3
	 * a3, CREATE_FROM, m1
	 * a3, CREATE_USING, cr1
	 */
	
	@Test
	public void shouldNotFindMatchingInfo() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRuleFixture.createNoMatch(infos);
		
		List<Info> results = Matcher.match(m, infoStore);
		Assert.assertEquals("Should return nothing", 0, results.size());
	}
	
	@Test
	public void shouldFindMatchingInfo1() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_GREATER_THAN, 1);
		m.addConstraint(InfoMatchTarget.MATCH_S, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(3));
		correctMatches.add(infoStore.get(4));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}
	
	@Test
	public void shouldFindMatchingInfo2() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_EQUALS, 3);
		m.addConstraint(InfoMatchTarget.MATCH_S, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(3));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo3() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_LESS_THAN, 2);
		m.addConstraint(InfoMatchTarget.MATCH_S, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(0));
		correctMatches.add(infoStore.get(1));
		correctMatches.add(infoStore.get(2));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo4() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_GREATER_THAN, 2);
		m.addConstraint(InfoMatchTarget.MATCH_P, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(2));
		correctMatches.add(infoStore.get(3));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}
	
	@Test
	public void shouldFindMatchingInfo5() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_EQUALS, 2);
		m.addConstraint(InfoMatchTarget.MATCH_P, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(0));
		correctMatches.add(infoStore.get(1));
		correctMatches.add(infoStore.get(4));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo6() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_LESS_THAN, 3);
		m.addConstraint(InfoMatchTarget.MATCH_P, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(0));
		correctMatches.add(infoStore.get(1));
		correctMatches.add(infoStore.get(4));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo7() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_GREATER_THAN, 4);
		m.addConstraint(InfoMatchTarget.MATCH_O, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(2));
		correctMatches.add(infoStore.get(3));
		correctMatches.add(infoStore.get(4));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}
	
	@Test
	public void shouldFindMatchingInfo8() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_EQUALS, 4);
		m.addConstraint(InfoMatchTarget.MATCH_O, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(1));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo9() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_LESS_THAN, 5);
		m.addConstraint(InfoMatchTarget.MATCH_O, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(0));
		correctMatches.add(infoStore.get(1));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo10() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m = MatchRule.create(0);
		Constraint c= Constraint.create(m.getId() + 1);
		c.addComparator(InfoComparator.COMPARATOR_NOT_EQUALS, 1);
		m.addConstraint(InfoMatchTarget.MATCH_S, c);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(3));
		correctMatches.add(infoStore.get(4));
		Assert.assertTrue("Should match some info", Matcher.match(m, infoStore).equals(correctMatches));
	}

	@Test
	public void shouldFindMatchingInfo11() {
		InfoStore infoStore = InfoStore.create();
		List<Info> infos = InfoFixture.createInfo();
		infoStore.addAll(infos);
		MatchRule m0 = MatchRule.create(10);		
		Constraint c0= Constraint.create(m0.getId() + 1);
		c0.addComparator(InfoComparator.COMPARATOR_EQUALS, 1);
		m0.addConstraint(InfoMatchTarget.MATCH_S, c0);
		infoStore.addAll(m0.getInfo());
		MatchRule m1 = MatchRule.create(c0.getId()+1);
		Constraint c1= Constraint.create(m1.getId() + 1);
		c1.addComparator(InfoComparator.COMPARATOR_EQUALS, InfoCompareTarget.COMPARE_WITH_O_FROM, m0.getId());
		m1.addConstraint(InfoMatchTarget.MATCH_S, c1);
		List<Info> correctMatches = new ArrayList<Info>();
		correctMatches.add(infoStore.get(3));
		List<Info> matches = Matcher.match(m1, infoStore);
		Assert.assertTrue("Should match some info", matches.equals(correctMatches));
	}

	@Test
	public void shouldReturnIndexesForInfo() {
		InfoStore infoStore = InfoStore.create();
		infoStore.add(Info.create(1, 2, 3));
		infoStore.add(Info.create(1, 2, 4));
		infoStore.add(Info.create(1, 4, 5));
		infoStore.add(Info.create(3, 4, 5));
		infoStore.add(Info.create(6, 2, 5));
		
		Info testInfo = Info.create(1, 10, 11);
		List<Integer> result = new ArrayList<Integer>();
		result.add(0);
		result.add(1);
		result.add(2);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject);
		List<Integer> indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertEquals("Should find correct indexes for matching subject", result, indexes);

		testInfo = Info.create(1, 2, 10);
		result.clear();
		result.add(0);
		result.add(1);
		result.add(4);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchPredicate);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertEquals("Should find correct indexes for matching predicate", result, indexes);

		testInfo = Info.create(10, 2, 5);
		result.clear();
		result.add(2);
		result.add(3);
		result.add(4);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchObject);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertEquals("Should find correct indexes for matching object", result, indexes);

		testInfo = Info.create(1, 2, 10);
		result.clear();
		result.add(0);
		result.add(1);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertEquals("Should find correct indexes for matching subject & predicate", result, indexes);

		testInfo = Info.create(10, 4, 5);
		result.clear();
		result.add(2);
		result.add(3);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchPredicate, Matcher.MatchElement.MatchObject);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertEquals("Should find correct indexes for matching predicate & object", result, indexes);
		
		testInfo = Info.create(3, 4, 5);
		result.clear();
		result.add(3);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate, Matcher.MatchElement.MatchObject);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertEquals("Should find correct indexes for matching subject, predicate & object", result, indexes);
	}
	
	@Test
	public void shouldNotReturnIndexesForInfo() {
		InfoStore infoStore = InfoStore.create();
		infoStore.add(Info.create(1, 2, 3));
		infoStore.add(Info.create(1, 2, 4));
		infoStore.add(Info.create(1, 4, 5));
		infoStore.add(Info.create(3, 4, 5));
		infoStore.add(Info.create(6, 2, 5));
		
		Info testInfo = Info.create(10, 2, 3);
		EnumSet<Matcher.MatchElement> matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject);
		List<Integer> indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertTrue("Should not find indexes for matching subject", indexes.isEmpty());

		testInfo = Info.create(1, 10, 5);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchPredicate);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertTrue("Should not find indexes for matching predicate", indexes.isEmpty());

		testInfo = Info.create(1, 2, 10);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchObject);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertTrue("Should not find indexes for matching object", indexes.isEmpty());

		testInfo = Info.create(10, 11, 3);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertTrue("Should not find indexes for matching subject & predicate", 
				indexes.isEmpty());

		testInfo = Info.create(1, 10, 11);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchPredicate, Matcher.MatchElement.MatchObject);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertTrue("Should not find indexes for matching predicate & object", 
				indexes.isEmpty());
		
		testInfo = Info.create(10, 11, 12);
		matchOn = EnumSet.of(Matcher.MatchElement.MatchSubject, Matcher.MatchElement.MatchPredicate, Matcher.MatchElement.MatchObject);
		indexes = Matcher.indexesForInfo(testInfo, matchOn, infoStore);
		Assert.assertTrue("Should not find indexes for matching subject, predicate & object", 
				indexes.isEmpty());
	}

}
