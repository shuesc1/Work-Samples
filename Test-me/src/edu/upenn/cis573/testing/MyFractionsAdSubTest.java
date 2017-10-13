package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.math.MyFractions;

public class MyFractionsAdSubTest {

	Fraction f1, f2, f3, f4, f5, f6 ;

	@Before
	public void intiate() {
		f1 = new Fraction(1/4) ;
		f2 = new Fraction(1/2) ;
		f3 = new Fraction(1/3) ;
		f4 = new Fraction(1) ;
		f5 = new Fraction(0) ;
		f6 = new Fraction(0/1) ;
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
}
