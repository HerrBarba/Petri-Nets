package analyzer.lexical;

import java.util.ArrayList;

public class XMLLexer extends Lexer {

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



