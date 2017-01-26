import java.util.ArrayList;
import java.util.Scanner;

import twitter4j.TwitterException;

public class UserInterface {

	public static void main(String[] args) throws TwitterException {

		int sampleSize = 0;
		ArrayList<Tweet> tweetList;

		System.out.print("Welcome to the Twitter tracking program. This program aggregates any english tweet\n"
				+ "associated with the product Truvada/PrEP, a prescription medicine that can be used\n"
				+ "to help reduce the risk of getting HIV-1 infection when used together with safer sex practices.\n");
		System.out.println("To begin with, what sample size of tweets would you like data from?");
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			if (in.hasNextInt()){
				sampleSize = in.nextInt();
			} else {
				System.out.println("Error: please put in a valid number");
			}

		}
		System.out.println("******Please wait, aggregating tweets*****");

		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		while(tfe.getTotalTweets() <= sampleSize){
			tfe.run();
		}
		tweetList = tfe.getTweetList();
		CsvWriter writer = new CsvWriter(tweetList);

		in.close();
	}

}
