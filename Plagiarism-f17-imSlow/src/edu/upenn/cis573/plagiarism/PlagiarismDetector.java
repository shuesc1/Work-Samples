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

	public int threshold = 0;

	/*
	 * This method reads the given file and then converts it into a List of Strings.
	 * It does not include punctuation and converts all words in the file to uppercase.
	 */
//	private static List<String> readFile(String filename) { //helper file for below method
//		if (filename == null) {
//			return null;
//		}
//		List<String> words = new ArrayList<String>(); //ArrayList of all words -- allows for repeats
//		try {
//			Scanner in = new Scanner(new File(filename)); //creates new Scanner obj
//			while (in.hasNext()) {	//while more words
//				//[[[SMALL CHANGE]]] -- to lowercase, not uppercase -- less changes to make
//				words.add(in.next().replaceAll("[^a-zA-Z]", "").toLowerCase()); //removes non-letter chars, to upper case
//				if(words.size() % threshold == 0) {
//
//				}
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return words;
//	}

	/*
	 * This method reads a file and converts it into a Set of distinct phrases,
	 * each of size "window". The Strings in each phrase are whitespace-separated.
	 */
	public static Set<String> createPhrases(String filename, int window) {
		//		String test = "It's the number 1 way.";
		//		String[] tokens = test.split(" ");       // Single blank is the separator.
		if (filename == null || window < 1) {
			return null;
		}
		//**MAJOR CHANGE** - eliminate the creation of a word list and just use the window from the start to create a phrase list
		// refactor method so we don't have to change any code below
		//		List<String> words = readFile(filename); //gets ArrayList of all words in doc
		Set<String> phrases = new HashSet<String>();
		//[MINOR CHANGE] - instead of using a giant AL of words just reuse same AL to get (#window) many words, make a phrase, then clear and reuse AL
		List<String> words = new ArrayList<String>() ;
		try {
			Scanner in = new Scanner(new File(filename)); 
			while (in.hasNext()) {	
				//[[[MINOR CHANGE]]] -- to lowercase, not uppercase -- less changes to make
				words.add(in.next().replaceAll("[^a-zA-Z]", "").toLowerCase()); //add each word to words arraylist
				if(words.size() % window == 0) { //then we have the right amount of words stored
					String phrase = "" ;
					for(String word: words) {
						phrase = phrase + " " + word ; //build correctly sized phrase
					}
//					System.out.println("Phrase added: " + phrase) ; //for testing
					//[MINOR CHANGE] -- got rid of check for if phrase already here (see below line)
					phrases.add(phrase) ; // if a repeat it won't be added due to data struct
					words.clear(); //clears up space for next (#window) many words
				}
			}
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//		Set<String> phrases = new HashSet<String>(); //TODO now uses set/hashset - wrong data struct
		//count up to size -- seems like a list may be a better data struct?
		//TODO figure out why this counts up to window + 1 -- maybe stop before you have less words
		// than you need to create a proper phrase
		//		for (int i = 0; i < words.size() - window + 1; i++) {
		//			String phrase = ""; //phrase stored as a string -- maybe better as an Array (always fixed size)
		//			//
		//			for (int j = 0; j < window; j++) {
		//				phrase += words.get(i+j) + " ";
		//				//TODO phrase = 
		//			}
		//[MINOR CHANGE] - since we are using a hashset we can't have repeat values anyway
		//			if (phrases.contains(phrase) == false) //unnecessary work -- if this is a HM then there can only
		//				phrases.add(phrase);
		//		}	
		return phrases;
	}



	/*
	 * Returns a Set of Strings that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	static int findMatches(Set<String> myPhrases, Set<String> yourPhrases ) {
		int matches = 0 ;
		Set<String> yourPhrasesCopy = yourPhrases ;
		/* **MAJOR CHANGE** - got rid of nested for loop, now just iterating over my phrase list */
		for (String mine : myPhrases) {
			if(yourPhrasesCopy.contains(mine)) {
//				System.out.println("Match: " + mine) ;
				matches++;
				yourPhrasesCopy.remove(mine) ;
			}
		}	
		return matches;
	}


	/*
	 * Returns a Map (sorted by the value of the Integer, in non-ascending order) indicating
	 * the number of matches of phrases of size windowSize or greater between each document in the corpus
	 * 
	 * Note that you may NOT remove this method or change its signature or specification!
	 */
	public static Map<String, Integer> detectPlagiarism(String dirName, int windowSize, int threshold) {
		File dirFile = new File(dirName);
		String[] files = dirFile.list(); //list of file names
		Map<String, Integer> numberOfMatches = new TreeMap<String, Integer>();
		ArrayList<String> processedPairs = new ArrayList<String>() ;
		for (int i = 0; i < files.length; i++) { //iterate over all files in corpus
			String file1 = files[i]; 
			Set<String> file1Phrases = createPhrases(dirName + "/" + file1, windowSize); //***MAJOR CHANGE*** - move this to outer for loop so it's only done once, not j many times
			for (int j = 0; j < files.length; j++) { 
				String file2 = files[j]; 
				//[[SMALL CHANGE]] - moved the 2 identical file comparison names out here so the same pairs of files aren't checked twice
				String key1 = file1 + "-" + file2;
				String key2 = file2 + "-" + file1;
				if(!file1.equals(file2) && !processedPairs.contains(key1) && !processedPairs.contains(key2)) { /*[[SMALL CHANGE]]] -- don't compare file to itself */
					Set<String> file2Phrases = createPhrases(dirName + "/" + file2, windowSize); 
					if (file1Phrases == null || file2Phrases == null)
						return null;
					int matches = findMatches(file1Phrases, file2Phrases);
					//			if (matches == null){ return null;	} /* [[SMALL CHANGE]] - unnecessary, removing statement					
					if (matches > threshold) {
						//[[SMALL CHANGE]] -- removed " && file1.equals(file2) == false" condition from below since same files shouldn't ever be compared
						if (numberOfMatches.containsValue(key1) == false) {
							numberOfMatches.put(key1, matches);
							processedPairs.add(key1);
							processedPairs.add(key2);
						}
					}

				}
			}

		}
		//[[SMALL CHANGE]]- tried using treemap for descending sorting, but then realized that I'd need repeat keys (not possible w/ TM)
		//instead created a (hopefully) more efficient sorting method
		return MapSort.sortByValue(numberOfMatches) ;
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
		Map<String, Integer> map = PlagiarismDetector.detectPlagiarism(directory, 4, 5);
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
