import java.util.ArrayList;
import java.util.List;


public class Interface {
	public List<EventTranslator> eventTranslators = new ArrayList<EventTranslator>();
	public List<BehaviorTranslator> behaviorTranslators = new ArrayList<BehaviorTranslator>();
	public List<ResourceManager> resourceManagers = new ArrayList<ResourceManager>();
	public Environment environment = new Environment();
	public long minInterval = 0;
	public long maxStepCount = 1000;
	public boolean stop = false;
	
	Interface(Environment e) {
		environment = e;
	}
	
	public void addEventTranslator(EventTranslator newEventTranslator) {
		eventTranslators.add(newEventTranslator);
	}
	
	public void addBehaviorTranslator(BehaviorTranslator newBehaviorTranslator) {
		behaviorTranslators.add(newBehaviorTranslator);
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
