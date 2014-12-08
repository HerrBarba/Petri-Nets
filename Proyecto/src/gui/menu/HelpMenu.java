package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


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
        about = new JMenuItem("Acerca");
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
			if(((JMenuItem)e.getSource()) == about)
			{

				JOptionPane.showMessageDialog(about, new JTextArea(
                  	    "\nMateria: Procesadores de Lenguaje\n\n" +
                  	    "\tProyecto: Redes de Petri\n\n" +
                  	    "\tVersion 1.0\n\n\n\n" +
                  	    "Intregrantes:\n\n" +
                  	    "\tJorge Barba\n" +
                  	    "\tAlejandro Mijares\n" +
                  	    "\tRodrigo Fornos\n" +
                  	    "\tJes�s Pegueros\n"),
                  	    "Acerca",
                  	    JOptionPane.PLAIN_MESSAGE);
			}
			else if(((JMenuItem)e.getSource()) == faqs){
				JOptionPane.showMessageDialog(faqs, new JTextArea(
                  	    "\nPreguntas Frecuentes\n\n" +
                  	    "1. �Qu� es una Red de Petri?\n" +
                  	    "\t\t\t\tUna Red de Petri es una representaci�n matem�tica o gr�fica de un sistema a eventos discretos en el cual\n" +
                  	    "\t\t\t\tse puede describir la topolog�a de un sistema distribuido, paralelo o concurrente.\n\n" +
                  	    "2. �En que �reas se pueden aplicar las Redes de Petri?\n" +
                  	    "\t\t\t\tSe pueden aplicar en:\n" +
                  	    "\t\t\t\t\t\t\t\t-Analisis de Datos\n" +
                  	    "\t\t\t\t\t\t\t\t-Dise�o de Software\n" +
                  	    "\t\t\t\t\t\t\t\t-Fiabilidad\n" +
                  	    "\t\t\t\t\t\t\t\t-Flujo de trabajo\n" +
                  	    "\t\t\t\t\t\t\t\t-Programaci�n concurrente\n"),
                  	    "FAQ's",
                  	    JOptionPane.PLAIN_MESSAGE);
			}
			else if(((JMenuItem)e.getSource()) == version){
				JOptionPane.showMessageDialog(version,new JTextArea(
                  	    "\n\t\t\t\t\t\t\t\t\tProyecto Redes de Petri\n\n" +
                  	    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tVersion 1.0\n"),
                  	    "Version",
                  	    JOptionPane.PLAIN_MESSAGE);	        
	}
}
}
