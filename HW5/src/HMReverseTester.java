import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HMReverseTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("one",1);
		map.put("ten",10);
		map.put("three",3);
		map.put("seven",7);
		map.put("twelve",12);
		map.put("one hundred",100);
		map.put("thirty",30);
		map.put("twenty",20);
		map.put("one seventeen",117);
		map.put("fifty",50);
		map.put("ninety three",93);
		map.put("twenty two",22);
		
		Map<String, List<Integer>> reverseMap = map.entrySet()
			    .stream()
			    .collect(Collectors.groupingBy(Map.Entry::getValue,
			        Collectors.mapping(
			            Map.Entry::getKey,
			            Collectors.toList())));
		System.out.println(reverseMap);
		
		
		
		
		
	}

}
