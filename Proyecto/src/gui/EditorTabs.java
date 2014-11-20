package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class EditorTabs extends JPanel implements DocumentListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2655671622457927372L;
	public static JTextPane output;
	private static JTabbedPane tabbedPane;
	
	public EditorTabs() {
        super(new GridLayout(1, 1));
         
        tabbedPane = new JTabbedPane();
        ImageIcon icon = null;// = createImageIcon("images/middle.gif");
         
        JComponent codePanel = makeTextPanel();
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
     
    private JComponent makeTextPanel() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextPane();
        output.setEditable(true);
        output.setBackground(Color.BLACK);
        output.setForeground(Color.WHITE);
        output.setCaretColor(Color.WHITE);
        output.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        output.getStyledDocument().addDocumentListener(this);
        
        JScrollPane scrollPane = new JScrollPane(output);
        
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;        
    }
 
    public static void colorPane() {
        ColorUtils.colorTextPane(output);
        resetTitle();
    }
    
    public static void resetTitle() {
    	tabbedPane.setTitleAt(0, "Código");
    }
    
    public static boolean hasChanges() {
    	return tabbedPane.getTitleAt(0).charAt(0) == '*';
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
		tabbedPane.setTitleAt(0, "*Código");
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		tabbedPane.setTitleAt(0, "*Código");
	}
}
