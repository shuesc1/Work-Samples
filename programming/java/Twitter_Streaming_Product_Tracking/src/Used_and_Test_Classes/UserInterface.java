import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import twitter4j.TwitterException;

/**
 * A class that prompts a user for input based on data desired
 * @author josephhaymaker
 *
 */
public class UserInterface {

	public static void main(String[] args) throws TwitterException {
		int sampleSize = 10;
		ArrayList<Tweet> tl;

		System.out.print("Welcome to the Twitter tracking program. This program aggregates any english tweet\n"
				+ "associated with the product Truvada/PrEP, a prescription medicine that can be used\n"
				+ "to help reduce the risk of getting HIV-1 infection when used together with safer sex practices.\n");
		System.out.println("\nTo begin with, what sample size of tweets would you like data from?");
		Scanner in = new Scanner(System.in);
		try {
			sampleSize = in.nextInt();
		}
		catch (InputMismatchException ime) {
			System.out.println("Error: please put in a valid number");
		}

		System.out.println("******Please wait, aggregating tweets*****");

		//get tweets 
		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		tfe.run();
		
		//get list of tweets locally
		tl = tfe.getTweetList();

		//tweet collection still running, waiting to get the adequate amount of information
		while(tl.size() < sampleSize){
		}

		//iterate over list of tweets to generate CSV
		for (Tweet tweet: tl){
			CsvWriter writer = new CsvWriter(tl);
			writer.setOutputData(tweet.getText(), tweet.getLocation(), tweet.getPlace(), tweet.getLanguage(), tweet.getUser(), tweet.getSentDate(), tweet.getTone());
			writer.toCSV();
		}

		System.out.println("Great! Now, what information would you like to know about the tweets?");

		String[] userChoice = new String[7];
		userChoice[0] = "1. Print all negative tweets";
		userChoice[1] = "2. Print all positive tweets";
		userChoice[2] = "3. Search for tweets from a certain geographic region";
		userChoice[3] = "4. Get tweets from a certain time period";
		userChoice[4] = "5. Get tweets in a certain language";
		userChoice[5] = "6. Get data about a certain user." ;
		userChoice[6] = "7. Exit program.";

		for (int i = 0; i < userChoice.length; i++)
		{		System.out.println(userChoice[i]); }
		boolean end = false;
		while (end == false){
			System.out.println("Which option would you like to choose?:\n");
			int userSelection = in.nextInt();

			switch (userSelection){
			case 1:
				System.out.println("Negative tweet list:\n");
				System.out.println("Not currently available :-/");
			case 2: 
				System.out.println("Positive tweet list:\n");
				System.out.println("Not currently available :-/");
				break;
			case 3: 
				Scanner geo = new Scanner(System.in);
				System.out.println("Please enter a geographic location where you would like to get tweets from:\n");
				String targetGeo = geo.nextLine();
				for (Tweet tweet : tl){
					if(tweet.getPlace().toString().contains(targetGeo)){
						System.out.println(tweet.toString());
					}
				}
				break;
			case 4: 
				System.out.println("What start date/time would you like to get tweets from:\n");
				Scanner startTime = new Scanner(System.in);
				String startTarget = startTime.nextLine();
				System.out.println("Great! Now, what end date/time would you like to get tweets from:\n");
				String endTarget = startTime.nextLine();
				for (Tweet tweet : tl){
					//need to continue to refine this-- not working currently
					//					if(tweet.getSentDate().after(startTarget) && tweet.getSentDate().before(endTarget);){
					System.out.println(tweet.toString());
					//					}
				}
				break;
			case 5: 
				Scanner targetLang = new Scanner(System.in);
				System.out.println("Please enter a language you would like tweet results in:");
				String lang = targetLang.nextLine();
				tfe.setLanguage(lang);
				tfe.run();
				break;
			case 6: 
				System.out.println("Please enter a valid username:");
				Scanner targetName = new Scanner(System.in);
				String username = "";
				try {
					username = targetName.nextLine();
				} catch (InputMismatchException ime){
					System.out.println("Please enter a valid username in the format '@username':");
				}
				for (Tweet tweet : tl){
					if(tweet.getUser().getScreenName().contentEquals(username)){
						System.out.println(tweet.toString());
					}
				}
			case 7: 
				System.out.println("Exiting...");
				end = true;
				break;
			default: 
				end = true;
				break;		
			}
		}
		in.close();
	}

}
