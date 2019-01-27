import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String args[]) {
		String filename = "memorydump.dmp";
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
		
		processData(lines);
	}
	
	public static void processData(String lines[]) {
		String[] trackIdata = new String[4];
		Pattern p0 = Pattern.compile("\\%[0-9a-zA-Z^\\/]*\\?"); //used for finding the track I records
		Matcher m0 = p0.matcher(lines[0]);
		int numTracks = 0;
		while(m0.find()) {
			trackIdata[numTracks] = m0.group(0);
			numTracks++;
		}
		m0 = p0.matcher(lines[1]);
		while(m0.find()) {
			trackIdata[numTracks] = m0.group(0);
			numTracks++;
		}
		
		/*for(int i = 0; i < numTracks; i++) { 			//print out track I records
			System.out.println(trackIdata[i]);
		}*/
		
		Pattern p = Pattern.compile("\\^[a-zA-Z\\/]+\\^"); //used for finding the names
		Matcher m1 = p.matcher(lines[0]);
		Matcher m2 = p.matcher(lines[1]);
		String name1 = "", name2 = "";
		if(m1.find()) {
			name1 = (m1.group(0)); // ^Binhai/Zhu^
		}
		if(m2.find()) {
			name2 = (m2.group(0)); // ^Paxton/John^
		}
		
		Pattern p2 = Pattern.compile("\\^[a-zA-Z\\/]+\\^[0-9][0-9][0-9][0-9][0-9][0-9][0-9]"); //used for finding expiration date
		Matcher a = p2.matcher(lines[0]);													//and cvc number, I think
		Matcher b = p2.matcher(lines[1]);
		String[] expDate = new String[2];
		int i = 0;
		while(a.find()) {
			expDate[i] = (a.group(0));
			i++;
		}
		while(b.find()) {
			expDate[i] = (b.group(0));
			i++;
		}
		
		System.out.println("There are 2 useable track I records in the memory data");
		System.out.println("<Information of the 1st record>");
		System.out.println("Cardholder's Name: " + name1.replace("^", ""));
		System.out.println("Card Number: " + trackIdata[0].substring(2, 18));
		System.out.println("Expiration Date: " + expDate[0].substring(expDate[0].length()-5,expDate[0].length()-3) + "/20" + expDate[0].substring(expDate[0].length()-7,expDate[0].length()-5));
		System.out.println("CVC Number: " + expDate[0].substring(expDate[0].length()-3));
		System.out.println();
		
		System.out.println("<Information of the 2nd record>");
		System.out.println("Cardholder's Name: " + name2.replace("^", ""));
		System.out.println("Card Number: " + trackIdata[3].substring(2, 18));
		System.out.println("Expiration Date: " + expDate[1].substring(expDate[1].length()-5,expDate[1].length()-3) + "/20" + expDate[1].substring(expDate[1].length()-7,expDate[1].length()-5));
		System.out.println("CVC Number: " + expDate[1].substring(expDate[1].length()-3));
		
	}
	
	
}
