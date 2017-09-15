package edu.upenn.cis573.hwk1;

import java.util.Scanner;

/**
 * A class that takes in an original file and a file that has been encrypted using cipher substitution, 
 * then decrypted using frequency calculation. It then calculates the number of characters that were correctly 
 * decrypted and the number of characters that were incorrectly decrypted using frequency calculation.
 * @author josephhaymaker
 *
 */
public class FileComparator {
	private String originalFile, decryptedFile, currentOrigChar, currentDecrypChar;
	private Scanner in1, in2;
	private int correctChars, incorrectChars;

	/**
	 * The constructor for the method
	 * It sets the instance variables of the original and decrypted filenames
	 * @param originalFile
	 * @param decryptedFile
	 */
	public FileComparator(String originalFile, String decryptedFile) {
		this.originalFile = originalFile ;
		this.decryptedFile = decryptedFile ;
	}

	/**
	 * A method that compares two files and calculates how many characters were correctly and incorrectly
	 * decoded.
	 * @param origChars a char array of the original characters that were encrypted then decrypted
	 */
	public void compare(String[] origChars) {
		in1 = new Scanner(originalFile).useDelimiter("") ;
		in2 = new Scanner(decryptedFile).useDelimiter("") ;
		correctChars = 0;
		incorrectChars = 0;

		while (in1.hasNextLine() && in2.hasNextLine()) {
			while(in1.hasNext()) {
				currentOrigChar = in1.next().toLowerCase();
			}
			while(in2.hasNext()) {
				currentDecrypChar = in2.next().toLowerCase() ;
			}
			//now on same line and same character position in texts
			//want to only consider 'correct' chars as ones that were encrypted then decrypted
			if (origChars.toString().contains(currentOrigChar) || origChars.toString().contains(currentDecrypChar)) {
				if (currentDecrypChar.equalsIgnoreCase(currentOrigChar)) {
					correctChars++;
				} else {
					incorrectChars++;
				}
			}
		}
	}


	/**
	 * A getter method for the number of correctly decrypted characters
	 * @return correctChars an int of correct characters
	 */
	public int getCorrectChars() {
		return correctChars;
	}

	/**
	 * A getter method for the number of incorrectly decrypted characters
	 * @return incorrectChars an int of the number of incorrect characters
	 */
	public int getIncorrectChars() {
		return incorrectChars;
	}
}
