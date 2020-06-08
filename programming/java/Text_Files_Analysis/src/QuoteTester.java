import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuoteTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String, Integer> quoteOccurrences = new HashMap<String, Integer>();
		FileReader fr = new FileReader("les-mis.txt");
		ArrayList<String> textLines = fr.getLines();
		
		for (String line : textLines){
			Scanner quoteScanner = new Scanner(line);
			//			quoteScanner.useDelimiter(" ");	
			String pattern = "([\"'])(?:(?=(\\?))\2.)*?\1)";
			Pattern p = Pattern.compile(pattern);

			while (quoteScanner.hasNext()){
				Matcher m = p.matcher(quoteScanner.findInLine(p));

//				if (m.matches()){
				if (m.find()){
					quoteOccurrences.put(m.group(1), m.group(1).length());
				}
			}
			quoteScanner.close();
		}
		
		
	}

}
