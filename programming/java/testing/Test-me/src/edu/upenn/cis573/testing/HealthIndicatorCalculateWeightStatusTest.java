package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.health.HealthIndicator;
import edu.upenn.cis573.health.HealthIndicator.WeightStatus;

public class HealthIndicatorCalculateWeightStatusTest {

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
	@Test
	public void testUnderweightCondition1() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 114.6 ;
		int age = 25 ;
		//boundary test -- BMI just below 18.5
		//(114.6*703)/(66.0^2) = 18.4949.. --> underweight
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI < 18.5 should return a WeightStatus of 'underweight'", "UNDERWEIGHT", ws) ; 
	}

	@Test
	public void testUnderweightCondition2() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 76.8 ;
		int age = 25 ;
		//BMI below 18.5
		//(76.8*703)/(66.0^2) = 12.39.. --> underweight
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI < 18.5 should return a WeightStatus of 'underweight'", "UNDERWEIGHT", ws) ; 
	}
	//<<<<<<<<<<<<<<<<<<<<<<'healthy'>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testHealthyCondition1() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 114.6315789 ;
		int age = 25 ;
		//boundary test -- BMI just above 18.5
		//(114.6315789*703)/(66.0^2) = 18.5 --> healthy
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI >= 18.5 should return a WeightStatus of 'healthy'", "HEALTHY", ws) ; 
	}

	@Test
	public void testHealthyCondition2() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 154.9 ;
		int age = 25 ;
		//boundary test -- BMI just below 25
		//(154.9*703)/(66.0^2) = 24.9988 --> healthy
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI >= 18.5 && < 25 should return a WeightStatus of 'healthy'", "HEALTHY", ws) ; 
	}
	//<<<<<<<<<<<<<<<<<<<<<<'overweight'>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testOverweightCondition1() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 154.9075391 ;
		int age = 25 ;
		//boundary test -- BMI at 25
		//(154.9075391*703)/(66.0^2) = 25 --> overweight
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI >= 25 && < 30 should return a WeightStatus of 'overweight'", "OVERWEIGHT", ws) ; 
	}

	@Test
	public void testOverweightCondition2() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 185.8 ;
		int age = 25 ;
		//boundary test -- BMI just below 30
		//(185.8*703)/(66.0^2) = 29.9856 --> overweight
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI >= 25 && < 30 should return a WeightStatus of 'overweight'", "OVERWEIGHT", ws) ; 
	}
	//<<<<<<<<<<<<<<<<<<<<<<'obese'>>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testObeseCondition1() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 185.8890469 ;
		int age = 25 ;
		//boundary test -- BMI at 30
		//(185.8890469*703)/(66.0^2) = 30 --> obese
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI >= 30 should return a WeightStatus of 'obese'", "OBESE", ws) ; 
	}

	@Test
	public void testObeseCondition2() {
		int height = 5 ;
		double heightIn = 6.0 ;
		double weight = 250 ;
		int age = 25 ;
		//BMI > 30
		//(250*703)/(66.0^2) = 40.35 --> obese
		ws = HealthIndicator.calculateWeightStatus(height	, heightIn, weight, age) ;
		assertEquals("any BMI >= 30 should return a WeightStatus of 'obese'", "OBESE", ws) ; 
	}

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

	@Test
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
		//16.0 inches --> (160.7pds*703)/(16in^2) --> 441.29 --> 'OBESE'
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
		//		//(11inx12) + (5ftx12) = 192.0 inches --> (160.7pds*703)/(192in^2) --> 3.06 --> "UNDERWEIGHT"
		ws = HealthIndicator.calculateWeightStatus(heightFt, heightIn, validWtPds, validAgeYrs) ;
		assertNotEquals("an incorrect combination where ht in in. value were mult. by 12 would be 'UNDERWEIGHT'", "UNDERWEIGHT", ws) ;
	}

	//=============================================================
	//==============SPEC: Semantically invalid inputs==============
	//=============================================================
	//<<<<<<<<<<<<<<<<<<<<<<Weight>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidWeight1() {
		double invalidWt = 1000.00 ; //value too large
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, invalidWt, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidWeight2() {
		double invalidWt = .00012 ; //value too small
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, invalidWt, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidWeight3() {
		double invalidWt = 19.0-200.00 ; //value negative
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, invalidWt, validAgeYrs) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidWeight4() {
		String wt = "one hundred pounds" ; //weight as string
		double invalidWt = (double) Integer.parseInt(wt) ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, invalidWt, validAgeYrs) ;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidWeight5() {
		String wt = "21O" ; //weight as string, swaps letter "O" for "0"
		double invalidWt = (double) Integer.parseInt(wt) ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, invalidWt, validAgeYrs) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<<Height FEET>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeight1() {
		int invalidHgt = 1 ; //height too small
		ws = HealthIndicator.calculateWeightStatus(invalidHgt, validHgtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeight2() {
		int invalidHgt = 15 ; //height too large
		ws = HealthIndicator.calculateWeightStatus(invalidHgt, validHgtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeight3() {
		int invalidHgt = -5 ; //height not possible
		ws = HealthIndicator.calculateWeightStatus(invalidHgt, validHgtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeight4() {
		int invalidHgt = 0 ; //height not possible
		ws = HealthIndicator.calculateWeightStatus(invalidHgt, validHgtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeight5() {
		String hgt = "five foot 9" ;
		int invalidHgt = Integer.parseInt(hgt) ; //height expressed as string
		ws = HealthIndicator.calculateWeightStatus(invalidHgt, validHgtIn, validWtPds, validAgeYrs) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<<Height INCHES>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeightIn1() {
		double invalidHtIn = 12.0 ; //any integer 12 or greater doesn't make sense
		ws = HealthIndicator.calculateWeightStatus(validHgt, invalidHtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeightIn2() {
		double invalidHtIn = 500.0 ; //any integer 12 or greater doesn't make sense
		ws = HealthIndicator.calculateWeightStatus(validHgt, invalidHtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeightIn3() {
		double invalidHtIn = Math.PI ; //any non-ending/fractional number not reasonable
		ws = HealthIndicator.calculateWeightStatus(validHgt, invalidHtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeightIn4() {
		double invalidHtIn = -5.2343433 ; //negative values illogical
		ws = HealthIndicator.calculateWeightStatus(validHgt, invalidHtIn, validWtPds, validAgeYrs) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidHeightIn5() {
		double invalidHtIn = 0 ; //non-possible value
		ws = HealthIndicator.calculateWeightStatus(validHgt, invalidHtIn, validWtPds, validAgeYrs) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<<Age - Years>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidAgeYr1() {
		int invalidAge = (int) 130.0 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, invalidAge) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidAgeYr2() {
		int invalidAge = (int) 0.02 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, invalidAge) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidAgeYr3() {
		int invalidAge = -7 ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, invalidAge) ;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSemanticallyInvalidAgeYr4() {
		String age = "eighteen (18)" ; //age a String but also below 20
		int invalidAge = Integer.parseInt(age) ;
		ws = HealthIndicator.calculateWeightStatus(validHgt, validHgtIn, validWtPds, invalidAge) ;
	}


}
