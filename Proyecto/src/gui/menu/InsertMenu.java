package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class InsertMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8225952954494124597L;
	private JMenuItem place;
	private JMenuItem transition;
	private JMenuItem label;
	
    public InsertMenu() {
        super("Insertar");
        setMnemonic(KeyEvent.VK_I);
 
        // New place menu item
        place = new JMenuItem("Nuevo Lugar");
        place.setMnemonic(KeyEvent.VK_L);
        place.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        place.addActionListener(this);
        add(place); 
        
        // New transition menu item
        transition = new JMenuItem("Nueva Transición");
        transition.setMnemonic(KeyEvent.VK_T);
        transition.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        transition.addActionListener(this);
        add(transition);
        
        // New transition menu item
        label = new JMenuItem("Nueva Etiqueta");
        label.setMnemonic(KeyEvent.VK_E);
        label.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        add(label);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
