package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.health.HealthIndicator;
import edu.upenn.cis573.health.HealthIndicator.WeightStatus;

public class HealthIndicatorTest {

	HealthIndicator hi;
	WeightStatus ws;
	int validHgt = 5 ;
	double validHgtIn = 9.0 ;
	double validWtPds = 160.7 ;
	int validAgeYrs = 34 ;

	@Before 
	public void initialize() {
		hi = new HealthIndicator() ;
	}

	//=============================================================
	//==============SPEC: WeightStatus/BMI Categorization==========
	//=============================================================
	//correct values should give correct categorizations
	//<<<<<<<<<<<<<<<<<<<<<<'underweight'>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<'healthy'>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<'overweight'>>>>>>>>>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<'obese'>>>>>>>>>>>>>>>>>>>>>>>>

	
	
	//=============================================================
	//==========================SPEC: Age < 20 ====================
	//=============================================================
	//age < 20 should return WeightStatus==invalid; age >= 20 should return categorical WeightStatus
	@Test
	public void testAgeBelow20Condition1() {
		int illegalAge = 19 ;
		ws = hi.calculateWeightStatus(validHgt, validHgtIn, validWtPds, illegalAge) ;
		assertEquals("an age below 20 should return a WeightStatus of 'invalid'", "INVALID", ws) ; 
	}
	
	public void testAgeBelow20Condition2() {
		int illegalAge = 5 ;
		ws = hi.calculateWeightStatus(validHgt, validHgtIn, validWtPds, illegalAge) ;
		assertEquals("an age below 20 should return a WeightStatus of 'invalid'", "INVALID", ws) ; 
	}
	
	@Test
	public void testAgeBelow20Condition3() {
		int legalAge = 20 ;
		ws = hi.calculateWeightStatus(validHgt, validHgtIn, validWtPds, legalAge) ;
		assertNotEquals("an age above 20 should return a WeightStatus other than 'invalid'", "INVALID", ws) ;
	}
	
	@Test
	public void testAgeBelow20Condition4() {
		int legalAge = 30 ;
		ws = hi.calculateWeightStatus(validHgt, validHgtIn, validWtPds, legalAge) ; //BMI==23.7 =(160.7*703)/(69^2)
		assertEquals("an age above 20 should return a WeightStatus other than 'invalid'-- 23.7 == 'healthy' in this case", "HEALTHY", ws) ;
	}
	
	//=============================================================
	//================SPEC: Height Ft/Inch combo ==================
	//=============================================================
	//int height in feet should combine with double height in inches and output double height in inches
	//and give the correct classification based on that -- should read both as feet or both inches for example
	@Test
	public void testHeightComboInBMI() {
		double invalidWt = 500.00 ;
		fail("Not yet implemented");
	}



	//=============================================================
	//==============SPEC: Semantically invalid inputs==============
	//=============================================================
	//<<<<<<<<<<<<<<<<<<<<<<Weight>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testSemanticallyInvalidWeight1() {
		double invalidWt = 500.00 ;
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidWeight2() {
		double invalidWt = .00012 ;
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidWeight3() {
		//		String invalidWt = "One hundred 60 pounds" ;
		//		hi.calculateWeightStatus(validHgt, validHgtIn, invalidWt, validAgeYrs) ;
		double invalidWt = 19.0-200.00 ;
		fail("Not yet implemented");
	}
	//<<<<<<<<<<<<<<<<<<<<<<Height FEET>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testSemanticallyInvalidHeight1() {
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidHeight2() {
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidHeight3() {
		fail("Not yet implemented");
	}
	//<<<<<<<<<<<<<<<<<<<<<<Height INCHES>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testSemanticallyInvalidHeightIn1() {
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidHeightIn2() {
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidHeightIn3() {
		fail("Not yet implemented");
	}
	//<<<<<<<<<<<<<<<<<<<<<<Age - Years>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testSemanticallyInvalidAgeYr1() {
		int invalidAge = (int) 130.0 ;
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidAgeYr2() {
		int invalidAge = (int) 65.02 ;
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidAgeYr3() {
		fail("Not yet implemented");
	}
	
	
	
}
