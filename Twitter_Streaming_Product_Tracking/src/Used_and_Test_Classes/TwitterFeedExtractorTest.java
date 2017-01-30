import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TwitterFeedExtractorTest {

	@Test
	public void testArraySize() {
		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		ArrayList<Tweet> array = new ArrayList<>();
		tfe.setTweetList(array);
		Tweet tweet1 = new Tweet("I am tweet the first", null, null, "en", null, true, null);
		Tweet tweet2 = new Tweet("I am tweet the second", null, null, "fr", null, false, null);
		array.add(tweet1);
		array.add(tweet2);
		
		assertEquals(2, tfe.getTotalTweets());
	}

	@Test
	public void testGetArray() {
		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		ArrayList<Tweet> array = new ArrayList<>();
		tfe.setTweetList(array);
		
		assertEquals(new ArrayList<>(0), tfe.getTweetList());
//		assertThat(tfe.getTweetList().isEmpty(), is(true);
	}
	
	@Test
	public void testGetLang() {
		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		assertEquals("en", tfe.getLanguage());
	}
	
	@Test
	public void testSetLang() {
		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		tfe.setLanguage("fr");
		
		assertEquals("fr", tfe.getLanguage());
	}
	
	@Test
	public void testSetLang2() {
		TwitterFeedExtractor tfe = new TwitterFeedExtractor();
		tfe.setLanguage("fr");
		
		assertNotEquals("en", tfe.getLanguage());
	}
	
}
