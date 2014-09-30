package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class EditMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295268862794155929L;
	private JMenuItem font;
	private JMenuItem color;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem cut;
	private JMenuItem delete;
	
	public EditMenu() {
        super("Editar");
        setMnemonic(KeyEvent.VK_E);
 
        // Change Font menu item
        font = new JMenuItem("Fuente");
        font.setMnemonic(KeyEvent.VK_F);
        font.addActionListener(this);
        add(font); 
        
        // Change object color menu item
        color = new JMenuItem("Color");
        color.setMnemonic(KeyEvent.VK_L);
        color.addActionListener(this);
        add(color);
 
        // Copy menu item
        copy = new JMenuItem("Copiar");
        copy.setMnemonic(KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        add(copy);
 
        // Paste menu item
        paste = new JMenuItem("Pegar");
        paste.setMnemonic(KeyEvent.VK_V);
        paste.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        add(paste);
        
        // Cut menu item
        cut = new JMenuItem("Cortar");
        cut.setMnemonic(KeyEvent.VK_U);
        cut.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        add(cut);
        
        // Deltte menu item
        delete = new JMenuItem("Eliminar");
        delete.setMnemonic(KeyEvent.VK_E);
        delete.addActionListener(this);
        add(delete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
