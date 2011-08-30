package com.uobia.gpes.actor;

import java.util.List;

import com.uobia.gpes.model.Info;

public class Inductor {

	public static List<Integer> find(Actor actor, Rule inductorRule) {
		List<Info> matchRule = inductorRule.getMatchRule();
		List<Integer> matchedIndexes = actor.infoStore().matchIndexes(matchRule);
		return matchedIndexes;
	}

	public static void findAndAct(Actor actor) {
		List<Rule> inductorRules = actor.getInductorRules();
		for (int i = 0; i < inductorRules.size(); i++) {
			List<Integer> matchedIndexes = find(actor, inductorRules.get(i));
			List<Info> actionRule = inductorRules.get(i).getActionRule();
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
