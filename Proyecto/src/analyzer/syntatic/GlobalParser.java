package analyzer.syntatic;

import gui.Console;

import java.util.List;

import analyzer.lexical.Lexer;
import analyzer.lexical.Token;

public class GlobalParser extends Parser {
	protected final static String[] firstOfS = {"type", "func"};
	boolean tagToStack = false;
	
	protected String[] getFirstOfS() {
		return firstOfS;
	}
	
	protected void createTable() {
		llTable.put("Sfunc", new Production("S", new String[] {"Global"}));
		llTable.put("Stype", new Production("S", new String[] {"Global"}));
		llTable.put("Smoney", new Production("sync", new String[] {"&"}));
		
		llTable.put("Globalfunc", new Production("Global", new String[] {"Declarations", "Functions"}));
		llTable.put("Globaltype", new Production("Global", new String[] {"Declarations", "Functions"}));
		llTable.put("Globalmoney", new Production("sync", new String[] {"&"}));
		
		llTable.put("Declarationstype", new Production("Declarations", new String[] {"Declaration", "Declarations"}));
		llTable.put("Declarationsfunc", new Production("Declarations", new String[] {"&"}));
		llTable.put("Declarationsmoney", new Production("Declarations", new String[] {"&"}));

		llTable.put("Declarationtype", new Production("Declaration", new String[] {"type", "id", "DecH", "semi"}));
		llTable.put("Declarationfunc", new Production("sync", new String[] {"&"}));
		llTable.put("Declarationmoney", new Production("sync", new String[] {"&"}));
		
		llTable.put("DecHequals", new Production("DecH", new String[] {"equals", "Expr"}));
		llTable.put("DecHsemi", new Production("DecH", new String[] {"&"}));
				
		llTable.put("Functionsfunc", new Production("Functions", new String[] {"Function", "Functions"}));
		llTable.put("Functionsmoney", new Production("Functions", new String[] {"&"}));
		
		llTable.put("Functionfunc", new Production("Funcion", new String[] {"func", "type", "id", "openPar", "Parameters","closePar", "openCurly", "Sentences", "closeCurly"}));
		llTable.put("Functionmoney", new Production("sync", new String[] {"&"}));
		
		llTable.put("Parameterstype", new Production("Parameters", new String[] {"Parameter", "Parameters2"}));
		llTable.put("ParametersclosePar", new Production("Parameters", new String[] {"&"}));
		
		llTable.put("Parameters2comma", new Production("Parameters2", new String[] {"comma", "Parameter", "Parameters2"}));
		llTable.put("Parameters2closePar", new Production("Parameters2", new String[] {"&"}));
		
		llTable.put("Parametertype", new Production("Parametro", new String[] {"type", "id"}));
		llTable.put("Parametercomma", new Production("sync", new String[] {"&"}));
		llTable.put("ParameterclosePar", new Production("sync", new String[] {"&"}));
		
		llTable.put("Sentencestype", new Production("Sentence", new String[] {"Sentence", "Sentences"}));
		llTable.put("Sentencesid", new Production("Sentence", new String[] {"Sentence", "Sentences"}));
		llTable.put("SentencesIf", new Production("Sentence", new String[] {"Sentence", "Sentences"}));
		llTable.put("SentencesWhile", new Production("Sentence", new String[] {"Sentence", "Sentences"}));
		llTable.put("SentencesFor", new Production("Sentence", new String[] {"Sentence", "Sentences"}));
		llTable.put("SentencesSwitch", new Production("Sentence", new String[] {"Sentence", "Sentences"}));
		llTable.put("SentencescloseCurly", new Production("Sentence", new String[] {"&"}));
		llTable.put("SentencesBreak", new Production("Sentence", new String[] {"&"}));
		
		llTable.put("Sentencetype", new Production("Sentence", new String[] {"Declaration"}));
		llTable.put("Sentenceid", new Production("Sentence", new String[] {"Assign", "semi"}));
		llTable.put("SentenceIf", new Production("Sentence", new String[] {"if"}));
		llTable.put("SentenceWhile", new Production("Sentence", new String[] {"while"}));
		llTable.put("SentenceFor", new Production("Sentence", new String[] {"for"}));
		llTable.put("SentenceSwitch", new Production("Sentence", new String[] {"switch"}));
		llTable.put("SentencecloseCurly", new Production("sync", new String[] {"&"}));
		llTable.put("SentenceBreak", new Production("sync", new String[] {"&"}));
		
		llTable.put("Assignid", new Production("Assign", new String[] {"id", "equals", "Expr"}));
		llTable.put("Assignsemi", new Production("sync", new String[] {"&"}));
		llTable.put("AssignclosePar", new Production("sync", new String[] {"&"}));
		llTable.put("Assigntype", new Production("sync", new String[] {"&"}));
		llTable.put("AssignIf", new Production("sync", new String[] {"&"}));
		llTable.put("AssignWhile", new Production("sync", new String[] {"&"}));
		llTable.put("AssignFor", new Production("sync", new String[] {"&"}));
		llTable.put("AssignSwitch", new Production("sync", new String[] {"&"}));
		llTable.put("AssigncloseCurly", new Production("sync", new String[] {"&"}));
		llTable.put("AssignBreak", new Production("sync", new String[] {"&"}));
		
		llTable.put("ifIf", new Production("if", new String[] {"If", "openPar", "Condition","closePar", "openCurly", "Sentences", "closeCurly"}));
		llTable.put("ifopenPar", new Production("sync", new String[] {"&"}));
		
		llTable.put("whileWhile", new Production("while", new String[] {"While", "openPar", "Condition","closePar", "openCurly", "Sentences", "closeCurly"}));
		llTable.put("whileopenPar", new Production("sync", new String[] {"&"}));
		
		llTable.put("forFor", new Production("for", new String[] {"For", "openPar", "Assign", "semi", "Condition", "semi", "Assign", "closePar", "openCurly", "Sentences", "closeCurly"}));
		llTable.put("foropenPar", new Production("sync", new String[] {"&"}));
		
		llTable.put("switchSwitch", new Production("switch", new String[] {"Switch", "openPar", "id","closePar", "openCurly", "Cases", "closeCurly"}));
		llTable.put("switchopenPar", new Production("sync", new String[] {"&"}));
		llTable.put("CasesCase", new Production("Cases", new String[] {"case", "Cases"}));
		llTable.put("CasesDefault", new Production("if", new String[] {"Default", "colon", "Sentences", "Break", "semi"}));
		llTable.put("CasescloseCurly", new Production("sync", new String[] {"&"}));
		llTable.put("caseCase", new Production("case", new String[] {"Case", "num", "colon", "Sentences", "Break", "semi"}));
		llTable.put("caseDefault", new Production("sync", new String[] {"&"}));
		
		llTable.put("ConditionopenPar", new Production("Condition", new String[] {"Expr", "opComp", "Expr"}));
		llTable.put("Conditionid", new Production("Condition", new String[] {"Expr", "opComp", "Expr"}));
		llTable.put("Conditionnum", new Production("Condition", new String[] {"Expr", "opComp", "Expr"}));
		llTable.put("ConditionclosePar", new Production("sync", new String[] {"&"}));
		llTable.put("Conditionsemi", new Production("sync", new String[] {"&"}));
		
		llTable.put("ExpropenPar", new Production("Expr", new String[] {"T", "E'"}));
		llTable.put("Exprid", new Production("Expr", new String[] {"T", "E'"}));
		llTable.put("Exprnum", new Production("Expr", new String[] {"T", "E'"}));
		llTable.put("ExpropComp", new Production("sync", new String[] {"&"}));
		llTable.put("ExprclosePar", new Production("sync", new String[] {"&"}));
		llTable.put("Exprsemi", new Production("sync", new String[] {"&"}));
		
		llTable.put("E'opSum", new Production("E'", new String[] {"opSum", "T", "E'"}));
		llTable.put("E'opMinus", new Production("E'", new String[] {"opMinus", "T", "E'"}));
		llTable.put("E'closePar", new Production("E'", new String[] {"&"}));
		llTable.put("E'opComp", new Production("E'", new String[] {"&"}));
		llTable.put("E'semi", new Production("E'", new String[] {"&"}));

		llTable.put("TopenPar", new Production("T", new String[] {"F", "T'"}));
		llTable.put("Tid", new Production("T", new String[] {"F", "T'"}));
		llTable.put("Tnum", new Production("T", new String[] {"F", "T'"}));
		llTable.put("TopSum", new Production("sync", new String[] {"&"}));
		llTable.put("TopMinus", new Production("sync", new String[] {"&"}));
		llTable.put("TopComp", new Production("sync", new String[] {"&"}));
		llTable.put("TclosePar", new Production("sync", new String[] {"&"}));
		llTable.put("Tsemi", new Production("sync", new String[] {"&"}));
		
		llTable.put("T'opMult", new Production("T'", new String[] {"&"}));		
		llTable.put("T'opSum", new Production("T'", new String[] {"&"}));
		llTable.put("T'opMinus", new Production("T'", new String[] {"&"}));
		llTable.put("T'closePar", new Production("T'", new String[] {"&"}));
		llTable.put("T'opComp", new Production("T'", new String[] {"&"}));
		llTable.put("T'semi", new Production("T'", new String[] {"&"}));

		llTable.put("FopenPar", new Production("F", new String[] {"openPar", "Expr", "closePar"}));
		llTable.put("Fid", new Production("F", new String[] {"id"}));
		llTable.put("Fnum", new Production("F", new String[] {"num"}));
		llTable.put("FopMult", new Production("sync", new String[] {"&"}));
		llTable.put("FopSum", new Production("sync", new String[] {"&"}));
		llTable.put("FopMinus", new Production("sync", new String[] {"&"}));
		llTable.put("FopComp", new Production("sync", new String[] {"&"}));
		llTable.put("FclosePar", new Production("sync", new String[] {"&"}));
		llTable.put("Fsemi", new Production("sync", new String[] {"&"}));
		
	}
	
	protected void evaluateToken(List<Token> tokens, Lexer lexer) {
		String s = stack.pop();
		if (s.equals(tokens.get(0).toString())) {			
			tokens.remove(0);
			token++;
		} else {
			Console.println("Line " + line + ": Missing \"" + 
					lexer.tokenToString(Token.valueOf(s)) + "\".");
			lexer.setMissingToken(Token.valueOf(s), token++);
		}
	}
	
	protected void nextProduction(List<Token> tokens, Lexer lexer) {
		Production p = llTable.get(stack.pop() + tokens.get(0));
		if (p == null) {
			Console.println("Line " + line + ": \"" +
					lexer.tokenToString(tokens.remove(0)) + "\" was not expected.");
			lexer.setErrorToken(token++);
			return;
		}

		String lhs = p.getLeftHandSide();
		if (lhs.equals("sync"))	return;
		
		String[] rhs = p.getRightHandSide();
		if (rhs[0].equals("&")) return;

		for (int i = rhs.length - 1; i >= 0; i--)
			stack.push(rhs[i]);
	}	
}
