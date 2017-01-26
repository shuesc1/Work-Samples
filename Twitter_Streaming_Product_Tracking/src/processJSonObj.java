import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

public class processJSonObj {

	public static void main(String[] args){

//		public JsonObject process(String jsonString) {
			String jsonString = "";
			JsonParser parser = new JsonParser(); 
			JsonObject myBean = parser.parse(jsonString).getAsJsonObject();

			ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
			service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());

			// Call the service and get the tone
			ToneAnalysis tone = service.getTone(myBean.get("text").getAsString(), null).execute();

			JsonObject json = parser.parse(tone.toString()).getAsJsonObject();

//			return json;
//		}
	}

}
