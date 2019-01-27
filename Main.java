import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String args[]) {
		String filename = "";
		String line = null;
		String lines[] = new String[2];
		int i = 0;
		//read in file
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				lines[i] = line;
				i++;
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to find/open file.");
		}
		catch(IOException ex) {
			System.out.println("Error reading file");
		}
		//print out 2 lines from .dmp file
		for(int j = 0; j < 2; j++) {
			//System.out.println(lines[j]);
		}
		
		processData(lines);
	}
	
	public static void processData(String lines[]) {
		Pattern p = Pattern.compile("\\^[a-zA-Z\\/]+\\^");
		Matcher m = p.matcher(lines[0]);
		if(m.find()) {
			String name = (m.group(0)); // ^Binhai/Zhu^
		}
    
	}
	
	
}
