Joseph Haymaker
CIT 591
Final Project
01/2017


OBJECTIVE: To create a project that would get live tweets pertaining to the prescription
drug Truvada/PrEP, and then allow the user to break down the data how they saw fit, primarily
focusing on negative tweets about the product.

BACKGROUND: The drug Emtricitabine / Tenofovir (known colloquially by its brand name “Truvada” or “PrEP” for Pre-Exposure Prophylaxis) is a medication that can be used to help reduce the risk of getting HIV-1 infection when used together with safer sex practices. The drug was released commercially in 2004 by Gilead Sciences, Inc., an American biopharmaceutical company, and, despite being a groundbreaking new tool for HIV prevention, has struggled to be embraced by at-risk communities. Major deterrents to usage include: lack of visibility in the community, prohibitive costs, efficacy concerns related to adherence, and possible major side effects such as lactic acidosis or liver dysfunction. This project assumes the role of a Gilead representative wishing to track the conversation around their product.

PROJECT KEYWORDS: [Twitter, Watson API, streaming API, Truvada, PrEP, Gilead, IBM Watson Developer Cloud, Java, IBM Bluemix, product tracking, product insight, linguistic analysis, JSON, NLP]

GITHUB REPO: << https://github.com/cit-upenn/cit-591-projects-fall-2016-product_feedback_twitter >>

PRIMARY CLASSES: 1. Tweet.java, 2. TwitterFeedExtractor.java, 3. WatsonToneAnalyzer.java, 4. CsvWriter.java, 5. UserInterface.java

JUNIT TEST CLASSES: 6. TweetTest.java, 7. TwitterFeedExtractorTest.java

TEST/PRELIMINARY DESIGN CLASSES: 8. AlchemyAPI.java, 9. processJSonObj.java, 10. Runner.java, 11. SentimentAnalysis.java, 12. SentimentAnalyzer.java, 13. ToneAnalyzer.java, 14. TwitterTesting2.java

REFERENCED LIBRARIES: twitter4j, org.json, opencsv

DESIGN:The original design I worked out with my project manager was broken down into five steps. It seemed logical at the time that each step would be handled by a distinct class. The first step was to get live tweets from Twitter filtered according to keywords. A class then would store these tweets and get the tone (“positive” or “negative”). Step 2 was creating the tweet object that would be stored in the previous class. The object would have instance variables such as text, geolocation, language, user, time, and sentiment, or any other characteristic that a company may be interested in. Step 3 was to be done by some sort of WatsonToneAnalyzer that would have a method to take in a string tweet and call IBM Watson Developer Cloud’s Tone Analyzer, a service that uses linguistic analysis to detect three types of tones in written text: emotions (anger, disgust, fear, joy, & sadness), social tendencies, and writing style. As an aside I mistakenly began looking at the documentation to use the Sentiment Analysis feature of Watson and trying to implement it before my project manager informed me this service is used to analyze large amounts of collected data. Step 4 was storing the tweet with its corresponding sentiment. The last step was to write out the information to a .csv file so as to be more easily analyzed by further classes. Actual implementation morphed during the course of working on the project, and resulted in the following 5 final classes: 1. Tweet.java, 2. TwitterFeedExtractor.java, 3. WatsonToneAnalyzer.java, 4. CsvWriter.java, 5. UserInterface.java. 


CLASSES: 

1. Tweet.java - creates a tweet object with the following instance variables: String text, GeoLocation location, Place place, String language, User user, boolean isRetweeted, Date sentDate, String tone. The constructor takes in all variables to create the object save the tone, which is set later. This class provides all appropriate getters and setters for the variables.

2. TwitterFeedExtractor.java - This class has 4 key instance variables: an ArrayList of Tweet objects, an instance of the WatsonToneAnalyzer class, a String tone, and a String language that sets the search to limit findings to tweets in English. There are getter methods for the ArrayList of tweets and the size of said list, as well as a setter method for the ArrayList. Getter/setter methods for the language are also present. The primary method here is the run() method. It initializes the listOfTweets ArrayList and also the WatsonToneAnalyzer object so tone can be added as Tweet objects are created. The method sets authorization credentials obtained from Watson, then prints out tweets as they are obtained. Near the bottom of the method you can see that the stream is being filtered by 2 keywords: “truvada” & “PrEP”. One noticeable flaw is that the latter word allows for tweets with any occurrence of “prep” (such as “meal prep”), thus leading to many off topic findings. A good expansion of this ability to filter would allow the user to set the keywords themselves as opposed to hard-coding them. It’s also worth noting in the onStatus() portion of the code here I put a limit on the number of tweets that would print out (arbitrarily 100). It was originally supposed to be the sample size the user put in, but this worked more readily. Without it the tweets continued to print out and essentially stopped any subsequent actions in the UI class. 

3. WatsonToneAnalyzer.java - This deceivingly simple class took a great deal of work to get functioning and, in fact, remains incomplete. While I was able to get the credentials to get the tone of a given piece of text, I was unable to adequately parse the resultant JSON object and extract the necessary data to decide the tone of each tweet. Hypothetical logic is provided and correct functioning would obviously be imperative for further work on this project. 

4. CsvWriter.java - The original design of this class was radically different from the final product, as I was trying to allow the user to set the information to include before writing the .csv file (some of this code still remains). For the sake of ease and clarity I decided to write all information to the .csv file and then extract the necessary information from it (ideally). The major issue with this class was setting the delimiter (“,”). I found an example of a class that already does this, and thus created a build path to the opencsv

5. UserInterface.java - First and foremost I acknowledge that this class is much longer than it should be. It was an oversight on my part to not fully comprehend how all the classes would fit together. Further work would refine many of the features of this class. Much of the implementation applies to only specific cases and don’t adequately handle errors. All in all there is still much work to be done in this class to produce a viable and logical/eloquent user interface. Most recent testing has also shown that it doesn’t break out of the tfe.run() loop. 


FURTHER READING:
- http://www.truvada.com/
- http://www.out.com/news-opinion/2013/09/09/hiv-prevention-new-condom-truvada-pill-prep
- https://www.nytimes.com/2014/07/17/upshot/is-truvada-the-pill-to-prevent-hiv-99-percent-effective-dont-be-so-sure.html?_r=0
- http://nymag.com/news/features/truvada-hiv-2014-7/
- http://www.npr.org/sections/health-shots/2012/07/17/156868446/deciding-on-truvada-who-should-take-new-hiv-prevention-pill
- https://www.ibm.com/watson/developercloud/doc/tone-analyzer/
- https://github.com/watson-developer-cloud/tone-analyzer-nodejs
- https://github.com/watson-developer-cloud/java-sdk
- http://twitter4j.org/en/

