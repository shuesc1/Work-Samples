package edu.upenn.cis573.hwk1;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCalculator {

	private Map<Integer, String> freqMapping = new HashMap<Integer, String>();
	
	public FrequencyCalculator(Map<Integer, String> initialMapping) {
		freqMapping = initialMapping ;
	}
	
}
