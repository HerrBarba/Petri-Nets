package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


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

				JOptionPane.showMessageDialog(about,
                  	    "\nProyecto Procesadores de Lenguaje\n\n" +
                  	    "\t\t\t\t\t\t\t\t\tProyecto: Redes de Petri\n\n" +
                  	    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tVersion 1.0\n\n\n\n" +
                  	    "Intregrantes:\n\n" +
                  	    "\t\t\tJorge Barba\n" +
                  	    "\t\t\tAlejandro Mijares\n" +
                  	    "\t\t\tRodrigo Fornos\n" +
                  	    "\t\t\tJesús Pegueros\n",
                  	    "Acerca",
                  	    JOptionPane.PLAIN_MESSAGE);
			}
			else if(((JMenuItem)e.getSource()) == faqs){
				JOptionPane.showMessageDialog(faqs,
                  	    "\nPreguntas Frecuentes\n\n" +
                  	    "1. ¿Qué es una Red de Petri?\n" +
                  	    "\t\t\t\tUna Red de Petri es una representación matemática o gráfica de un sistema a eventos discretos en el cual\n" +
                  	    "\t\t\t\tse puede describir la topología de un sistema distribuido, paralelo o concurrente.\n\n" +
                  	    "2. ¿En que áreas se pueden aplicar Redes de Petri?\n" +
                  	    "\t\t\t\tSe pueden aplicar en:\n" +
                  	    "\t\t\t\t\t\t\t\tAnalisis de Datos\n" +
                  	    "\t\t\t\t\t\t\t\tDiseño de Software\n" +
                  	    "\t\t\t\t\t\t\t\tFiabilidad\n" +
                  	    "\t\t\t\t\t\t\t\tFlujo de trabajo\n" +
                  	    "\t\t\t\t\t\t\t\tProgramación concurrente\n",
                  	    "FAQ's",
                  	    JOptionPane.PLAIN_MESSAGE);
			}
			else if(((JMenuItem)e.getSource()) == version){
				JOptionPane.showMessageDialog(version,
                  	    "\n\t\t\t\t\t\t\t\t\tProyecto Redes de Petri\n\n" +
                  	    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tVersion 1.0\n",
                  	    "Version",
                  	    JOptionPane.PLAIN_MESSAGE);	        
	}
}
}
