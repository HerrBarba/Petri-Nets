package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.ColorUtils;
import analyzer.Compiler;

public class EditorTabs extends JPanel implements DocumentListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2655671622457927372L;
	public static JTextPane xmlPane;
	public static JTextPane globalPane;
	private static JTabbedPane tabbedPane;
	public static final int CODE = 1, GLOBAL = 0;
	private final static String[] titles = {"Global", "Código", "Gráfico"};
	public final static String CODE_SEPARATOR = "<EndOfGlobal>";
	
	public EditorTabs() {
        super(new GridLayout(1, 1));
         
        tabbedPane = new JTabbedPane();

        // Create global functions and variables pane
        globalPane = new JTextPane();
        tabbedPane.addTab(titles[0], null, makeTextPanel(globalPane),
                "Global functions and variables here...");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
        
        // Create code pane
        xmlPane = new JTextPane();
        tabbedPane.addTab(titles[1], null, makeTextPanel(xmlPane),
                "XML definition here...");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);
        
        // Create graph tab
        JComponent graphPanel = makeGraphPanel("Gráfico");
        tabbedPane.addTab(titles[2], null, graphPanel,
                "Graphic representation...");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_G);
                 
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), 800));
        //Add the tabbed pane to this 
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(Console.pane, BorderLayout.PAGE_END);
        
    }
    
	private JPanel makeTextPanel(JTextPane pane) {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        pane.setEditable(true);
        pane.setBackground(Color.BLACK);
        pane.setForeground(Color.WHITE);
        pane.setCaretColor(Color.WHITE);
        pane.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        pane.getStyledDocument().addDocumentListener(this);
        
        JScrollPane scrollPane = new JScrollPane(pane);
        
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;
    }

	public static String getCode() {
		return globalPane.getText() + "\n" + CODE_SEPARATOR
				+ "\n" + xmlPane.getText();
	}	
	
	public static void setCode(String code) {
		String[] codes = code.split("<EndOfGlobal>");
		globalPane.setText(codes[0].trim());
		xmlPane.setText(codes[1].trim());
	}

	public static JTextPane getCurrentPane() {
    	switch(tabbedPane.getSelectedIndex()) {
			case CODE:
				return xmlPane;
			case GLOBAL:
				return globalPane;
			default:
				return null;
    	}
	}
 
    public static void colorPane() {
		ColorUtils.colorTextPane(xmlPane, Compiler.xmlLexer);
		ColorUtils.colorTextPane(globalPane, Compiler.globalLexer);
        resetTitle();
    }
    
    public static void resetTitle() {
		tabbedPane.setTitleAt(0, titles[0]);
		tabbedPane.setTitleAt(1, titles[1]);
    }
    
    public static boolean hasChanges() {
    	return tabbedPane.getTitleAt(0).charAt(0) == '*' ||
    			tabbedPane.getTitleAt(1).charAt(0) == '*';
    }
    
    private JComponent makeGraphPanel(String text) {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);        

        JPanel graphPanel = new JPanel();
        graphPanel.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(graphPanel);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = EditorTabs.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	@Override
	public void changedUpdate(DocumentEvent arg0) {}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		changeTitle();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		changeTitle();
	}
	
	private void changeTitle() {
		int index = tabbedPane.getSelectedIndex();
		tabbedPane.setTitleAt(index, "*" + titles[index]);
	}
}
