import java.util.ArrayList;
import java.util.List;


public class Actor {
	private InfoStore store;
	
	
	//Actor functions
	public void sense(List<Event> events) {
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
}
