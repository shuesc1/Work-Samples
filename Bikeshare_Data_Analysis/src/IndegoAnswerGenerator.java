import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that calculates answers for all questions posed
 * @author josephhaymaker
 *
 */
public class IndegoAnswerGenerator {

	private IndegoObjectCreator ioc = new IndegoObjectCreator("Q3_2016_trips.csv", "Station_table.txt");
	private int ques1counter = 0 ;
	private int ques2counter = 0 ;
	private String tripType = "" ;
	private ArrayList<Trip> trips = ioc.getTrips();
	private ArrayList<Station> stations = ioc.getStations();
	private Trip maxTrip;

	/**
	 * The constructor for the class. It creates a new instance of the IndegoObjectCreator class and provides it with two specific files.
	 */
	public IndegoAnswerGenerator(){
		IndegoObjectCreator ioc = new IndegoObjectCreator("Q3_2016_trips.csv", "Station_table.txt");
	}

	//<<<<<<<QUESTION 1>>>>>>>>>>>
	//"1. How many walk-up trips were there in 2016?";
	/**
	 * A method that calculates the answer to question 1 (How many walk-up trips were there in 2016?)
	 * @return ques1counter, the total number of walk-up trips in 2016
	 */
	public int ques1Getter(){
		for (Trip trip : trips){ 
			if(trip.getPassholderType().equalsIgnoreCase("walk-up")){
				ques1counter++;
			}
		} return ques1counter;
	}

	//<<<<<<<QUESTION 2>>>>>>>>>>>
	//"2. How many stations that had a Go-Live date in 2015 are still 'Active'?";
	/**
	 * A method that calculates the answer to question 2 (How many stations that had a Go-Live date in 2015 are still 'Active'?)
	 * @return ques2counter, the number of stations that have a go-live date in 2015 and are still active
	 */
	public int ques2Getter(){
		for (Station station : stations){ 
			String goLiveDate = station.getGoLiveDate();
			if(goLiveDate.contains("/2015") && station.getStatus().equalsIgnoreCase("Active")){ 
				ques2counter++;
			}
		} return ques2counter;
	}

	//<<<<<<<QUESTION 3>>>>>>>>>>>
	//"3. What percentage of trips started in Rittenhouse Square?";
	/**
	 * A method that calculates the answer to question 3 (What percentage of trips started in Rittenhouse Square?)
	 * @return rittenhousePercentage, the percentage of trips originating from Rittenhouse Square
	 */
	public double ques3Getter(){
		double tripCounter = 0;
		double ritCounter = 0;
		int rittenhouseStationID = 0;
		double rittenhousePercentage = 0;

		for (Station station : stations){ 
			if (station.getStationName().contains("Rittenhouse")){
				rittenhouseStationID = station.getStationID();
			}
		}
		for (Trip trip : trips){	
			tripCounter++;
			if (trip.getStartStationID() == rittenhouseStationID){
				ritCounter++;
			}
		} rittenhousePercentage = (ritCounter / tripCounter) * 100;
		return rittenhousePercentage;
	}

	//<<<<<<<QUESTION 4>>>>>>>>>>>
	//"4. What percentage of trips made by Indego30 riders are roundtrip?";
	/**
	 * A method that calculates the answer to question 4 (What percentage of trips made by Indego30 riders are roundtrip?)
	 * @return rountTripPercentage, the percentage of trips made by Indego30 riders
	 */
	public double ques4getter(){
		double ind30counter = 0;
		double indRoundtripCounter = 0;

		for (Trip trip : trips){
			if (trip.getPassholderType().equalsIgnoreCase("Indego30")){
				ind30counter++;
			}
			if (trip.getPassholderType().equalsIgnoreCase("Indego30") && trip.getTripRouteCategory().equalsIgnoreCase("round trip")){
				indRoundtripCounter++;
			}
		}
		double rountTripPercentage = (indRoundtripCounter / ind30counter) *100;
		return rountTripPercentage;
	}

	//<<<<<<<QUESTION 5>>>>>>>>>>>
	//"5. What is the ID of the bike that has traveled the most in terms of duration?";
	/**
	 * A method that calculates the answer to question 5 (What is the ID of the bike that has traveled the most in terms of duration?)
	 * @return longestBikeID, the numerical ID of the bike that traveled the most in terms of duration
	 */
	public int ques5getter(){
		int thisBikeID = 0;
		int longestBikeID = 0;
		double currentDuration = 0;
		double longestDuration = 0; //start time and end time are currently strings

		for (Trip trip : trips){
			thisBikeID = trip.getBikeID();
			currentDuration = trip.getDuration();

			if (currentDuration > longestDuration){
				longestDuration = currentDuration;
				longestBikeID = thisBikeID ; 
			}
		}
		return longestBikeID;
	}

	//<<<<<<<QUESTION 6>>>>>>>>>>>
	//"6. On 8/3/16 at 7:00am, how many bikes were being used?";
	/**
	 * A method that calculates the answer to question 6 (On 8/3/16 at 7:00am, how many bikes were being used?)
	 * @return bikesUsed, the number of bikes being used at 7:00am on 8/3/16
	 * @throws ParseException
	 */
	public int ques6getter() throws ParseException{
		int bikesUsed = 0;

		for (Trip trip : trips){
			String startTime = trip.getStartTime();
			java.util.Date parsedStartTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(startTime);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(parsedStartTime);

			String endTime = trip.getEndTime();
			java.util.Date parsedEndTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(endTime);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(parsedEndTime);

			String targetTime = "8/3/16 07:00";
			java.util.Date parsedTargetTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(targetTime);
			Calendar calendar3 = Calendar.getInstance();
			calendar3.setTime(parsedTargetTime);

			java.util.Date x = calendar3.getTime();
			if (trip.getStartTime().contains("8/3/16") && x.after(parsedStartTime) && x.before(parsedEndTime)){
				bikesUsed++;
			}
		}
		return bikesUsed;
	}

	//<<<<<<<QUESTION 7>>>>>>>>>>>
	// "7. Get trip info for longest trip by distance";
	/**
	 * A method that calculates the longest trip by distance and prints out all trip information directly
	 */
	public void ques7getter(){
		double maxDistance = 0;
		for (Trip trip : trips){
			//distance between 2 points on a coordinate plane
			//	 [sqrt ((x2-x1)^2 - (y2-y1)^2) ]
			double latitudeDiff = trip.getEndLatitude() - trip.getStartLatitude();
			double longitudeDiff = trip.getEndLongitude() - trip.getStartLongitude();
			double thisDistance = Math.sqrt((latitudeDiff*latitudeDiff) - (longitudeDiff*longitudeDiff));

			if (thisDistance > maxDistance){
				maxTrip = trip;
			}
		} System.out.println("<<<<LONGEST TRIP INFO>>>>" );
		System.out.println("TripID: " + maxTrip.getBikeID());
		System.out.println("Duration: " + maxTrip.getDuration());
		System.out.println("Start time: " + maxTrip.getStartTime());
		System.out.println("End time: " + maxTrip.getEndTime());
		System.out.println("Start Station ID: " + maxTrip.getStartStationID());
		System.out.println("Start latitude: " + maxTrip.getStartLatitude());
		System.out.println("Start longitude: " + maxTrip.getStartLongitude());
		System.out.println("End Station ID: " + maxTrip.getEndStationID());
		System.out.println("End latitude: " + maxTrip.getEndLatitude());
		System.out.println("End longitude: " + maxTrip.getEndLongitude());
		System.out.println("Bike ID: " + maxTrip.getBikeID());
		System.out.println("Plan duration: " + maxTrip.getPlanDuration());
		System.out.println("Trip route category: " + maxTrip.getTripRouteCategory());
		System.out.println("Passholder type: " + maxTrip.getPassholderType());
	}


	//<<<<<<<QUESTION 8>>>>>>>>>>>
	//"8. Print the list of trip IDs of all trips that involved a station which was the only station to go live on its respective go-live date.\n";
	/**
	 * A method that calculates the answer to question 8 (Print the list of trip IDs of all trips that involved a station which was the only station to go live on its respective go-live date)
	 * The method prints out tripIDs directly.
	 */
	public void ques8getter(){
		Set<String> uniques = new HashSet<String>();
		Set<String> dups    = new HashSet<String>();
		ArrayList<Integer> stationIDs = new ArrayList<>();

		for (Station station : stations){
			String goLiveDate = station.getGoLiveDate();
			if (!uniques.add(goLiveDate)){
				dups.add(goLiveDate);
			}
		}
		uniques.removeAll(dups);

		for (Station station2 : stations){
			if (uniques.contains(station2.getGoLiveDate())){
				stationIDs.add(station2.getStationID());
			}
		}

		for (Trip trip : trips){
			for(int i = 0; i < stationIDs.size(); i++){
				if (stationIDs.get(i) == trip.getStartStationID() || stationIDs.get(i) == trip.getEndStationID()){
					System.out.println("Trip ID: " + trip.getTripID());
				}
			}
		}
	}

	//<<<<<<<QUESTION 9>>>>>>>>>>>
	//"9. (MY QUESTION) What was the shortest trip in terms of time?";
	/**
	 * A method that calculates the shortest trip by duration
	 * @return shortestDuration, the time in minutes of the shortest trip
	 */
	public double ques9getter(){
		double currentDuration = 0;
		double shortestDuration = 100; //start time and end time are currently strings

		for (Trip trip : trips){
			currentDuration = trip.getDuration();

			if (currentDuration < shortestDuration){
				shortestDuration = currentDuration;
			}
		}
		return shortestDuration;
	}

	//<<<<<<<QUESTION 10>>>>>>>>>>>
	//"10. (MY QUESTION) What day was Indego bike usage highest?";
	/**
	 * A method that calculates the answer to question 10 (what day was bike usage highest?)
	 * @return String mostUseDate, a string containing the date when usage was highest
	 */
	public String ques10getter(){
		String date = "";
		int thisDateOccurence = 0;
		int highestDateOccurence = 0;
		String mostUseDate = "";
//		ArrayList<String> allDates = new ArrayList<>();
		HashMap hm = new HashMap();
		
		for (Trip trip : trips){
			String dateLine = trip.getEndTime();
			date = dateLine.substring(0, 7);
			hm.put(date, trip);
		}
		
		for (Trip tripList : trips){
				hm.get(date);
				if (tripList.getEndTime().contains(date)){
					thisDateOccurence++;
				}
				if (thisDateOccurence > highestDateOccurence){
					highestDateOccurence = thisDateOccurence;
					mostUseDate = date;
				}
			} 
		
		System.out.println("Total usage: " + highestDateOccurence + " trips");
		return mostUseDate;
	}
}