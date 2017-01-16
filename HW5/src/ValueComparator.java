import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ValueComparator implements Comparator {
	Map map;
	
	public ValueComparator(Map map) {
		this.map = map;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return ((Integer) map.get(o2)).compareTo((Integer) map.get(o1));
	}


//
//	public Map sortByValue(Map map) { 
//		List list = new LinkedList(map.entrySet()); 
//		Collections.sort(list, new Comparator() { 
//			public int compare(Object o2, Object o1) { 
//				return ((Comparable) ((Map.Entry) (o1)).getValue()) .compareTo(((Map.Entry) (o2)).getValue()); } }); 
//		Map result = new LinkedHashMap(); for (Iterator it = list.iterator(); it.hasNext();) {
//			Map.Entry entry = (Map.Entry)it.next(); result.put(entry.getKey(), entry.getValue()); } 
//		return result; }










}
