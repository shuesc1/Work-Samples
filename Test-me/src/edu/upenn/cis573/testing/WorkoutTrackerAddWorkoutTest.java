package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

	//===============================================================================
	//===++==SPEC: semantically invalid args/IllegalArgumentException================
	//===============================================================================

	//===============================================================================
	//================SPEC: illegalStateException====================================
	//===============================================================================

	//<<<<<<<1. Workout object contents are semantically invalid in list>>>>>>>>>


	//<<<<<<2. Workout object contents are semantically invalid invalid in list>>>>>>>>>
	//contains other data types, for example


	//<<<<<<<<<<<<<<<<<<<<3. List is in an invalid state>>>>>>>>>


}
