import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A class that uses the FileReader class to read both files, parses their resulting lines, stores them in a Stationa and Trip object, and then creates arrayLists of those objects
 * @author josephhaymaker
 *
 */
public class IndegoObjectCreator {

	private StringTokenizer st;
	private ArrayList<String> stationLines;
	private ArrayList<String> tripLines;
	private ArrayList<Trip> trips;
	private ArrayList<Station> stations;

	/**
	 * The constructor for the class. It employs the FileReader class to read 2 files, get the arrayLists of lines they create, then creates arrayLists for the Trip and Station objects
	 * @param tripFile a file with the trip information
	 * @param stationFile a file with the station information
	 */
	public IndegoObjectCreator(String tripFile, String stationFile){
		FileReader tripFR = new FileReader(tripFile);
		FileReader stationFR = new FileReader(stationFile);

		tripFR.getLines();
		stationFR.getLines();

		stationLines = stationFR.getLines();
		stationLines.remove(0);
		tripLines = tripFR.getLines();
		tripLines.remove(0);
		trips = new ArrayList<>();
		stations = new ArrayList<>();
		loadData();
	}

	/**
	 * A method that parses each line from both files and stores data as distinct variables in Station and Trip objects. These objects are then stored in their own ArrayLists.
	 */
	public void loadData(){

		for (String storedLines : stationLines){
			String[] result = storedLines.split(",");

			int id = Integer.parseInt(result[0]);
			String name = result[1];
			String goLiveDate = result[2];
			String status = result[3];

			Station station = new Station(id, name, goLiveDate, status); //create each individual station object
			stations.add(station); //store station objs in ArrayList
		}

		for (String tripLine : tripLines){

			String[] result = tripLine.split(",");

			int thisTripID = Integer.parseInt(result[0]);
			int thisDuration = Integer.parseInt(result[1]);
			String thisStartTime = result[2];
			String thisEndTime = result[3];
			int thisStartStationID = Integer.parseInt(result[4]);
			double thisStartLatitude = Double.parseDouble(result[5]);
			double thisStartLongitude = Double.parseDouble(result[6]);
			int thisEndStationID = Integer.parseInt(result[7]);
			double thisEndLatitude = Double.parseDouble(result[8]);
			double thisEndLongitude;
			try  {thisEndLongitude = Double.parseDouble(result[9]);
			}  
			catch(NumberFormatException nfe)  
			{  
				thisEndLongitude = 0;
			}
			int thisBikeID = Integer.parseInt(result[10]);
			int thisPlanDuration = Integer.parseInt(result[11]);
			String thisTripRouteCategory = result[12];
			String thisPassholderType = result[13];

			Trip trip = new Trip(thisTripID, thisDuration, thisStartTime, thisEndTime, thisStartStationID, thisStartLatitude, thisStartLongitude, thisEndStationID, thisEndLatitude, thisEndLongitude, thisBikeID, thisPlanDuration, thisTripRouteCategory, thisPassholderType);
			trips.add(trip);
		}

	}

	/**
	 * a getter method for the ArrayList of Trip objects
	 * @return ArrayList trips
	 */
	public ArrayList<Trip> getTrips() {
		return trips;
	}

	/**
	 * A getter method for the ArrayList of Station objects
	 * @return ArrayList stations
	 */
	public ArrayList<Station> getStations() {
		return stations;
	}
}
