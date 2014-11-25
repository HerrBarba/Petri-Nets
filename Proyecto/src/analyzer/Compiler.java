package analyzer;

import gui.Console;
import gui.EditorTabs;

import java.util.List;

import analyzer.lexical.GlobalLexer;
import analyzer.lexical.Token;
import analyzer.lexical.XMLLexer;
import analyzer.syntatic.GlobalParser;
import analyzer.syntatic.XMLParser;

public class Compiler {
	public static GlobalLexer globalLexer = new GlobalLexer();
	public static XMLLexer xmlLexer = new XMLLexer();
	
	public static void compile() {
		String[] codes = EditorTabs.getCode().split(EditorTabs.CODE_SEPARATOR);
		compileGlobal(codes[0].trim());
		compileCode(codes[1].trim());
	}
	
	private static void compileGlobal(String code) {		
		GlobalParser parser = new GlobalParser();
		globalLexer.analyze(code);

		Console.println("\nCompiling global functions...");
		
		List<Token> tokens = globalLexer.getTokens();
		String error = "Object not recognized";
		if (tokens.contains(Token.error)) Console.println(error);
		
		error = parser.parse(tokens);
		if (error != null) Console.println(error);
		else Console.println("Compiled Successfully");
	}
		
	private static void compileCode(String code) {
		XMLParser parser = new XMLParser();
		xmlLexer.analyze(code);

		Console.println("\nCompiling xml definition...");
		
		List<Token> tokens = xmlLexer.getTokens();
		String error = "Object not recognized";
		if (tokens.contains(Token.error)) Console.println(error);
		
		error = parser.parse(tokens);
		if (error != null) Console.println(error);
		else Console.println("Compiled Successfully");
	}
}
