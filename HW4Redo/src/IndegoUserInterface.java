import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that prints a list of questions based on data provided and allows the user to choose which question to get the answer to.
 * User choices are presented in a switch list.
 * @author josephhaymaker
 *
 */
public class IndegoUserInterface {

	private static final int NULL = 0;

	public static void main(String[] args) throws ParseException {
		//				IndegoObjectCreator ioc = new IndegoObjectCreator("Q3_2016_trips.csv", "Station_table.txt");
		//				System.out.println(ioc.getStations().size());
		//				System.out.println(ioc.getTrips().size());
		//				IndegoAnswerGenerator iag;
		IndegoAnswerGenerator iag = new IndegoAnswerGenerator();

		String[] userChoice;
		Scanner in = new Scanner(System.in);

		System.out.println("This program analyzes data from Philadelphia's bike share program, Indego.");
		System.out.println("Please select what you would like to know about the program from the following list:\n");

		userChoice = new String[10];
		userChoice[0] = "1. How many walk-up trips were there in 2016?";
		userChoice[1] = "2. How many stations that had a Go-Live date in 2015 are still 'Active'?";
		userChoice[2] = "3. What percentage of trips started in Rittenhouse Square?";
		userChoice[3] = "4. What percentage of trips made by Indego30 riders are roundtrip?";
		userChoice[4] = "5. What is the ID of the bike that has traveled the most in terms of duration?";
		userChoice[5] = "6. On 8/3/16 at 7:00am, how many bikes were being used?";
		userChoice[6] = "7. Get trip info for longest trip by distance";
		userChoice[7] = "8. Print the list of trip IDs of all trips that involved a station which was the only station to go live on its respective go-live date.";
		userChoice[8] = "9. What was the shortest trip in terms of time?";
		userChoice[9] = "10. What day was Indego bike usage highest?\n";

		for (int i = 0; i < userChoice.length; i++)
		{		System.out.println(userChoice[i]); }
		System.out.println("**Loading**");
		
		int walkupTripTotal = iag.ques1Getter();
		int stationsTotal = iag.ques2Getter();
		double RittenhousePercent = iag.ques3Getter();
		double roundtripPercent = iag.ques4getter();
		int longestBikeID = iag.ques5getter();
		int bikeNum = iag.ques6getter();
		double shortestTime = iag.ques9getter();
		//		String highestUseDay = iag.ques10getter();

		boolean end = false;

		while (end == false){
			System.out.println("Which option would you like to choose?:\n");
			int userSelection = in.nextInt();
			switch (userSelection){
			case 1:
				System.out.println("There were " + walkupTripTotal + " walk-up trips in 2016!\n");
				break;
			case 2: 
				System.out.println("There are " + stationsTotal + " stations that had a go-live date in 2015 and are still active\n");
				break;
			case 3: 
				System.out.println("A whopping " + RittenhousePercent + "% of all trips started in Rittenhouse Square\n");
				break;
			case 4: 
				System.out.println("A total of " + roundtripPercent + "% of trips made by Indego30 riders are roundtrip\n");
				break;
			case 5: 
				System.out.println("The ID of the bike that has traveled the longest (in terms of duration) is " + longestBikeID + "\n");
				break;
			case 6: 
				System.out.println("On 8/3/16 at 7:00am, there were " + bikeNum + " bikes being used.\n");
				break;
			case 7: 
				iag.ques7getter();
				System.out.println("\n");
				break;
			case 8: 
				System.out.println("List of trip IDs of all trips that involved a station which was the only station to go live on its respective go-live date: " + "\n");
				iag.ques8getter();
				break;
			case 9: 
				System.out.println("The shortest trip in terms of time was only " + shortestTime + " minutes long!\n");
				break;
			case 10:
				String highestUseDay = iag.ques10getter();
				System.out.println("The day that experienced the most usage was: " + highestUseDay);
				break;
			default: 
				//	                userSelection = in.nextInt();
				end = true;
				break;		
			}
		}
		in.close();
	}
}
