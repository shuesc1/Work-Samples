package edu.upenn.cis573.hwk1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that takes an original file and uses cipher substitution to create an encrypted version of the file
 * @author josephhaymaker
 *
 */
public class Encryptor {
	private String[] baseChars, encryptionKeys;
	private Scanner in;
	private HashMap<String, String> origCharsWithCiphers = new HashMap<>();
	private String currentBaseChar, filepath, encryptedFilename;
	private PrintWriter out;
	private File encryptedFile, originalFile;

	/**
	 * The constructor for the class
	 * It sets instance variables and creates a HashMap of character values matched with their cipher keys
	 * @param baseChars a char array of the original characters to be encrypted
	 * @param encryptionKeys a char array of the cipher characters that correspond to the original characters
	 * @param filepath the filepath to the corpus files
	 */
	public Encryptor(String[] baseChars, String[] encryptionKeys, String filepath) {
		this.baseChars = baseChars ;
		this.encryptionKeys = encryptionKeys ;
		this.filepath = filepath ;
		setCharWithCipher(createEncryptionKey());
	}

	/**
	 * A method that encrypts a file and writes out the correctly encoded content
	 * @param origFilesName a valid name of a file encrypted using a substitution cipher
	 */
	public void encrypt(String origFilesName){
		//=======READ IN ORIGINAL FILE==============
		try {
			originalFile = new File(filepath, origFilesName) ;
			in = new Scanner(originalFile).useDelimiter("");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!") ;
			e.printStackTrace();
		}
		//		while(in.hasNextLine()) {
		//			String currentLine = "";
		//			while(in.hasNext()) {
		//				currentBaseChar = in.next().toLowerCase();
		//				if(origCharsWithCiphers.containsKey(currentBaseChar)) { 	
		//					currentBaseChar = origCharsWithCiphers.get(currentBaseChar) ;
		//				}
		////				currentLine = currentLine + currentBaseChar ;
		//			}
		//			currentLine = currentLine + "/n" ;
		//TODO check if this is adding a newline correctly
		//============GENERATED STRING OF 1 LINE OF ENCRYPTED FILE============

		//===========CREATE DECRYPTED FILE OR APPEND LINE TO EXISTING FILE=====================	
		try {
			encryptedFilename = nameEncryptedFile(origFilesName) ;
			File encryptedFile = new File(filepath, nameEncryptedFile(origFilesName));
			if (!encryptedFile.exists()) {
				encryptedFile.createNewFile();
			}
			out = new PrintWriter(new BufferedWriter(new FileWriter(encryptedFile.getAbsoluteFile(), true)));
			while(in.hasNextLine()) {
				String currentLine = "";
				while(in.hasNext()) {
					currentBaseChar = in.next().toLowerCase();
					if(origCharsWithCiphers.containsKey(currentBaseChar)) { 	
						currentBaseChar = origCharsWithCiphers.get(currentBaseChar) ;
					}
					//						currentLine = currentLine + currentBaseChar ;
					out.write(currentBaseChar);
				}
			}


			//				System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
			//==========CLOSE PRINTWRITER=====================
		}
		in.close();
	}
	


/**
 * A helper method that names the encrypted file that is to be written out
 * @param originalFilename 
 * @return decryptedFilename a string of the encrypted filename in the form (prefix)ENCRYPTED.(extension)
 */
public String nameEncryptedFile(String originalFilename) {
	String prefix = "" ;
	String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
	String encryptedFilename = "" ;
	String namePattern = "[ \\w-]+?(?=\\.)";
	Pattern p1 = Pattern.compile(namePattern);
	Matcher m = p1.matcher(originalFilename);
	if (m.find( )) {
		prefix = m.group(0) ; 

	}else {
		//			System.out.println("No encrypted file match found!");
	}


	encryptedFilename = prefix + "ENCRYPTED." + extension ;

	//		System.out.println("Prefix: " + prefix);
	//		System.out.println("Extension: " + extension);
	//		System.out.println(decryptedFilename);
	return encryptedFilename;
}

/**
 * A method that creates a HashMap with keys of original characters and values of their corresponding ciphers
 * @return baseCharsWithCiphers a HashMap of chars mapped to ciphers
 */
public HashMap<String, String> createEncryptionKey(){
	HashMap<String, String> baseCharsWithCiphers = new HashMap<>();
	for(int i = 0; i < baseChars.length; i++) {
		try {
			baseCharsWithCiphers.put(baseChars[i], encryptionKeys[i]) ;
		} catch(ArrayIndexOutOfBoundsException aioobe){
			System.err.println("Error: number of base characters doesn't match number of cipher characters!");
		}
	}
	return baseCharsWithCiphers;
}

/**
 * A getter method for the hashmap used to decode the text
 * @return
 */
public HashMap<String, String> getCharWithCipher() {
	return origCharsWithCiphers;
}

/**
 * A setter method for the HashMap used to decode the text
 * @param encryptedCharWithKey
 */
public void setCharWithCipher(HashMap<String, String> encryptedCharWithKey) {
	this.origCharsWithCiphers = encryptedCharWithKey;
}

/**
 * A getter method for the encrypted file's name
 * @return
 */
public String getEncryptedFilename() {
	return encryptedFilename;
}

/**
 * A setter method for the encrypted file's name
 * @param decryptedFilename
 */
public void setEncryptedFilename(String decryptedFilename) {
	this.encryptedFilename = decryptedFilename;
}

/**
 * A helper method that assures that all characters are lowercase so they are sure to be matched
 * @param keyChars
 * @return 
 */
public String[] charsToLowercase(String[] keyChars) {
	for (int i =0; i < keyChars.length; i++) {
		keyChars[i] = keyChars[i].toLowerCase() ;
	}
	return keyChars;
}

}
