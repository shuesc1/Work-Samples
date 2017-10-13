package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.math.MySorter;
import edu.upenn.cis573.math.MySorter.OrderDirection;

public class MySorterSortInPlaceTest {

	MySorter ms = new MySorter();
	double[] mixed1Forward = {0, 1, 2, 3, 4} ;
	double[] mixed1Backward = {4, 3, 2, 1, 0} ;
	double[] mixed1 = {2, 4, 0, 3, 1} ;
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

	@Test(expected=NullArgumentException.class)
	public void testNullArguementException() { //decreasing
		MySorter.sortInPlace(mixed1, OrderDirection.DECREASING, mixed3);
		assertArrayEquals(mixed3Backward, mixed3, 0) ;
	}
}
