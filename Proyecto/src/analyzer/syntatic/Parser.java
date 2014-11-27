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
	private static Stack<String> stack = new Stack<String>();
	protected static HashMap<String, Production> llTable = new HashMap<String, Production>();
	protected abstract String[] getFirstOfS();
	protected abstract void createTable();
	private HashSet<String> tokenValues;
	
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
		
		private String getLeftHandSide() {
			return leftHandSide;
		}
		
		private String[] getRightHandSide() {
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
		
		int line = 1;
		int token = 0;
		
		while (!tokens.isEmpty()) {
			System.out.println("Input: " + tokens);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");
			System.out.println("Stack: " + stack);//.toString().replaceAll("[\\[\\], ]", "") + "\t\t");
			//System.out.println("Coincidences" + coincidences);
			
			// Reset stack
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
				continue;
			}
			
			if (tokens.get(0) == Token.whitespace || tokens.get(0) == Token.tab 
					|| tokens.get(0) == Token.error) {
				tokens.remove(0);
				token++; 
				continue;
			}			
			
			if (tokens.get(0) == Token.line) {
				tokens.remove(0);
				line++;
				token++;
				continue;
			}
			
			
			if (isToken(stack.peek())) {
				String s = stack.pop();
				if (s.equals(tokens.get(0).toString())) {
					tokens.remove(0);
					token++;
				} else {
					Console.println("Line " + line + ": Missing \"" + 
							lexer.tokenToString(Token.valueOf(s)) + "\".");
					lexer.setMissingToken(Token.valueOf(s), token++);
				}
			} else {
				Production p = llTable.get(stack.peek() + tokens.get(0));
				if (p == null) {
					Console.println("Line " + line + ": \"" +
							lexer.tokenToString(tokens.remove(0)) + "\" was not expected.");
					lexer.setErrorToken(token++);
					continue;
				}
				stack.pop();								

				String lhs = p.getLeftHandSide();
				if (lhs.equals("sync"))	continue;
				
				String[] rhs = p.getRightHandSide();
				if (rhs[0].equals("&")) continue;

				for (int i = rhs.length - 1; i >= 0; i--)
					stack.push(rhs[i]);
			}
		}
	}
	
	private boolean isInFirstOfS(Token token) {
		String[] firstOfS = getFirstOfS();
		if (token == Token.money) return true;
		
		for (String s: firstOfS)
			if (s.equals(token.toString())) return true;

		return false;
	}
	
	public boolean isToken(String s) {
		if (tokenValues != null)
			return tokenValues.contains(s);
		
		tokenValues = new HashSet<String>();
		
		for (Token t: Token.values()) {
			tokenValues.add(t.toString());
		}

		return tokenValues.contains(s);
	}
}
