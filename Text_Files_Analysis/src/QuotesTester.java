import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class QuotesTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		String line = "if(getip(document.referrer)==\"www.eg.com\" || getip(document.referrer)==\"192.57.42.11\"";
		String[] lines = {" \"Hey there,\" he said, and I wondered why, \"but everything \nwill be okay.\"", "Then she told me, \"you shouldn't be here,\" but I didn't listen", "He once told me, \"We are our own worst enemies.\""};
		StringTokenizer stk;

		for (String line : lines){
			System.out.println(line);

			stk = new StringTokenizer(line, "\"");
			
			while (stk.hasMoreTokens()){
				try{
				stk.nextToken();
				String egStr = stk.nextToken().replaceAll("[^a-zA-Z]", " ").trim();
				System.out.println("\"" + egStr + "\"" + " , Length: " + egStr.length());
				}
				catch (NoSuchElementException nsee){
					break;
				}
			}
			//		stk.nextToken();
			//		String ipStr = stk.nextToken();
			//		System.out.println(ipStr);
		}






		//<<<<<<<<<<ORIGINAL CODE W/ SCANNER>>>>>>>>>>>>>>>>
		//		public HashMap<String, Integer> getQuoteOccurrences(){
		//			quoteOccurrences = new HashMap<String, Integer>();
		//			StringTokenizer stk;
		//			
		//			for (String line : textLines){
		////				Scanner quoteScanner = new Scanner(line);
		//				//			quoteScanner.useDelimiter(" ");	
		//				stk = new StringTokenizer(line, "\"");
		//				stk.nextToken();
		//				String egStr = stk.nextToken().replaceAll("[^a-zA-Z]", " ").trim();
		//
		//				while (quoteScanner.hasNext()){
		//					
		//					if (m.find()){
		//						quoteOccurrences.put(m.group(1), m.group(1).length());
		//					}
		//				}
		//				quoteScanner.close();
		//			}
		//			return quoteOccurrences;
		//		}


	}

}
