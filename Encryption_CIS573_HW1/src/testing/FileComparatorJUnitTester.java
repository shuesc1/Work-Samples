package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.upenn.cis573.hwk1.Encryptor;
import edu.upenn.cis573.hwk1.FileComparator;

public class FileComparatorJUnitTester {

	String[] baseset = {"E", "S", "o", "l"} ;
	String[] ciphers = {"3" , "5", "0", "1"} ;
	String filepath = "/Users/josephhaymaker/Desktop/test_corpus" ;
	String testFile = "encrypted_test_file.txt" ;
	String testFile2 = "test2.txt" ; //mock original text;  contents- "test file"
	String testFile3 = "test3.txt" ; //mock decrypted text; contents- "t35t fi13"
	FileComparator comp = new FileComparator(testFile2, testFile3) ;
	
	@Test
	public void testGetCorrectChars() {
		comp.compare(baseset);
		int correct = comp.getCorrectChars() ;
		int actualCorrect = 0 ;
		System.out.println(correct) ;
		
		assertEquals("Should be 0 -- none of 4 present base characters were decrypted correctly", correct, actualCorrect) ;
	}
//
	@Test
	public void testGetCorrectChars2() {
		FileComparator comp2 = new FileComparator(testFile2, testFile2) ;
		int correct = comp2.getCorrectChars() ;
		int actualCorrect = 4 ;
		System.out.println(correct) ;
		
		assertEquals("Since it's the same file all 4 base chars should be counted as 'correct'", correct,actualCorrect) ;
	}
	
	@Test
	public void testGetIncorrectChars() {
		comp.compare(baseset);
		int incorrect = comp.getIncorrectChars() ;
		int actualIncorrect = 4 ;
		System.out.println(incorrect) ;
		
		assertEquals(incorrect, actualIncorrect) ;
	}
	
	@Test
	public void testGetIncorrectChars2() {
		FileComparator comp2 = new FileComparator(testFile2, testFile2) ;
		int incorrect = comp.getIncorrectChars() ;
		int actualIncorrect = 0 ;
		System.out.println(incorrect) ;
		
		assertEquals(incorrect, actualIncorrect) ;
	}
}