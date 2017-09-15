package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.hwk1.Decryptor;
import edu.upenn.cis573.hwk1.Encryptor;

public class EncryptorJUnitTester {

	String[] baseset = {"E", "S", "o", "l"} ;
	String[] ciphers = {"3" , "5", "0", "1"} ;
	String filepath = "/Users/josephhaymaker/Desktop/test_corpus" ;
	Encryptor e = new Encryptor(baseset, ciphers, filepath) ;
	String testFile = "encrypted_test_file.txt" ;
	
	@Before
	public void setUp() throws Exception {
//		char[] baseSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'} ;
//		char[] decryptionKeys = {'z','y','x','w','v','u','t','s','r','q','p','o','n','m','l','k','j','i','h','g','f','e','d','c','b','a'} ;
	}
	
	@Test
	public void testNameEncryptedFile() {
		String expectedName = "encrypted_test_fileENCRYPTED.txt" ;
		String methodName = e.nameEncryptedFile(testFile) ;
		
		assertEquals("Should be the same if method works correctly", methodName, expectedName) ;
	}

	@Test
	public void testGetEncryptedFilename() {
		String encryptedName = "encrypted_test_fileENCRYPTED.txt" ;
		e.setEncryptedFilename(encryptedName);
		String methodReturn = e.getEncryptedFilename() ;
		
		assertEquals("Should return the string it was set to", methodReturn, encryptedName) ;
	}
	
	@Test
	public void testSetEncryptedFilename() {
		String encryptedName = "encrypted_test_fileENCRYPTED.txt" ;
		e.setEncryptedFilename(encryptedName);
		String objectsEncrFilename = e.getEncryptedFilename() ;
		
		assertEquals("Should correctly append 'ENCRYPTED' to file prefix", objectsEncrFilename, encryptedName) ;
	}
	
	@Test
	public void testCharsToLowercase() {
		String a = "a" ;
		String[] arr = {"A"} ;
		String[] burr = {"B"} ;
		Encryptor e2 = new Encryptor(arr, burr, filepath) ;
		e2.charsToLowercase(arr) ;
		
		assertEquals("Should return array characters in lower case", arr[0], a) ;
	}
}
