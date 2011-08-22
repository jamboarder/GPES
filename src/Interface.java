import java.util.ArrayList;
import java.util.List;


public class Interface {
	public List<EventTranslator> eventTranslators = new ArrayList<EventTranslator>();
	public List<BehaviorTranslator> behaviorTranslators = new ArrayList<BehaviorTranslator>();
	public List<ResourceManager> resourceManagers = new ArrayList<ResourceManager>();
	public Environment environment = new Environment();
	public long minInterval = 0;
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
		stop = false;
		while (!stop) {
			//Get new events
			for (int i = 0; i < eventTranslators.size(); i++) {
				EventTranslator eventTranslator = eventTranslators.get(i);
				List<Event> newEvents = eventTranslator.getEvents();
				environment.events.addAll(newEvents);
			}
			
			//Determine step cost
			int cost = 0;
			for (int i = 0; i < resourceManagers.size(); i++) {
				ResourceManager resourceManager = resourceManagers.get(i);
				cost += resourceManager.stepCost();
			}
			
			//Animate environment one step
			environment.step(cost);
			
			//Generate new behaviors
			for (int i = 0; i < behaviorTranslators.size(); i++) {
				BehaviorTranslator behaviorTranslator = behaviorTranslators.get(i);
				behaviorTranslator.behave(environment.events);
			}
			
			//Stop if environment is dead
			stop = environment.isDead();
			
			//Wait between steps
			try {
				this.wait(minInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
