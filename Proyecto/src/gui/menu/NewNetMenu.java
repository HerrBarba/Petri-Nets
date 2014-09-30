package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class NewNetMenu extends JMenu implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5724047285008563122L;
	private JMenuItem ptNet;
	private JMenuItem colouredNet;
	private JMenuItem mutilevelNet;
	private JMenuItem temporizedNet;
	private JMenuItem continuousNet;
	private JMenuItem fluidNet;
	
	
	public NewNetMenu(String s) {
        setText(s);
        setMnemonic(KeyEvent.VK_N);
        
        ptNet = new JMenuItem("P-T");
        ptNet.addActionListener(this);
		add(ptNet);
		
		colouredNet = new JMenuItem("Coloreadas");
		colouredNet.addActionListener(this);
		add(colouredNet);
		
		mutilevelNet = new JMenuItem("Multinivel");
		mutilevelNet.addActionListener(this);
		add(mutilevelNet);
		
		temporizedNet = new JMenuItem("Temporizadas");
		temporizedNet.addActionListener(this);
		add(temporizedNet);
		
		continuousNet = new JMenuItem("Continuas");
		continuousNet.addActionListener(this);
		add(continuousNet);
		
		fluidNet = new JMenuItem("Fluidificadas");		
		fluidNet.addActionListener(this);		
		add(fluidNet);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
