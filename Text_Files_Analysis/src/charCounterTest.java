import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class charCounterTest {

	public static void main(String[] args) {

		ArrayList<String> textLines; 
		FileReader file;
		HashMap<String, Integer> Occurrences;
		HashMap<String, Integer> charOccurrences;
		HashMap<String, Integer> wordOccurrences;
		file = new FileReader("les-mis.txt");
		//		file = new FileReader("stop-list.txt");
		textLines = file.getLines();

		charOccurrences = new HashMap<>();
		for (String str : textLines){
			for (int i = 0; i < str.length(); ++i)
			{
				String charAt = str.substring(i, i+1).toLowerCase();
						
//						Character.toLowerCase(str.charAt(i));
//				if (Character.isLetter(str.charAt(i))){ //assures that we will only be storing letters
				if (charAt.matches("[a-zA-z]") && !charAt.contains("[") && !charAt.contains("]") && !charAt.contains("_")){	
					if (!charOccurrences.containsKey(charAt)) //if key not present then add with count of 1(occurence)
					{
						charOccurrences.put(charAt, 1);
					}
					else //if key is already represented, overwrite and add 1 to current value (ie occurence +1)
					{
						charOccurrences.put(charAt, charOccurrences.get(charAt) + 1);
					}
				}
			}
		
	}
//		charOccurrences.remove("[");
//		charOccurrences.remove("]");
//		charOccurrences.remove("_");
		
		
		HashMap<String, Integer> unsortedMap = charOccurrences;
		System.out.println("Unsorted Map:");
		printMap(unsortedMap);

//		System.out.println("\nTop ten by values:");
//		Map<Character, Integer> sortedMap = sortByValue(unsortedMap);
//		printMap(sortedMap);

		//		System.out.println(wordOccurrences);
	}


	private static Map<Character, Integer> sortByValue(HashMap<String, Integer> unsortedMap) {

		List <Map.Entry<String, Integer>> list =
				new LinkedList<Map.Entry<String, Integer>>((Collection<? extends Entry<String, Integer>>) unsortedMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();
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
