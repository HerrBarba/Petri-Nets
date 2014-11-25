package gui.menu;

import filemanager.FileManager;
import gui.EditorTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class NewNetMenu extends JMenu implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5724047285008563122L;
	private JMenuItem ptNet;
	private JMenuItem colouredNet;
	private JMenuItem multilevelNet;
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
		
		multilevelNet = new JMenuItem("Multinivel");
		multilevelNet.addActionListener(this);
		add(multilevelNet);
		
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
	public void actionPerformed(ActionEvent ev) {
		JMenuItem item = (JMenuItem) ev.getSource();
		
		// New P-T Petri net
		if (item == ptNet)
			newNet("<P-T>\n</P-T>");
		
		
		// New coloured Petri net
		else if (item == colouredNet)
			newNet("<Coloreada>\n</Coloreada>");
		
		// New multilevel Petri net
		else if (item == multilevelNet)
			newNet("<Multinivel>\n</Multinivel>");
		
		// New temporized Petri net
		else if (item == temporizedNet)
			newNet("<Temporizada>\n</Temporizada>");

		// New continuous Petri net
		else if (item == continuousNet)
			newNet("<Continua>\n</Continua>");
		
		// New fluid Petri net
		else if (item == fluidNet)
			newNet("<Fluidificada>\n</Fluidificada>");
		
	}
	
	private void newNet(String net) {
		if (!FileManager.confirmSaveDialog()) return;
		JTextPane pane = EditorTabs.getCurrentPane();
		if (pane == null) return;
		pane.setText(net);
		FileManager.newFile();
	}	
}
