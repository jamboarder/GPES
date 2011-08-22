import java.util.ArrayList;
import java.util.List;


public class Environment {
	public List<Actor> actors = new ArrayList<Actor>();
	public List<Event> events = new ArrayList<Event>();
	public int maxSize;
	public int stepCount = 0;
	
	public void clear() {
		actors.clear();
		events.clear();
	}
	
	public boolean isDead() {
		if (actors.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Event> step(int stepCost) {
		List<Event> allCreatedEvents = new ArrayList<Event>();

		//Extract cost to execute this step
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).extractCost(stepCost);
		}

		//Actor death check
		for (int i = 0; i < actors.size(); i++) {
			if (actors.get(i).isDead()) {
				actors.remove(i);
			}
		}

		//Event expiration check
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).isExpired()) {
				events.remove(i);
			}
		}
		
		//Return if there are no actors
		if (actors.size() == 0) {
			return allCreatedEvents;
		}

		//Actor sensing
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).sense(events);
		}
		
		//Actor induction
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).induct();
		}
		
		//Actor deduction
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).deduct();
		}
		
		//Actor actuation
		for (int i = 0; i < actors.size(); i++) {
			List<Event> createdEvents = actors.get(i).actuate();
			allCreatedEvents.addAll(createdEvents);
			events.addAll(createdEvents);
		}
		
		//Actor reproduction
		for (int i = 0; i < allCreatedEvents.size(); i++) {
			Event createdEvent = allCreatedEvents.get(i);
			if (createdEvent.isReproductionEvent()) {
				Actor newActor = actors.get(i).reproduce(createdEvent);
				actors.add(newActor);
			}
		}

		stepCount++;
		
		return allCreatedEvents;
	}
}
