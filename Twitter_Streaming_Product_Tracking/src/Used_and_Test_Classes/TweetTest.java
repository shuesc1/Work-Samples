import static org.junit.Assert.*;

import org.junit.Test;

import twitter4j.GeoLocation;

public class TweetTest {

	@Test
	public void testSetText() {
//		GeoLocation gl = new GeoLocation();
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		t.setText("new text");
		assertEquals("new text", t.getText());
	}

	@Test
	public void testSetLang() {
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		t.setLanguage("sp");
		assertEquals("sp", t.getLanguage());
		
	}
	
	@Test
	public void testSetTone(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		t.setTone("negative");
		assertEquals("negative", t.getTone());
	}
	
	@Test
	public void testGetLocation(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		assertNull(t.getLocation());
	}
	
	@Test
	public void testGetPlace(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		assertNull(t.getPlace());
	}
	
	@Test
	public void testGetUser(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		assertNull(t.getUser());
	}
	
	@Test
	public void testGetSentDate(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		assertNull(t.getSentDate());
	}
	
	@Test
	public void testToString(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		assertNotNull(t.toString());
	}
	
	@Test
	public void testRetweeted(){
		Tweet t = new Tweet("I am a tweet", null, null, "en", null, true, null);
		assertEquals(true, t.isRetweeted());
	}
	
	
}
