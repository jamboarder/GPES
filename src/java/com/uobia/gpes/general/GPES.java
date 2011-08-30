package com.uobia.gpes.general;
import com.uobia.gpes.bridge.Bridge;
import com.uobia.gpes.environment.Environment;


public class GPES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("This is the General Purpose Evolutionary System");
		Environment environment = Environment.create();
		Bridge bridge = Bridge.create(environment);
		//TODO: Create application-specific translators and resource manager
		//      and add to the interface.
		bridge.minInterval = 1000;
		bridge.animate();
	}

}
