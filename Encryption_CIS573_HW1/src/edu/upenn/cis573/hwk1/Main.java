package edu.upenn.cis573.hwk1;

import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {

		int totalCorrect = 0;
		int totalIncorrect = 0;
		double accuracy = 0;
		FileEncryptor fe = new FileEncryptor();
		FrequencyCalculator fc = new FrequencyCalculator();

		//
		//get array of all files in target directory
		//while there are more values in array of text files in directory

		//TODO: while loop logic for every single individual file {
		//1. encrypt file and save new encrypted output file
		//2. create frequency lists --from remaining texts and from newly encrypted text
		//3. use frequency list to decrypt the target ciphertext
		//4. compare original plaintext doc A to decrypted doc C
		//5. print out "'filename' + ": " + correctNum + " correct, " + incorrectNum + " incorrect"
		//6. store correctNum and incorrectNum for final tally
		// }

		if((totalCorrect + totalIncorrect) != 0) {
			accuracy = totalCorrect / (totalCorrect + totalIncorrect) ;
		} else {
			System.out.println("Can't compute accuracy -- dividing correct number by all characters changed"
					+ "would lead to division by 0") ;
		}

		System.out.println("\n" + "Total: " + totalCorrect + " correct, " + totalIncorrect + " incorrect") ;
		System.out.printf("Accuracy: %.2f%%%n", accuracy) ;
	}

}
