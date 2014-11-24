package gui.menu;

import gui.EditorTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
public class EditMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295268862794155929L;
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem font;
	private JMenuItem color;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem cut;
	private JMenuItem delete;
	private String clipboard;
	

	private UndoManager undoManager = new UndoManager();


	public EditMenu() {
        super("Editar");
        setMnemonic(KeyEvent.VK_E);
         
        // Change Font menu item
        undo = new JMenuItem("Undo");
        undo.setMnemonic(KeyEvent.VK_U);
        undo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.addActionListener(this);
        add(undo); 
        
        // Change object color menu item
        redo = new JMenuItem("Redo");
        redo.setMnemonic(KeyEvent.VK_R);
        redo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redo.addActionListener(this);
        add(redo);
        
        addSeparator();
        
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
                
        addSeparator();
        
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
        cut.setMnemonic(KeyEvent.VK_T);
        cut.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        add(cut);  
        
        // Delete menu item
        delete = new JMenuItem("Eliminar");
        delete.setMnemonic(KeyEvent.VK_E);
        delete.addActionListener(this);
        add(delete);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		JTextPane textArea = EditorTabs.output;
		
		// Selection indexes
		int start = textArea.getSelectionStart();
		int end = textArea.getSelectionEnd();
		
		String text = textArea.getText();
		
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
					}
				});
		
		//Undo
		if(item == undo)

			try {
				if (undoManager.canUndo()) {
					undoManager.undo();
				}
			} catch (CannotUndoException exp) {
				exp.printStackTrace();
			}

		//Redo
		else if(item == redo)
		{
			try {
				if (undoManager.canRedo()) {
					undoManager.redo();
				}
			} catch (CannotRedoException exp) {
				exp.printStackTrace();
			}
		}
		
		// Copy selected string
		else if (item == copy)			
			clipboard = text.substring(start, end);
			
		// Paste string at cursor position
		else if (item == paste)			
			textArea.setText(paste(text, clipboard, start, end));
			
		// Delete selected string
		else if (item == delete)
			textArea.setText(paste(text, "", start, end));
		
		// Cut selected string
		else if (item == cut) {			
			clipboard = text.substring(start, end);
			textArea.setText(paste(text, "", start, end));			
		}
	}
	
	private String paste(String text, String clipboard, 
							int start, int end) {
		
		StringBuilder newText = new StringBuilder("");
		
		// Add text before selection
		newText.append(text.substring(0, start));
		
		// Add selection
		newText.append(clipboard);
		
		// Add text after selection
		newText.append(text.substring(end));
		
		return newText.toString();
	}
}


