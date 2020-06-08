import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A class that takes a HashMap and sorts it according to values
 * @author josephhaymaker
 *
 */
public class HashmapOrdering {

	private HashMap<String, Integer> analyzedHM;

	/**
	 * The constructor for the class. 
	 * Constructor sets the instance variable equal to the parameter
	 * @param unsortedMap a full HashMap with keys and values that are unsorted
	 */
	public HashmapOrdering(HashMap<String, Integer> unsortedMap){
				analyzedHM = unsortedMap;
		//		analyzedHM = (HashMap<String, Integer>) sortByValue(unsortedMap);
	}

/**
 * A method that takes and unsorted HashMap and orders it by value in ascending order
 * @param unsortMap a full HashMap with String keys and Integer values
 * @return sortedMap a Map with elements sorted by value
 */
	public Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
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

	/**
	 * A method that prints out the first 10 elements of a Map with corresponding numbers starting with 1
	 * @param map a filled Map 
	 */
	public <K, V> void printMap(Map<K, V> map) {
		System.out.println("Keys & values: ");
		int counter = 0;
		//		while (counter < 11){
		for (Map.Entry<K, V> entry : map.entrySet()) {
			counter += 1;
			if(counter < 11){
				System.out.println(counter + ". " + entry.getKey()+ " , " + entry.getValue());
			}
		}

		//		}
	}

	/**
	 * A method that prints out the last 10 elements of a Map and adds corresponding numbers counting down from 10
	 * @param map a filled Map 
	 */
	public <K, V> void printReverseMap(Map<K, V> map) {
		System.out.println("Keys & values: ");
		int listCounter = 11;
		int counter = 0;
		int length = map.size();
		int startingPoint = length - 10;
		for (Map.Entry<K, V> entry : map.entrySet()) {
			counter += 1;
			if (counter > startingPoint){
				listCounter--;
				System.out.println(listCounter + ". " + entry.getKey()+ " , " + entry.getValue());
			}

		}
	}






}
