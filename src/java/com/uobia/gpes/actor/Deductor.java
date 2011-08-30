package com.uobia.gpes.actor;

import java.util.List;

import com.uobia.gpes.model.Info;

public class Deductor {

	public static List<Integer> find(Actor actor, Rule deductorRule) {
		List<Info> matchRule = deductorRule.getMatchRule();
		List<Integer> matchedIndexes = actor.infoStore().matchIndexes(matchRule);
		return matchedIndexes;
	}

	public static void findAndAct(Actor actor) {
		List<Rule> deductorRules = actor.getInductorRules();
		for (int i = 0; i < deductorRules.size(); i++) {
			List<Integer> matchedIndexes = find(actor, deductorRules.get(i));
			List<Info> actionRule = deductorRules.get(i).getActionRule();
			addInfo(actor, actionRule, matchedIndexes);
			modifyInfo(actor, actionRule, matchedIndexes);
			removeInfo(actor, actionRule, matchedIndexes);
		}
	}

	private static void addInfo(Actor actor, List<Info> actionRule,
			List<Integer> matchedIndexes) {
		// TODO Auto-generated method stub
		
	}

	private static void modifyInfo(Actor actor, List<Info> actionRule,
			List<Integer> matchedIndexes) {
		// TODO Auto-generated method stub
		
	}

	private static void removeInfo(Actor actor, List<Info> actionRule,
			List<Integer> matchedIndexes) {
		// TODO Auto-generated method stub
		
	}
}
