package analyzer.semantic;

public class Attribute {
	private String name;
	private String value;
	private String scope;
	
	public Attribute() {}
	
	public Attribute(String scope) {
		this.scope = scope;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public boolean setValue(String value) {
		if (name.equals("marks"))
			try {
				Integer.parseInt(value);
			} catch (Exception e) {
				return false;
			}
		
		this.value = value;
		return true;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getScope() {
		return scope;
	}
	
	public boolean checkScope() {
		switch(name) {
			case "name":
				return true;
			case "id":
				return true;
			case "color":
				return scope.equals("Coloreada");
			case "time":
				return scope.equals("Temporizada");
			case "marks":
				return scope.equals("Place");		
			case "from":
			case "to":
				return scope.equals("Transition");
			default:
				return false;
		}
	}
	
	public String toString() {
		return scope + " " + name + "=" + value;		
	}
}
