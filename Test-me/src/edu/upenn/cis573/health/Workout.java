package edu.upenn.cis573.health;

import java.util.Date;

public class Workout {
	
	private Date workoutDate;
	private int durationMinutes;
	
	public Workout(Date date, int duration) {
		workoutDate = date;
		durationMinutes = duration;
	}

	public Date getWorkoutDate() {
		return workoutDate;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

}
