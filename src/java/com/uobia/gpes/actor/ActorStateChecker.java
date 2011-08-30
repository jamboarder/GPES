package com.uobia.gpes.actor;

public class ActorStateChecker {

	public static boolean isDead(Actor actor) {
		// TODO Finish implementation
		return actor.infoStore().isEmpty();
	}

	public static boolean hasActuatorRule(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean hasSensorRule(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean hasInductorRule(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean hasDeductorRule(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}
}
