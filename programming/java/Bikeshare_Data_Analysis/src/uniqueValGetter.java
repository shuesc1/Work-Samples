		import java.util.*;

public class uniqueValGetter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

String[] list = {"I", "am", "he", "i", "am", "she", "i", "am", "sam", "i", "am", "this", "i", "am", "that"};
	
		        Set<String> uniques = new HashSet<String>();
		        Set<String> dups    = new HashSet<String>();

		        for (String a : list){
		            if (!uniques.add(a)){
		                dups.add(a);
		            }
		        }
		      
		        // Destructive set-difference
		        uniques.removeAll(dups);

		        System.out.println("Unique words:    " + uniques);
		        System.out.println("Duplicate words: " + dups);
		    }
		
		
	}


