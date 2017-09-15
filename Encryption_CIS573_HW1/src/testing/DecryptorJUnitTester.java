package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.hwk1.Decryptor;

public class DecryptorJUnitTester {

	String[] baseSet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"} ;
	String[] decryptionKeys = {"z","y","x","w","v","u","t","s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a"} ;
	String filepath = "/Users/josephhaymaker/Desktop/test_corpus" ;
	String testFile = "encrypted_test_file.txt" ;
	Decryptor decr = new Decryptor(baseSet, decryptionKeys,filepath);
	
	
	@Test
	public void testNameDecryptedFile() {
		String expectedName = "encrypted_test_fileDECRYPTED.txt" ;
		String inputEncryptedName = "encrypted_test_fileENCRYPTED.txt" ;
		String classNamed = decr.nameDecryptedFile(inputEncryptedName) ;
//		System.out.println(classNamed) ;
		
		assertEquals("Should be the same if method works correctly", expectedName, classNamed) ;
	}

}
