package analyzer.lexical;

public class LexToken {
	String lexeme;
	Token token;
	
	public LexToken(String l, Token t) {
		lexeme = l;
		token = t;
	}
	
	public String getLexeme() {
		return lexeme;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public String toString() {
		//return "Token: " + token + ", Lexeme: " + lexeme + "\n";
		return lexeme;
	}
}
