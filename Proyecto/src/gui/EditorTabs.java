package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class EditorTabs extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2655671622457927372L;
	public static JTextPane output;
	
	public EditorTabs() {
        super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = null;// = createImageIcon("images/middle.gif");
         
        JComponent codePanel = makeTextPanel("Código");
        tabbedPane.addTab("Código", icon, codePanel,
                "Code here...");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);
         
        JComponent graphPanel = makeGraphPanel("Gráfico");
        tabbedPane.addTab("Gráfico", icon, graphPanel,
                "Modify your petri net...");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_G);
                 
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
     
    private JComponent makeTextPanel(String text) {
    	
    	String markup = "Questions are <font size='+1' color='blue'>a burden</font> to others,\n"  
    			  +"answers <font size='+2' color='red'>a prison</font> for oneself."; 
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);        

        //Create a scrolled text area.
        output = new JTextPane();
        output.setEditable(true);
        
        output.setText(markup);
        SimpleAttributeSet attrs= new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, Color.blue);
        StyledDocument sdoc = output.getStyledDocument();  
        sdoc.setCharacterAttributes(14, 29, attrs, false);  
        sdoc.setCharacterAttributes(51,  7, attrs, false);  
        sdoc.setCharacterAttributes(78, 28, attrs, false);  
        sdoc.setCharacterAttributes(114, 7, attrs, false); 
        
        JScrollPane scrollPane = new JScrollPane(output);
        
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;
        
    
        
        
        
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
}
