package analyzer.semantic.xml;


public class Transition extends Tag{		
	
	public String getFrom() {
		return get("from");
	}
	
	public String getTo() {
		return get("to");
	}	
	
	public boolean isValid() {		
		return getId() != null || getFrom() != null || getTo() != null;
	}	
}
