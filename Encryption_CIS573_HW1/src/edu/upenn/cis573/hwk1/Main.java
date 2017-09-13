package edu.upenn.cis573.hwk1;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.NotDirectoryException;
import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {

		int totalCorrect = 0;
		int totalIncorrect = 0;
		double accuracy = 0;
		FileEncryptor fe = new FileEncryptor();
		FrequencyCalculator fc = new FrequencyCalculator();
		String corpusDir, absoluteCorpusDir, relativeCorpusDir;

		//=======OBTAIN RUNTIME ARGUMENT-ABSOLUTE OR RELATIVE PATH=========
		File argFilePath = new File(args[0]);
		//CHECKS IF: 1. the number of runtime arguments isn't correct
		if (args.length != 1) {
			throw new IllegalArgumentException("Exactly 1 runtime argument ('{filepath}') required!");
		}

		//CHECKS IF PATH IS DIRECTORY
		if (argFilePath.isDirectory()) { //TODO does this check absolute and relative filepaths?
			System.out.println("Filepath given is a directory.") ;
		} else {
			throw new IllegalArgumentException("Pathname specified is not a directory!") ;
		}
		
		//CHECKS IF PATH EXISTS
		if (argFilePath.exists()) {
			System.out.println("The path indicated by the filepath does exist.") ;
		} else if (!argFilePath.exists()) {
			throw new InvalidPathException("Filepath does not exist!:", args[0]) ;
		}
		
		//CHECKS IF PATH IS READABLE/WRITABLE
		
		
		//TODO throw exceptions for 2. specified dir doesn't exist, 3. specified dir can't be opened for reading, 4. specified dir is empty
		//InvalidPathException ? , DirectoryNotFoundException ?
//		corpusDir = args[0] ;
		
//		try {
//			corpusDir = args[0] ;
//		} catch(DirectoryNotFoundException) {
//			System.err.print("Please enter a *valid* runtime argument specifying an absolute or relative path to the target corpus;") ;
//			System.err.println("Argument '" + args[0] + "' must be valid string.");
//			System.exit(1);



		//========================END SECTION============================

		
		
		//========ITERATE OVER ALL FILES IN SPECIFIED DIRECTORY========
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

		//========================END SECTION============================
		
		//============CALCULATE ACCURACY AND PRINT OUT=================
		if((totalCorrect + totalIncorrect) != 0) {
			accuracy = totalCorrect / (totalCorrect + totalIncorrect) ;
		} else {
			System.out.println("Can't compute accuracy -- dividing correct number by all characters changed"
					+ " would lead to division by 0") ;
		}

		System.out.println("\n" + "Total: " + totalCorrect + " correct, " + totalIncorrect + " incorrect") ;
		System.out.printf("Accuracy: %.2f%%%n", accuracy) ;
	}

}
