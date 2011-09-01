package com.uobia.gpes.actor;

import java.util.List;

import com.uobia.gpes.model.MatchRule;

public class ActorStateChecker {

	public static boolean isDead(Actor actor) {
		return (deathConditionA(actor) || 
				deathConditionB(actor));
	}

	public static boolean hasActuatorRule(Actor actor) {
		return !actor.getActuatorRules().isEmpty();
	}

	public static boolean hasSensorRule(Actor actor) {
		return !actor.getSensorRules().isEmpty();
	}

	public static boolean hasInductorRule(Actor actor) {
		return !actor.getInductorRules().isEmpty();
	}

	public static boolean hasDeductorRule(Actor actor) {
		return !actor.getDeductorRules().isEmpty();
	}
	
	private static boolean deathConditionA(Actor actor) {
		return (!hasActuatorRule(actor));
	}
	
	private static boolean deathConditionB(Actor actor) {
		return (deathConditionB1(actor) && 
				deathConditionB2(actor));
	}

	private static boolean deathConditionB1(Actor actor) {
		List<ActorRule> actuatorRules = actor.getActuatorRules();
		for (int i = 0; i < actuatorRules.size(); i++) {
			List<MatchRule> matchRule = actuatorRules.get(i).getMatchRules();
			if (!actor.infoStore().matchIndexes(matchRule).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private static boolean deathConditionB2(Actor actor) {
		return !(hasSensorRule(actor) ||
				 hasInductorRule(actor) ||
				 hasDeductorRule(actor));
	}
}
