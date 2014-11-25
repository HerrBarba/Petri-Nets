package analyzer.lexical;

import java.util.ArrayList;

public class GlobalLexer extends Lexer{

	private static final String regexOpenTag = "<";
	private static final String regexCloseTag = ">";
	private static final String regexOpenSlashTag = "</";
	private static final String regexCloseSlashTag = "/>";
	private static final String regexEquals = "=";
	private static final String regexAttr = "(name|id|color|time)";
	private static final String regexPetriNet = "(Coloreada|P-T|Multinivel|Temporizada|Continua|Fluidificada)";
	private static final String regexString = "\".*\"";
	private static final String regexTag = "(Place|Transition)";
	private static final String regexTab = "[(    )\t]*";
	private static final String regexWhitespace = "[ ]*";
	
	/*
	public void analyze() {
		lastMatchingTokens = new ArrayList<Token>();
		lexTokens = new ArrayList<LexToken>();
		tokens = new ArrayList<Token>();
		
		File file = FileManager.getFile();
		Scanner s = new Scanner(System.in);
		
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			try {
				file.createNewFile();
				s = new Scanner(file);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
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
			
			if (!lexeme.equals("$")) {
				tokens.add(Token.error);
				lexeme = lexeme.substring(0, lexeme.length() - 1);
				lexTokens.add(new LexToken(lexeme,Token.error));
			}
			
			lexTokens.add(new LexToken("\n", Token.line));
		}
		
		s.close();
	}
	*/
	protected ArrayList<Token> tokenize(String s) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		if (s.matches(regexOpenTag)) 
			tokens.add(Token.openTag);

		if (s.matches(regexCloseTag))
			tokens.add(Token.closeTag);

		if (s.matches(regexOpenSlashTag))
			tokens.add(Token.openSlashTag);	
		
		if (s.matches(regexCloseSlashTag))
			tokens.add(Token.closeSlashTag);			
		
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
}



