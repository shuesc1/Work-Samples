import java.util.Date;

import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.User;

/**
 * A class that creates a Tweet object with variables of interest
 * @author josephhaymaker
 *
 */
public class Tweet {
	private String text; 
	private GeoLocation location;
	private Place place; 
	private String language;
	private User user;
	private boolean isRetweeted; 
	private Date sentDate; 
	private String tone;

	/**
	 * The constructor for the class that takes in all instance variables save the tone
	 * @param aText A string of the actual text of the tweet
	 * @param location A geolocation type that represents a certain latitude and longitude
	 * @param place A place type that contains coordinates, country name, full name, and address
	 * @param language A string representing the language of the tweet
	 * @param user A data interface representing Basic user information element
	 * @param isRetweeted A boolean that is true if tweet was retweeted, false if not
	 * @param sentDate A date type that records when the tweet was created
	 */
	public Tweet(String aText, GeoLocation aLocation, Place aPlace, String aLanguage, User aUser, boolean Retweeted, Date aSentDate) {
		text = aText;
		location = aLocation;
		place = aPlace;
		language = aLanguage;
		user = aUser;
		isRetweeted = Retweeted;
		sentDate = aSentDate;
	}



	/**
	 * A method that prints out all tweet variables.
	 */
	public String toString() {
		String str = ("User: " + user + "\nText: " + text + "\nSent Date: " + sentDate +
				"\nLocation: " + location + "\nPlace:" + place + "\nLanguage:" + language + "\nTone:" + tone);
		return str;
	}

	/**
	 * A getter method for tweet text
	 * @return a string of the tweet's text
	 */
	public String getText() {
		return text;
	}

	/**
	 * A setter method for the tweet's text
	 * @param text the String of text to be used for the change
	 */
	public void setText(String newText) {
		text = newText;
	}

	/**
	 * A getter method for the location of the tweet
	 * @return location the geolocation of the tweet
	 */
	public GeoLocation getLocation() {
		return location;
	}

	/**
	 * A setter method for the tweet's location
	 * @param location the geolocation to be set
	 */
	public void setLocation(GeoLocation newLocation) {
		location = newLocation;
	}

	/**
	 * A getter method for the tweet's place
	 * @return place where the tweet was produced
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * A setter method for the tweet's place
	 * @param place the place to be set
	 */
	public void setPlace(Place newPlace) {
		place = newPlace;
	}

	/**
	 * A getter method for the language of the tweet
	 * @return language the language the tweet is in
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * A setter method for the tweet's language
	 * @param language the language to set the tweet to
	 */
	public void setLanguage(String setlanguage) {
		language = setlanguage;
	}

	/**
	 * A getter method for the tweet's user
	 * @return user the user who sent the tweet
	 */
	public User getUser() {
		return user;
	}

	/**
	 * A setter method for the tweet user
	 * @param user a tweet user to set
	 */
	public void setUser(User newUser) {
		user = newUser;
	}

	/**
	 * a getter method for if a tweet is retweeted
	 * @return isRetweeted a boolean 
	 */
	public boolean isRetweeted() {
		return isRetweeted;
	}
	
	/**
	 * A setter method for the boolean isRetweeted
	 * @param newIsRetweeted a new boolean value
	 */
	public void setRetweeted(boolean newIsRetweeted) {
		isRetweeted = newIsRetweeted;
	}

	/**
	 * A getter method for the sent date
	 * @return sentDate when the tweet was sent
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * A setter method for the sent date of a tweet
	 * @param sentDate a date to set
	 */
	public void setSentDate(Date newSentDate) {
		sentDate = newSentDate;
	}

	/**
	 * a getter method for the tone
	 * @return tone the String representing the tone, either "positive" or "negative"
	 */
	public String getTone() {
		return tone;
	}

	/**
	 * A setter method for the tweet tone
	 * @param tone a string of either "positive" or "negative"
	 */
	public void setTone(String newTone) {
		tone = newTone;
	}
}
