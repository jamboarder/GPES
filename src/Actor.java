import java.util.ArrayList;
import java.util.List;


public class Actor {
	private InfoStore store;
	
	//Actor functions
	public boolean isDead(){
		//TODO: Finish implementation
		return false;
	}
	
	public void extractCost(int cost) {
		//TODO: Finish implementation
	}
		
	public void sense(List<Event> events) {
		List<Rule> sensorRules = getSensorRules();
		for (int i = 0; i < events.size(); i++) {
			InfoStore eventStore = events.get(i).infoStore();
			for (int j = 0; j < sensorRules.size(); j++) {
				List<Info> sensorMatchRule = sensorRules.get(j).matchRule;
				List<Info> eventMatches = eventStore.matchInfo(sensorMatchRule);
				addSensorInfo(eventMatches);
			}
		}
	}
	
	public void induct() {
		List<Rule> inductorRules = getInductorRules();
		for (int i = 0; i < inductorRules.size(); i++) {
			List<Info> inductorMatchRule = inductorRules.get(i).matchRule; 
			List<Info> inductorModRule = inductorRules.get(i).actionRule; 
			List<Info> inductorMatches = store.matchInfo(inductorMatchRule);
			modifyInfo(inductorModRule, inductorMatches);
		}
	}

	public void deduct() {
		List<Rule> deductorRules = getDeductorRules();
		for (int i = 0; i < deductorRules.size(); i++) {
			List<Info> deductorMatchRule = deductorRules.get(i).matchRule; 
			List<Info> deductorModRule = deductorRules.get(i).actionRule;
			List<Info> deductorMatches = store.matchInfo(deductorMatchRule);
			modifyInfo(deductorModRule, deductorMatches);
		}
	}
	
	public List<Event> actuate() {
		List<Event> events = new ArrayList<Event>();
		List<Rule> actuatorRules = getActuatorRules();
		for (int i = 0; i < actuatorRules.size(); i++) {
			List<Info> actuatorMatchRule = actuatorRules.get(i).matchRule;
			List<Info> actuatorEventRule = actuatorRules.get(i).actionRule;
			List<Info> actuatorMatches = store.matchInfo(actuatorMatchRule);
			List<Event> newEvents = createEvents(actuatorEventRule, actuatorMatches);
			events.addAll(newEvents);
		}
		return events;
	}
	
	public Actor reproduce(Event event)
	{
		Actor child = new Actor();
		//TODO: Finish implementation
		return child;
	}
	
	//Convenience functions
	public InfoStore infoStore() {
		return store;
	}
	
	//Private functions	
	private List<Rule> getSensorRules() {
		List<Rule> rules = new ArrayList<Rule>();
		//TODO: Finish implementation
		return rules;
	}
	
	private List<Rule> getInductorRules() {
		List<Rule> rules = new ArrayList<Rule>();
		//TODO: Finish implementation
		return rules;
	}
	
	private List<Rule> getDeductorRules() {
		List<Rule> rules = new ArrayList<Rule>();
		//TODO: Finish implementation
		return rules;
	}
	

	private List<Rule> getActuatorRules() {
		List<Rule> rules = new ArrayList<Rule>();
		//TODO: Finish implementation
		return rules;
	}
	
	private boolean addSensorInfo(List<Info> sensorInfo) {
		//TODO: Finish implementation
		return true;
	}

	private void modifyInfo(List<Info> modRule,
			List<Info> sourceInfo) {
		//TODO: Finish implementation
	}
	
	private List<Event> createEvents(List<Info> eventRule, 
			List<Info> sourceInfo) {
		List<Event> events = new ArrayList<Event>();
		//TODO: Finish implementation
		return events;
	}
}
