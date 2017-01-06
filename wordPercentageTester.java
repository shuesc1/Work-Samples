import java.util.ArrayList;
import java.util.Scanner;

public class wordPercentageTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileReader fr = new FileReader("les-mis.txt");
		ArrayList<String> textLines = fr.getLines();

		String keyword = "he" ;
		double totalWordCounter = 0;
		double keywordCount = 0;
		double wordPercentage = 0;

		for (String line : textLines){
			Scanner in = new Scanner(line);
			in.useDelimiter(" ");	
			while (in.hasNext()){
				totalWordCounter++;
				String chunk = in.next();
				String thisChunk = chunk.toLowerCase().replaceAll("[^a-zA-z]", " ").trim();
				if (thisChunk.equalsIgnoreCase(keyword)){
					keywordCount++;
				}
			}
			in.close();
		}
		wordPercentage = (keywordCount / totalWordCounter)*100;
		System.out.println("Your word \"" + keyword + "\" comprises " + wordPercentage + "% of this text.");
	}
	
}
