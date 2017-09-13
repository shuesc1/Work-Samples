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

	private char[] baseCharsByFreq;
	private char[] encryptionKeysByFreq;
	private Scanner in;
	private HashMap<Character, Character> encryptedCharWithKey = new HashMap<>();
	private String currentEncryptedChar;
	private PrintWriter out;
	private boolean encrypt;

	/**
	 * The constructor for the class
	 * It sets instance variables and creates a HashMap of cipher keys matched with their character values
	 * @param baseCharsByFreq
	 * @param encryptionKeysByFreq
	 */
	public Decryptor(char[] baseCharsByFreq, char[] encryptionKeysByFreq) {
		this.baseCharsByFreq = baseCharsByFreq;
		this.encryptionKeysByFreq = encryptionKeysByFreq;
		setEncryptedCharWithKey(createDecryptionKey());
	}

	/**
	 * A method that decrypts a file and writes out the correctly decoded original content
	 * @param encryptedFilename a valid name of a file encrypted using a substitution cipher
	 */
	public void decrypt(String encryptedFilename){
		File file = new File(encryptedFilename) ;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(nameDecryptedFile(encryptedFilename))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in = new Scanner(file).useDelimiter("");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!") ;
			e.printStackTrace();
		}
		//iterate over all lines in encrypted file
		while(in.hasNextLine()) {
			String currentLine = "";
			//iterate over all tokens (characters)
			while(in.hasNext()) {
				currentEncryptedChar = in.next();
//				System.out.println(currentEncryptedChar) ;
				if(encryptedCharWithKey.containsKey(currentEncryptedChar)) { 	
					//if current char is part of the encrypted char set sorted by frequency (if it is in fact a letter)
					//swap it for the (supposedly) original letter
					currentEncryptedChar = encryptedCharWithKey.get(currentEncryptedChar).toString() ;
				}
				currentLine = currentLine + currentEncryptedChar ;
			}
			//the fully converted line to be appended to the file
			currentLine = currentLine + "/n" ;
//			out.
			out.write(currentLine);
			//TODO: append swapped/decrypted LINE [encryptedCharWithKey.get(currentEncryptedChar)] to output file
		}
		in.close();
		out.flush();
		out.close();
	}

	/**
	 * A method that names the decrypted file that is to be written out
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
	 * A method that creates a HashMap with keys of ciphers and values of their corresponding original
	 * characters based on frequency representations.
	 * @return
	 */
	public HashMap<Character, Character> createDecryptionKey(){
		HashMap<Character, Character> ciphersWithOrigChars = new HashMap<>();
		for(int i = 0; i < baseCharsByFreq.length; i++) {
			//add key of cipher with value of original correct character
			//			if(encryptionKeysByFreq[i] != null && baseCharsByFreq[i] != null) { //trying to catch possible OOB error
			ciphersWithOrigChars.put(encryptionKeysByFreq[i], baseCharsByFreq[i]) ;
			//			}
		}
		return ciphersWithOrigChars;
	}

	/**
	 * A getter method for the hashmap used to decode the text
	 * @return
	 */
	public HashMap<Character, Character> getEncryptedCharWithKey() {
		return encryptedCharWithKey;
	}

	/**
	 * A setter method for the HashMap used to decode the text
	 * @param encryptedCharWithKey
	 */
	public void setEncryptedCharWithKey(HashMap<Character, Character> encryptedCharWithKey) {
		this.encryptedCharWithKey = encryptedCharWithKey;
	}

	/**
	 * A getter method for the boolean denoting if the file is to be encrypted (T) or decrypted (F)
	 * @return encrypt a boolean T if the file is to be encrypted
	 */
	public boolean outTypeEncrypt() {
		return encrypt;
	}

	/**
	 * A setter for the boolean encrypt that denotes if the file is to be encrypted (T) or decrypted (F)
	 * @param encrypt a boolean T if the file is to be encrypted or F if the file is to be decrypted
	 */
	public void setOutTypeToEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	/*=========================================
	 * TESTING
    ======================================== */
	public static void main(String[] args) {

		char[] baseSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'} ;
		char[] decryptionKeys = {'z','y','x','w','v','u','t','s','r','q','p','o','n','m','l','k','j','i','h','g','f','e','d','c','b','a'} ;

		Decryptor d = new Decryptor(baseSet, decryptionKeys);
		//		d.decrypt("test_file.txt");
		d.decrypt("encrypted_test_file.txt");
		//		d.nameDecryptedFile("test_fileENCRYPTED.txt") ;

	}

}