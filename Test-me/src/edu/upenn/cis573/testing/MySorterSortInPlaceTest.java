package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.math.MySorter;
import edu.upenn.cis573.math.MySorter.OrderDirection;

public class MySorterSortInPlaceTest {

	MySorter ms = new MySorter();
	double[] mixed1 = {2, 4, 0, 3, 1} ;
	double[] mixed1Forward = {0, 1, 2, 3, 4} ;
	double[] mixed1Backward = {4, 3, 2, 1, 0} ;
	double[] mixed2 = {3, 0, 4, 1, 2} ;
	double[] mixed3 = {9, 6, 5, 7, 8} ;
	double[] mixed3Forward = {5, 6, 7, 8, 9};
	double[] mixed3Backward = {9, 8, 7, 6, 5} ;

	@Test
	public void testSorting1() { //increasing
		MySorter.sortInPlace(mixed1, OrderDirection.INCREASING, mixed3);
		assertArrayEquals(mixed1Forward, mixed1, 0) ;
	}

	@Test
	public void testSorting2() { //increasing
		MySorter.sortInPlace(mixed1, OrderDirection.INCREASING, mixed3);
		assertArrayEquals(mixed3Forward, mixed3, 0) ;
	}

	@Test
	public void testSorting3() { //decreasing
		MySorter.sortInPlace(mixed1, OrderDirection.DECREASING, mixed3);
		assertArrayEquals(mixed1Backward, mixed1, 0) ;
	}

	@Test
	public void testSorting4() { //decreasing
		MySorter.sortInPlace(mixed1, OrderDirection.DECREASING, mixed3);
		assertArrayEquals(mixed3Backward, mixed3, 0) ;
	}

	@Test
	public void testNullArguementException1() { 
		double[] nullArr = null ;
		try {
			MySorter.sortInPlace(nullArr, OrderDirection.DECREASING, mixed3);
			fail("method should have thrown NullArguementException") ;
		} 
		catch (Throwable NullArgumentException) {

		} 
//		catch (Throwable DimensionMismatchException) { //won't compile
//			fail("wrong exception thrown") ;
//		}
	}
	
	@Test
	public void testNullArguementException2() { 
		double[] nullArr = null ;
		try {
			MySorter.sortInPlace(mixed3, OrderDirection.DECREASING, nullArr);
			fail("method should have thrown NullArguementException") ;
		} 
		catch (Throwable NullArgumentException) {

		} 
//		catch (Throwable DimensionMismatchException) { //won't compile
//			fail("wrong exception thrown") ;
//		}
	}
	
	@Test
	public void testDimensionMismatchException() { 
		double[] shortArr = {0, 1} ;
		try {
			MySorter.sortInPlace(shortArr, OrderDirection.DECREASING, mixed3);
			fail("method should have thrown DimensionMismatchException") ;
		} 
//		catch (Throwable NullArgumentException) { //won't compile
//			fail("wrong exception thrown") ;
//		} 
		catch (Throwable DimensionMismatchException) { 
			
		}

	}
}
