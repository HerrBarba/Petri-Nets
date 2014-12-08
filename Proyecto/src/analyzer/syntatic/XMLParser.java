package analyzer.syntatic;

import gui.Console;

import java.util.List;

import analyzer.lexical.Lexer;
import analyzer.lexical.Token;
import analyzer.semantic.Attribute;
import analyzer.semantic.xml.PetriNet;
import analyzer.semantic.xml.Place;
import analyzer.semantic.xml.PlaceTable;
import analyzer.semantic.xml.Tag;
import analyzer.semantic.xml.Transition;
import analyzer.semantic.xml.TransitionTable;

public class XMLParser extends Parser {
	private PlaceTable placeTable = new PlaceTable();
	private TransitionTable transitionTable = new TransitionTable();
	
	protected final static String[] firstOfS = {"openTag"};
	
	boolean tagToStack = false;
	Attribute attr = null;
	Tag tag;
	
	protected String[] getFirstOfS() {
		return firstOfS;
	}
	
	protected void createTable() {
		llTable.put("SopenTag", new Production("S", new String[] {"S'", "S"}));
		llTable.put("S'openTag", new Production("S'", new String[] {"openTag", "T", "A", "E"}));
		llTable.put("EopenTag", new Production("sync", new String[] {"&"}));
		llTable.put("EcloseTag", new Production("E", new String[] {"closeTag", "S", "openSlashTag", "T", "closeTag"}));
		llTable.put("AcloseTag", new Production("A", new String[] {"&"}));
		llTable.put("TcloseTag", new Production("sync", new String[] {"&"}));
		llTable.put("SopenSlashTag", new Production("S", new String[] {"&"}));
		llTable.put("S'openSlashTag", new Production("sync", new String[] {"&"}));
		llTable.put("EopenSlashTag", new Production("sync", new String[] {"&"}));
		llTable.put("EcloseSlashTag", new Production("E", new String[] {"closeSlashTag"}));		
		llTable.put("AcloseSlashTag", new Production("A", new String[] {"&"}));	
		llTable.put("TcloseSlashTag", new Production("sync", new String[] {"&"}));
		llTable.put("TpetriNet", new Production("T", new String[] {"petriNet"}));
		llTable.put("Ttag", new Production("T", new String[] {"tag"}));		
		llTable.put("Aattr", new Production("A", new String[] {"attr", "equals", "string", "A"}));
		llTable.put("Smoney", new Production("S", new String[] {"&"}));		
	}	
		
	protected void evaluateToken(List<Token> tokens, Lexer lexer) {
		String s = stack.pop();
		if (s.equals(tokens.get(0).toString())) {
			if (s.equals(Token.closeSlashTag.toString()))
				tagStack.pop();
			
			if (s.equals(Token.attr.toString())) {
				String name = lexer.getLexemeAt(token);
				attr.setName(name);
				
				if (!attr.checkScope()) {
					Console.println("Line " + line + ": \"" + name +
							"\" is not a valid attribute for " + attr.getScope());
					lexer.setErrorToken(token);
					attr.setName("");
				}
			}
			
			if (s.equals(Token.string.toString())) {
				if (!attr.getName().equals("")) {					
					String value = lexer.getLexemeAt(token);
					if (!attr.setValue(value)) {
						Console.println("Line " + line + ": " + value +
								" is not a valid number");
						lexer.setErrorToken(token);
					}
					tag.addAttribute(attr);
				}
				attr = new Attribute(attr.getScope());
			}
			tokens.remove(0);
			token++;
		} else {
			Console.println("Line " + line + ": Missing \"" + 
					lexer.tokenToString(Token.valueOf(s)) + "\".");
			lexer.setMissingToken(Token.valueOf(s), token++);
		}
	}
	
	protected void nextProduction(List<Token> tokens, Lexer lexer) {
		if (tokens.get(0) == Token.openTag)
			tagToStack = true;
		
		Production p = llTable.get(stack.peek() + tokens.get(0));
		if (p == null) {
			Console.println("Line " + line + ": \"" +
					lexer.tokenToString(tokens.remove(0)) + "\" was not expected.");
			lexer.setErrorToken(token++);
			return;
		}
		stack.pop();

		String lhs = p.getLeftHandSide();
		if (lhs.equals("T")) {
			String lexeme = lexer.getLexemeAt(token);
			attr = new Attribute(lexeme);
			
			if (lexeme.equals("Place"))
				tag = new Place();
			else if (lexeme.equals("Transition"))
				tag = new Transition();			
			else
				tag = new PetriNet(lexeme);
			
			if (tagToStack) {
				tagStack.push(lexeme);
				tagToStack = false;
			} else {
				String tagName = tagStack.pop();
				if (!tagName.equals(lexeme)) {					
					Console.println("Line " + line + ": \"" + tagName +
							"\" was expected.");
					lexer.setErrorToken(token);
				}
			}
		}
		
		
		
		if (lhs.equals("sync"))	return;
		
		String[] rhs = p.getRightHandSide();
		if (lhs.equals("A") && rhs[0].equals("&"))
			saveTag();
				
		if (rhs[0].equals("&")) return;

		for (int i = rhs.length - 1; i >= 0; i--)
			stack.push(rhs[i]);
	}
	
	private void saveTag() {		
		if (tag instanceof Place) {
			Place place = (Place) tag;
			if (!place.isValid()) {
				Console.println("Line " + line + ": missing attributes");
				return;
			}
			
			if (placeTable.get(place.getId()) != null) {
				Console.println("Line " + line + ": Place " + place.getId() + " already declared");
				return;
			}
			
			placeTable.insert(place);
		} else if (tag instanceof Transition) {
			Transition transition = (Transition) tag;
			
			if(!transition.isValid()) {
				Console.println("Line " + line + ": missing attributes");
				return;
			}
			
			String from = transition.getFrom();
			String to = transition.getTo();
			boolean flag = false;
			
			if (placeTable.get(from) == null) {
				Console.println("Line " + line + ": Place " + from + " not found");
				flag = true;
			}
			
			if (placeTable.get(to) == null) {
				Console.println("Line " + line + ": Place " + to + " not found");
				flag = true;
			}
			
			if (transitionTable.get(transition.getId()) != null) {
				Console.println("Line " + line + ": Transition " + transition.getId() + " already declared");
				flag = true;
			}
			
			if (flag) return;
			
			transitionTable.insert(transition);
		}
	}

}
