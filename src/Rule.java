import java.util.ArrayList;
import java.util.List;


public class Rule {
	public List<Info> matchRule;
	public List<Info> actionRule;
	
	Rule() {
		matchRule = new ArrayList<Info>();
		actionRule = new ArrayList<Info>();
	}
}
