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
	private String text; //1
	private GeoLocation location; //2
	private Place place; //3
	private String language; //4
	private User user; //5
	private boolean isRetweeted; //6
	private Date sentDate; //7
	private String tone;//8

	/**
	 * The constructor for the class that takes in all instance variables save the tone
	 * @param text A string of the actual text of the tweet
	 * @param location A geolocation type that represents a certain latitude and longitude
	 * @param place A place type that contains coordinates, country name, full name, and address
	 * @param language A string representing the language of the tweet
	 * @param user A data interface representing Basic user information element
	 * @param isRetweeted A boolean that is true if tweet was retweeted, false if not
	 * @param sentDate A date type that records when the tweet was created
	 */
	public Tweet(String text, GeoLocation location, Place place, String language, User user, boolean isRetweeted, Date sentDate) {
		this.text = text;
		this.location = location;
		this.place = place;
		this.language = language;
		this.user = user;
		this.isRetweeted = isRetweeted;
		this.sentDate = sentDate;
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
	public void setText(String text) {
		this.text = text;
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
	public void setLocation(GeoLocation location) {
		this.location = location;
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
	public void setPlace(Place place) {
		this.place = place;
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
	public void setLanguage(String language) {
		this.language = language;
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
	public void setUser(User user) {
		this.user = user;
	}

	public void setRetweeted(boolean isRetweeted) {
		this.isRetweeted = isRetweeted;
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
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
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
	public void setTone(String tone) {
		this.tone = tone;
	}
}
