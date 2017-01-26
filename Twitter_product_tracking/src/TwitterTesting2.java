import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTesting2 {

	public static void main(String[] args) throws TwitterException {
		// TODO Auto-generated method stub

		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("KY1Wh2MoiP1Q5c1BVUyemcRlR");
		cb.setOAuthConsumerSecret("BZGxfGZjYjDB2DjBuWlJaPw4pFaiahVb1eFfzHc5ymACrx17oz");
		cb.setOAuthAccessToken("4878823353-LszyOWEfmfnrUdHi3po7HhRjmZg8NVDA6BCpUuo");
		cb.setOAuthAccessTokenSecret("a0IUMlpxZ6EXsCY87rAUFa4iDRUBPzhxCykNCEW1MGj3x");
		
		//get username, get status, time of post
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		
		List<Status> status = twitter.getHomeTimeline();
			for (Status st : status){
//				if (st.)
				System.out.println(st.getUser().getName() + "-------------" + st.getText());
			}
		
	}

}
