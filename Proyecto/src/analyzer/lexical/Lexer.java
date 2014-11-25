package analyzer.lexical;

import java.util.ArrayList;

public abstract class Lexer {
	private ArrayList<Token> lastMatchingTokens = new ArrayList<Token>();
	private ArrayList<LexToken> lexTokens = new ArrayList<LexToken>();
	private ArrayList<Token> tokens = new ArrayList<Token>();
	
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
					if (token != Token.whitespace && token != Token.tab)
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
			}
			
			lexTokens.add(new LexToken("\n", Token.line));
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
	
	protected abstract ArrayList<Token> tokenize(String s);
}



