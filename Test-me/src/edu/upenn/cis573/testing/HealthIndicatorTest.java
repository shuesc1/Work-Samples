package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.health.HealthIndicator;

public class HealthIndicatorTest {

	HealthIndicator hi;
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
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidAgeYr2() {
		fail("Not yet implemented");
	}

	@Test
	public void testSemanticallyInvalidAgeYr3() {
		fail("Not yet implemented");
	}
	
	
	
}
