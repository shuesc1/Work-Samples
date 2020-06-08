import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that prompts the user for a file to read, then provides a list of analysis options
 * @author josephhaymaker
 *
 */
public class UserInterface {

	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, Integer> letterMap;
		HashMap<String, Integer> wordMap;
		HashMap<String, Integer> uniqueWordMap;
		HashMap<String, Integer> quoteMap;
		HashMap<String, Integer> sortedMap;

		System.out.println("Welcome user! This program will analyze a text file. ");
		System.out.println("Current .txt files available are:\n");
		System.out.println("alice-in-wonderland.txt\n christmas-carol.txt\n huck-finn.txt\n les-mis.txt\n metamorphosis.txt\n my-man-jeeves.txt\n pride-prejudice.txt\n tale-of-two-cities.txt\n tom-sawyer.txt\n");
		System.out.println("To start off, please provide the name of a .txt file you would like to analyze:\n");
		//		boolean wantsThisFile = true;
		//		while(wantsThisFile == true){
		Scanner fileScanner = new Scanner(System.in);
		String userFile = fileScanner.nextLine();
		OccurrenceTracker oc = new OccurrenceTracker(userFile);
		letterMap = oc.getLetterOccurences();
		wordMap = oc.getWordOccurrences();
		uniqueWordMap = oc.getWordsWStopList();
		quoteMap = oc.getQuoteOccurrences();

		String[] userChoice;
		Scanner in = new Scanner(System.in);
		System.out.println("\nGreat! Now please select what you would like to know about the file from the following list:\n");

		userChoice = new String[6];
		userChoice[0] = "1. Print out the top-10 most frequent letters along with the frequency in the book:";
		userChoice[1] = "2. Print out the top-10 most frequent words along with the frequency in the book:";
		userChoice[2] = "3. Print out the top-10 most frequent words disregarding the 573 most common words in the English language";
		userChoice[3] = "4. Print out the top-10 shortest and longest quotations based on length.";
		userChoice[4] = "5. Enter a word to learn what percentage of the text it occupies.";
		userChoice[5] = "6. Exit program.";
		//			userChoice[6] = "7. Choose a different file\n";

		for (int i = 0; i < userChoice.length; i++)
		{		System.out.println(userChoice[i]); }

		boolean end = false;
		while (end == false){
			//			while(wantsThisFile == true){
			System.out.println("Which option would you like to choose?:\n");
			int userSelection = in.nextInt();

			switch (userSelection){
			case 1:
				System.out.println("Top 10 most frequent letters and frequencies:\n");
				HashmapOrdering letterOrdering = new HashmapOrdering(letterMap);
				sortedMap = (HashMap<String, Integer>) letterOrdering.sortByValue(letterMap);
				//				letterOrdering.printMap(sortedMap);
				letterOrdering.printReverseMap(sortedMap);
				break;
			case 2: 
				System.out.println("Top 10 most frequent words and frequencies:\n");
				HashmapOrdering wordOrdering = new HashmapOrdering(wordMap);
				sortedMap = (HashMap<String, Integer>) wordOrdering.sortByValue(wordMap);
				//				wordOrdering.printMap(sortedMap);
				wordOrdering.printReverseMap(sortedMap);
				break;
			case 3: 
				System.out.println("Top 10 most frequent words and frequencies minus the most common words in the English language:\n");
				HashmapOrdering uniqueWordOrdering = new HashmapOrdering(uniqueWordMap);
				sortedMap = (HashMap<String, Integer>) uniqueWordOrdering.sortByValue(uniqueWordMap);
				//				uniqueWordOrdering.printMap(sortedMap);
				uniqueWordOrdering.printReverseMap(sortedMap);
				break;
			case 4: 
				System.out.println("Top 10 shortest quotes (and corresponding character lengths):\n");
				HashmapOrdering quoteOrdering = new HashmapOrdering(quoteMap);
				sortedMap = (HashMap<String, Integer>) quoteOrdering.sortByValue(quoteMap);
				quoteOrdering.printMap(sortedMap);
				System.out.println("\nTop 10 longest quotes (with corresponding character length):\n");
				quoteOrdering.printReverseMap(sortedMap);
				break;
			case 5: 
				Scanner wordScanner = new Scanner(System.in);
				System.out.println("Please enter a word to analyze:");
				String userKeyword = wordScanner.nextLine();
				double percentage = oc.getKeywordPercentage(userKeyword);
				DecimalFormat formatter = new DecimalFormat("#0.00");
				System.out.println("Your keyword \"" + userKeyword + "\" makes up " + formatter.format(percentage) + "% of the text.");
				break;
			case 6: 
				System.out.println("Exiting...");
				end = true;
				break;
				//				case 7: 
				//					System.out.println("What new file would you like to analyze?\n");
				//					end = true;
				//					//				wantsThisFile = false;
				//					break;	
			default: 
				end = true;
				//					wantsThisFile = false;
				break;		
			}
		}
		in.close();
	}


}


