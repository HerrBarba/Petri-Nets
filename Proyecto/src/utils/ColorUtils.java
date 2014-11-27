package utils;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import analyzer.lexical.LexToken;
import analyzer.lexical.Lexer;
import analyzer.lexical.Token;

public class ColorUtils {

	public static void colorTextPane(JTextPane pane, Lexer lexer) {
        ArrayList<LexToken> lextokens = lexer.getLexTokens();
        StringBuilder text = new StringBuilder();
        ArrayList<WordToColor> words = new ArrayList<WordToColor>();
        
        int offset = 0;
        for (LexToken lextoken : lextokens) {
        	String lexeme = lextoken.getLexeme();
        	text.append(lexeme);
        	
        	Color color = colorWord(lextoken.getToken());
        	words.add(new WordToColor(offset, lexeme.length(), color));
        	
        	offset += lexeme.length();
        }
                
        pane.setText(text.toString());
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        
        for (WordToColor word: words) {
            StyleConstants.setForeground(attrs, word.color);
            StyledDocument sdoc = pane.getStyledDocument();
            sdoc.setCharacterAttributes(word.offset,
            		word.length, attrs, false); 
        }

        StyleConstants.setForeground(attrs, Color.BLACK);
	}
	
    private static Color colorWord(Token token) {
    	switch (token){
    		case equals:
    		case openTag:
    		case closeTag:
    		case openSlashTag:
    		case closeSlashTag:
    			return Color.MAGENTA;
    		case tag:
    			return Color.CYAN;
    		case petriNet:
    			return Color.ORANGE;
    		case attr:
    			return Color.PINK;
    		case string:
    			return Color.WHITE;
    		case error:
    			return Color.RED;
    		default:
    			return Color.GRAY;
    	}
    }
    
    private static class WordToColor {
    	int offset;
    	int length;
    	Color color;
    	
    	public WordToColor(int offset, int length, Color color) {
    		this.offset = offset;
    		this.length = length;
    		this.color = color;
    	}
    }
}
