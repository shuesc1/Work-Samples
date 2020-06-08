package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.math.MyFractions;

public class MyFractionsAdSubTest {

	MyFractions mf = new MyFractions();
	Fraction f1, f2, f3, f4, f5, f6, f7, f8 ;

	@Before
	public void intiate() {
		f1 = new Fraction(1/4) ;
		f2 = new Fraction(1/2) ;
		f3 = new Fraction(1/3) ;
		f4 = new Fraction(1) ;
		f5 = new Fraction(0) ;
		f6 = new Fraction(0/1) ;
		f7 = new Fraction((2^Integer.MAX_VALUE)/1) ;
		f8 = new Fraction((-2^Integer.MAX_VALUE)/1) ;
	}

	@Test
	public void testAddSub1() {
		Fraction sum = MyFractions.addSub(f2, f2, true) ;
		assertEquals("sum should be 1", 1, sum.intValue()) ;
	}

	@Test
	public void testAddSub2() {
		Fraction diff = MyFractions.addSub(f4, f2, false) ;
		assertEquals("sum should be .5", 1/2, diff.intValue()) ;
	}

	@Test
	public void testAddSub3() {
		Fraction sum = MyFractions.addSub(f5, f5, true) ;
		assertEquals("sum should be 0", 0, sum.intValue()) ;
	}

	@Test
	public void testAddSub4() {
		Fraction sum = MyFractions.addSub(f4, f5, true) ;
		assertEquals("sum should be 1", 1, sum.intValue()) ;
	}

	@Test
	public void testAddSub5() {
		Fraction sum = MyFractions.addSub(f4, f6, true) ;
		assertEquals("sum should be 1", 1, sum.intValue()) ;
	}

	@Test
	public void testMaxIntValues() {
		Fraction diff = MyFractions.addSub(f7, f8, false) ;
		assertEquals("diff should be 1", 0, diff.intValue()) ;
	}

	@Test
	public void testMaxIntValues2() {
		Fraction diff = MyFractions.addSub(f7, f7, false) ;
		assertEquals("diff should be 0", 0, diff.intValue()) ;
	}

	@Test
	public void testMaxIntValues3() {
		Fraction large = new Fraction(f7.intValue()-(20)) ;
		Fraction sum = MyFractions.addSub(large, f3, true) ;
		assertNotEquals("sum should not be as large as Integer.MAX_VALUE", f7.intValue(), sum.intValue()) ;
	}

	@Test
	public void testMaxIntValues4() {
		Fraction sum = MyFractions.addSub(f7, f3, false) ;
		assertNotEquals("sum should not be as large as Integer.MAX_VALUE", f7.intValue(), sum.intValue()) ;
	}
	
	@Test
	public void testMaxIntValues5() {
		Fraction frac = new Fraction(1234565678/1) ;
		Fraction sum = MyFractions.addSub(frac, f3, false) ;
		assertNotEquals("sum should not be as large as Integer.MAX_VALUE", f7.intValue(), sum.intValue()) ;
	}
	
	@Test
	public void testMaxIntValues6() {
		Fraction frac = new Fraction(1234565678/1) ;
		Fraction sum = MyFractions.addSub(frac, f3, true) ;
		assertNotEquals("sum should not be as large as Integer.MAX_VALUE", f7.intValue(), sum.intValue()) ;
	}
	
	@Test
	public void testMaxIntValues7() {
		Fraction frac1 = new Fraction((2^Integer.MAX_VALUE - 2^100)/479) ;
		Fraction frac2 = new Fraction(1233/479) ;
		Fraction sum = MyFractions.addSub(frac1, frac2, true) ;
		assertNotEquals("sum should not be as large as Integer.MAX_VALUE", f7.intValue(), sum.intValue()) ;
	}

	@Test
	public void testZeroNumeratorSubtraction() {
		Fraction diff = MyFractions.addSub(f6, f4, false) ;
		assertEquals("diff should be -1", -1, diff.intValue()) ;
	}

	@Test
	public void testAddition1() {
		Fraction sum = MyFractions.addSub(f2, f2, true) ;
		assertEquals("sum should be 1", 1, sum.intValue()) ;
	}
	
	@Test
	public void testAddition2() {
		Fraction frac1 = new Fraction(5/9) ;
		Fraction frac2 = new Fraction(4/7) ;
		Fraction sum = MyFractions.addSub(frac1, frac2, true) ;
		assertEquals("sum should be 71/63", 71/63, sum.intValue()) ;
	}
	
	@Test
	public void testAddition3() {
		Fraction frac1 = new Fraction(3948785/7873245) ;
		Fraction frac2 = new Fraction(4/12317) ;
		Fraction sum = MyFractions.addSub(frac1, frac2, true) ;
		assertNotEquals("shouldn't be max int value", f7, sum.intValue()) ;
	}
	
	@Test
	public void testAddition4() {
//		Fraction frac1 = new Fraction(3948785/7873245) ;
//		Fraction frac2 = new Fraction(4/12317) ;
		Fraction sum = MyFractions.addSub(f4, f4, true) ;
		assertEquals("sum should be 2", 2/1, sum.intValue()) ;
	}
	
	@Test
	public void testAddition5() {
		Fraction frac1 = new Fraction(3948785/1) ;
		Fraction frac2 = new Fraction(4/1) ;
		Fraction sum = MyFractions.addSub(frac1, frac2, true) ;
		assertEquals("sum should be 3948789/1", 3948789/1, sum.intValue()) ;
	}
	
	@Test
	public void testAddition6() {
		Fraction frac1 = new Fraction(17/1) ;
		Fraction frac2 = new Fraction(37/1) ;
		Fraction sum = MyFractions.addSub(frac1, frac2, true) ;
		assertEquals("sum should be 54/1", 54/1, sum.intValue()) ;
	}
	
	@Test
	public void testSubtraction1() {
		Fraction frac1 = new Fraction(5/9) ;
		Fraction frac2 = new Fraction(4/7) ;
		Fraction diff = MyFractions.addSub(frac1, frac2, false) ;
		assertEquals("diff should be -1/63", -1/63, diff.intValue()) ;
	}
	
	@Test
	public void testSubtraction2() {
//		Fraction frac1 = new Fraction(5/9) ;
		Fraction frac2 = new Fraction(230938490/3) ;
		Fraction diff = MyFractions.addSub(f1, frac2, false) ;
		assertNotEquals("shouldn't be max int value", f7, diff.intValue()) ;
	}

	@Test
	public void testNullInputs1() {
		Fraction nullFrac = null ;
		try {
			Fraction sum = MyFractions.addSub(nullFrac, f2, true) ;
			fail("input is null, should throw NullArgmuentException") ;
		} catch(NullArgumentException nae) {

		}
	}
	
	@Test
	public void testNullInputs2() {
		Fraction nullFrac = null ;
		try {
			Fraction sum = MyFractions.addSub(f2, nullFrac, true) ;
			fail("input is null, should throw NullArgmuentException") ;
		} catch(NullArgumentException nae) {

		}
	}
	
	



}
