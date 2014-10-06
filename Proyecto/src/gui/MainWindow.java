package gui;

import gui.menu.Menu;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Main window of the application
 * 
 * @author Jorge Barba
 * @since 29/09/2014
 * @version 1.0
 *
 */
public class MainWindow {
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Petrificate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add tabbed pane to the window.
        frame.add(new EditorTabs(), BorderLayout.CENTER);
        
        // Create and set up the menu.
        frame.setJMenuBar(new Menu());

        // Display the window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
