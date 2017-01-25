import java.io.FileReader;

/**
 * This is a Trip class that creates an object that contains all variables supplied in the CSV file
 * @author josephhaymaker
 *
 */
public class Trip {
	//state of trips--characteristics
	private int tripID; //1
	private int duration; //2
	private String startTime;//3
	private String endTime; //4--parse to double eventually
	private int startStationID;//5
	private double startLatitude;//6
	private double startLongitude;//7
	private int endStationID;//8
	private double endLatitude;//9
	private double endLongitude;//10
	private int bikeID;//11
	private int planDuration;//12
	private String tripRouteCategory;//13
	private String passholderType;//14
	//	FileReader fr = new FileReader("Q3_2016_trips.csv");

	/**
	 * This is the constructor for the class. It takes the 14 variables from the CSV file as arguments and initializes them.
	 * @param thisTripID trip ID number stored as an integer
	 * @param thisDuration trip duration expressed in minutes and stored as an integer
	 * @param thisStartTime the date and time of the start of the trip, stored as a string
	 * @param thisEndTime the end time of the trip (date and military time), stored as a string
	 * @param thisStartStationID the station ID number, stored as an integer
	 * @param thisStartLatitude the start latitude of the trip, stored as a double
	 * @param thisStartLongitude the start longitude of the trip, stored as a double
	 * @param thisEndStationID the end station ID, stored as an int
	 * @param thisEndLatitude the end latitude of the trip, stored as a double
	 * @param thisEndLongitude the end longitude of the trip, stored as a double
	 * @param thisBikeID the ID number of the bike used in the trip
	 * @param thisPlanDuration the plan duration number, stored as an int
	 * @param thisTripRouteCategory trip category "one way", "roundtrip", etc. stored as a string
	 * @param thisPassholderType the plan the user employed (ie walk-up, Indego30, etc)
	 */
	public Trip(int thisTripID, int thisDuration, String thisStartTime, String thisEndTime, int thisStartStationID, double thisStartLatitude,double thisStartLongitude, int thisEndStationID, double thisEndLatitude,double thisEndLongitude,int thisBikeID, int thisPlanDuration, String thisTripRouteCategory, String thisPassholderType){
		tripID = thisTripID;
		duration = thisDuration;
		startTime = thisStartTime;
		endTime = thisEndTime;
		startStationID = thisStartStationID;
		startLatitude = thisStartLatitude;
		startLongitude = thisStartLongitude;
		endStationID = thisEndStationID;
		endLatitude = thisEndLatitude;
		endLongitude = thisEndLongitude;
		bikeID = thisBikeID;
		planDuration = thisPlanDuration;
		tripRouteCategory = thisTripRouteCategory;
		passholderType = thisPassholderType;
	}

	/**
	 * A getter method for trip id.
	 * @return int tripID
	 */
	public int getTripID() {
		return tripID;
	}

	/**
	 * a getter method for duration
	 * @return int duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * a getter method for the trip start time
	 * @return String startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * a getter method for the trip end time
	 * @return String endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * A getter method for the start station ID of the trip
	 * @return int startStationID
	 */
	public int getStartStationID() {
		return startStationID;
	}

	/**
	 * A getter method for the trip start latitude
	 * @return double startLatitude
	 */
	public double getStartLatitude() {
		return startLatitude;
	}

	/**
	 * A getter method for the trip start longitude
	 * @return double startLongitude
	 */
	public double getStartLongitude() {
		return startLongitude;
	}

	/**
	 * A getter method for the trip end station ID
	 * @return int endStationID
	 */
	public int getEndStationID() {
		return endStationID;
	}

	/**
	 * a getter method for the trip end latitude
	 * @return double endLatitude
	 */
	public double getEndLatitude() {
		return endLatitude;
	}

	/**
	 * a getter method for the trip end longitude
	 * @return double endLongitude
	 */
	public double getEndLongitude() {
		return endLongitude;
	}

	/**
	 * A getter method for the trip bike ID
	 * @return int bikeID
	 */
	public int getBikeID() {
		return bikeID;
	}

	/**
	 * A getter method for the plan duration
	 * @return int planDuration
	 */
	public int getPlanDuration() {
		return planDuration;
	}

	/**
	 * A getter method for the trip route category (One way, round trip, etc.)
	 * @return String tripRouteCategory
	 */
	public String getTripRouteCategory() {
		return tripRouteCategory;
	}

	/**
	 * A getter method for the passholder type (walk up user, Indego30 user, etc.)
	 * @return String passholderType
	 */
	public String getPassholderType() {
		return passholderType;
	}
}
