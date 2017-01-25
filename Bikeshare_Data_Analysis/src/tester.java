import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IndegoObjectCreator ioc = new IndegoObjectCreator("Q3_2016_trips.csv", "Station_table.txt");
		String tripType = "" ;
		ArrayList<Trip> trips = ioc.getTrips();
		ArrayList<Station> stations = ioc.getStations();


		//<<<<<<<QUESTION 8>>>>>>>>>>>
		//	userChoice[7] = "8. Print the list of trip IDs of all trips that involved a station which was the only station to go live on its respective go-live date.\n";

		//			public void ques8getter(){
		//		ArrayList<Integer> uniqueGoLiveStations = new ArrayList<>();
		//		ArrayList<Integer> uniqueGoLiveStations2 = new ArrayList<>();

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

//		System.out.println(uniques);
//		System.out.println(dups);

	}
}


//<<<<<<<QUESTION 3>>>>>>>>>>>
//	userChoice[2] = "3. What percentage of trips started in Rittenhouse Square?";
//		public double ques3Getter(){
//			double tripCounter = 0;
//			double ritCounter = 0;
//			int rittenhouseStationID = 0;
//			double rittenhousePercentage = 0;
//
//			for (Station station : stations){ 
//				if (station.getStationName().contains("Rittenhouse")){
//					rittenhouseStationID = station.getStationID();
//				}
//			}
//			for (Trip trip : trips){	
//				tripCounter++;
//				if (trip.getStartStationID() == rittenhouseStationID){
//					ritCounter++;
//				}
//			} rittenhousePercentage = (ritCounter / tripCounter);
//			System.out.println("trip ctr, rit ctr: " + tripCounter + " " + ritCounter);
//			System.out.printf("%.10f",rittenhousePercentage);
////			return rittenhousePercentage;
////		}
//
//		//<<<<<<<QUESTION 4>>>>>>>>>>>
//		//	userChoice[3] = "4. What percentage of trips made by Indego30 riders are roundtrip?";
////		public double ques4getter(){
//			int ind30counter = 0;
//			int indRoundtripCounter = 0;
//
//			for (Trip trip : trips){
//				if (trip.getPassholderType().equalsIgnoreCase("Indego30")){
//					ind30counter++;
//				}
//			}
//
//			for (Trip allTrips : trips){	
//				if (allTrips.getTripRouteCategory().equalsIgnoreCase("round trip")){
//					indRoundtripCounter++;
//				}
//			} 
//			double rountTripPercentage = indRoundtripCounter / ind30counter;
////			return rountTripPercentage;
//		}
//		
//		
//		
//		
//	}
//
////}
