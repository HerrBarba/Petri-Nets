package gui.menu;

import gui.EditorTabs;
import gui.FileManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import analyzer.Compiler;

public class ToolsMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6748775731044777549L;
	private JMenuItem compile;
	private JMenuItem run;
	private JMenuItem options;
	
	// Create "Tools" menu and add it to the JMenuBar
    public ToolsMenu() {
        super("Herramientas");
        setMnemonic(KeyEvent.VK_H);
        
        // Compile menu item
        compile = new JMenuItem("Compilar");
        compile.setMnemonic(KeyEvent.VK_C);
        compile.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        compile.addActionListener(this);
        add(compile); 

        // Run menu item
        run = new JMenuItem("Correr");
        run.setMnemonic(KeyEvent.VK_R);
        run.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        run.addActionListener(this);
        add(run);
        
        // Options menu item
        options = new JMenuItem("Opciones");
        options.setMnemonic(KeyEvent.VK_O);
        options.addActionListener(this);
        add(options);
    }
    

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		JMenuItem item = (JMenuItem) ev.getSource();
		if (item == compile) {
			try {
				FileManager.save();
				System.out.println(Compiler.compile());
				EditorTabs.colorPane();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
