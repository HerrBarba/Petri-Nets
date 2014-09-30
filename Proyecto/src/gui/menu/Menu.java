package gui.menu;

import javax.swing.JMenuBar;

/**
 * Returns an JMenuBar with all basic editor properties:
 * save, save as, copy, paste, help, compile, run...
 * 
 * @author Jorge Barba
 * @since 29/09/2014
 * @version 1.0
 *
 */
public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5617155576631422259L;


	public Menu() {
		// Create "File" menu and add it to the JMenuBar
        add(new FileMenu());

    	// Create "Edit" menu and add it to the JMenuBar
        add(new EditMenu());

    	// Create "I" menu and add it to the JMenuBar
        add(new InsertMenu());

    	// Create "Tools" menu and add it to the JMenuBar
        add(new ToolsMenu());

    	// Create "Help" menu and add it to the JMenuBar
        add(new HelpMenu());
    }
}
