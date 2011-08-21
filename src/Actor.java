import java.util.ArrayList;
import java.util.List;


public class Actor {
	private InfoStore store;
	
	
	//Actor functions
	public void sense(List<Event> events) {
		List<Info> sensorMatchRules = getSensorMatchRules();
		List<Info> sensorMatches = new ArrayList<Info>();
		for (int i = 0; i < events.size(); i++) {
			InfoStore eventStore = events.get(i).infoStore();
			List<Info> eventMatches = eventStore.matchInfo(sensorMatchRules);
			sensorMatches.addAll(eventMatches);
		}
		addSensorInfo(sensorMatches);
		//TODO: Finish implementation
	}
	
	public void induct() {
		//TODO: Finish implementation
	}
	
	public void deduct() {
		//TODO: Finish implementation
	}
	
	public List<Event> actuate() {
		List<Event> events = new ArrayList<Event>();
		//TODO: Finish implementation
		return events;
	}
	
	public Actor reproduce(Event event)
	{
		Actor child = new Actor();
		//TODO: Finish implementation
		return child;
	}
	
	public boolean isDead(){
		//TODO: Finish implementation
		return false;
	}
	
	public void extractCost(int cost) {
		//TODO: Finish implementation
	}
	
	//Convenience functions
	public InfoStore infoStore() {
		return store;
	}
	
	private List<Info> getSensorMatchRules() {
		List<Info> rules = new ArrayList<Info>();
		//TODO: Finish implementation
		return rules;
	}
	
	private List<Info> getInductorMatchRules() {
		List<Info> rules = new ArrayList<Info>();
		//TODO: Finish implementation
		return rules;
	}
	
	private List<Info> getDeductorMatchRules() {
		List<Info> rules = new ArrayList<Info>();
		//TODO: Finish implementation
		return rules;
	}
	
	private List<Info> getActuatorMatchRules() {
		List<Info> rules = new ArrayList<Info>();
		//TODO: Finish implementation
		return rules;
	}
	
	private boolean addSensorInfo(List<Info> sensorInfo) {
		//TODO: Finish implementation
		return true;
	}

	private boolean addInductorInfo(List<Info> sensorInfo) {
		//TODO: Finish implementation
		return true;
	}

	private boolean addDeductorInfo(List<Info> sensorInfo) {
		//TODO: Finish implementation
		return true;
	}
}
