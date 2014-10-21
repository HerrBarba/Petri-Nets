package analyzer;

import java.util.List;

import analyzer.lexical.Lexer;
import analyzer.lexical.Token;
import analyzer.syntatic.Parser;

public class Compiler {

	//TODO finish compilation function
	public static boolean compile() {
		List<Token> tokens = Lexer.getTokens();
		if (tokens.contains(Token.error)) return false;
		if (!Parser.parse(tokens)) return false;
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(compile());
	}
}
