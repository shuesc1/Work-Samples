package edu.upenn.cis573.plagiarism;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/*
 * This class implements a simple plagiarism detection algorithm.
 * 
 * Your goal is to improve its execution time by identifying three major inefficiencies
 * (each of which will result in at least a 10% reduction in execution time compared to the
 * original), as well as ten minor inefficiencies.
 */
public class PlagiarismDetector {

	/*
	 * This method reads the given file and then converts it into a List of Strings.
	 * It does not include punctuation and converts all words in the file to uppercase.
	 */
	private static List<String> readFile(String filename) { //helper file for below method
		if (filename == null) {
			return null;
		}
		List<String> words = new ArrayList<String>(); //ArrayList of all words -- allows for repeats
		try {
			Scanner in = new Scanner(new File(filename)); //creates new Scanner obj
			while (in.hasNext()) {	//while more words
				//TODO should be lowercase -- less changes
				words.add(in.next().replaceAll("[^a-zA-Z]", "").toUpperCase()); //removes non-letter chars, to upper case
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return words;
	}

	/*
	 * This method reads a file and converts it into a Set of distinct phrases,
	 * each of size "window". The Strings in each phrase are whitespace-separated.
	 */
	private static Set<String> createPhrases(String filename, int window) {
		//		String test = "It's the number 1 way.";
		//		String[] tokens = test.split(" ");       // Single blank is the separator.

		if (filename == null || window < 1) {
			return null;
		}
		List<String> words = readFile(filename); //gets ArrayList of all words in doc
		Set<String> phrases = new HashSet<String>(); //TODO now uses set/hashset - wrong data struct
		//count up to size -- seems like a list may be a better data struct?
		//TODO figure out why this counts up to window + 1 -- maybe stop before you have less words
		// than you need to create a proper phrase
		for (int i = 0; i < words.size() - window + 1; i++) {
			String phrase = ""; //phrase stored as a string -- maybe better as an Array (always fixed size)
			//
			for (int j = 0; j < window; j++) {
				phrase += words.get(i+j) + " ";
				//TODO phrase = 
			}
			if (phrases.contains(phrase) == false) //unnecessary work -- if this is a HM then there can only
				//be one key instance anyway
				phrases.add(phrase);
		}	
		return phrases;
	}



	/*
	 * Returns a Set of Strings that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	private static int findMatches(Set<String> myPhrases, Set<String> yourPhrases ) {
		int matches = 0 ;
		Set<String> myPhrasesCopy = myPhrases ;
		Set<String> yourPhrasesCopy = yourPhrases ;
		for (String mine : myPhrasesCopy) {
			for(String yours: yourPhrasesCopy) {
				if (mine.equalsIgnoreCase(yours)) {
					matches++ ;
					myPhrasesCopy.remove(mine) ; //remove match from both lists
					yourPhrasesCopy.remove(yours) ;
					break ;
				}
			}
		}
		return matches;
	}

	//TODO get rid of this method and instead use a TreeMap of (key=match num, value= file names)
	/*
	 * Returns a LinkedHashMap in which the elements of the Map parameter
	 * are sorted according to the value of the Integer, in non-ascending order.
	 */
	public static LinkedHashMap<String, Integer> sortResults(Map<String, Integer> possibleMatches) {
		// Because this approach modifies the Map as a side effect of printing 
		// the results, it is necessary to make a copy of the original Map
		Map<String, Integer> copy = new HashMap<String, Integer>();
		for (String key : possibleMatches.keySet()) {
			copy.put(key, possibleMatches.get(key));
		}		
		LinkedHashMap<String, Integer> list = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < copy.size(); i++) {
			int maxValue = 0;
			String maxKey = null;
			for (String key : copy.keySet()) {
				if (copy.get(key) > maxValue) {
					maxValue = copy.get(key);
					maxKey = key;
				}
			}		
			list.put(maxKey, maxValue);	
			if (copy.containsKey(maxKey)) {
				copy.put(maxKey, -1);
			}
		}
		return list;
	}

	/*
	 * Returns a Map (sorted by the value of the Integer, in non-ascending order) indicating
	 * the number of matches of phrases of size windowSize or greater between each document in the corpus
	 * 
	 * Note that you may NOT remove this method or change its signature or specification!
	 */
	public static Map<Integer, String> detectPlagiarism(String dirName, int windowSize, int threshold) {
		File dirFile = new File(dirName);
		String[] files = dirFile.list(); //list of file names
		Map<Integer, String> numberOfMatches = new TreeMap<Integer, String>();
		for (int i = 0; i < files.length; i++) { //iterate over all files in corpus
			String file1 = files[i];
			for (int j = 0; j < files.length; j++) { 
				//TODO make sure file isn't compared to itself
				String file2 = files[j];
				//uses helper method CREATEPHRASES
				Set<String> file1Phrases = createPhrases(dirName + "/" + file1, windowSize); 
				Set<String> file2Phrases = createPhrases(dirName + "/" + file2, windowSize); 
				if (file1Phrases == null || file2Phrases == null)
					return null;
				//uses helper method FINDMATCHES
				//TODO ESSENTIALLY HELPER METHOD CREATES SET OF MATCHES ONLY TO USE THE SET SIZE
				//TO DETERMINE IF IT'S ABOVE THE THRESHOLD OR NOT
				int matches = findMatches(file1Phrases, file2Phrases);
				//TODO necessary?
				//			if (matches == null)
				//				return null;					
				if (matches > threshold) {
					String key = file1 + "-" + file2;
					//TODO make it so same files are never compared above
					if (numberOfMatches.containsValue(file2 + "-" + file1) == false && file1.equals(file2) == false) {
						numberOfMatches.put(matches, key);
					}
//					((Object) numberOfMatches).reverseSort();
				}				
			}

		}	
		//uses helper method SORTRESULTS to print out which 
		((TreeMap) numberOfMatches).descendingMap() ;
		return numberOfMatches ;
//		return sortResults(numberOfMatches);
	}

	//======================= MAIN =============================
	/*
	 * This method is here to help you measure the execution time and get the output of the program.
	 * You do not need to consider it for improving the efficiency of the detectPlagiarism method.
	 */
	///  Users/josephhaymaker/eclipse-workspace/Plagiarism-f17-imSlow/docs
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the directory containing the corpus.");
			System.exit(0);
		}
		String directory = args[0];
		long start = System.currentTimeMillis();
		//creates new PlaigarismDetector object-- common phrases size 4, threshold 5
		Map<Integer, String> map = PlagiarismDetector.detectPlagiarism(directory, 4, 5);
		long end = System.currentTimeMillis();
		double timeInSeconds = (end - start) / (double)1000;
		System.out.println("Execution time (wall clock): " + timeInSeconds + " seconds");
		Set<Map.Entry<String, Integer>> entries = map.entrySet();
		for (Map.Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		// t1: 115.358 seconds
		//t2: 124.048 seconds
		//t3: 114.542 seconds
		//t4: 114.883 seconds
		//t5: 129.186 seconds
	}

}
