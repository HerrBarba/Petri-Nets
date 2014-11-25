package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Console {
	private static JTextPane out;
	public static JScrollPane pane;
	
	static {
		//Create a scrolled text area.
		out = new JTextPane();		
        out.setEditable(false);
        out.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
       
		pane = new JScrollPane(out);
		
        Dimension dimension = new Dimension(pane.getWidth(), 150);
		pane.setSize(dimension);
		pane.setPreferredSize(dimension);
	}
	
	public static void println(String text) {
		out.setText(out.getText() + text + "\n");
	}
	
	public static void clear() {
		out.setText("");
	}
}
