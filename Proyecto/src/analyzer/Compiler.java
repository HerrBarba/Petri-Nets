package analyzer;

import gui.Console;
import gui.EditorTabs;

import java.util.ArrayList;
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
		
		List<Token> tokens =  new ArrayList<Token>(globalLexer.getTokens());
		if (tokens.contains(Token.error))
			Console.println("Object not recognized.");
		
		
		parser.parse(tokens, globalLexer);		
		Console.println(globalLexer.hadSuccess()?
				"Compiled successfully.": "Could not compile.");
	}
		
	private static void compileCode(String code) {
		XMLParser parser = new XMLParser();
		xmlLexer.analyze(code);
		
		Console.println("\nCompiling xml definition...");
		
		List<Token> tokens = new ArrayList<Token>(xmlLexer.getTokens());
		String error = "Object not recognized.";
		if (tokens.contains(Token.error))
			Console.println(error);
		
		parser.parse(tokens, xmlLexer);

		Console.println(xmlLexer.hadSuccess()?
				"Compiled successfully.": "Could not compile.");
	}
}
