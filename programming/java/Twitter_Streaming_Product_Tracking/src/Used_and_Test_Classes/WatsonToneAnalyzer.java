import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

/**
 * A class that calls the Watson Tone Analyzer and creates a tone variable for each tweet
 * @author josephhaymaker
 *
 */
public class WatsonToneAnalyzer {
	private String TweetToAnalyze;
	private String tweetTone;

	/**
	 * The constructor for the class
	 */
	WatsonToneAnalyzer(){
	}

	/**
	 * A class that calls the Tone Analyzer service which creates a JSON object, which is parsed, read, and used to return a tone
	 * @param tweet a tweet object with corresponding variables
	 * @return tweetTone a string with the value of either "positive", "negative", or "indeterminate"
	 */
	public String GetTweetTone(String tweet){
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("0b749d00-1e64-4e19-9e39-12596e5975da", "0wjKfSOI0wRc");
		ToneAnalysis tone = service.getTone(tweet, null).execute();

		//need to parse return and provide logic to decide if it is 'positive' or 'negative'
		//PARSE JSON--would look like something in processJSonObj.java
		// get "docEmotions", returns 1. anger, 2. disgust, 3. fear, 4. joy, 5. sadness
		//each has a score from 0.0 to 1.0 ; if score > 0.5 text "contains" emotion
		//////LOGIC//////
//		if (anger >= 0.5 || disgust >= 0.5 || fear >= 0.5){
//			tweetTone = "negative";
//		} else if (joy >= 0.5 || sadness < 0.5){
//			tweetTone = "positive";
//		} else {
//			tweetTone = "indeterminate";
//		}

		return tweetTone;
	}
}
