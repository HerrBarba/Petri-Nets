package utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadFile {
	
	public static ArrayList<String> openFile(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> text = new ArrayList<String>();
		String line = br.readLine();
		
		while (line != null) {
			text.add(line);		
			line = br.readLine();
		}
		
		br.close();
		return text;
	}
}
