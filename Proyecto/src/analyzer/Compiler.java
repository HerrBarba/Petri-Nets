package analyzer;

import java.util.List;

import analyzer.lexical.Lexer;
import analyzer.lexical.Token;
import analyzer.syntatic.Parser;

public class Compiler {

	//TODO finish compilation function
	public static String compile() {
		Lexer.analyze();
		List<Token> tokens = Lexer.getTokens();
		String error = "Object not recognized";
		if (tokens.contains(Token.error)) return error;
		
		error = Parser.parse(tokens);
		if (error != null) return error;
		
		return "Compiled Successfully";
	}
	
	public static void main(String[] args) {
		System.out.println(compile());
	}
}
