package edu.upenn.cis573.plagiarism;

import java.util.Comparator;
import java.util.Map;

//source: http://hmkcode.com/sorting-java-map-by-key-value/ (modified)
public class ValueComparator implements Comparator{

	Map map;
	public ValueComparator(Map map){
		this.map = map;
	}
	public int compare(Object keyA, Object keyB){
		Comparable valueA = (Comparable) map.get(keyA);
		Comparable valueB = (Comparable) map.get(keyB);
//		System.out.println(valueA +" - "+valueB);
		int x = valueB.compareTo(valueA) ;
		if (x == 0) {
			return 1;
		} else {
			return x;
		}
	}
}
