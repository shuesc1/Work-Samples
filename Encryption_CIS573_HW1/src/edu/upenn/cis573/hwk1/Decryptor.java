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
 * A class that takes in a cipher list sorted by frequency and an original character list also sorted by frequency
 * This class decrypts a given file using a frequency model
 * @author josephhaymaker
 *
 */
public class Decryptor {
	private String[] baseCharsByFreq, encryptionKeysByFreq;
	private File encryptedFile, decryptedFile;
	private HashMap<String, String> encryptedCharWithKey = new HashMap<>();
	private PrintWriter out;
	private Scanner in;
	private String currentEncryptedChar, filepath, decryptedFilename;

	//TODO reconcile the data struct inconsistencies with how the frequency calcs are stored
	/**
	 * The constructor for the class
	 * It sets instance variables and creates a HashMap of cipher keys matched with their character values
	 * @param baseCharsByFreq
	 * @param encryptionKeysByFreq
	 */
	public Decryptor(String[] baseCharsByFreq, String[] encryptionKeysByFreq, String filepath) {
		this.baseCharsByFreq = baseCharsByFreq ;
		this.encryptionKeysByFreq = encryptionKeysByFreq ;
		this.filepath = filepath ;
		setEncryptedCharWithKey(createDecryptionKey());
	}

	/**
	 * A method that decrypts a file and writes out the correctly decoded original content
	 * @param encryptedFilename a valid name of a file encrypted using a substitution cipher
	 */
	public void decrypt(String encryptedFilename){
		//=======READ IN ENCRYPTED FILE==============
		try {
			encryptedFile = new File(filepath, encryptedFilename) ;
			in = new Scanner(encryptedFile).useDelimiter("");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!") ;
			e.printStackTrace();
		}
		while(in.hasNextLine()) {
			String currentLine = "";
			while(in.hasNext()) {
				currentEncryptedChar = in.next().toLowerCase();
				if(encryptedCharWithKey.containsKey(currentEncryptedChar)) { 	
					currentEncryptedChar = encryptedCharWithKey.get(currentEncryptedChar).toString() ;
				}
				currentLine = currentLine + currentEncryptedChar ;
			}
			currentLine = currentLine + "/n" ;
			//TODO check if this is adding a newline correctly
			//============GENERATED STRING OF 1 LINE OF ENCRYPTED FILE============

			//===========CREATE DECRYPTED FILE OR APPEND LINE TO EXISTING FILE=====================	
			try {
				decryptedFilename = nameDecryptedFile(encryptedFilename) ;
				File decryptedFile = new File(filepath, nameDecryptedFile(encryptedFilename));
				if (!decryptedFile.exists()) {
					decryptedFile.createNewFile();
				}
				out = new PrintWriter(new BufferedWriter(new FileWriter(decryptedFile.getAbsoluteFile(), true)));
				out.write(currentLine);
				//				System.out.println("Done");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null)
					out.close();
				//==========CLOSE PRINTWRITER=====================
			}
		}
		in.close();
	}

	/**
	 * A helper method that names the decrypted file that is to be written out
	 * @param encryptedFilename a string of the encrypted filename in the form (prefix)ENCRYPTED.(extension)
	 * @return decryptedFilename a string of the decrypted filename in the form (prefix)DECRYPTED.(extension)
	 */
	public String nameDecryptedFile(String encryptedFilename) {
		String prefix = "" ;
		String extension = encryptedFilename.substring(encryptedFilename.lastIndexOf(".") + 1);
		String decryptedFilename = "" ;
		String namePattern = "[ \\w-]+?(?=ENCRYPTED\\.)";
		Pattern p1 = Pattern.compile(namePattern);
		Matcher m = p1.matcher(encryptedFilename);
		if (m.find( )) {
			prefix = m.group(0) ; 
		}else {
			System.out.println("No encrypted file match found!");
		}

		decryptedFilename = prefix + "DECRYPTED." + extension ;
		//		System.out.println("Prefix: " + prefix);
		//		System.out.println("Extension: " + extension);
		//		System.out.println(decryptedFilename);
		return decryptedFilename;
	}

	/**
	 * A helper method that creates a HashMap with keys of ciphers and values of their corresponding original
	 * characters based on frequency representations.
	 * @return
	 */
	public HashMap<String, String> createDecryptionKey(){
		HashMap<String, String> ciphersWithOrigChars = new HashMap<>();
		for(int i = 0; i < baseCharsByFreq.length; i++) {
			try {
				ciphersWithOrigChars.put(encryptionKeysByFreq[i], baseCharsByFreq[i]) ;
			} catch(ArrayIndexOutOfBoundsException aioobe){
				System.err.println("Error: number of cipher characters doesn't match number of base characters!");
			}
		}
		return ciphersWithOrigChars;
	}

	/**
	 * A getter method for the hashmap used to decode the text
	 * @return
	 */
	public HashMap<String, String> getEncryptedCharWithKey() {
		return encryptedCharWithKey;
	}

	/**
	 * A setter method for the HashMap used to decode the text
	 * @param encryptedCharWithKey
	 */
	public void setEncryptedCharWithKey(HashMap<String, String> encryptedCharWithKey) {
		this.encryptedCharWithKey = encryptedCharWithKey;
	}

	/**
	 * A getter method for the decrypted file's name
	 * @return decryptedFilename a string of the decrypted file's name
	 */
	public String getDecryptedFilename() {
		return decryptedFilename;
	}
	
	/*=========================================
	 * TESTING
    ======================================== */
	public static void main(String[] args) {

		String[] baseSet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"} ;
		String[] decryptionKeys = {"z","y","x","w","v","u","t","s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a"} ;

		Decryptor d = new Decryptor(baseSet, decryptionKeys, "/Users/josephhaymaker/desktop");
		//		d.decrypt("test_file.txt");
		d.decrypt("encrypted_test_file.txt");
		//		d.nameDecryptedFile("test_fileENCRYPTED.txt") ;

	}

}