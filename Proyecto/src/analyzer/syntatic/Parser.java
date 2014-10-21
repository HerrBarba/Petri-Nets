package analyzer.syntatic;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import analyzer.lexical.Token;

public class Parser {

	private static Stack<String> stack = new Stack<String>();
	private static HashMap<String, Production> llTable = new HashMap<String, Production>();
	private Parser() {}
	
	private static void createTable() {
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
	
	private static class Production {
		private String leftHandSide;
		private String[] rightHandSide;
		
		public Production(String leftHandSide, String[] rightHandSide) {
			this.leftHandSide = leftHandSide;
			this.rightHandSide = rightHandSide;
		}
		
		@SuppressWarnings("unused")
		private String getLeftHandSide() {
			return leftHandSide;
		}
		
		private String[] getRightHandSide() {
			return rightHandSide;
		}
	}
	
	static {
		createTable();
	}
	
	public static boolean parse(List<Token> tokens) {
		if (tokens == null) return false;
		
		stack.add("money");
		stack.add("S");
		tokens.add(Token.money);
		//String coincidences = "";
		
		
		while (!tokens.isEmpty()) {
			//System.out.println("Input: " + tokens);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");
			//System.out.println("Stack: " + stack);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");
			//System.out.println("Coincidences" + coincidences);
			
			if (stack.peek().equals(tokens.get(0).toString())) {
				//coincidences += 
				stack.pop();
				tokens.remove(0);
			} else {
				Production p = llTable.get(stack.pop() + tokens.get(0));				
				if (p == null) return false;
				
				String[] rhs = p.getRightHandSide();
				
				if (rhs[0].equals("&")) continue;
				for (int i = rhs.length - 1; i >= 0; i--) {
					stack.push(rhs[i]);
				}
			}
		}
		
		return true;
	}
}
