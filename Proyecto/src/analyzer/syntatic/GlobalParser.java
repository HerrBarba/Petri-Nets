package analyzer.syntatic;

public class GlobalParser extends Parser {
	protected final static String[] firstOfS = {};
	
	protected String[] getFirstOfS() {
		return firstOfS;
	}
	
	protected void createTable() {
		llTable.put("SopenTag", new Production("S", new String[] {"S'", "S"}));
		llTable.put("S'openTag", new Production("S'", new String[] {"openTag", "T", "A", "E"}));
		llTable.put("EcloseTag", new Production("E", new String[] {"closeTag", "S", "openSlashTag", "T", "closeTag"}));
		llTable.put("AcloseTag", new Production("A", new String[] {"&"}));
		llTable.put("SopenSlashTag", new Production("S", new String[] {"&"}));		
		llTable.put("EcloseSlashTag", new Production("E", new String[] {"closeSlashTag"}));		
		llTable.put("AcloseSlashTag", new Production("A", new String[] {"&"}));		
		llTable.put("TpetriNet", new Production("T", new String[] {"petriNet"}));		
		llTable.put("Ttag", new Production("T", new String[] {"tag"}));		
		llTable.put("Aattr", new Production("A", new String[] {"attr", "equals", "string", "A"}));
		llTable.put("Smoney", new Production("S", new String[] {"&"}));
	}
	
}
