package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.hwk1.Decryptor;

public class DecryptorJUnitTester {

	@Before
	public void setUp() throws Exception {
		char[] baseSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'} ;
		char[] decryptionKeys = {'z','y','x','w','v','u','t','s','r','q','p','o','n','m','l','k','j','i','h','g','f','e','d','c','b','a'} ;
		Decryptor decr = new Decryptor(baseSet, decryptionKeys);
		String testFile = "encrypted_test_file.txt" ;
	}
	
	@Test
	public void testDecrypt() {
		fail("Not yet implemented");
	}

}
