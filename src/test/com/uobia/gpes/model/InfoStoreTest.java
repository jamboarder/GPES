package com.uobia.gpes.model;

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
		Assert.assertFalse("Should not contain info", infoStore.getInfo().contains(info));
		infoStore.add(info);
		Assert.assertTrue("Info retrieved should be info stored", infoStore.getInfo().contains(info));
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
	public void shouldMatch() {
	}

	@Test 
	public void shouldNotMatch() {
	}
	
}
