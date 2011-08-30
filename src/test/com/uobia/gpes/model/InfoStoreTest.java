package com.uobia.gpes.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class InfoStoreTest {
	
    @Test
    public void shouldBeEqual() {
    	InfoStore infoStore1 = InfoStore.create();
    	infoStore1.add(Info.create(1, 2, 3));
    	InfoStore infoStore2 = InfoStore.create();
    	infoStore2.add(Info.create(1, 2, 3));
    	Assert.assertTrue("Should be equal", infoStore1.equals(infoStore2));
    }
    
    @Test
    public void shouldNotBeEqual() {
    	InfoStore infoStore1 = InfoStore.create();
    	infoStore1.add(Info.create(1, 2, 3));
    	InfoStore infoStore2 = InfoStore.create();
    	infoStore2.add(Info.create(4, 5, 6));
    	Assert.assertFalse("Should not be equal", infoStore1.equals(infoStore2));
    }
    
	@Test
	public void shouldAddInfo() {
		InfoStore infoStore = InfoStore.create();
		Info info = Info.create(1, 2, 3);
		int sizeBefore = infoStore.size();
		infoStore.add(info);
		int sizeDifference = infoStore.size() - sizeBefore;
		Assert.assertTrue("Size should increase by one", sizeDifference == 1);
		
		int lastIndex = infoStore.size() - 1;
		Assert.assertTrue("Info retrieved should be info stored", infoStore.get(lastIndex).equals(info));
	}
	
	@Test
	public void shouldNotAddInfo() {
		InfoStore infoStore = InfoStore.create();
		int maxSize = 1;
		infoStore.setMaxSize(maxSize);
		Info info1 = Info.create(1, 2, 3);
		for (int i = 0; i < maxSize; i++) {
			infoStore.add(info1);
		}
		Assert.assertFalse("Add should fail when maxSize is exceeded", infoStore.add(Info.create(3, 4, 5)));
		
		Assert.assertTrue("Info should be unaltered when maxSize is exceeded", infoStore.get(0).equals(info1));
	}
	
	@Test
	public void shouldRemoveInfo() {
		InfoStore infoStore = InfoStore.create();
		Info info = Info.create(1, 2, 3);
		infoStore.add(info);
		int sizeBefore = infoStore.size();
		infoStore.remove(0);
		int sizeDifference = sizeBefore - infoStore.size();
		Assert.assertTrue("Size should decrease by one", sizeDifference == 1);
	}
	
	@Test
	public void shouldNotRemoveInfo() {
		InfoStore infoStore = InfoStore.create();
		Assert.assertFalse("Remove should fail for invalid index (0 when empty)", infoStore.remove(0));
		
		infoStore.add(Info.create(1, 2, 3));
		Assert.assertFalse("Remove should fail for invalid index (> max index)", infoStore.remove(2));
		Assert.assertFalse("Remove should fail for invalid index (< 0)", infoStore.remove(-1));
	}

	@Test
	public void shouldReturnSize() {
		InfoStore infoStore = InfoStore.create();
		Info info = Info.create(1, 2, 3);
		int size = 25;
		for (int i = 0; i < size; i++) {
			infoStore.add(info);
		}
		Assert.assertTrue("Size should be " + size, infoStore.size() == size);
		
		infoStore.clear();
		Assert.assertTrue("Size should be zero", infoStore.size() == 0);
	}
	
	@Test
	public void shouldBeEmpty() {
		InfoStore infoStore = InfoStore.create();
		Assert.assertTrue("Should be empty on create", infoStore.isEmpty());
		
		infoStore.add(Info.create(1, 2, 3));
		infoStore.clear();
		Assert.assertTrue("Should be empty on clear", infoStore.isEmpty());
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
		List<Integer> indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchSubject));
		Assert.assertTrue("Should find correct indexes for matching subject", indexes.equals(result));

		testInfo = Info.create(1, 2, 10);
		result.clear();
		result.add(0);
		result.add(1);
		result.add(4);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchPredicate));
		Assert.assertTrue("Should find correct indexes for matching predicate", indexes.equals(result));

		testInfo = Info.create(10, 2, 5);
		result.clear();
		result.add(2);
		result.add(3);
		result.add(4);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchObject));
		Assert.assertTrue("Should find correct indexes for matching object", indexes.equals(result));

		testInfo = Info.create(1, 2, 10);
		result.clear();
		result.add(0);
		result.add(1);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate));
		Assert.assertTrue("Should find correct indexes for matching subject & predicate", 
				indexes.equals(result));

		testInfo = Info.create(10, 4, 5);
		result.clear();
		result.add(2);
		result.add(3);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchPredicate, InfoStore.MatchElement.MatchObject));
		Assert.assertTrue("Should find correct indexes for matching predicate & object", 
				indexes.equals(result));
		
		testInfo = Info.create(3, 4, 5);
		result.clear();
		result.add(3);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate, InfoStore.MatchElement.MatchObject));
		Assert.assertTrue("Should find correct indexes for matching subject, predicate & object", 
				indexes.equals(result));
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
		List<Integer> indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchSubject));
		Assert.assertTrue("Should not find indexes for matching subject", indexes.isEmpty());

		testInfo = Info.create(1, 10, 5);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchPredicate));
		Assert.assertTrue("Should not find indexes for matching predicate", indexes.isEmpty());

		testInfo = Info.create(1, 2, 10);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchObject));
		Assert.assertTrue("Should not find indexes for matching object", indexes.isEmpty());

		testInfo = Info.create(10, 11, 3);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate));
		Assert.assertTrue("Should not find indexes for matching subject & predicate", 
				indexes.isEmpty());

		testInfo = Info.create(1, 10, 11);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchPredicate, InfoStore.MatchElement.MatchObject));
		Assert.assertTrue("Should not find indexes for matching predicate & object", 
				indexes.isEmpty());
		
		testInfo = Info.create(10, 11, 12);
		indexes = infoStore.indexesForInfo(testInfo, 
				EnumSet.of(InfoStore.MatchElement.MatchSubject, InfoStore.MatchElement.MatchPredicate, InfoStore.MatchElement.MatchObject));
		Assert.assertTrue("Should not find indexes for matching subject, predicate & object", 
				indexes.isEmpty());
	}
	
	@Test 
	public void shouldMatch() {
		List<Info> matchRule = testMatchRule();
		InfoStore infoStore = InfoStore.create();
		infoStore.addAll(testMatchableInfo());
		List<Info> matchedInfo = infoStore.matchInfo(matchRule);
		Assert.assertTrue("Info should match based on match rule", matchedInfo.equals(testMatchableInfo()));
	}

	@Test 
	public void shouldNotMatch() {
		List<Info> matchRule = testMatchRule();
		InfoStore infoStore = InfoStore.create();
		infoStore.addAll(testUnMatchableInfo());
		List<Info> matchedInfo = infoStore.matchInfo(matchRule);
		Assert.assertFalse("Info should not match based on match rule", matchedInfo.equals(testMatchableInfo()));
	}

	private List<Info> testMatchableInfo() {
		List<Info> testData = new ArrayList<Info>();
		//TODO create test data
		return testData;
	}

	private List<Info> testUnMatchableInfo() {
		List<Info> testData = new ArrayList<Info>();
		//TODO create test data
		return testData;
	}

	private List<Info> testMatchRule() {
		//TODO: Define requirements for rule validation
		List<Info> matchRule = new ArrayList<Info>();
		matchRule.add(Info.create(1, 2, 3));
		matchRule.add(Info.create(3, 4, 5));
		return matchRule;
	}
}
