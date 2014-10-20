package analyzer.lexical;

import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {

	private static final String regexOpenTag = "<";
	private static final String regexCloseTag = ">";
	private static final String regexSlash = "/";
	private static final String regexEquals = "=";
	private static final String regexAttr = "(name|id)";
	private static final String regexPetriNet = "(Coloreada|P-T)";
	private static final String regexString = "\".*\"";
	private static final String regexTag = "(Place|Transition|Arch)";
	private static final String regexTab = "[(    )\t]*";
	private static final String regexWhitespace = "[ ]*";
	
	private static ArrayList<Token> lastMatchingTokens = new ArrayList<Token>();
	private static ArrayList<LexToken> lexTokens = new ArrayList<LexToken>();
	private static ArrayList<Token> tokens = new ArrayList<Token>();
	
	static {
		analyze();
	}
	
	private static void analyze() {
		/*Scanner s = null;
		
		try {
			s = new Scanner(InternalFile.getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		Scanner s = new Scanner(System.in);
		
		while (s.hasNextLine()) {
			String line = s.nextLine() + '$';
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
			
			if (!lexeme.equals("$"))
				tokens.add(Token.error);
			lexTokens.add(new LexToken("\n", Token.line));
		}
	}
	
	private static Token matches(String s) {
		ArrayList<Token> tmp = tokenize(s);
		Token token = null;
		
		if (!lastMatchingTokens.isEmpty() && tmp.isEmpty()) {
			token = lastMatchingTokens.get(0);
			lastMatchingTokens.clear();
		} else
			lastMatchingTokens = tmp;
		
		return token;
	}
	
	private static ArrayList<Token> tokenize(String s) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		if (s.matches(regexOpenTag)) 
			tokens.add(Token.openTag);

		if (s.matches(regexCloseTag))
			tokens.add(Token.closeTag);
		
		if (s.matches(regexSlash))
			tokens.add(Token.slash);		
		
		if (s.matches(regexEquals))
			tokens.add(Token.equals);
		
		if (s.matches(regexAttr))
			tokens.add(Token.attr);

		if (s.matches(regexPetriNet))
			tokens.add(Token.petriNet);
		
		if (s.matches(regexString))
			tokens.add(Token.string);
		
		if (s.matches(regexTag))
			tokens.add(Token.tag);

		if (s.matches(regexTab))
			tokens.add(Token.tab);
		
		if (s.matches(regexWhitespace))
			tokens.add(Token.whitespace);
		
		return tokens;
	}
	
	public static ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public static ArrayList<LexToken> getLextokens() {
		return lexTokens;
	}
	
	public static void main(String[] args) {
		System.out.println(getLextokens());
	}
}



