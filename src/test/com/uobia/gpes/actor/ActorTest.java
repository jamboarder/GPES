package com.uobia.gpes.actor;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoStore;

public class ActorTest {
	@Test
	public void shouldReturnInfoStore() {
		InfoStore infoStore = InfoStore.create();
		Info info = Info.create(1, 2, 3);
		infoStore.add(info);
		Actor actor = Actor.create();
		actor.setInfoStore(infoStore);
		Assert.assertTrue("Should return infoStore", actor.infoStore().get(0).equals(info));
		
	}
	
	@Test
	public void shouldHaveEmptyInfoStoreOnCreate() {
		Actor actor = Actor.create();
		Assert.assertTrue("Newly created event infostore should be empty", actor.infoStore().isEmpty());
	}
	
	@Test
	public void shouldReturnId() {
		int id = 453453;
		Actor actor = Actor.create(id);
		Assert.assertTrue("Actor id should be set correctly", actor.getId() == id);
	}
	
	@Test
	public void shouldReturnRules() {
		Actor actor = Actor.create();
		//TODO: add rules of all types to actor's infoStore
		actor.getActuatorRules();
		//TODO: Assert on correct rule type and rule contents returned
		actor.getSensorRules();
		//TODO: Assert on correct rule type and rule contents returned
		actor.getInductorRules();
		//TODO: Assert on correct rule type and rule contents returned
		actor.getDeductorRules();
		//TODO: Assert on correct rule type and rule contents returned
	}
	
	@Test
	public void shouldBeEqual() {
		Actor actor1 = Actor.create();
		actor1.infoStore().add(Info.create(1, 2, 3));
		Actor actor2 = Actor.create();
		actor2.infoStore().add(Info.create(1, 2, 3));
		Assert.assertTrue("Should be equal", actor1.equals(actor2));
	}
	
	@Test
	public void shouldNotBeEqual() {
		Actor actor1 = Actor.create();
		actor1.infoStore().add(Info.create(1, 2, 3));
		Actor actor2 = Actor.create();
		actor2.infoStore().add(Info.create(3, 4, 5));
		Assert.assertFalse("Should not be equal", actor1.equals(actor2));
	}
}
