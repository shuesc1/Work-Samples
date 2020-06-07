import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FlightSimulatorTest {


	@Test(expected=IllegalArgumentException.class)
	public void testPlanesNull() {
		FlightSimulator.simulateFlights(null, 0, 0);
	}

	@Test
	public void testNotSafeDistance() {
		Airplane a = new Airplane(10, 20, 90, 80);
		Airplane b = new Airplane(10, 20, 90, 100) ; //both have same location and same bearing
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 10, 100) ;
		assertEquals("If planes are too close should return false", actual, false) ;
	}

	@Test
	public void testNotSafeDistance2() {
		Airplane a = new Airplane(-50, 0, 90, 80); //headed due north
		Airplane b = new Airplane(50, 0, 135, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes get too close during travel method should return false", actual, false) ;
	}

	@Test
	public void testNotSafeDistance3() {
		Airplane a = new Airplane(-50, -50, 45, 80); //headed due north
		Airplane b = new Airplane(50, 50, 225, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes get too close during travel method should return false", actual, false) ;
	}
	
	@Test
	public void testNotSafeDistance4() {
		Airplane a = new Airplane(-50, 0, 45, 80); //headed due north
		Airplane b = new Airplane(50, 0, 135, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 1, 99) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes get too close during travel method should return false", actual, false) ;
	}

	@Test
	public void testSafeDistance() {
		Airplane a = new Airplane(-50, 0, 135, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}

	@Test
	public void testSafeDistance2() {
		Airplane a = new Airplane(-50, 0, 270, 80); //headed due north
		Airplane b = new Airplane(50, 0, 90, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}

	@Test
	public void testSafeDistance3() {
		Airplane a = new Airplane(-50, 0, 180, 80); //headed due north
		Airplane b = new Airplane(50, 0, 0, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}

	@Test
	public void testSafeDistance4() {
		Airplane a = new Airplane(49, 0, 270, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 1) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}
	
	@Test
	public void testSafeDistance5() {
		Airplane a = new Airplane(50, 5, 45, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 2) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}
	
	@Test
	public void testSafeDistance6() {
		Airplane a = new Airplane(-50, 5, 225, 80); //headed due north
		Airplane b = new Airplane(-50, 0, 225, 100) ; //headed northeast
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 2) ; //90 is safest min distance -- will eventually pass this and thus return false
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}
	
	@Test
	public void testSafeDistance7() {
		Airplane a = new Airplane(-50, 5, 315, 80); //perpendicular trajectories
		Airplane b = new Airplane(-50, 0, 225, 80) ;
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 2) ; 
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}
	
	@Test
	public void testSafeDistance8() {
		Airplane a = new Airplane(-50, 5, 290, 80); //perpendicular trajectories
		Airplane b = new Airplane(-50, 0, 255, 80) ;
		Airplane[] planes = {a, b};

		Boolean actual = FlightSimulator.simulateFlights(planes, 50, 2) ; 
		assertEquals("If planes are safe dist during travel method should return true", actual, true) ;
	}
	
	@Test
	public void testInvalidArg1() {
		//		Airplane a = new Airplane(-50, 0, 135, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, -2) ; //headed northeast
		Airplane[] planes = {b};
		try {
			FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("array only has 1 plane whose velocity is neg-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testInvalidArg2() {
		Airplane[] planes = null;
		try {
			FlightSimulator.simulateFlights(planes, 50, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("array of planes is null/invalid, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testInvalidArg3() {
		Airplane a = new Airplane(-50, 0, 135, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, 90) ; //headed northeast
		Airplane[] planes = {a, b};
		try {
			FlightSimulator.simulateFlights(planes, -5, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("neg number of steps-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testInvalidArg4() {
		Airplane a = new Airplane(-50, 0, 135, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, 90) ; //headed northeast
		Airplane[] planes = {a, b};
		try {
			FlightSimulator.simulateFlights(planes, 10, -50) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("neg number of min safe distance-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testInvalidArg5() {
		Airplane a = new Airplane(-50, 0, 500, 80); //headed due north
		Airplane b = new Airplane(50, 0, 45, 90) ; //headed northeast
		Airplane[] planes = {a, b};
		try {
			FlightSimulator.simulateFlights(planes, 10, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("bearing not between 0-360-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testInvalidArg6() {
		Airplane a = null; //headed due north
		Airplane b = new Airplane(50, 0, 45, 90) ; //headed northeast
		Airplane[] planes = {a, b};
		try {
			FlightSimulator.simulateFlights(planes, 10, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("one airplane is null-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testInvalidArg7() {
		Airplane a = new Airplane(0, 0, 0, 0) ; //grounded, not moving
		Airplane b = new Airplane(50, 0, 45, 90) ; //headed northeast
		Airplane[] planes = {a, b}; 
		assertEquals("method should actually be able to handle non moving plane", FlightSimulator.simulateFlights(planes, 10, 40), true) ;
	}

	@Test
	public void testInvalidArg8() {
		Airplane[] planes = new Airplane[2]; //initialized but empty
		try {
			FlightSimulator.simulateFlights(planes, 10, 90) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("one airplane is null-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}	
	}

	@Test
	public void testInvalidArg9() {
		Airplane a = new Airplane(51, 0, 45, 90) ; //grounded, not moving
		Airplane b = new Airplane(50, 0, 45, 90) ; //headed northeast
		Airplane[] planes = {a, b}; 
		try {
			FlightSimulator.simulateFlights(planes, 10, -500) ; //90 is safest min distance -- will eventually pass this and thus return false
			fail("negative min save distance-- invalid input, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}


}
