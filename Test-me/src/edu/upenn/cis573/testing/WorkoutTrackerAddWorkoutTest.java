package edu.upenn.cis573.testing;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import edu.upenn.cis573.health.Workout;
import edu.upenn.cis573.health.WorkoutTracker;

public class WorkoutTrackerAddWorkoutTest {

	WorkoutTracker wt ;
	int newDuration = 33 ;
	@SuppressWarnings("deprecation")
	Date newDate = new Date(2017, 10, 13) ;
//	Date newDate =  parseDate( "21/5/2009", "d/M/yyyy", false);
	Workout newWorkout = new Workout(newDate, newDuration) ;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	
	//=============================================================
	//====SPEC: return X to satis. mean==30 when list.size() >=3 ==
	//=============================================================
	

	//=============================================================
	//====SPEC: return X to satis. mean==30 when list.size() <3====
	//=============================================================
	
	
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
