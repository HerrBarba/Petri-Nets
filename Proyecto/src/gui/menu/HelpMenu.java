package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class HelpMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2624208662127807794L;
	private JMenuItem about;
	private JMenuItem faqs;
	private JMenuItem version;
	
	// Create "Help" menu and add it to the JMenuBar
    public HelpMenu() {
    	super("Ayuda");
        setMnemonic(KeyEvent.VK_Y);
        
        // About menu item
        about = new JMenuItem("Sobre...");
        about.setMnemonic(KeyEvent.VK_S);
        about.addActionListener(this);
        add(about); 
        
        // FAQ's menu item
        faqs = new JMenuItem("FAQ's");
        faqs.addActionListener(this);
        add(faqs);

        // Version menu item
        version = new JMenuItem("Version");
        version.addActionListener(this);
        add(version);
    } 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
