package analyzer.semantic.xml;

import java.util.HashMap;

import analyzer.semantic.Attribute;


public abstract class Tag {
	private HashMap<String, String> attributes = new HashMap<String, String>();
	protected abstract boolean isValid();
	
	public String getId() {
		return get("id");
	}
	
	public String getName() {
		String s = get("name");
		if (s == null) return get("id"); 
		return s;
	}
	
	public String getScope() {
		return get("scope");
	}
		
	protected String get(String attr) {
		return attributes.get(attr);
	}
		
	public void addAttribute(Attribute attr) {
		attributes.put(attr.getName(), attr.getValue());
	}
}
