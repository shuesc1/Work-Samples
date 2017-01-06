*************************
HW 5
Textual Analysis of books
J. Haymaker

Classes
In the Textual Analysis of books project, I've designed the following 
classes:

	1. FileReader - Taken from in-class work-- a class takes in a file and stores lines in 
	an ArrayList and provides a getter method for said lines.
	
	2. OccurrenceTracker - the primary problem solving class in this project. While 
	originally only containing a method to track letter occurrence, the final version of
	this class includes 5 methods to address the questions posed in the assignment
	description: getLetterOccurences(), getWordOccurrences(), 
	getWordsWStopList(), getQuoteOccurrences(), and getKeywordPercentage().
	
	3. HashmapOrdering - a class that has a method to order a HashMap in ascending order
	and two printing methods to printout the (sorted) HashMaps, isolating the top ten
	frequencies and ten least frequencies. 
			
	4. UserInterface - the public interface of this project, where a user is prompted to 
	enter a textfile to analyze, then presented with a list of options to choose from, that
	then print out the corresponding information. 
	
	Additional classes included, but not used in final program (tester classes):
	charCounterTest.java
	ComparatorTester.java
	HashMapValueSorting.java
	HMReverseTester.java
	QuotesTester.java
	QuoteTester.java
	readingCharsPractice.java
	RegexTesting.java
	SortingByValue.java
	ValueComparator.java
	WordOccurrenceTester.java
	wordPercentageTester.java
	
	HW5_txt_files:
	alice-in-wonderland.txt
	christmas-carol.txt
	huck-finn.txt
	les-mis.txt
	metamorphosis.txt
	my-man-jeeves.txt
	pride-prejudice.txt
	stop-list.txt
	tale-of-two-cities.txt
	tom-sawyer.txt

	

************************
Design

Included in this folder is a .jpg of my initial CRC design. The final design, however, 
varied greatly from the original design. As you can see, there were initially to be
8 unique classes: FileReader, LetterCounter, WordCounter, KeywordCounter, QuotationCounter,
WordPercentageCalculator, ValueSorter, and UserInterface. The idea was originally to have
the file information taken in and stored in the FileReader class, then accessed by the 
subsequent 5 classes, as each was responsible for one aspect of the assignment goals. The 
ValueSorter was then to take in an object of any of these classes, sort it, and produce 
a fully sorted object based on values. The UserInterface remains the same as in the original
planning, an performs all expected operations with input from the user. 



Design decisions:

	1. FileReader - an existing class that is capable of reading the target files and 
	storing their contents line by line in an ArrayList.
	
	2. OccurrenceTracker -- As I mentioned this class was originally only supposed to be
	charged with storing all alphabetic characters as keys in a HashMap with their 
	occurrences as values. As I was thinking about what the other similar classes would
	be doing in terms of collecting data, it occurred to me that the means of obtaining 
	this information would be very similar across all objectives (letter, word, unique word
	with stop list, and quote occurrences). For this reason I decided to collapse all these 
	functions into this one class in an attempt to achieve "drier" code, but possibly
	at the risk of weakening class cohesion. Clearly I found this benefit to 
	outweigh the possible risk. Consequently, this class handles most of the data analysis, 
	which I concede is a somewhat risky design choice in terms of bugs and such. Regardless,
	I chose to have the constructor take in a String argument corresponding to the filename,
	then create a FileReader object using this information, and then store the text file
	lines in an ArrayList in this class to easily access it. I also chose to initialize the 
	four HashMaps declared that correspond to the data to be extracted. The choice of using 
	HashMaps versus ArrayLists seemed logical, as we were looking for 2 corresponding data
	points (letter/word/quote and frequency). It also made sense given that HashMaps only
	allow for single instances of a key, yet multiple mappings to one value. 
	
	With respect to the individual methods, design decisions were made as such:
	
	getLetterOccurences() -- an advanced For loop to iterate over text lines from the file
	seemed an obvious choice. While I initially thought about having Scanner read in each
	letter I opted for a nested For loop and the .substring and .toLowerCase methods to 
	get 1 lowercase letter at a time. I have to note that I originally had the variable 
	charAt as a char type, but then trouble down the road caused me to change it to a 
	String, so as to have uniformity across the 4 HashMaps declared in the constructor 
	(String, Integer) and allow for a printing method that would work for any of the 
	HashMaps. I then used 2 if statements to isolate only alphabetical characters (using 
	regular expressions), as well as limiting the intake of non-word characters. The 
	subsequent if statement addresses the value/frequency issue by checking if the character
	is stored as a key already or not. If not, it is added and its value is set to 1. If
	it is already present, the old mapping is overwritten with the previous value plus 1. 
	The method then returns the filled HashMap.
	
	
	getWordOccurrences() -- The layout of this method is very similar to the previous with
	an advanced For loop over the stored lines and the same if/else statement to add keys
	and values. In this method I chose to use Scanner to read in the lines, knowing I'd be
	able to use a delimiter to split the line on the spaces(" "), effectively reading in each 
	separate word. I then stored the string between spaces in the variable "chunk". After
	some testing it became clear that I needed to clean these strings, as there were too 
	many characters that were throwing off the values(for example, "This" was different from
	"this", "Scrooge" different from "Scrooge's", etc). Thus, I created another string
	that would store this original string, but paired down with methods: replace("'s", "") 
	to account for possessives, .replaceAll("[^A-Za-z]", "") to account for any remaining 
	non-word characters, .trim() to eliminate any white space that might have been generated, 
	and .toLowerCase() to create uniformity. A filled HashMap is then returned.
	 
	getWordsWStopList() -- Having the previous method at my disposal, I began this method
	by making the HashMap for these values equal to the HashMap returned by the 
	getWordOccurrences() method, then used Scanner to read in each word from the stop list, 
	then check if the HashMap had that word stored as a key. If yes, that key was removed.
	The HashMap of words minus the stop list words is then returned.
	
	getQuoteOccurrences() -- This method proved to be fairly difficult to maneuver/design. 
	Given the nature of quotes (occurring between quotation marks), it seemed most logical
	to use regular expressions to extract this data. This approach failed to adequately 
	work, however (read: I couldn't figure out how to make it work-- see QuoteTester class)
	, so I opted to use the StringTokenizer to split the line on the double quote, then
	store the quote as a key and length as a value using the .length method. 
	
	getKeywordPercentage() -- I originally was thinking of the wildcard question as 
	what percentage of the text was made up of non-word characters, but then decided it 
	would be more interesting if the user could choose the information to search for and 
	see how much of the text it actually occupies. Thus, the design of this class is 
	almost identical to the getWordOccurrences() method, except now just isolating the 
	keyword provided by the user to search for, and diving the number of its occurrences
	by that of all words and multiplying by 100 to produce a number that made sense to the 
	user. 
	
	3. HashmapOrdering -- along with the last method, this class presented the greatest 
	challenge in terms of design. While the current implementation is far from perfect,
	it is the most feasible design I could create and still adequately grasp/utilize. The 
	design for this class originally started with the most logical tool I could think of,
	the Comparable class. The results from this attempt can be seen in the ValueComparator
	and ComparatorTester classes. As can be seen in the Tester class, the results were
	not what I was looking for or expecting. The use of TreeMap and Collections in this 
	class were pretty much primarily based on the ability to use the .reverseOrder() and/or
	.descendingMap() method, which didn't end up working anyway. I also believe I ran into 
	trouble with TreeMaps in a different class with keys that were mapped to the same value. 
	After much trial and error, the SortingByValue class became the blueprint for the final
	class design. While simple ascending ordering gave me a significant challenge, trying
	to implement reverse ordering was even more taxing. I eventually settled on creating
	a print method that would work off of the ascending value sorted list (printReverseMap()).
	Lastly, when doing final testing of this program there was a complication with the 
	constructor for this class. The constructor was supposed to take in a HashMap and then
	call the sortByValue() method on it automatically, but this wasn't what happened in practice
	in the UserInterface, so that method had to be called again on the HashMaps. 
	
	4. UserInterface -- This method would clearly contain the main method, and prompt
	the user for a file to analyze, which was taken in by Scanner and used to create a 
	new OccurrenceTracker object. I then populated 4 HashMaps with the methods from 
	the OccurrenceTracker class. I then used a switch statement for the analysis possibilities.
	I tried (unsuccessfully) to allow the user to choose a different file if they wished, but
	couldn't get the loop to work correctly. Lastly, the cases essentially almost all used 
	the HashmapOrdering class to generate and print results.
	
	
************************
Program Instruction

To use this program:

Make sure all 4 .java files (FileReader.java, OccurrenceTracker.java, HashmapOrdering.java,
and UserInterface.java) are saved in the same project in Ecclipse. Similarly, make sure
the corresponding text files are saved in the same project file in Eclipse. The .txt files
are located in the included "HW5_txt_files" folder (contents listed above), though the 
user can also use their own text files. If placed correctly, these files should then appear 
below "JRE System Library", as opposed to the same "src" folder .java files are stored in.
Once all this is complete, the user only needs to open the "UserInterface.java" file
and run it. The user can then follow prompts to find out various aspects of their given text
file. If they wish to try another file they must exit the program and begin again. 




















