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
		Map<String, Integer> origFreqMappingCharToFreq = null ;
		Map<Integer, String> freqMappingFiles = new HashMap<Integer, String>();
		Map<Integer, String> encryptedFileMapping = new HashMap<Integer, String>();
		String corpusDir, absoluteCorpusDir, relativeCorpusDir;
		//		FrequencyCalculator fc = null ;
		String[] baseSet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"} ;
		String[] decryptionKeys = {"z","y","x","w","v","u","t","s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a"} ;

		//======================================================================================
		//=================OBTAIN RUNTIME ARGUMENT-ABSOLUTE OR RELATIVE PATH====================
		//======================================================================================
		//TODO change all if statements to just the error condition.
		//CHECKS IF: 1. the number of runtime arguments isn't correct
		if (args.length != 1) {
			throw new IllegalArgumentException("Exactly 1 runtime argument ('{filepath}') required!");
		}
		File argFilePath = new File(args[0]);

		//CHECKS IF PATH IS DIRECTORY
		//TODO does this check absolute and relative filepaths?
		//TODO comment out print statements
		if (argFilePath.isDirectory()) { 
//			System.out.println("Filepath given is a directory.") ;
		} else {
			throw new IllegalArgumentException("Pathname specified is not a directory!") ;
		}
		//CHECKS IF PATH EXISTS
		if (argFilePath.exists()) {
//			System.out.println("The path indicated by the filepath does exist.") ;
		} else if (!argFilePath.exists()) {
			throw new InvalidPathException("Filepath does not exist!:", args[0]) ;
		}
		//CHECKS IF PATH IS READABLE/WRITABLE
		if (argFilePath.canWrite() && argFilePath.canRead()) {
//			System.out.println("Specified directory is readable and writeable.") ;
		} else {
			throw new AccessDeniedException("You don't have permission to read/write to this directory!") ;
		}

		//==>FILEPATH PASSES ALL CHECKS -- NOW DETERMINE IF IT IS RELATIVE OR ABSOLUTE<==
		if (argFilePath.isAbsolute()) {
			corpusDir = args[0] ;
		} else {
			corpusDir = argFilePath.getPath();
			System.out.println("Relative to absolute path?:" + corpusDir) ;
			//			corpusDir = argFilePath.getParent() + arg[0]; ??
			//TODO make sure this is formatting relative filepaths correctly
		}
		//======================================================================================
		//========================END ASSESS RUNTIME ARGUMENT SECTION===========================
		//======================================================================================



		//======================================================================================
		//==================START ITERATING OVER ALL FILES IN DIR AND EXECUTE ALGORITHM=========
		//======================================================================================
		String[] corpusFiles = argFilePath.list() ;		
		for (int i = 0; i < corpusFiles.length; i++) {
			String currentFile = corpusFiles[i];

			//============================>>>>ALGORITHM<<<<<<===================================
			//====1. READ CURRENT FILE IN, ENCRYPT IT, AND SAVE TO SAME DIR=====================
//			System.out.println("1. Encryption section reached") ;
			Encryptor encrypt = new Encryptor(baseSet, decryptionKeys, corpusDir) ;
			baseSet = encrypt.charsToLowercase(baseSet);
			decryptionKeys = encrypt.charsToLowercase(decryptionKeys) ;
			encrypt.encrypt(currentFile);
			String encryptedFilename = encrypt.getEncryptedFilename() ;

			//===2. USE NON-CURRENT FILES AND NON-ENCRYPTED FILES TO CREATE FREQ LISTS=========
			//letter freq list for just corpus files
//			System.out.println("2. Frequency list section reached") ;
			FrequencyCalculator fc = new FrequencyCalculator() ;
			origFreqMappingCharToFreq = new HashMap<String, Integer>() ; //this is the Map to be used by all the corpus files
			int x = 0 ;
			for (int j = 0; j < corpusFiles.length; j++) {
				//TODO fix this mess -- find a way to exclude the original and encrypted files when generating freq lists
				
//				if (corpusFiles[j].equalsIgnoreCase(currentFile)){
//					break;
//				} else if (corpusFiles[j].equalsIgnoreCase(encryptedFilename)){
//					break;
//				} else { //file is not original file and isn't encrypted file
					//TODO make sure this logic works for iterating over all files and not erasing the occurrences
//					if (origFreqMappingCharToFreq == null) {
//						origFreqMappingCharToFreq = new HashMap<String, Integer>() ; //this is the Map to be used by all the corpus files
//					}
					x++;
					fc = new FrequencyCalculator(origFreqMappingCharToFreq, corpusDir, corpusFiles[j]) ;
//					if (origFreqMappingCharToFreq.isEmpty()) {
					if (x == 1) {
						origFreqMappingCharToFreq = fc.generateInitialMapping(baseSet) ; //sets base char keys and values of instances as 0
//						System.out.println("I have been reached!");
					}
					String currentFileFreqCount = corpusFiles[j] ;
					origFreqMappingCharToFreq = fc.generateFreqMapping(currentFileFreqCount);
//				}
			}
			origFreqMappingCharToFreq = fc.orderMap() ;//last step-- order final resulting map

			//for encrypted file get letter frequency
			Map<String, Integer> encryptedFreqMappingCharToFreq = new HashMap<String, Integer>() ; //this is the Map to be used by all the corpus files
			fc = new FrequencyCalculator(encryptedFreqMappingCharToFreq, corpusDir, encryptedFilename) ;
			fc.generateInitialMapping(decryptionKeys) ; //using the encryption keys here, not the base characters
			encryptedFreqMappingCharToFreq = fc.generateFreqMapping(encryptedFilename) ;
			encryptedFreqMappingCharToFreq = fc.orderMap() ;

			//now that we have freq list for corpus files AND encrypted file time to create one mapping based on their respective character frequencies
			//both arrays are sorted in ascending order--Decryptor class takes advantage of this fact
			String[] sortedBaseCharArray = fc.sortedMapToSortedArray(origFreqMappingCharToFreq) ;
			String[] sortedCipherCharArray = fc.sortedMapToSortedArray(encryptedFreqMappingCharToFreq) ;

			//=================3. DECRYPT ENCRYPTED FILE USING FREQ LISTS=====================
//			System.out.println("3. Decryption section reached") ;
			Decryptor decrypt = new Decryptor(sortedBaseCharArray, sortedCipherCharArray, corpusDir); 
			decrypt.decrypt(encryptedFilename);
			String decryptedFilename = decrypt.getDecryptedFilename() ;

			//=================4. COMPARE DECRYPTED CIPHERTEXT TO ORIGINAL TEXT==============
//			System.out.println("4. Compare section reached") ;
			int currentFileCorrect = 0;
			int currentFileIncorrect = 0;
			FileComparator comparator = new FileComparator(currentFile, decryptedFilename) ; 
			comparator.compare(sortedBaseCharArray) ;
			currentFileCorrect = comparator.getCorrectChars() ;
			currentFileIncorrect = comparator.getIncorrectChars() ;

			System.out.println(currentFile + ": " + currentFileCorrect + " correct, " + currentFileIncorrect + " incorrect");

			totalCorrect = totalCorrect + currentFileCorrect ;
			totalIncorrect = totalIncorrect + currentFileIncorrect ;
			
			//TODO: while loop logic for every single individual file logic {
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
			accuracy = (totalCorrect / (totalCorrect + totalIncorrect)) * 100 ;
		} else {
			System.out.println("Can't compute accuracy -- dividing correct number by all characters changed"
					+ " would lead to division by 0") ;
		}

		System.out.println("\n" + "Total: " + totalCorrect + " correct, " + totalIncorrect + " incorrect") ;
		System.out.printf("Accuracy: %.2f%%%n", accuracy) ;
	}

}
