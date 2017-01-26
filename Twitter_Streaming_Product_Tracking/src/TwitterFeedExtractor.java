
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFeedExtractor {

	private ArrayList<Tweet> listOfTweets;
	private WatsonToneAnalyzer wta;
	String tone;

	public TwitterFeedExtractor() {	
	}

	public int getTotalTweets(){
		int size = listOfTweets.size();
		return size;
	}
	
	public ArrayList<Tweet> getTweetList(){
		return listOfTweets;
	}
	
	public void run() throws TwitterException {
		listOfTweets = new ArrayList<>();
		wta = new WatsonToneAnalyzer();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("KY1Wh2MoiP1Q5c1BVUyemcRlR");
		cb.setOAuthConsumerSecret("BZGxfGZjYjDB2DjBuWlJaPw4pFaiahVb1eFfzHc5ymACrx17oz");
		cb.setOAuthAccessToken("4878823353-LszyOWEfmfnrUdHi3po7HhRjmZg8NVDA6BCpUuo");
		cb.setOAuthAccessTokenSecret("a0IUMlpxZ6EXsCY87rAUFa4iDRUBPzhxCykNCEW1MGj3x");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {

			@Override
			public void onStatus(Status status) {				
				Tweet thisTweet = new Tweet(status.getText(), status.getGeoLocation(), status.getPlace(), status.getLang(), status.getUser(), status.isRetweeted(), status.getCreatedAt());
				tone = wta.GetTweetTone(thisTweet.getText());
				thisTweet.setTone(tone);
				listOfTweets.add(thisTweet);
				System.out.println(status);
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		};

		FilterQuery fq = new FilterQuery();
		String[] keywords = {"truvada", "PrEP"};
		fq.language(new String[]{"en"});
		fq.track(keywords);

		twitterStream.addListener(listener);
		twitterStream.filter(fq);

		//POSSIBLE FURTHER IMPLEMENTATION
		//		SentimentAnalyzer sa = new SentimentAnalyzer(listOfTweets);
		//		ArrayList<Tweet> positiveTweets = sa.getPosTweets();
		//		ArrayList<Tweet> negativeTweets = sa.getNegTweets();
		//		//prompt user for csv filename and type of info they want in CSV file
		//		String filename = new Scanner(System.in);
		//		CsvWriter cw = new CsvWriter(filename);
		//		CSVWriter csvw = new CSVWriter();
		//		csvw.writeall(listOfTweets, true);
		//writes out CSV file according to user defined name and values/info desired

	}
}



