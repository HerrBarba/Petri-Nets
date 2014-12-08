package analyzer.semantic.xml;

import java.util.HashMap;

public class PlaceTable {
	private HashMap<String, Place> 
			placeTable = new HashMap<String, Place>();
	
	public boolean insert(Place place) {
		if (placeTable.containsKey(place.getId()))
			return false;
		placeTable.put(place.getId(), place);
		return true;
	}
	
	public Place get(String id) {
		return placeTable.get(id);
	}

}
