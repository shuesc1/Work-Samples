import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HTMLValidatorTester {

	HTMLValidator val1;
	HTMLValidator val2;
	HTMLValidator val3;
	HTMLValidator val4;
	HTMLValidator val5;
	
	@Before
	public void setUp() throws Exception {
		val1 = new HTMLValidator("html1.html");
		val2 = new HTMLValidator("html2.html");
		val3 = new HTMLValidator("html3.html");
		val4 = new HTMLValidator("html4.html");
		val5 = new HTMLValidator("html5_INVALID.html");
	}

	@Test
	public void testValid1() {
		assertTrue("Is a valid file", val1.isValid());
	}
	
	@Test
	public void testValid2() {
		assertTrue(val2.isValid());
	}

	@Test
	public void testValid3() {
		assertTrue(val3.isValid());
	}
	
	@Test
	public void testValid4() {
		assertTrue(val4.isValid());
	}
	
	@Test
	public void testValid5() {
		assertFalse("Is not a valid file", val5.isValid());
	}
}
