package filemanager;

import gui.Console;
import gui.EditorTabs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
	private static String path = ".";
	private static String filename = "tmp";
	private final static String fileExtension = ".pet";
	private final static String separator = File.separator;
	static { newFile(); }
	
	public static void newFile() {
		File file = new File(".\\tmp");
		
		if (file.exists()) file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static File getFile() {
		return new File(path + separator + filename);
	}
		
	private static void readFile() throws IOException {
		FileReader fr = new FileReader(path + separator + filename);
		BufferedReader br = new BufferedReader(fr);
		
		StringBuilder text = new StringBuilder();
		String line = br.readLine();
		
		while (line != null) {
			text.append(line).append("\n");	
			line = br.readLine();
		}			
		
		br.close();

		EditorTabs.setCode(text.toString());
		EditorTabs.resetTitle();
		
		Console.println("Opened " + path + separator + filename + " succesfully.");
	}
	
	private static void saveFile(String file) throws Exception {
		PrintWriter writer = new PrintWriter(file);
		writer.print(EditorTabs.getCode());
		writer.close();
		EditorTabs.resetTitle();
		Console.println("Saved " + file + " succesfully.");
	}
	
	public static void open() throws IOException {
		if (!FileManager.confirmSaveDialog()) return;
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setFileFilter(new FileNameExtensionFilter("petrified files (*.pet)", "pet"));
        
		int result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			path = fc.getCurrentDirectory().toString();
			filename = fc.getSelectedFile().getName();
			readFile();
		}
	}
	
	public static void save() throws Exception {
		if (path.equals(".") && filename.equals("tmp"))
			saveAs();
		else
			saveFile(path + separator + filename);
	}

	public static void saveAs() throws Exception {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setFileFilter(new FileNameExtensionFilter("petrified files (*.pet)", "pet"));
        
		int result = fc.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			path = fc.getCurrentDirectory().toString();
			filename = fc.getSelectedFile().getName();
			saveFile(path + separator + filename + fileExtension);
		}
	}
	
	public static boolean confirmSaveDialog() {
		if (!EditorTabs.hasChanges()) return true;
		int reply = JOptionPane.showConfirmDialog(null, 
				"Quieres guardar los cambios");
		if (reply == JOptionPane.OK_OPTION)
			try {
				FileManager.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else if (reply == JOptionPane.CANCEL_OPTION || 
				reply == JOptionPane.CLOSED_OPTION)
			return false;
		
		return true;
		
	}
}
