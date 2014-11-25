package gui.menu;

import filemanager.FileManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FileMenu extends JMenu implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4163657867326062798L;

	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem export;
	private JMenuItem importItem;
	private JMenuItem exit;
	
	// Create "File" menu
    public FileMenu() {
        super("Archivo");
        setMnemonic(KeyEvent.VK_A);
        
        // Create new net submenu
       	add(new NewNetMenu("Crear nueva red"));
        addSeparator();

        // Open menu item
        open = new JMenuItem("Abrir...", KeyEvent.VK_A);
        open.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
        open.addActionListener(this);
        add(open);
        
        // Save menu item
        addSeparator();
        save = new JMenuItem("Guardar", KeyEvent.VK_G);
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        add(save);
 
        // Save as... menu item
        saveAs = new JMenuItem("Guardar como", KeyEvent.VK_C);
        saveAs.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveAs.addActionListener(this);
        add(saveAs);
        
        // Export menu item
        addSeparator();
        export = new JMenuItem("Exportar", KeyEvent.VK_E);
        export.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        export.addActionListener(this);
        add(export);
 
        // Import menu item
        importItem = new JMenuItem("Importar", KeyEvent.VK_I);
        importItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        importItem.addActionListener(this);
        add(importItem);
        
        // Exit menu item
        addSeparator();
        exit = new JMenuItem("Salir");
        exit.addActionListener(this);
        add(exit);
    }

	@Override
	public void actionPerformed(ActionEvent ev) {
		JMenuItem item = (JMenuItem) ev.getSource();
		
		// Open file
		if (item == open) {
			try {
				
				FileManager.open();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		// Save file
		else if (item == save) {
			try {
				FileManager.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Save file as...
		else if (item == saveAs) {
			try {
				FileManager.saveAs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		// Exit
		else if (item == exit) {
			System.exit(0);
		}	
	}

}
