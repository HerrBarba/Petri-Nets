package analyzer.semantic.xml;

public class PetriNet extends Tag {
	private String typeOfNet;
	
	public PetriNet(String type) {
		typeOfNet = type;
	}
	
	public String getColor() {
		return get("color");
	}
	
	public int getTime() {
		String s = get("time");
		if (s == null) return 0;
		return Integer.parseInt(s);
	}
	
	public boolean isValid() {
		if (getId() == null) return false;
		
		switch(typeOfNet) {
			case "Coloreada":
				return getColor() != null;
			case "Temporizada":
				return getTime() != 0;			
		}
		
		return getId() != null;
	}	
}
