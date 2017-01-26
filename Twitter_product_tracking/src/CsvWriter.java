import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.opencsv.CSVWriter;

import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.User;

public class CsvWriter {
	private static final String COMMA_DELIM = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	ArrayList<Tweet> allTweets;
	private static String filename = "Aggregated_tweets.csv";
	private static String textData; 
	private static GeoLocation locationData; 
	private static Place placeData; 
	private static String languageData;
	private static User userData; 
	private static boolean isRetweetedData;
	private static Date sentDateData;
	private static String toneData;
//	private static boolean wantText;
//	private static boolean wantLocation;
//	private static boolean wantPlace;
//	private static boolean wantLang;
//	private static boolean wantUser;
//	private static boolean wantDate;

	public CsvWriter(ArrayList<Tweet> tweetList){
	}
	//	public void setOutputData(boolean wantText, boolean wantLocation, boolean wantPlace, boolean wantLang, boolean wantUser, boolean wantDate){
	//		wantText = this.wantText;
	//		wantLocation = this.wantLocation;
	//		wantPlace = this.wantPlace;
	//		wantLang = this.wantLang;
	//		wantUser = this.wantUser;
	//		wantDate = this.wantDate;
	//	}

	public void toCSV() {
		//check to see if file already exists
		boolean alreadyExists = new File(filename).exists();

		try {
//			CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
			FileWriter csvOutput = new FileWriter(filename);
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				//HANDLE HEADER
				csvOutput.write("TEXT");
				csvOutput.append(COMMA_DELIM);
				csvOutput.write("LOCATION");
				csvOutput.append(COMMA_DELIM);
				csvOutput.write("PLACE");
				csvOutput.append(COMMA_DELIM);
				csvOutput.write("LANGUAGE");
				csvOutput.append(COMMA_DELIM);
				csvOutput.write("USER");
				csvOutput.append(COMMA_DELIM);
				csvOutput.write("DATE");
				csvOutput.append(COMMA_DELIM);
				csvOutput.write("TONE");
				csvOutput.append(NEW_LINE_SEPARATOR);
			}
			// else assume that the file already has the correct header line
			csvOutput.write(textData);
			csvOutput.append(COMMA_DELIM);
			csvOutput.write(locationData.toString());
			csvOutput.append(COMMA_DELIM);
			csvOutput.write(placeData.toString());
			csvOutput.append(COMMA_DELIM);
			csvOutput.write(languageData);
			csvOutput.append(COMMA_DELIM);
			csvOutput.write(userData.toString());
			csvOutput.append(COMMA_DELIM);
			csvOutput.write(sentDateData.toString());
			csvOutput.append(COMMA_DELIM);
			csvOutput.write(toneData);
			csvOutput.append(NEW_LINE_SEPARATOR);

			csvOutput.close();
			csvOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
