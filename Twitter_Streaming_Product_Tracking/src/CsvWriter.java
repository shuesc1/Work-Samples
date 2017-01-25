import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.User;

public class CsvWriter {
//    private static final String COMMA_DELIMITER = ",";
//    private static final String NEW_LINE_SEPARATOR = "\n";
	private static String filename;
	private static String textData; //1 //idea being that the user can choose the data to be included in file
	private static GeoLocation locationData; //2
	private static Place placeData; //3
	private static String languageData; //4
	private static User userData; //5
	private static boolean isRetweetedData; //6
	private static Date sentDateData;//7
	private static boolean wantText;
	private static boolean wantLocation;
	private static boolean wantPlace;
	private static boolean wantLang;
	private static boolean wantUser;
	private static boolean wantDate;

	//Takes in filename so it can easily be changed
	//existing CsvWriter class and constructors??
	public CsvWriter(String filename){
		filename = this.filename;
	}

	public void setOutputData(boolean wantText, boolean wantLocation, boolean wantPlace, boolean wantLang, boolean wantUser, boolean wantDate){
		wantText = this.wantText;
		wantLocation = this.wantLocation;
		wantPlace = this.wantPlace;
		wantLang = this.wantLang;
		wantUser = this.wantUser;
		wantDate = this.wantDate;
	}

//	public static boolean getWantText() {
//		return wantText;
//	}
//
//	public static void setWantText(boolean wantText) {
//		CsvWriter.wantText = wantText;
//	}
//
//	public static boolean getWantLocation() {
//		return wantLocation;
//	}
//
//	public static void setWantLocation(boolean wantLocation) {
//		CsvWriter.wantLocation = wantLocation;
//	}
//
//	public static boolean getWantPlace() {
//		return wantPlace;
//	}
//
//	public static void setWantPlace(boolean wantPlace) {
//		CsvWriter.wantPlace = wantPlace;
//	}
//
//	public static boolean getWantLang() {
//		return wantLang;
//	}
//
//	public static void setWantLang(boolean wantLang) {
//		CsvWriter.wantLang = wantLang;
//	}
//
//	public static boolean getWantUser() {
//		return wantUser;
//	}
//
//	public static void setWantUser(boolean wantUser) {
//		CsvWriter.wantUser = wantUser;
//	}
//
//	public static boolean getWantDate() {
//		return wantDate;
//	}
//
//	public static void setWantDate(boolean wantDate) {
//		CsvWriter.wantDate = wantDate;
//	}

	public static void main(String[] args) {
		//check to see if file already exists
		boolean alreadyExists = new File(filename).exists();

		try {
			// use FileWriter constructor that specifies open file, set appending TRUE
			//DELIMITER
			//			FileWriter fw = new FileWriter(filename, true);
			FileWriter csvOutput = new FileWriter(filename, true);
			//			CsvWriter csvOutput = new CsvWriter(new FileWriter(filename, true));

			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				//HANDLE HEADER
				//				csvOutput.write("DATA NAME 1");
				//				csvOutput.write("DATA NAME 2"); //ETC.
				//				csvOutput.endRecord();
				if (wantText == true){
					csvOutput.write("TEXT");
				}
				if (wantLocation == true){
					csvOutput.write("LOCATION");
				}
				if (wantPlace == true){
					csvOutput.append("PLACE");
				}
				if (wantLang == true){
					csvOutput.write("LANGUAGE");
				}
				if (wantUser == true){
					csvOutput.append((CharSequence) userData);
				}
				//				csvOutput.write(isRetweetedData);
				if (wantDate == true){
					csvOutput.append((CharSequence) sentDateData);
				}
				 
				csvOutput.append("/n");

			}
			// else assume that the file already has the correct header line
			if (wantText == true){
				csvOutput.write(textData);
			}
			if (wantLocation == true){
				csvOutput.append((CharSequence) locationData);
			}
			if (wantPlace == true){
				csvOutput.append((CharSequence) placeData);
			}
			if (wantLang == true){
				csvOutput.write(languageData);
			}
			if (wantUser == true){
				csvOutput.append((CharSequence) userData);
			}
			//			csvOutput.write(isRetweetedData);
			if (wantDate == true){
				csvOutput.append((CharSequence) sentDateData);
			}

			//regardless of line elements always add new line at end
			csvOutput.append("/n");
			//			csvOutput.endRecord();

			csvOutput.close();
			csvOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
