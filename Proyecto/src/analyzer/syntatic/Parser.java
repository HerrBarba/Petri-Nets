package analyzer.syntatic;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import analyzer.lexical.Token;

public class Parser {

	private static Stack<String> stack = new Stack<String>();
	private static HashMap<String, Production> llTable = new HashMap<String, Production>();
	private Parser() {}
	/*void createTable() {
		llTable.put("Eid", new Production("E", new String[] {"T", "E'"}));
		llTable.put("Tid", new Production("T", new String[] {"F", "T'"}));
		llTable.put("Fid", new Production("F", new String[] {"id"}));
		llTable.put("E'+", new Production("E'", new String[] {"+", "T", "E'"}));
		llTable.put("T'+", new Production("T'", new String[] {"&"}));
		llTable.put("T'*", new Production("T'", new String[] {"*", "F", "T'"}));
		llTable.put("E(", new Production("E", new String[] {"T", "E'"}));
		llTable.put("T(", new Production("T", new String[] {"F", "T'"}));
		llTable.put("F(", new Production("F", new String[] {"(", "E", ")"}));
		llTable.put("E')", new Production("E'", new String[] {"&"}));
		llTable.put("T')", new Production("T'", new String[] {"&"}));
		llTable.put("E'$", new Production("E'", new String[] {"&"}));
		llTable.put("T'$", new Production("T'", new String[] {"&"}));
	}*/
	
	private static void createTable() {
		llTable.put("SopenTag", new Production("S", new String[] {"S'", "S"}));
		llTable.put("S'openTag", new Production("S'", new String[] {"openTag", "tag", "A", "slash", "closeTag"}));
		llTable.put("Aslash", new Production("A", new String[] {"&"}));		
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
		
		private String getLeftHandSide() {
			return leftHandSide;
		}
		
		private String[] getRightHandSide() {
			return rightHandSide;
		}
	}

	private static List<Token> checkInitialconditions(List<Token> tokens) {
		String open = "";
		String close = "";
		
		for (int i = 0; i < 6; i++)
			open += tokens.remove(0);
		
		for (int i = 0; i < 4; i++)
			close += tokens.remove(tokens.size() - 4 + i);
		
		if (open.equals("openTagpetriNetattrequalsstringcloseTag") &&
				close.equals("openTagslashpetriNetcloseTag"))
			return tokens;
		return null;
	}	
	
	static {
		createTable();
	}
	
	public static boolean parse(List<Token> tokens) {
		tokens = checkInitialconditions(tokens);
		//System.out.println(tokens);
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
