package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
		createMenuBar();
	}
	
	/**
	 * 
	 * @return      the basic menu bar
	 * @see         JMenuBar
	 */
	private void createMenuBar() {
        createFileMenu();
        createEditMenu();
        createInsertMenu();
        createToolsMenu();
        createHelpMenu();
    }
    
	// Create "File" menu and add it to the JMenuBar
    private void createFileMenu() {
        JMenu menu, submenu;
        JMenuItem menuItem;
        
    	// Build the File menu
        menu = new JMenu("Archivo");
        menu.setMnemonic(KeyEvent.VK_A);
        add(menu);
        
        // Create new net submenu
        menu.addSeparator();
        submenu = new JMenu("Crear nueva red");
        submenu.setMnemonic(KeyEvent.VK_N);
        
        String[] petriNets = {"P-T", "Coloreadas", "Multinivel", "Temporizadas",
        		"Continuas", "Fluidificadas"};
        
        for (String s: petriNets)
        	submenu.add(new JMenuItem(s));
        menu.add(submenu);
        
        // Open menu item
        menuItem = new JMenuItem("Abrir...", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menu.add(menuItem);
        
        // Save menu item
        menu.addSeparator();
        menuItem = new JMenuItem("Guardar", KeyEvent.VK_G);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
 
        // Save as... menu item
        menuItem = new JMenuItem("Guardar como", KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        // Export menu item
        menu.addSeparator();
        menuItem = new JMenuItem("Exportar", KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
 
        // Import menu item
        menuItem = new JMenuItem("Importar", KeyEvent.VK_I);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        // Exit menu item
        menu.addSeparator();
        menuItem = new JMenuItem("Salir");
        menu.add(menuItem);
    }
    

	// Create "Edit" menu and add it to the JMenuBar
    private void createEditMenu() {
        JMenu menu;
        JMenuItem menuItem;
        
        // Edit menu
        menu = new JMenu("Editar");
        menu.setMnemonic(KeyEvent.VK_E);
        add(menu);
 
        // Change Font menu item
        menuItem = new JMenuItem("Fuente");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menu.add(menuItem); 
        
        // Change object color menu item
        menuItem = new JMenuItem("Color");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menu.add(menuItem);
 
        // Copy menu item
        menuItem = new JMenuItem("Copiar");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
 
        // Paste menu item
        menuItem = new JMenuItem("Pegar");
        menuItem.setMnemonic(KeyEvent.VK_V);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        // Delete menu item
        menuItem = new JMenuItem("Eliminar");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menu.add(menuItem);
    }
    

	// Create "Insert" menu and add it to the JMenuBar
    private void createInsertMenu() {
    	JMenu menu;
        JMenuItem menuItem;
        
        // Edit menu
        menu = new JMenu("Insertar");
        menu.setMnemonic(KeyEvent.VK_I);
        add(menu);
 
        // New place menu item
        menuItem = new JMenuItem("Nuevo Lugar");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menu.add(menuItem); 
        
        // New transition menu item
        menuItem = new JMenuItem("Nueva Transición");
        menuItem.setMnemonic(KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        // New transition menu item
        menuItem = new JMenuItem("Nueva Etiqueta");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
    }
    

	// Create "Tools" menu and add it to the JMenuBar
    private void createToolsMenu() {
    	JMenu menu;
        JMenuItem menuItem;
        
        // Edit menu
        menu = new JMenu("Herramientas");
        menu.setMnemonic(KeyEvent.VK_H);
        add(menu);
 
        // Compile menu item
        menuItem = new JMenuItem("Compilar");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        menu.add(menuItem); 

        // Run menu item
        menuItem = new JMenuItem("Correr");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        // Options menu item
        menuItem = new JMenuItem("Opciones");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menu.add(menuItem);
    }
    

	// Create "Help" menu and add it to the JMenuBar
    private void createHelpMenu() {
    	JMenu menu;
        JMenuItem menuItem;
        
        // Help menu
        menu = new JMenu("Ayuda");
        menu.setMnemonic(KeyEvent.VK_Y);
        add(menu);
 
        // About menu item
        menuItem = new JMenuItem("Sobre...");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menu.add(menuItem); 
        
        // FAQ's menu item
        menuItem = new JMenuItem("FAQ's");
        menu.add(menuItem);

        // Version menu item
        menuItem = new JMenuItem("Version");
        menu.add(menuItem);
    }    
}
