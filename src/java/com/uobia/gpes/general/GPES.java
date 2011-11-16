package com.uobia.gpes.general;
import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.bridge.Bridge;
import com.uobia.gpes.environment.Environment;
import com.uobia.gpes.eventspace.EventSpace;


public class GPES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("This is the General Purpose Evolutionary System");
		Environment environment = Environment.create();
		List<Environment> environments = new ArrayList<Environment>();
		environments.add(environment);
		EventSpace eventSpace = EventSpace.create();
		Bridge bridge = Bridge.create();
		//TODO: Create application-specific translators
		//      and add to the bridge.
		Animator animator = Animator.create(environments, eventSpace, bridge);
		animator.setMinInterval(1000);
		animator.animate();
	}

}
