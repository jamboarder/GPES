package com.uobia.gpes.general;

import java.util.List;

import com.uobia.gpes.bridge.BehaviorTranslator;
import com.uobia.gpes.bridge.Bridge;
import com.uobia.gpes.bridge.EventTranslator;
import com.uobia.gpes.environment.Environment;
import com.uobia.gpes.eventspace.EventSpace;

public class Animator {
	private int step;
	private List<Environment> environments;
	private EventSpace eventSpace;
	private Bridge bridge;
	private boolean stop;
	private long maxStepCount;
	private int minInterval;
	
	private Animator() {
		step = 0;
		stop = false;
		maxStepCount = 1000;
		minInterval = 0;
	}

	public static final Animator create(List<Environment> environments, EventSpace eventSpace, Bridge bridge) {
		Animator animator = new Animator();
		animator.setEnvironments(environments);
		animator.setEventSpace(eventSpace);
		animator.setBridge(bridge);
		return animator;
	}
	
	public int getStep() {
		return step;
	}

	public List<Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}

	public EventSpace getEventSpace() {
		return eventSpace;
	}

	public void setEventSpace(EventSpace eventSpace) {
		this.eventSpace = eventSpace;
	}

	public Bridge getBridge() {
		return bridge;
	}

	public void setBridge(Bridge bridge) {
		this.bridge = bridge;
	}

	public int getMinInterval() {
		return minInterval;
	}

	public void setMinInterval(int minInterval) {
		this.minInterval = minInterval;
	}

	public long getMaxStepCount() {
		return maxStepCount;
	}

	public void setMaxStepCount(long maxStepCount) {
		this.maxStepCount = maxStepCount;
	}

	public void animate() {
		System.out.println("Starting animation...");
		stop = false;
		while (!stop) {
			System.out.println("Starting step: " + step + "...");

			//Get new events
			System.out.println("Getting new events...");
			for (EventTranslator eventTranslator: bridge.getEventTranslators()) {
				eventSpace.addEvents(eventTranslator.getEvents());
			}
			
			//Animate environment one step
			System.out.println("Stepping environments...");
			for (Environment environment: environments) {
				environment.step();
			}
			
			//Generate new behaviors
			System.out.println("Generating behaviors...");
			for (BehaviorTranslator behaviorTranslator: bridge.getBehaviorTranslators()) {
				behaviorTranslator.behave(eventSpace.getEvents());
			}
			
			//Stop if environments are dead or max step count is reached
			boolean isDead = true;
			for (Environment environment: environments) {
				if (!environment.isDead()) {
					isDead = false;
					break;
				}
			}
			
			stop = (isDead || step == maxStepCount);
			if (stop) {
				System.out.println("Stopping.");
			}
			
			//TODO: Wait minInterval between steps
		}
	}

}
