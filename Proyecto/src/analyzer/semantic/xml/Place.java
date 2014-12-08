package analyzer.semantic.xml;


public class Place extends Tag {
	
	public int getMarks() {
		String s = get("marks");
		if (s == null) return 0;
		return Integer.parseInt(s);
	}
	
	public boolean isValid() {
		return getId() != null;
	}	
}
