package edu.upenn.cis573.plagiarism;

import java.util.HashSet;
import java.util.Set;

public class mainTester {

	public static void main(String[] args) {
	Set<String> myPhrases = new HashSet<>();
	Set<String> yourPhrases = new HashSet<>() ;
	myPhrases.add("I am") ;
	myPhrases.add("You are") ;
	myPhrases.add("He is");
	myPhrases.add("She is");
	myPhrases.add("We are") ;
	myPhrases.add("You all are") ;
	myPhrases.add("They are") ;
	
	yourPhrases.add("Yo soy") ;
	yourPhrases.add("I am") ;
	yourPhrases.add("Tu eres") ;
	yourPhrases.add("Ella es") ;
	yourPhrases.add("She is") ;
	yourPhrases.add("Nosotros somos") ;
	yourPhrases.add("Vosotros sois");
	yourPhrases.add("Ellos son") ;
	yourPhrases.add("They are") ;
	
//	Set<String> set1 = PlagiarismDetector.createPhrases("test_file.txt", 2) ; //all changed to 'public' for testing
//	Set<String> set2 = PlagiarismDetector.createPhrases("test_file2.txt", 2) ;
//	int matches = PlagiarismDetector.findMatches(myPhrases, yourPhrases) ;
//	System.out.println("final no. matches: " + matches) ;
	}

}
