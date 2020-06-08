import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class readingCharsPractice {

	
	public static void main(String[] args) throws FileNotFoundException{
		
//		create two hashmaps -- only single instances of keys(letters), mapped to values (last position?)
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	HashMap<String, Integer> charCount = new HashMap<String, Integer>();	
	
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter a valid file to analyze:");//input from user -- filename
	
	File newFile = new File(scanner.nextLine());
	Scanner in = new Scanner(newFile);
	Scanner in2 = new Scanner(newFile);
	in.useDelimiter(""); //causes file to be read in 1 char at a time
	in2.useDelimiter(""); //same for this scanner(same info)

	int position = 0;
	
	while (in.hasNext()){
		position++;
		String character = in.next();
		hm.put(character, position);
	}
//	System.out.println(hm.keySet());
	
	for (String key : hm.keySet()){
	System.out.println("key: " + key + " , value: " + hm.get(key));
		int count = 0;
	}
	while(in2.hasNext()){
		if (in2.next().equalsIgnoreCase(key)){
			int count;
			count++;
//				System.out.println(in2.next());
			}
//			charCount.put(key, count);
//		}
//		System.out.println("key: " + key + " , Count: " + charCount.get(key));
//	}
	scanner.close() ;
	in.close();
	in2.close();
//}
//}
}
}
}