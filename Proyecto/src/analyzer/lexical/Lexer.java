package analyzer.lexical;

import java.util.ArrayList;

public abstract class Lexer {
	private ArrayList<Token> lastMatchingTokens;
	private ArrayList<LexToken> lexTokens;
	private ArrayList<Token> tokens;
	private boolean success = true;
	
	public void analyze(String code) {
		lastMatchingTokens = new ArrayList<Token>();
		lexTokens = new ArrayList<LexToken>();
		tokens = new ArrayList<Token>();
		
		String[] lines = code.split("\n");
		
		for(String line: lines) {
			line += '$';
			String lexeme = "";
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				Token token = matches(lexeme + c);
				
				if (token != null) {
					lexTokens.add(new LexToken(lexeme, token));
					tokens.add(token);
					
					lexeme = "";
					i--;
				} else 
					lexeme += c;
			}
			
			if (!lexeme.equals("$")) {
				tokens.add(Token.error);
				lexeme = lexeme.substring(0, lexeme.length() - 1);
				lexTokens.add(new LexToken(lexeme,Token.error));
				success = false;
			}

			lexTokens.add(new LexToken("\n", Token.line));
			tokens.add(Token.line);
		}
	}
	
	private Token matches(String s) {
		ArrayList<Token> tmp = tokenize(s);
		Token token = null;
		
		if (!lastMatchingTokens.isEmpty() && tmp.isEmpty()) {
			token = lastMatchingTokens.get(0);
			lastMatchingTokens.clear();
		} else
			lastMatchingTokens = tmp;
		
		return token;
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public ArrayList<LexToken> getLexTokens() {
		return lexTokens;
	}	
	
	public boolean hadSuccess() {
		return success;
	}
	
	public void setErrorToken(int index) {
		String oldLexeme = lexTokens.remove(index).getLexeme();
		lexTokens.add(index, new LexToken(oldLexeme, Token.error));
		tokens.remove(index);
		tokens.add(index, Token.error);
		success = false;
	}
	
	public void setMissingToken(Token token, int index) {
		String lexeme = tokenToString(token);
		lexTokens.add(index, new LexToken(lexeme, Token.missing));
		tokens.add(index, Token.missing);
		success = false;
	}
	
	public String tokenToString(Token token) {
		switch(token) {
			case openTag:
				return "<";
			case closeTag:
				return ">";
			case openSlashTag:
				return "</";
			case closeSlashTag:
				return "/>";
			case equals:
				return "=";
			case attr:
				return "attr";
			case string:
				return "string";
			case petriNet:
				return "petri";
			case tag:
				return "tag";
			default:
				return "";
		}
	}
	
	protected abstract ArrayList<Token> tokenize(String s);
}



