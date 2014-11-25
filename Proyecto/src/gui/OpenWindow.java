package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class OpenWindow {
	private static JFrame frame;
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
    	ImageIcon icon = new ImageIcon("Petrificate.png"); 
    	JLabel thumb = new JLabel();
    	thumb.setIcon(icon);
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	    	
    	frame = new JFrame();
    	frame.setSize(450, 300);
    	frame.setUndecorated(true);
    	frame.add(thumb);
    	frame.setVisible(true);      	
    	frame.setLocation((screenSize.width - 450) / 2, (screenSize.height - 300) / 2);
    	close();
    }
 
    private static void close() {
    	Timer timer = new Timer(1200, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {					
				(new MainWindow()).open();
				frame.dispose();
			}
		});
    	
		timer.setRepeats(false);
		timer.start();
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

