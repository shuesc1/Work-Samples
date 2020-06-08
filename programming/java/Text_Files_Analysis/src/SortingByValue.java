import java.util.List;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortingByValue {

	public static void main(String[] args){
		Map<String,Integer> unsortedMap = new HashMap<String,Integer>();
		unsortedMap.put("one",1);
		unsortedMap.put("ten",10);
		unsortedMap.put("three",3);
		unsortedMap.put("seven",7);
		unsortedMap.put("twelve",12);
		unsortedMap.put("one hundred",100);
		unsortedMap.put("thirty",30);
		unsortedMap.put("twenty",20);
		unsortedMap.put("one seventeen",117);
		unsortedMap.put("fifty",50);
		unsortedMap.put("ninety three",93);
		unsortedMap.put("twenty two",22);

		//TreeMap<String,Integer> treemap = new TreeMap<String,Integer>();
		//	    Set<String> keyList = unsortedMap.keySet();
		// for (String key : unsortedMap.keySet()){
		//treemap.put(key, unsortedMap.get(key));
		//}

		//	    treemap = (TreeMap<String, Integer>) unsortedMap.entrySet();
		//	    map.put("one", 1);
		//	    map.put("two", 2);
		//	    map.put("seventy", 70);
		//NavigableSet nmap = treemap.descendingKeySet();
		//	    NavigableMap<String,Integer> nmap = treemap.descendingMap();
		//	    for (NavigableMap.Entry<String, Integer> entry : ((Map<String, Integer>) nmap).entrySet()) {
		//	        System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		//	    }
		//		System.out.println(nmap);
		//		System.out.println("Unsorted Map:");
		//		printMap(unsortedMap);

		System.out.println("\nTop ten by values:");
		Map<String, Integer> sortedMap = sortByValue(unsortedMap);
		printMap(sortedMap);
		printReverseMap(sortedMap);
		//		printMap(nmap);
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
			counter += 1;
			if (counter < 11){
				System.out.println(counter + ". " + entry.getKey()+ " , " + entry.getValue());
			}

		}
	}


	public static <K, V> void printReverseMap(Map<K, V> map) {
		System.out.println("Keys & values: ");
		int listCounter = 0;
		int counter = 0;
		int length = map.size();
		int startingPoint = length - 10;
		for (Map.Entry<K, V> entry : map.entrySet()) {
			counter += 1;
			if (counter > startingPoint){
				listCounter++;
				System.out.println(listCounter + ". " + entry.getKey()+ " , " + entry.getValue());
			}

		}
	}
	//	public static <K, V> void printReverseMap(Map<K, V> map) {
	//		System.out.println("Keys & values: ");
	//		int counter = 0;
	////		int numKeysandValues = map.size();
	//		String allKeys = map.toString();
	//		System.out.println(allKeys);
	//		ArrayList<String> list = new ArrayList(map.entrySet());
	//		int indices = list.size();
	//		for (int i = 1; i < 11; i++){
	//			counter++;
	//			int end = indices - i;
	//			System.out.println(counter + "Keys & values:" + list.get(end));
	//		}
	//	}



}








