package analyzer.semantic.xml;

import java.util.HashMap;

public class TransitionTable {
	private HashMap<String, Transition> 
			transitionTable = new HashMap<String, Transition>();
	
	public boolean insert(Transition transition) {
		if (transitionTable.containsKey(transition.getId()))
			return false;
		transitionTable.put(transition.getId(), transition);
		return true;
	}
	
	public Transition get(String id) {
		return transitionTable.get(id);
	}
}
