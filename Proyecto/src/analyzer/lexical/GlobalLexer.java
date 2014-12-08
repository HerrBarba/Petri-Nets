package analyzer.lexical;

import java.util.ArrayList;

public class GlobalLexer extends Lexer{

	private static final String regexType = "(int|float|boolean)";
	private static final String regexFunc = "func";
	private static final String regexClosePar = "\\)";
	private static final String regexOpenPar = "\\(";
	private static final String regexOpenCurly = "\\{";
	private static final String regexCloseCurly = "\\}";
	private static final String regexEquals = "=";
	private static final String regexSemi = ";";
	private static final String regexComma = ",";
	private static final String regexColon = ":";
	private static final String regexIf = "if";
	private static final String regexWhile = "while";
	private static final String regexFor = "for";
	private static final String regexSwitch = "switch";
	private static final String regexCase = "case";
	private static final String regexDefault = "default";
	private static final String regexBreak = "break";
	private static final String regexOpComp= "(==|\\|\\||\\&\\&|<=|>=|<|>)";
	private static final String regexSum= "\\+";	
	private static final String regexMinus= "-";
	private static final String regexMult= "(\\*)";
	private static final String regexNum= "[1-9]*[0-9](\\.)?[0-9]*";
	private static final String regexId= "[_a-zA-z]([_a-zA-Z0-9])*";
	private static final String regexWhitespace = "[ ]*";
	
	protected ArrayList<Token> tokenize(String s) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		if (s.matches(regexType)) 
			tokens.add(Token.type);

		if (s.matches(regexClosePar))
			tokens.add(Token.closePar);

		if (s.matches(regexOpenPar))
			tokens.add(Token.openPar);	
		
		if (s.matches(regexOpenCurly))
			tokens.add(Token.openCurly);
		
		if (s.matches(regexCloseCurly))
			tokens.add(Token.closeCurly);			

		if (s.matches(regexEquals))
			tokens.add(Token.equals);

		if (s.matches(regexSemi))
			tokens.add(Token.semi);
		
		if (s.matches(regexComma))
			tokens.add(Token.comma);
		
		if (s.matches(regexFunc))
			tokens.add(Token.func);
		
		if (s.matches(regexColon))
			tokens.add(Token.colon);

		if (s.matches(regexIf))
			tokens.add(Token.If);
		
		if (s.matches(regexWhile))
			tokens.add(Token.While);

		if (s.matches(regexFor))
			tokens.add(Token.For);

		if (s.matches(regexSwitch))
			tokens.add(Token.Switch);
		
		if (s.matches(regexCase))
			tokens.add(Token.Case);
		
		if (s.matches(regexBreak))
			tokens.add(Token.Break);		

		if (s.matches(regexDefault))
			tokens.add(Token.Default);
		
		if (s.matches(regexOpComp))
			tokens.add(Token.opComp);

		if (s.matches(regexSum))
			tokens.add(Token.opSum);
		
		if (s.matches(regexMinus))
			tokens.add(Token.opMinus);
		
		if (s.matches(regexMult))
			tokens.add(Token.opMult);

		if (s.matches(regexNum))
			tokens.add(Token.num);

		if (s.matches(regexId))
			tokens.add(Token.id);
		
		if (s.matches(regexWhitespace))
			tokens.add(Token.whitespace);
		
		return tokens;
	}
}



