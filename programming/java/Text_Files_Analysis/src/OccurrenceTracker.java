import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class creates methods to count the occurrence of various elements of a text file
 * @author josephhaymaker
 *
 */
public class OccurrenceTracker {

	private ArrayList<String> textLines; 
	private FileReader file;
	private HashMap<String, Integer> letterOccurrences;
	private HashMap<String, Integer> wordOccurrences;
	private HashMap<String, Integer> wordsLessStopList;
	private HashMap<String, Integer> quoteOccurrences;

	/**
	 * The constructor for the class
	 * creates a new FileReader object and stores the lines from the file in an arraylist
	 * The constructor also initializes HashMaps corresponding to all the available methods
	 */
	public OccurrenceTracker(String fileName){
		file = new FileReader(fileName);
		textLines = file.getLines();
		letterOccurrences = new HashMap<String, Integer>();
		wordOccurrences = new HashMap<String, Integer>();
		wordsLessStopList = new HashMap<String, Integer>();
		quoteOccurrences = new HashMap<String, Integer>();
	}

	/**
	 * A method that reads in lines from a file and stores individual letters as keys with occurrence (frequency) as values in a HashMap
	 * @return charOccurrences a HashMap with letters for keys and letter frequency in the text for values
	 */
	public HashMap<String, Integer> getLetterOccurences(){
		for (String str : textLines){
			for (int i = 0; i < str.length(); ++i)
			{
				String charAt = str.substring(i, i+1).toLowerCase(); //to avoid double counting making all letters lowercase
				if (charAt.matches("[a-zA-z]") && !charAt.contains("[") && !charAt.contains("]") && !charAt.contains("_")){		 //assures that we will only be storing letters
					if (!letterOccurrences.containsKey(charAt)) //if key not present then add with count of 1(occurence)
					{
						letterOccurrences.put(charAt, 1);
					}
					else //if key is already represented, overwrite and add 1 to current value (ie occurence +1)
					{
						letterOccurrences.put(charAt, letterOccurrences.get(charAt) + 1);
					}
				}
			}
		}
		return letterOccurrences;
	}


	/**
	 * A method that stores unique words as keys in a HashMap with corresponding occurrence as values
	 * @return wordOccurrences a HashMap with all word keys and occurrence values
	 */
	public HashMap<String, Integer> getWordOccurrences(){
		for (String line : textLines){
			Scanner in = new Scanner(line);
			in.useDelimiter(" ");	
			while (in.hasNext()){
				String chunk = in.next();
				String newChunk = chunk.replace("'s", "").replaceAll("[^A-Za-z]", "").trim().toLowerCase();				
				if (!wordOccurrences.containsKey(newChunk)) //if key not present then add with count of 1(occurence)
				{
					wordOccurrences.put(newChunk, 1);
				}
				else //if key is already represented, overwrite and add 1 to current value (ie occurence +1)
				{
					wordOccurrences.put(newChunk, wordOccurrences.get(newChunk) + 1);
				}
			}
			in.close();
			wordOccurrences.remove(""); //this throws off the count, so needs to be removed
		}
		return wordOccurrences;
	}


	/**
	 * A method that finds all unique words in a provided text file, removing commonly occurring English words that add no significant meaning to the text
	 * @return wordsLessStopList a HashMap with unique words as keys and their instances as values
	 */
	public HashMap<String, Integer> getWordsWStopList(){
		wordsLessStopList = getWordOccurrences();
		Scanner stopListScanner;
		try {stopListScanner = new Scanner(new File("stop-list.txt"));
		while (stopListScanner.hasNext()) {
			String stopListWord = stopListScanner.next();
			if (wordsLessStopList.containsKey(stopListWord)){
				wordsLessStopList.remove(stopListWord);
			}
		} 
		stopListScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Please make sure file name for stop list is correct and file is in the appropriate folder");
			e.printStackTrace();
		}
		return wordsLessStopList;
	}


	/**
	 * A method that finds all strings within double quotes in a given text
	 * @return quoteOccurrences a HashMap with keys of quotations and values of the length (number of characters) of the quotation
	 */
	public HashMap<String, Integer> getQuoteOccurrences(){
		StringTokenizer stk;

		for (String line : textLines){
			stk = new StringTokenizer(line, "\"");
			while (stk.hasMoreTokens()){
				try{
					stk.nextToken();
					String egStr = stk.nextToken().replaceAll("[^a-zA-Z]", " ").trim();
					quoteOccurrences.put(egStr, egStr.length());
				}
				catch (NoSuchElementException nsee){
					break;
				}
			}
		}
		quoteOccurrences.remove("");//remove a key of just ""
		return quoteOccurrences;
	}


	//	Wild Card question: supply a word and program will tell you 
	/**
	 * A method that counts all words in the provided text, as well as the times a user supplied keyword occurs, then returns the occurrence percentage
	 * @param userKeyword a string word supplied by the user that is to be searched for
	 * @return wordPercentage a double value representing the ratio of keyword instances to total word instances
	 */
	public double getKeywordPercentage(String userKeyword){
		String keyword = userKeyword ;
		double totalWordCounter = 0;
		double keywordCount = 0;
		double wordPercentage = 0;

		for (String line : textLines){
			Scanner in = new Scanner(line);
			in.useDelimiter(" ");	
			while (in.hasNext()){
				totalWordCounter++;
				String chunk = in.next();
				String thisChunk = chunk.toLowerCase().replaceAll("[^a-zA-z]", " ").trim();
				if (thisChunk.equalsIgnoreCase(keyword)){
					keywordCount++;
				}
			}
			in.close();
		}
		return wordPercentage = (keywordCount / totalWordCounter)*100;
	}




}



