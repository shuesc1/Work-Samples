
public class dateFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String date = "";
		String dateLine = "8/2/16 12:34";
//		String dateLine = trip.getEndTime();
			
	      int i = 0; // Locate the start of the first digit
	      dateLine.s
//	    char character = dateLine.charAt(i);
	     while (dateLine.substring(i) != " "){
	      String date = dateLine.substring(0, i);
	     }
	      while (!char.equals(" ")){ i++; }
//	    		  dateLine.length() <= i) {(dateLine.charAt(i))) { i++; }}
//	    		  !Character.isAlphabetic(dateLine.charAt(i))) { i++; }
	      int j = i - 1; // Locate the end of the preceding word
	      while (Character.isWhitespace(dateLine.charAt(j))) { j--; }     
	      date = dateLine.substring(0, j + 1); // Extract the country name
//	      value = Double.parseDouble(line.substring(i).trim()); // Extract the value
		
		System.out.println(date);
		
	}

}
