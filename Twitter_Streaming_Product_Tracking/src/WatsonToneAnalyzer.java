import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

public class WatsonToneAnalyzer {

	private String TweetToAnalyze;
	private String tweetTone;
	
	WatsonToneAnalyzer(){
	}

	public String GetTweetTone(String tweet){
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("0b749d00-1e64-4e19-9e39-12596e5975da", "0wjKfSOI0wRc");
		ToneAnalysis tone = service.getTone(tweet, null).execute();
		
//		tone.
		//need to parse return and provide logic to decide if it is 'positive' or 'negative'
		
		return tweetTone;
	}
}
