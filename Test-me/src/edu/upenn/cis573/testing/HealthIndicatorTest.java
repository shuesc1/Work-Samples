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
//		hi = new HealthIndicator() ; //not needed since method is static
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
	public void testAgeBelow20InvalidCondition1() {
		int illegalAge = 19 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, illegalAge) ;
		assertEquals("an age below 20 should return a WeightStatus of 'invalid'", "INVALID", ws) ; 
	}
	
	public void testAgeBelow20InvalidCondition2() {
		int illegalAge = 5 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, illegalAge) ;
		assertNotEquals("an age below 20 should return a WeightStatus of 'invalid'", "HEALTHY", ws) ; 
	}
	
	@Test
	public void testAgeBelow20ValidCondition1() {
		int legalAge = 30 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, legalAge) ; //BMI==23.7 =(160.7*703)/(69^2)
		assertEquals("an age above 20 should return a WeightStatus other than 'invalid'-- 23.7 == 'healthy' in this case", "HEALTHY", ws) ;
	}
	
	@Test
	public void testAgeBelow20ValidCondition2() {
		int legalAge = 20 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, legalAge) ;
		assertNotEquals("an age above 20 should return a WeightStatus other than 'invalid'", "INVALID", ws) ;
	}
	
	//=============================================================
	//================SPEC: Height Ft/Inch combo ==================
	//=============================================================
	//int height in feet should combine with double height in inches and output double height in inches
	//and give the correct classification based on that -- should not read both as feet or both inches for example
	@Test
	public void testHeightComboUsedCorrectly1() {
		int heightFt = 6 ;
		double heightIn = 2.0 ;
		//BMI = ((160.7) * 703) / ((74)^2) --> 20.63--> 'HEALTHY'
		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
		assertEquals("if inputs read correctly WeightStatus should be 'HEALTY'", "HEALTHY", ws) ;
	}
	
	@Test
	public void testHeightComboUsedCorrectly2() {
		int heightFt = 4 ;
		double heightIn = 3.0 ;
		//BMI = ((160.7) * 703) / ((51)^2) --> 43.43--> 'OBESE'
		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
		assertEquals("if inputs read correctly WeightStatus should be 'HEALTY'", "HEALTHY", ws) ;
	}
	
	@Test
	public void testHeightComboUsedIncorrectly1() {
		//if both read as feet
		int heightFt = 5 ;
		double heightIn = 5.0 ;
		//10ft --> 120 inches --> (160.7pds*703)/(120in^2) --> 7.85 --> if incorrectly read WS would be 'UNDERWEIGHT'
		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
		assertNotEquals("an incorrect combination interpreting both as feet would be 'UNDERWEIGHT'", "UNDERWEIGHT", ws) ;
	}
	
	@Test
	public void testHeightComboUsedIncorrectly2() {
		//if both read as inches
		int heightFt = 5 ;
		double heightIn = 11.0 ;
		//16.0 inches --> (160.7pds*703)/(16in^2) --> 441.29 --> 'OBSESE'
		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
		assertNotEquals("an incorrect combination interpreting both as inches would be 'OBESE'", "OBESE", ws) ;
	}
	
	@Test
	public void testHeightComboUsedIncorrectly3() {
		//if it multiplied inches by 12 instead of feet
		int heightFt = 5 ;
		double heightIn = 11.0 ;
		//(11inx12) + 5 = 137.0 inches --> (160.7pds*703)/(137in^2) --> 6.02 --> "UNDERWEIGHT"
		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
		assertNotEquals("an incorrect combination where ht in in. value were mult. by 12 would be 'UNDERWEIGHT'", "UNDERWEIGHT", ws) ;
	}
	
	@Test
	public void testHeightComboUsedIncorrectly4() {
		//if both inch and feet values were multiplied by 12
		int heightFt = 5 ;
		double heightIn = 11.0 ;
//		//(11inx12) + 5 = 137.0 inches --> (160.7pds*703)/(137in^2) --> 6.02 --> "UNDERWEIGHT"
//		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
//		assertNotEquals("an incorrect combination where ht in in. value were mult. by 12 would be 'UNDERWEIGHT'", "UNDERWEIGHT", ws) ;
	}
	

//	BMI = ((weight pds) * 703) / ((height inches)^2)
//	int validHgt = 5 ;
//	double validHgtIn = 9.0 ;
//	double validWtPds = 160.7 ;
//	int validAgeYrs = 34 ;

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
