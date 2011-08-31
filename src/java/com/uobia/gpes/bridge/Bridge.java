package com.uobia.gpes.bridge;
import java.util.ArrayList;
import java.util.List;

import com.uobia.gpes.environment.Environment;
import com.uobia.gpes.event.Event;


public class Bridge {
	private List<EventTranslator> eventTranslators = new ArrayList<EventTranslator>();
	private List<BehaviorTranslator> behaviorTranslators = new ArrayList<BehaviorTranslator>();
	private List<ResourceManager> resourceManagers = new ArrayList<ResourceManager>();
	private final Environment environment;
	private int minInterval = 0;
	private long maxStepCount = 1000;
	private boolean stop = false;
	
	private Bridge(Environment e) {
		environment = e;
	}
	
	public static Bridge create(Environment e) {
		Bridge bridge = new Bridge(e);
		return bridge;
	}
	
	public Environment getEnvironment() {
		return environment;
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

	public List<EventTranslator> eventTranslators() {
		return eventTranslators;
	}
	
	public List<BehaviorTranslator> behaviorTranslators() {
		return behaviorTranslators;
	}
	
	public List<ResourceManager> resourceManagers() {
		return resourceManagers;
	}
	
	public void animate() {
		System.out.println("Starting animation...");
		stop = false;
		while (!stop) {
			System.out.println("Starting step: " + environment.stepCount + "...");

			//Get new events
			System.out.println("Getting new events...");
			for (int i = 0; i < eventTranslators.size(); i++) {
				EventTranslator eventTranslator = eventTranslators.get(i);
				List<Event> newEvents = eventTranslator.getEvents();
				environment.events.addAll(newEvents);
			}
			
			//Determine step cost
			System.out.println("Determining step cost...");
			int cost = 0;
			for (int i = 0; i < resourceManagers.size(); i++) {
				ResourceManager resourceManager = resourceManagers.get(i);
				cost += resourceManager.stepCost();
			}
			
			//Animate environment one step
			environment.step(cost);
			
			//Generate new behaviors
			System.out.println("Generating behaviors...");
			for (int i = 0; i < behaviorTranslators.size(); i++) {
				BehaviorTranslator behaviorTranslator = behaviorTranslators.get(i);
				behaviorTranslator.behave(environment.events);
			}
			
			//Stop if environment is dead or max step count is reached
			stop = (environment.isDead() || environment.stepCount == maxStepCount);
			if (stop) {
				System.out.println("Stopping.");
			}
			
			//TODO: Wait minInterval between steps
		}
	}
}
