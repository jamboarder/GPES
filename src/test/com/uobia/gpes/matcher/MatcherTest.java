package com.uobia.gpes.matcher;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.uobia.gpes.model.Info;
import com.uobia.gpes.model.InfoFixture;
import com.uobia.gpes.model.MatchRule;
import com.uobia.gpes.model.MatchRuleFixture;

public class MatcherTest {

	/**
	 * Example sensor rule
	 * s1, IS_TYPE, TYPE_SENSOR_RULE
	 * s1, HAS_PART, m1
	 * m1, IS_TYPE, TYPE_MATCH_RULE
	 * m1, MATCH_S, c1
	 * c1, COMPARE_USING, COMPARE_GREATER_THAN
	 * c1, COMPARE_WITH_FIXED, 4
	 * s1, HAS_PART, a1
	 * a1, IS_TYPE, TYPE_ACTION_RULE
	 * ...
	 */
	
	@Test
	public void shouldFindMatchingInfo() {
		List<Info> infos = InfoFixture.createInfo();
		MatchRule m = MatchRuleFixture.createNoMatch(infos);
		
		List<Info> results = Matcher.match(m, infos);
		Assert.assertEquals("Should return nothing", 0, results.size());
	}
	
}
