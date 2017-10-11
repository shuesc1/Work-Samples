package edu.upenn.cis573.health;

import java.util.LinkedList;
import java.util.List;

public class WorkoutTracker {
	
	protected List<Workout> workouts;
	
	public WorkoutTracker() {
		workouts = new LinkedList<Workout>();
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}
	
	public void clearWorkouts() {
		workouts.clear();
	}
	
	/*
	 * See the assignment description for more information.
	 */
	public int addWorkout(Workout workout) {
		/*
		 * DO NOT IMPLEMENT THIS METHOD!
		 * It is intentionally unimplemented - you just need to test it!
		 */
		return -1;
	}
}
