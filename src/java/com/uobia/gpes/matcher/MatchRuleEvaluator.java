package com.uobia.gpes.matcher;

import java.util.List;

import com.uobia.gpes.model.Info;

public interface MatchRuleEvaluator {
	void eval(List<Info> in, List<Info> out);
}
