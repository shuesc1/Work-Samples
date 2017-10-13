package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.health.Workout;
import edu.upenn.cis573.health.WorkoutTracker;

public class WorkoutTrackerAddWorkoutTest {

	WorkoutTracker wt ;
	int newDuration, duration1, duration2, duration3 ;
	Date newDate, date1, date2, date3 ;
	Workout newWorkout, workout1, workout2, workout3 ;
	List<Workout> workouts;

	@SuppressWarnings("deprecation")
	@Before 
	public void initialize() {
		wt = new WorkoutTracker() ; 
		workouts = wt.getWorkouts() ;
		//Workout to be added
		newDate = new Date(2017, 10, 13) ; 
		newDuration = 33 ;
		newWorkout = new Workout(newDate, newDuration) ;
		//oldest Workout obj
		date1 = new Date(2017, 10, 10) ; 
		duration1 = 25 ;
		workout1 = new Workout(date1, duration1) ;
		//2nd oldest Workout obj
		date2 = new Date(2017, 10, 11) ; 
		duration2 = 40 ;
		workout2 = new Workout(date2, duration2) ;
		//most recent Workout obj
		date3 = new Date(2017, 10, 11) ; 
		duration3 = 21 ;
		workout3 = new Workout(date3, duration3) ;
	}

	//=============================================================
	//====SPEC: return X to satis. mean==30 when list.size() >=3 ==
	//=============================================================
	@Test
	public void test3WorkoutsReturnCorrectX1() {
		workouts.add(workout1) ;
		workouts.add(workout2) ;
		workouts.add(workout3) ;
		int predictedX = wt.addWorkout(newWorkout) ;
		assertEquals("with duration values 25, 40, 21, and 33 predicted X should be 31", 31, predictedX) ;
	}
	@Test
	public void test3WorkoutsReturnCorrectX2() {
		workouts.add(workout1) ;
		workouts.add(workout2) ;
		workouts.add(workout3) ;
		int predictedX = wt.addWorkout(newWorkout) ;
		assertNotEquals("with duration values 25, 40, 21, and 33 predicted X should (due to floor) be 31", 32, predictedX) ;
	}
	@Test
	public void test3WorkoutsReturnCorrectX3() {
		workouts.add(workout1) ;
		workouts.add(workout2) ;
		workouts.add(workout3) ;
		int predictedX = wt.addWorkout(newWorkout) ;
		assertNotEquals("with duration values 25, 40, 21, and 33 predicted X should (due to floor) be 31", 33, predictedX) ;
	}
	@Test
	public void test3WorkoutsReturnCorrectX4() {
		workouts.add(workout1) ;
		workouts.add(workout2) ;
		workouts.add(workout3) ;
		int predictedX = wt.addWorkout(newWorkout) ;
		assertNotEquals("with duration values 25, 40, 21, and 33 predicted X should (due to floor) be 31", 34, predictedX) ;
	}
	@Test
	public void test3WorkoutsReturnCorrectX5() {

		workouts.add(workout1) ;
		workouts.add(workout2) ;
		workouts.add(workout3) ;
		int predictedX = wt.addWorkout(newWorkout) ;
		assertNotEquals("with duration values 25, 40, 21, and 33 predicted X should (due to floor) be 31", 35, predictedX) ;
	}
	//==========================================================================
	//====SPEC: return X to satis. mean==30 when list.size() < 3 using floor====
	//==========================================================================
	//<<<<<<<<<<<<<<<<<<<<<<<<<only 2 workouts>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testTwoWorkoutsReturnCorrectX1() {
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertEquals("with duration values 25, 40, and 33 predicted X should (due to floor) be 22", 22, predictedX) ;
	}
	@Test
	public void testTwoWorkoutsReturnCorrectX2() {
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("with duration values 25, 40, and 33 predicted X should (due to floor) be 22", 23, predictedX) ;
	}
	@Test
	public void testTwoWorkoutsReturnCorrectX3() {
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("with duration values 25, 40, and 33 predicted X should (due to floor) be 22", 24, predictedX) ;
	}
	@Test
	public void testTwoWorkoutsReturnCorrectX4() {
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("with duration values 25, 40, and 33 predicted X should (due to floor) be 22", 25, predictedX) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<only 1 workout>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testOneWorkoutReturnCorrectX1() {
		workouts.add(workout1) ; //dur 25
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertEquals("with duration values 25, and 33 predicted X should (due to floor) be 32", 32, predictedX) ;
	}
	@Test
	public void testOneWorkoutReturnCorrectX2() {
		workouts.add(workout1) ; //dur 25
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("with duration values 25, and 33 predicted X should (due to floor) be 32", 33, predictedX) ;
	}
	@Test
	public void testOneWorkoutReturnCorrectX3() {
		workouts.add(workout1) ; //dur 25
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("with duration values 25, and 33 predicted X should (due to floor) be 32", 34, predictedX) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<0 workouts>>>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testZeroWorkoutsReturnCorrectX1() {
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertEquals("with duration 33 predicted X should (due to floor) be 27", 27, predictedX) ;
	}
	@Test
	public void testZeroWorkoutsReturnCorrectX2() {
		int predictedX = wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("with duration 33 predicted X should (due to floor) be 27", 28, predictedX) ;
	}
	//===============================================================================
	//=SPEC: append new Workout to end of list & don't modify existent objs==========
	//===============================================================================
	//<<<<<<<<<<<<<<<<<<<<<list only has 1 Workout object>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testNewWorkoutAtEndOfList1() { //added at end
		workouts.add(workout1) ; //dur 25
		wt.addWorkout(newWorkout) ; //dur 33
		assertEquals("new Workout object should be added at end of list after method call", newWorkout, workouts.get(1)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList2() { //not added at beginning
		workouts.add(workout1) ; //dur 25
		wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("new Workout object should not be added to beginning of list after method call", newWorkout, workouts.get(0)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList3() { //not modifying existent object in list
		workouts.add(workout1) ; //dur 25
		wt.addWorkout(newWorkout) ; //dur 33
		assertSame("Workout object in list shouldn't be modified", workout1, workouts.get(0)) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<list has 2 Workout objects>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testNewWorkoutAtEndOfList4() { //added at end
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		wt.addWorkout(newWorkout) ; //dur 33
		assertEquals("new Workout object should be added at end of list after method call", newWorkout, workouts.get(2)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList5() { //not added at index 1
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("new Workout object should not be added at index 0 or 1", newWorkout, workouts.get(1)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList6() { //not added at index 0
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		wt.addWorkout(newWorkout) ; //dur 33
		assertNotEquals("new Workout object should not be added at index 0 or 1", newWorkout, workouts.get(0)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList7() { //not modify object at index 1
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		wt.addWorkout(newWorkout) ; //dur 33
		assertSame("Workout object at index 0 and 1 should not be modified", workout2, workouts.get(1)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList8() { //not modify object at index 0
		workouts.add(workout1) ; //dur 25
		workouts.add(workout2) ; //dur 40
		wt.addWorkout(newWorkout) ; //dur 33
		assertSame("Workout object at index 0 and 1 should not be modified", workout1, workouts.get(0)) ;
	}
	//<<<<<<<<<<<<<<<<<<<<<list has 3 Workout objects>>>>>>>>>>>>>>>>>>>>>
	@Test
	public void testNewWorkoutAtEndOfList9() { //added at end
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertEquals("new Workout object should be added at end of list after method call", newWorkout, workouts.get(3)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList10() { //not added at index 2
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertNotEquals("new Workout object should not be added at index 0, 1, or 2", newWorkout, workouts.get(2)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList11() { //not added at index 1
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertNotEquals("new Workout object should not be added at index 0, 1, or 2", newWorkout, workouts.get(1)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList12() { //not added at index 0
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertNotEquals("new Workout object should not be added at index 0, 1, or 2", newWorkout, workouts.get(0)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList13() { //not modify object at index 2
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertSame("Workout object at index 0, 1, and 2 should not be modified", workout3, workouts.get(2)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList14() { //not modify object at index 1
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertSame("Workout object at index 0, 1, and 2 should not be modified", workout2, workouts.get(1)) ;
	}
	@Test
	public void testNewWorkoutAtEndOfList15() { //not modify object at index 0
		workouts.add(workout1) ; //dur 25, index 0
		workouts.add(workout2) ; //dur 40, index 1
		workouts.add(workout3) ; //dur 21, index 2
		wt.addWorkout(newWorkout) ; //dur 33, index 3
		assertSame("Workout object at index 0, 1, and 2 should not be modified", workout1, workouts.get(0)) ;
	}
	//===============================================================================
	//=======SPEC: semantically invalid args/IllegalArgumentException================
	//===============================================================================
	//<<<<<<<<<<<<<<<<<<<<<<<<<<Argument: Date invalid>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDateIAE1() {
		Date illegalDate = null ; //date is null
		newWorkout = new Workout(illegalDate, newDuration) ;
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDateIAE2() {
		Date illegalDate = new Date(2020, 15, 15) ; //date in the future
		newWorkout = new Workout(illegalDate, newDuration) ;
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDateIAE3() {
		Date illegalDate = new Date(75, 30, 900) ; //date illogical
		newWorkout = new Workout(illegalDate, newDuration) ;
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDateIAE34() {
		Date illegalDate = new Date((long) 00000000000.00000000000) ; //date illogical
		newWorkout = new Workout(illegalDate, newDuration) ;
		wt.addWorkout(newWorkout);
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<Argument: Duration invalid>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDurationIAE1() {
		int illegalDuration = (Integer) null ; //duration is null
		newWorkout = new Workout(newDate, illegalDuration) ;
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDurationIAE2() {
		int illegalDuration = -5 ; //duration is negative
		newWorkout = new Workout(newDate, illegalDuration) ;
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDurationIAE3() {
		double illegalDuration = 9999999.5 ; //duration is too long and wrong data type
		newWorkout = new Workout(newDate, (int) illegalDuration) ;
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidDurationIAE4() {
		String illegalDuration = "thirty minutes" ; //duration in string form
		Integer dur = Integer.parseInt(illegalDuration) ;
		newWorkout = new Workout(newDate, dur) ;
		wt.addWorkout(newWorkout);
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<Argument: Workout invalid>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidWorkoutIAE1() {
		newWorkout = null ; //workout null
		wt.addWorkout(newWorkout);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSemanticInvalidWorkoutIAE2() {
		newWorkout = (Workout) new Object() ; //workout empty object
		wt.addWorkout(newWorkout);
	}
	//===============================================================================
	//================SPEC: illegalStateException====================================
	//===============================================================================
	//<<<<<<<1. Workout object contents are semantically invalid in list>>>>>>>>>
	@Test
	public void testWorkoutContentsInListInvalidISE1() {
		int illegDur = -20 ;
		Workout illegalWorkout = new Workout(newDate, illegDur) ; //Workout obj illegal due to duration value
		workouts.add(workout1) ;
		workouts.add(illegalWorkout) ; //illegal workout now in list
		workouts.add(workout2) ;
		int listPreSize = workouts.size() ; //size of list before error thrown
		try {
			wt.addWorkout(newWorkout) ;
			fail("List contains illegal Workout object and should have thrown Illegal State Exception") ;
		} catch (IllegalStateException ise) { //for time and clarity adding all necessary checks here
			assertEquals("Correct exception thrown; newest workout shouldn't be added to list", listPreSize, workouts.size()) ;
			assertNotEquals("Correct exception thrown; newest workout shouldn't be added to list", illegalWorkout, workouts.get(workouts.size()-1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout1, workouts.get(0)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", illegalWorkout, workouts.get(1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout2, workouts.get(2)) ;
		} catch (IllegalArgumentException iae) {
			fail("Wrong exception thrown: valid Workout provided, illegal Workout already in list") ;
		}
	}
	@Test
	public void testWorkoutContentsInListInvalidISE2() {
		Date illegDate = null ;
		Workout illegalWorkout = new Workout(newDate, newDuration) ; //Workout obj illegal due to date value
		workouts.add(workout1) ;
		workouts.add(illegalWorkout) ; //illegal workout now in list
		workouts.add(workout2) ;
		int listPreSize = workouts.size() ; //size of list before error thrown
		try {
			wt.addWorkout(newWorkout) ;
			fail("List contains illegal Workout object and should have thrown Illegal State Exception") ;
		} catch (IllegalStateException ise) { //for time and clarity adding all necessary checks here
			assertEquals("Correct exception thrown; newest workout shouldn't be added to list", listPreSize, workouts.size()) ;
			assertNotEquals("Correct exception thrown; newest workout shouldn't be added to list", illegalWorkout, workouts.get(workouts.size()-1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout1, workouts.get(0)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", illegalWorkout, workouts.get(1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout2, workouts.get(2)) ;
		} catch (IllegalArgumentException iae) {
			fail("Wrong exception thrown: valid Workout provided, illegal Workout already in list") ;
		}
	}
	//<<<<<<2. Workout object contents are semantically invalid invalid in list>>>>>>>>>
	@Test
	public void testWorkoutInListInvalidISE1() {
		Workout illegalWorkout = null ; //Workout obj illegal due to null value
		workouts.add(workout1) ;
		workouts.add(illegalWorkout) ; //illegal workout now in list
		workouts.add(workout2) ;
		int listPreSize = workouts.size() ; //size of list before error thrown
		try {
			wt.addWorkout(newWorkout) ;
			fail("List contains illegal Workout object and should have thrown Illegal State Exception") ;
		} catch (IllegalStateException ise) { //for time and clarity adding all necessary checks here
			assertEquals("Correct exception thrown; newest workout shouldn't be added to list", listPreSize, workouts.size()) ;
			assertNotEquals("Correct exception thrown; newest workout shouldn't be added to list", illegalWorkout, workouts.get(workouts.size()-1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout1, workouts.get(0)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", illegalWorkout, workouts.get(1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout2, workouts.get(2)) ;
		} catch (IllegalArgumentException iae) {
			fail("Wrong exception thrown: valid Workout provided, illegal Workout already in list") ;
		}
	}
	@Test
	public void testWorkoutInListInvalidISE2() {
		Workout illegalWorkout = (Workout) new Object() ; //Workout obj illegal b/c empty/ wrong type
		workouts.add(workout1) ;
		workouts.add(illegalWorkout) ; //illegal workout now in list
		workouts.add(workout2) ;
		int listPreSize = workouts.size() ; //size of list before error thrown
		try {
			wt.addWorkout(newWorkout) ;
			fail("List contains illegal Workout object and should have thrown Illegal State Exception") ;
		} catch (IllegalStateException ise) { //for time and clarity adding all necessary checks here
			assertEquals("Correct exception thrown; newest workout shouldn't be added to list", listPreSize, workouts.size()) ;
			assertNotEquals("Correct exception thrown; newest workout shouldn't be added to list", illegalWorkout, workouts.get(workouts.size()-1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout1, workouts.get(0)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", illegalWorkout, workouts.get(1)) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout2, workouts.get(2)) ;
		} catch (IllegalArgumentException iae) {
			fail("Wrong exception thrown: valid Workout provided, illegal Workout already in list") ;
		}
	}
	//<<<<<<<<<<<<<<<<<<<<3. List is in an invalid state>>>>>>>>>
	//contains other data types, for example
	@Test
	public void testListInvalidStateISE1() {
		workouts = null ; //list is null
		int listPreSize = workouts.size() ; //size of list before error thrown
		try {
			wt.addWorkout(newWorkout) ;
			fail("List contains illegal Workout object and should have thrown Illegal State Exception") ;
		} catch (IllegalStateException ise) { //for time and clarity adding all necessary checks here
			assertEquals("Correct exception thrown; newest workout shouldn't be added to list", listPreSize, workouts.size()) ;
			assertNotEquals("Correct exception thrown; newest workout shouldn't be added to list", newWorkout, workouts.get(workouts.size()-1)) ;
		} catch (IllegalArgumentException iae) {
			fail("Wrong exception thrown: valid Workout provided, illegal Workout already in list") ;
		}
	}
	@Test
	public void testListInvalidStateISE2() {
		workouts = new Stack() ; //new objects added to top (LIFO)
		workouts.add(workout1) ;
		int listPreSize = workouts.size() ; //size of list before error thrown
		try {
			wt.addWorkout(newWorkout) ;
			fail("List contains illegal Workout object and should have thrown Illegal State Exception") ;
		} catch (IllegalStateException ise) { //for time and clarity adding all necessary checks here
			assertEquals("Correct exception thrown; newest workout shouldn't be added to list", listPreSize, workouts.size()) ;
			assertNotEquals("Correct exception thrown; newest workout shouldn't be added to list", newWorkout, ((Stack) workouts).peek()) ;
			assertEquals("Correct exception thrown; prior list items shouldn't be modified", workout1, workouts.get(0)) ;
		} catch (IllegalArgumentException iae) {
			fail("Wrong exception thrown: valid Workout provided, illegal Workout already in list") ;
		}
	}
}
