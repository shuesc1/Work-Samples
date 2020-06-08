import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ComparatorTester {

	public static void main(String[] args) {
	
	HashMap<String, Integer> hm = new HashMap<String, Integer>();

	hm.put("this", 33);
	hm.put("Ahead of our time", 8);
	hm.put("dessicated", 1043);
	hm.put("the", 10);
	hm.put("them", 10);
	
	ValueComparator vc = new ValueComparator(hm);
	Map<String, Integer> descendingSortedMap = new TreeMap(vc);
	descendingSortedMap.putAll(hm);
	
	Map<String, Integer> reverseMap = new TreeMap<String, Integer>(Collections.reverseOrder());
	reverseMap.putAll(hm);
	
//	System.out.println(descendingSortedMap.entrySet());
	System.out.println(reverseMap.entrySet());
	
//	MyComparator comp = new MyComparator(myMap);
//	Map<String, Integer> newMap = new TreeMap(comp);
//	newMap.putAll(myMap);
	

	/*
     * Java method to sort Map in Java by value e.g. HashMap or Hashtable
     * throw NullPointerException if Map contains null values
     * It also sort values even if they are duplicates
     */
//    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
//        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
//      
//        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {
//
//            @Override
//            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
//                return o1.getValue().compareTo(o2.getValue());
//            }
//        });
//      
//        //LinkedHashMap will keep the keys in the order they are inserted
//        //which is currently sorted on natural ordering
//        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
//      
//        for(Map.Entry<K,V> entry: entries){
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//      
//        return sortedMap;
//    }
//
//}


	
	
	
	
	
	
	
	

	}

}
