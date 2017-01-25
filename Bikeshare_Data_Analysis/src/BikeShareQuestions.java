import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BikeShareQuestions {

	public static void main(String[] args) throws IOException{
		
		FileReader fr = new FileReader("Q3_2016_trips.csv");
		BufferedReader br = new BufferedReader(fr);
		
		String string;
		ArrayList<String> lines = new ArrayList<>();
//		String keyPhrase = "Round trip";
//		String keyPhrase = "Round";
		String keyPhrase = "Walk-up";
		int count = 0;
		
		while ((string = br.readLine()) != null){
			
			
//			String words[] = string.split(" ");
//				for(String word : words){
//					if (word.equalsIgnoreCase(keyPhrase));
			
			if (string.indexOf(keyPhrase) > 0){
					count++;
				}
//			if (string.contains(keyPhrase));
//			lines.add(string);
		}
		System.out.println("The key phrase " + "'" + keyPhrase + "'" + " was found " + count + " times!");
	
//	for (String element: lines){
//		System.out.println(element);
//	}
	
	}
	
//	public BikeShareQuestions() throws FileNotFoundException{
////		FileReaderv2 fr = new FileReaderv2("Q3_2016_trips.csv");
//	}
////	File Q3Trips = new File("Q3_2016_trips.csv");
	
	
}
