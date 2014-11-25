package analyzer.syntatic;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import analyzer.lexical.Token;

public abstract class Parser {
	private static Stack<String> stack = new Stack<String>();
	protected static HashMap<String, Production> llTable = new HashMap<String, Production>();
	
	protected abstract void createTable();
	
	public Parser() {
		createTable();
	}	
	
	protected static class Production {
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
	
	public String parse(List<Token> tokens) {
		if (tokens == null) return "No text found";
		
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
				if (p == null)
					return tokens.get(0) + " was not expected";				
				
				String[] rhs = p.getRightHandSide();
				
				if (rhs[0].equals("&")) continue;
				
				for (int i = rhs.length - 1; i >= 0; i--)
					stack.push(rhs[i]);				
			}
		}
		
		return null;
	}
}
