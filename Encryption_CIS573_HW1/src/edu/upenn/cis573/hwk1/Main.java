package edu.upenn.cis573.hwk1;

import java.io.File;
import java.nio.file.AccessDeniedException;
import java.nio.file.InvalidPathException;
import java.nio.file.NotDirectoryException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws AccessDeniedException {

		int totalCorrect = 0;
		int totalIncorrect = 0;
		double accuracy = 0;
		Map<Integer, String> freqMappingFiles = new HashMap<Integer, String>();
		Map<Integer, String> encryptedFileMapping = new HashMap<Integer, String>();
		FileEncryptor fe = new FileEncryptor();
		FrequencyCalculator fc;
		String corpusDir, absoluteCorpusDir, relativeCorpusDir;

		//======================================================================================
		//=================OBTAIN RUNTIME ARGUMENT-ABSOLUTE OR RELATIVE PATH====================
		//======================================================================================
		File argFilePath = new File(args[0]);
		//TODO change all if statements to just the error condition.
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
		if (argFilePath.canWrite() && argFilePath.canRead()) {
			System.out.println("Specified directory is readable and writeable.") ;
		} else {
			throw new AccessDeniedException("You don't have permission to read/write to this directory!") ;
		}
		
		//==>FILEPATH PASSES ALL CHECKS -- NOW DETERMINE IF IT IS RELATIVE OR ABSOLUTE<==
		//TODO does this even need to be done if the info is already saved as a File object?
		if (argFilePath.isAbsolute()) {
			corpusDir = args[0] ;
		} else {
			corpusDir = argFilePath.getPath();
			System.out.println("Relative to absolute path?:" + corpusDir) ;
//			corpusDir = argFilePath.getParent() + arg[0]; ??
		}
		//======================================================================================
		//========================END ASSESS RUNTIME ARGUMENT SECTION===========================
		//======================================================================================
		
		
		
		//======================================================================================
		//==================START ITERATING OVER ALL FILES IN DIR AND EXECUTE ALGORITHM=========
		//======================================================================================
		String[] corpusFiles = argFilePath.list() ;
		//TODO maybe store filenames as dynam array so certain filenames can be removed or added as needed
//		ArrayList<String> corpusFilesDyn = argFilePath.list() ;
		
		//TODO create mapping from original chars to instance
		
		for (int i = 0; i < corpusFiles.length; i++) {
			String currentFile = corpusFiles[i];
		
			
			//============================>>>>ALGORITHM<<<<<<===================================
			//====1. READ CURRENT FILE IN, ENCRYPT IT, AND SAVE TO SAME DIR=====================
		    readWriteFiles encrypt = new readWriteFiles(String filepath, char[] baseChars, char[] cipherChars) ;
			encrypt.setEncryptFile(True) ;
			encrypt.readAndWrite();
			String encryptedFilename = encrypt.nameOutputFile(currentFile);
			
			//===2. USE NON-CURRENT FILES AND NON-ENCRYPTED FILES TO CREATE FREQ LISTS==
			//freq list for just 
			fc = new FrequencyCalculator(freqMappingFiles) ;
			
			//freq list for encrypted file
			fc = new FrequencyCalculator(encryptedFileMapping) ;
			
			//for all remaining files get a representation of the letter frequency
			for (int j = 0; j < corpusFiles.length; j++) {
				if (corpusFiles[j].equalsIgnoreCase(currentFile)){
					break;
				} else if (corpusFiles[j].equalsIgnoreCase(encryptedFilename)){
					break;
				} else { //file is not original file and isn't encrypted file
					
				}
			}
			fc.calculateFreq(encryptedFilename) ; //get a freq list for the newly encrypted file (old_fileENCRYPTED.txt)
			
			
			
			//=================3. DECRYPT ENCRYPTED FILE USING FREQ LISTS==============
			
			
			
			
			//TODO: while loop logic for every single individual file {
			//1. encrypt file and save new encrypted output file
			//2. create frequency lists --from remaining texts and from newly encrypted text
			//3. use frequency list to decrypt the target ciphertext
			//4. compare original plaintext doc A to decrypted doc C
			//5. print out "'filename' + ": " + correctNum + " correct, " + incorrectNum + " incorrect"
			//6. store correctNum and incorrectNum for final tally
			// }
		}
		//======================================================================================
		//========================END ALGORITHM IMPLEMENTATION SECTION==========================
		//======================================================================================
		
		
		//======================================================================================
		//=========================CALCULATE ACCURACY AND PRINT OUT=============================
		//======================================================================================
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
