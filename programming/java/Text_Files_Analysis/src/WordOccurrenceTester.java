import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordOccurrenceTester {

	public static void main(String[] args) {

		ArrayList<String> textLines; 
		FileReader file;
		HashMap<String, Integer> Occurrences;
		HashMap<Character, Integer> charOccurrences;
		HashMap<String, Integer> wordOccurrences;
		file = new FileReader("les-mis.txt");
		//		file = new FileReader("stop-list.txt");
		textLines = file.getLines();


		//		public HashMap<String, Integer> getWordOccurrences(){
		wordOccurrences = new HashMap<String, Integer>();

		for (String line : textLines){
			//				String[] chunk = line.split("/s");
			Scanner in = new Scanner(line);
			in.useDelimiter(" ");	
			while (in.hasNext()){
				String chunk = in.next();
//				String s = chunk.replace("'s", "");
				String newChunk = chunk.replace("'s", "").replaceAll("[^A-Za-z]", "").trim().toLowerCase();
				//				char c;	
				//					for (int i = 0; i < chunk.length(); i++){
				//						while (chunk.substring(0, i).matches("[a-zA-Z]")){
				//							newChunk = chunk.substring(0, i);
				//						}
				//					}
				if (!wordOccurrences.containsKey(newChunk)) //if key not present then add with count of 1(occurence)
				{
					wordOccurrences.put(newChunk, 1);
				}
				else //if key is already represented, overwrite and add 1 to current value (ie occurence +1)
				{
					wordOccurrences.put(newChunk, wordOccurrences.get(newChunk) + 1);
				}
			}
			in.close();
			wordOccurrences.remove("");
		}
		
		HashMap<String, Integer> wordsLessStopList;
		wordsLessStopList = wordOccurrences;
		Scanner stopListScanner;
		try {stopListScanner = new Scanner(new File("stop-list.txt"));
			
			while (stopListScanner.hasNext()) {
				String stopListWord = stopListScanner.next();
				if (wordsLessStopList.containsKey(stopListWord)){
					wordsLessStopList.remove(stopListWord);
				}
			} 
			stopListScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Please make sure file name for stop list is correct and file is in the appropriate folder");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		HashMap<String, Integer> unsortedMap = wordsLessStopList;
		System.out.println("Unsorted Map:");
		printMap(unsortedMap);

		System.out.println("\nTop ten by values:");
		Map<String, Integer> sortedMap = sortByValue(unsortedMap);
		printMap(sortedMap);

		//		System.out.println(wordOccurrences);
	}


	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

		List <Map.Entry<String, Integer>> list =
				new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static <K, V> void printMap(Map<K, V> map) {
		System.out.println("Keys & values: ");
		int counter = 0;
		for (Map.Entry<K, V> entry : map.entrySet()) {
//			while (counter < 11){
				counter += 1;
				System.out.println(counter + ". " + entry.getKey()+ " , " + entry.getValue());
//			}
		}
	}
}
