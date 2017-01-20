import java.util.Date;

import twitter4j.GeoLocation;
import twitter4j.Status;

public class TweetListCreator {
	private String fromSN;
	private long fromID ;
	private String text;
	private Date sentDate;
	private GeoLocation location;
	private String name;
	private long tweetID;
	private int amtFriends;
	private Status stat;

	public TweetListCreator(String fromSN, long fromID, String text, Date sentDate, GeoLocation location, String name, long tweetID, Status stat) {
		this.fromSN = fromSN;
		this.fromID = fromID;
		this.text = text;
		this.sentDate = sentDate;
		this.location = location;
		this.name = name;
		this.tweetID = tweetID;
		this.stat = stat;
	}
	
//	public TweetListCreator() {
//		
//	}
	
	public String getSenderSN() {
		return fromSN;
	}
	
	public long getSenderID() {
		return fromID;
	}
	
	public String getText() {
		return text;
	}
	
	public Date getSentDate() {
		return sentDate;
	}
	
	public GeoLocation getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	public long getTweetID() {
		return tweetID;
	}
	
	public int getAmtFriends() {
		return amtFriends;
	}
	
	public Status getStat() {
		return stat;
	}
	
	public void setSenderSN(String fromSN) {
		this.fromSN = fromSN;
	}
	
	public void setStat(Status stat) {
		this.stat = stat;
	}
	
	public void setSenderID(long fromID) {
		this.fromID = fromID;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	public void setLocation(GeoLocation location) {
		this.location = location;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTweetID(long tweetID) {
		this.tweetID = tweetID;
	}
	
	public void setAmtFriends(int amtFriends) {
		this.amtFriends = amtFriends;
	}
	
	public String toString() {
		String str = ("SN: " + fromSN + "\nID: " + fromID + 
				"\nText: " + text + "\nName: " + name + "\nSent Date: " + sentDate +
				"\nLocation: " + location + "\nTweetID: " + tweetID + "\nAmount of Friends: " + amtFriends);
		
		return str;
	}
	
	
	
	

	
	
	
}
