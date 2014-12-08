package analyzer.syntatic;

import gui.Console;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import analyzer.lexical.Lexer;
import analyzer.lexical.Token;

public abstract class Parser {
	protected static Stack<String> stack = new Stack<String>();
	protected static Stack<String> tagStack = new Stack<String>();
	protected static HashMap<String, Production> llTable = new HashMap<String, Production>();
	protected abstract String[] getFirstOfS();
	protected abstract void createTable();
	private HashSet<String> tokenValues;
	
	protected abstract void nextProduction(List<Token> tokens, Lexer lexer);
	protected abstract void evaluateToken(List<Token> tokens, Lexer lexer);
	
	int line = 1;
	int token = 0;
	
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
		
		protected String getLeftHandSide() {
			return leftHandSide;
		}
		
		protected String[] getRightHandSide() {
			return rightHandSide;
		}
		
		@Override
		public String toString() {
			return leftHandSide + " = " + Arrays.toString(rightHandSide);
		}
	}
	
	public void parse(List<Token> tokens, Lexer lexer) {
		if (tokens == null) Console.println("No code found");
				
		stack.add("money");
		tokens.add(Token.money);
		//String coincidences = "";
		
		
		while (!tokens.isEmpty()) {
			System.out.println("Input: " + tokens);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");
			System.out.println("Stack: " + stack);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");
			//System.out.println("TagStack: " + tagStack);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");

			// Reset stack
			if (resetStack(tokens, lexer)) continue;
			
			switch (tokens.get(0)) {
				case line:
					line++;
				case whitespace:
				case tab:
				case error:
					tokens.remove(0);
					token++;
					continue;
				default:
					break;
			}
						
			if (isToken(stack.peek())) {
				evaluateToken(tokens, lexer);
			} else {
				nextProduction(tokens, lexer);
			}
		}
	}
	
	protected boolean resetStack(List<Token> tokens, Lexer lexer) {
		if (stack.peek().equals("money") && tokens.get(0) != Token.money) {
			stack.add("S");
			while (!isInFirstOfS(tokens.get(0))) {
				Token t = tokens.remove(0);
				if (t == Token.line) line++;
				if (t != Token.whitespace && t != Token.tab
						&& t != Token.line)
					Console.println("Line " + line + ": \"" + 
						lexer.tokenToString(t) + "\" was not expected.");
				
				lexer.setErrorToken(token++);
			}
			return true;
		}		
		return false;
	}
	
	protected boolean isInFirstOfS(Token token) {
		String[] firstOfS = getFirstOfS();
		if (token == Token.money) return true;
		
		for (String s: firstOfS)
			if (s.equals(token.toString())) return true;

		return false;
	}
		
	protected boolean isToken(String s) {
		if (tokenValues != null)
			return tokenValues.contains(s);
		
		tokenValues = new HashSet<String>();
		
		for (Token t: Token.values()) {
			tokenValues.add(t.toString());
		}

		return tokenValues.contains(s);
	}
}
