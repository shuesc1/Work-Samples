package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.math.MyPrime;

public class MyPrimeNextPrimeTest {

	MyPrime mp ;
	
	@Before 
	public void initialize() {
//		mp = new MyPrime();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeIAG() {
		int p = -7 ;
		MyPrime.nextPrime(p) ;
	}
	
	@Test
	public void testCorrectReturnValue1() {
		int p = 3 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 3 or 5", nextP == 3 || nextP == 5) ;
	}
	
	@Test
	public void testCorrectReturnValue2() {
		int p = 4 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 5", nextP == 5) ;
	}
	
	@Test
	public void testCorrectReturnValue3() {
		int p = 13 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 13 or 17", nextP == 13 || nextP == 17) ;
	}
	
	@Test
	public void testCorrectReturnValue4() {
		int p = 1 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 1 or 2", nextP == 1 || nextP == 2) ;
	}
	
	@Test
	public void testCorrectReturnValue5() {
		int p = 2 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 2 or 3", nextP == 2 || nextP == 3) ;
	}
	
	@Test
	public void testCorrectReturnValue6() {
		int p = 377 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 379", nextP == 379) ;
	}
	
	@Test
	public void testCorrectReturnValue7() {
		int p = 244 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 251", nextP == 251) ;
	}
	
	@Test
	public void testCorrectReturnValue8() {
		int p = 10363 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 10369", nextP == 10369) ;
	}

	@Test
	public void testCorrectReturnValue9() {
		int p = 363 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 367", nextP == 367) ;
	}
	
	@Test
	public void testCorrectReturnValue10() {
		int p = 2049 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 2053", nextP == 2053) ;
	}
	
	@Test
	public void testCorrectReturnValue11() {
		int p = 1373654 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 1373677", nextP == 1373677) ;
	}
	
	@Test
	public void testCorrectReturnValue12() {
		int p = 25326002 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 25326002", nextP == 25326002) ;
	}
	
	@Test
	public void testCorrectReturnValue13() {
		int p = 12001 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 12007", nextP == 12007) ;
	}
	
	@Test
	public void testCorrectReturnValue14() {
		int p = 2039 ;
		int nextP = MyPrime.nextPrime(p) ;
		assertTrue("next prime should return 2039 or 2053", nextP == 2039 || nextP == 2053) ;
	}
}
