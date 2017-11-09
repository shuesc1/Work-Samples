package edu.upenn.cis573.plagiarism;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapSort {

	public static Map sortByValue(Map unsortedMap){
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}
//	public static Map sortByKey(Map unsortedMap){
//		Map sortedMap = new TreeMap();
//		sortedMap.putAll(unsortedMap);
//		return sortedMap;
//	}


	public static void main(String[] args){
		Map map = new HashMap();

		//*value Class should implements the Comparable interface
		//*String implements Comparable by default.

//		map.put("Z", "3");
		map.put("Z", "5") ;
		map.put("D", "4");
		map.put("A", "1");
		map.put("B", "2");
		map.put("F", "6");
		map.put("E", "5");

		System.out.println("Unsorted Map: "+ map);
		System.out.println("Sorted Map By Values: "+MapSort.sortByValue(map));
//		System.out.println("Sorted Map By Keys: "+MapSort.sortByKey(map));

	}
}


