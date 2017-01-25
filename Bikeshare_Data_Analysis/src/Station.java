/**
 * A class that creates and object that represents and Indego bike station with characteristics from the provided .txt file
 * @author josephhaymaker
 *
 */
public class Station {

	private int stationID;
	private String stationName;
	private String goLiveDate;
	private String status;

	/**
	 * The constructor for the class. It takes in as arguments the variables obtained from the .txt file and initializes them
	 * @param thisStationID the station ID number, stored as an int
	 * @param thisStationName the station name, stored as a string
	 * @param thisGoLiveDate the station go live date, stored as a string
	 * @param thisStatus the station status (Active, inactive, etc.), stored as a string
	 */
	public Station(int thisStationID, String thisStationName, String thisGoLiveDate, String thisStatus){
		stationID = thisStationID;
		stationName = thisStationName;
		goLiveDate = thisGoLiveDate;
		status = thisStatus;
	}

	/**
	 * A getter method for the station id
	 * @return int stationID
	 */
	public int getStationID() {
		return stationID;
	}

	/**
	 * a getter method for the station name
	 * @return String stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * a getter method for the station go-live date
	 * @return String goLiveDate
	 */
	public String getGoLiveDate() {
		return goLiveDate;
	}

	/**
	 * a getter method for the station status (active, inactive, etc.)
	 * @return String status
	 */
	public String getStatus() {
		return status;
	}



}
