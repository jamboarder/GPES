package com.uobia.gpes.actor;

import org.junit.Assert;
import org.junit.Test;


public class ActorStateCheckerTest {
	@Test
	public void shouldBeDead() {
		Actor actor = Actor.create();
		Assert.assertTrue("Empty actor should be dead", ActorStateChecker.isDead(actor));
		
		//TODO Test all other death state conditions
	}
	
	@Test
	public void shouldNotBeDead() {
		//TODO Test non-death state conditions
	}
	
	@Test
	public void shouldHaveActuatorRules() {
		Actor actor = Actor.create();
		Assert.assertFalse("Empty actor has no actuator rules", ActorStateChecker.hasActuatorRule(actor));
		
		actor.addRule(ActorRuleFixture.createValidRule(ActorRuleType.TYPE_ACTUATOR_RULE));
		Assert.assertTrue("Should have actuator rules", ActorStateChecker.hasActuatorRule(actor));
	}
	
	@Test
	public void shouldHaveSensorRules() {
		Actor actor = Actor.create();
		Assert.assertFalse("Empty actor has no sensor rules", ActorStateChecker.hasSensorRule(actor));
		
		actor.addRule(ActorRuleFixture.createValidRule(ActorRuleType.TYPE_SENSOR_RULE));
		Assert.assertTrue("Should have actuator rules", ActorStateChecker.hasSensorRule(actor));
	}
	
	@Test
	public void shouldHaveInductorRules() {
		Actor actor = Actor.create();
		Assert.assertFalse("Empty actor has no actuator rules", ActorStateChecker.hasInductorRule(actor));
		
		actor.addRule(ActorRuleFixture.createValidRule(ActorRuleType.TYPE_INDUCTOR_RULE));
		Assert.assertTrue("Should have actuator rules", ActorStateChecker.hasInductorRule(actor));
	}
	
	@Test
	public void shouldHaveDeductorRules() {
		Actor actor = Actor.create();
		Assert.assertFalse("Empty actor has no actuator rules", ActorStateChecker.hasDeductorRule(actor));
		
		actor.addRule(ActorRuleFixture.createValidRule(ActorRuleType.TYPE_DEDUCTOR_RULE));
		Assert.assertTrue("Should have actuator rules", ActorStateChecker.hasDeductorRule(actor));
	}
}
