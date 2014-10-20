package gui;

import java.io.File;

import javax.swing.JFileChooser;

public class FileManager {
	File file;
	JFileChooser fc;
	
	public void open () {
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}
	
	public void save() {
		
	}
}
