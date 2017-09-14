package edu.upenn.cis573.hwk1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FrequencyCalculator {
	
	private Map<String, Integer> freqMappingCharToFreq ;
	private String filepath ;

	/**
	 * The constructor for the class.
	 * It initializes the instance variable map.
	 * @param freqMappingCharToFreq
	 */
	public FrequencyCalculator(Map<String, Integer> freqMappingCharToFreq, String filepath) {
		this.freqMappingCharToFreq = freqMappingCharToFreq;
		this.filepath = filepath ;
	}
	
	/**
	 * A method that reads in a file and counts the frequency of the target characters
	 * @param currentFile
	 * @return
	 */
	public Map<String, Integer> generateFreqMapping(String currentFile){
		//TODO read in file and iterate over chars
		//TODO if char is in freqMappingCharToFreq ==> get current freq count, ++, then store key and value again
		//int freq = freqMappingCharToFreq.get(char) + 1;
		//freqMappingCharToFreq.put(char, freq) ;
		
	
		
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
}
