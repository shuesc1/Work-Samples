package edu.upenn.cis573.hwk1;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A class that counts the instances of letter occurrences in a text file
 * @author josephhaymaker
 *
 */
public class FrequencyCalculator {

	private Map<String, Integer> freqMappingCharToFreq ;
	private String filepath, currentFilename ;
	private Scanner in;

	/**
	 * The constructor for the class.
	 * It initializes the instance variable map.
	 * @param freqMappingCharToFreq
	 */
	public FrequencyCalculator(Map<String, Integer> freqMappingCharToFreq, String filepath, String currentFilename) {
		this.freqMappingCharToFreq = freqMappingCharToFreq;
		this.filepath = filepath ;
		this.currentFilename = currentFilename ;
	}
	
	public FrequencyCalculator() {
	}

	/**
	 * A method that reads in a file and counts the frequency of the target characters
	 * @param currentFile
	 * @return freqMappingCharToFreq the mapping of characters to their frequencies, now updated with the current file's occurrences
	 */
	public Map<String, Integer> generateFreqMapping(String currentFile){
		in = new Scanner(currentFile).useDelimiter("") ;
		int freq = 0 ;
		String thisChar = "" ;
		while (in.hasNextLine()) {
			while (in.hasNext()) {
				thisChar = in.next().toLowerCase();
				if (freqMappingCharToFreq.containsKey(thisChar)) { //if char is in freqMappingCharToFreq ==> get current freq count, ++, then store key and value again
					freq = freqMappingCharToFreq.get(thisChar) + 1;
					freqMappingCharToFreq.put(thisChar, freq) ;
				}
			}
		}
		in.close();
		
		return freqMappingCharToFreq;
	}

	/**
	 * A method that creates the initial mapping with strings of the characters and values of 0
	 * @param characters
	 * @return
	 */
	public Map<String, Integer> generateInitialMapping(String[] characters){
		for (int i = 0; i < characters.length; i++) {
			freqMappingCharToFreq.put(characters[i], 0);
		}
		return freqMappingCharToFreq;
	}


	/** 
	 * A method that sorts a map based on natural ordering of values (frequencies) in ascending order
	 * Algorithm source: https://www.mkyong.com/java/how-to-sort-a-map-in-java/
	 * @return 
	 */
	public Map<String, Integer> orderMap(){
		List<Map.Entry<String, Integer>> mapToList = new LinkedList<Map.Entry<String, Integer>>(freqMappingCharToFreq.entrySet());
		Collections.sort(mapToList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<String, Integer> sortedfreqMappingCharToFreq = new LinkedHashMap<String, Integer>() ;
		for (Map.Entry<String, Integer> thisMapEntry : mapToList) {
			sortedfreqMappingCharToFreq.put(thisMapEntry.getKey(), thisMapEntry.getValue());
		}
		return sortedfreqMappingCharToFreq;
	}
	
	/**
	 * A helper method that takes the sorted maps and makes an array of their key elements (Strings of chars)
	 * @param sortedMap
	 * @return
	 */
	public String[] sortedMapToSortedArray(Map<String, Integer> sortedMap) {
		Set<String> keys = sortedMap.keySet() ;
		String[] sortedArray =  keys.toArray(new String[0]);
//		String[] sortedArray = new String[keys.size()] ;
//		for (String thisKey : keys) {
//			
//		}
		
		return sortedArray ;
	}
}


