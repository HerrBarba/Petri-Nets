package gui;

import gui.menu.Menu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main window of the application
 * 
 * @author Jorge Barba
 * @since 29/09/2014
 * @version 1.0
 *
 */
public class MainWindow extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void open() {
        // Create and set up the window.
        setTitle("Petrificate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add tabbed pane to the window.
        add(new EditorTabs(), BorderLayout.CENTER);
        
        // Create and set up the menu.
        setJMenuBar(new Menu());
        
         // Display the window
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(600, 600));
        setVisible(true);
    }
    
}
