
import java.io.IOException;
import java.util.List;

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

	public static void main(String[] args) throws TwitterException {
		//<<CONFIGURATIONBUILDER>> - A builder that can be used to construct a twitter4j configuration with desired settings.
		//methods to set OAuth tokens
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("KY1Wh2MoiP1Q5c1BVUyemcRlR");
		cb.setOAuthConsumerSecret("BZGxfGZjYjDB2DjBuWlJaPw4pFaiahVb1eFfzHc5ymACrx17oz");
		cb.setOAuthAccessToken("4878823353-LszyOWEfmfnrUdHi3po7HhRjmZg8NVDA6BCpUuo");
		cb.setOAuthAccessTokenSecret("a0IUMlpxZ6EXsCY87rAUFa4iDRUBPzhxCykNCEW1MGj3x");


		/*NEED SOMETHING LIKE THIS TO GET STATUSES?
		 * <<TWITTERFACTORY>> --A factory class for Twitter. An instance of this class is completely thread safe and can be re-used and used concurrently.
		 * 	METHOD: getInstance(AccessToken accessToken)-- Returns a OAuth Authenticated instance.	
		 * 	TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();

		List<Status> status = twitter.getHomeTimeline();
			for (Status st : status){
//				if (st.)
				System.out.println(st.getUser().getName() + "-------------" + st.getText());
			}
		 * 
		 * 
		 */

		/*<<TWITTERSTREAM>>-- A factory class for TwitterFactory.
		An instance of this class is completely thread safe and can be re-used and used concurrently.
		Note that TwitterStream is NOT compatible with Google App Engine as GAE is not capable of handling requests longer than 30 seconds.
		 * 
		 */
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		//A listener for receiving asynchronous responses from Twitter Async APIs.
		//interface <<STATUSLISTENER>>, extends StreamListener
		StatusListener listener = new StatusListener() {

			/*
			 * public interface <<STATUS>> - extends java.lang.Comparable<Status>, TwitterResponse, EntitySupport, java.io.Serializable
			A data interface representing one single status of a user.
			Relevant METHODS-- getText(), getGeoLocation(), getLang(), getPlace(),  getUser(), isRetweeted()
			 */
			@Override
			public void onStatus(Status status) {
				//				if (status.toString().contains("truvada") || status.toString().contains("PrEP")){
				//					System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				//					//idea being that instead of printing all valid tweets they get stored in arraylist
				//					//tweet variables to be stored: text, geoprint, language, user, time, sentiment
				//				}
				System.out.println(status);
			}

			/*
			 * (non-Javadoc)
			 * Called upon deletionNotice notices. Clients are urged to honor deletionNotice requests and discard deleted statuses immediately. At times, status deletionNotice messages may arrive before the status. Even in this case, the late arriving status should be deleted from your backing store.
			 */
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
		String[] keywords = {"trump", "PrEP"};
		fq.language("en");
		fq.track(keywords);
		
		twitterStream.addListener(listener);
		twitterStream.filter(fq);
		/*
		 * METHOD <<void sample()>> --Starts listening on random sample of all public statuses. 
		 * The default access level provides a small proportion of the Firehose. The "Gardenhose" 
		 * access level provides a proportion more suitable for data mining and research applications 
		 * that desire a larger proportion to be statistically significant sample.
		 * METHOD <<void sample(java.lang.String language)>> - Starts listening on random sample of 
		 * all public statuses. The default access level provides a small proportion of the Firehose. 
		 * The "Gardenhose" access level provides a proportion more suitable for data mining and 
		 * research applications that desire a larger proportion to be statistically significant 
		 * sample. Only samples Tweets written in the given language.
		 */
//		twitterStream.sample();
	}
}



