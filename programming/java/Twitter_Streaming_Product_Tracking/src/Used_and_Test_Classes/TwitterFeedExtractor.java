
import java.util.ArrayList;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * A class that gets tweets live with the Watson Streaming API
 * @author josephhaymaker
 *
 */
public class TwitterFeedExtractor {
	private ArrayList<Tweet> listOfTweets;
	private WatsonToneAnalyzer wta;
	private String tone;
	private String language = "en";
	private int counter = 0;
	
	/**
	 * The constructor for the class
	 */
	public TwitterFeedExtractor() {	
	}

	/**
	 * A getter method for the size of the ArrayList holding the tweet objects
	 * @return size an int of the size of the tweet ArrayList
	 */
	public int getTotalTweets(){
		int size = listOfTweets.size();
		return size;
	}

	/**
	 * A getter method for the arraylist of tweets
	 * @return listOfTweets an arraylist filled with tweet objects
	 */
	public ArrayList<Tweet> getTweetList(){
		return listOfTweets;
	}

	/**
	 * A setter method for the arraylist of tweets
	 * @param tweetList an arraylist filled with tweet objects
	 */
	public void setTweetList(ArrayList<Tweet> tweetList){
		listOfTweets = tweetList;
	}

	
	/**
	 * A getter method for the tweet language
	 * @return language the two letter code for the current language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * A setter method for the tweet language
	 * @param language the two letter code of the desired language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * A method that makes calls to the Watson Streaming API and gets live tweets containing keywords
	 * @throws TwitterException
	 */
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
//				for (int i = 0; i < 100; i++){
//					System.out.println(status);
//				}
				if (counter < 1){
				System.out.println("*****Tweet processing will continue running in the background...******");
				}
				counter++;
//				if(counter > 100){
//					break;
//				}
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
		fq.language(new String[]{language});
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



