import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

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
			
			
			private static ArrayList<String> extractIdsFromResponse(String response) {
				ArrayList<String> ids = new ArrayList<String>();
				JSONObject obj = new JSONObject(response);
				JSONArray theResults = obj.getJSONArray("results");
				
				for (int i = 0; i < theResults.length(); i++)
				{
				    JSONObject singleGame = theResults.getJSONObject(i);
				    ids.add("" + singleGame.getInt("id"));
				}
				return ids;
			}
			
			private static String searchToString(String searchResponse) {
				JSONArray items = new JSONObject(searchResponse).getJSONArray("results");
				StringBuilder sb = new StringBuilder();
				
				sb.append("Games:");
				for (int i = 0; i < items.length(); i++)
				    sb.append(" " + items.getJSONObject(i).getString("name") + ",");
				
				sb.deleteCharAt(sb.length() - 1);
				
				return sb.toString();
			}
			
			private static String emtionToString(String gameResponse) {
				JSONObject singleEmotion = new JSONObject(toneResponse).getJSONObject("results");
				StringBuilder sb = new StringBuilder();
				
				sb.append("name" + ": " + singleEmotion.getString("name") + "  |  ");
				singleEmotion.remove("name");
				Set allKeys = singleEmotion.keySet();
				String[] keys = (String[]) allKeys.toArray();
//				StringTokenizer st = new StringTokenizer(allKeys);
				
//				for (String k : singleGame.keySet()) {
				for (String k : keys) {
					try {
						JSONArray fieldArr = singleEmotion.getJSONArray(k);
						sb.append(k + ":");
						
						for (int i = 0; i < fieldArr.length(); i++)
						{
						    JSONObject singleValue = fieldArr.getJSONObject(i);
						    sb.append(" " + singleValue.getString("name") + ",");
						}
						sb.deleteCharAt(sb.length()-1);
						sb.append( "  |  ");
					}
					catch (Exception e) {
						// if exception, just skip this field
					}
				}
				
				return sb.toString();	    
			}
			
	}

}
