import java.util.Date;

import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.User;

public class Tweet {
	
//	getText(1), getGeoLocation(2), getPlace(3), getLang(4), getUser(5), isRetweeted(6), getCreatedAt(7)
	private String text; //1
	private GeoLocation location; //2
	private Place place; //3
	private String language; //4
	private User user; //5
	private boolean isRetweeted; //6
	private Date sentDate; //7
//	private Status stat;

	public Tweet(String text, GeoLocation location, Place place, String language, User user, boolean isRetweeted, Date sentDate) {
		this.text = text;
		this.location = location;
		this.place = place;
		this.language = language;
		this.user = user;
		this.isRetweeted = isRetweeted;
		this.sentDate = sentDate;
	}
	
//	public TweetListCreator() {
//		
//	}
	
	public String toString() {
		String str = ("User: " + user + "\nText: " + text + "\nSent Date: " + sentDate +
				"\nLocation: " + location + "\nPlace:" + place + "\nLanguage:" + language);
		
		return str;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRetweeted() {
		return isRetweeted;
	}

	public void setRetweeted(boolean isRetweeted) {
		this.isRetweeted = isRetweeted;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	
	
	

	
	
	
}
