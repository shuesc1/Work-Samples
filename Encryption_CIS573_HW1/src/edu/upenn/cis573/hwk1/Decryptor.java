package edu.upenn.cis573.hwk1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
			in = new Scanner(file).useDelimiter("");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!") ;
			e.printStackTrace();
		}
		//iterate over all lines in encrypted file
		while(in.hasNextLine()) {
			//iterate over all tokens (characters)
			while(in.hasNext()) {
				String currentEncryptedChar = in.next();
				System.out.println(currentEncryptedChar) ;
				if(baseCharsByFreq.toString().contains(currentEncryptedChar)) { 
					//if current char is part of the encrypted char set sorted by frequency (if it is in fact a letter)

					//write out to file all chars swa

				}
			}
		}
		in.close();
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


	/*=========================================
	 * TESTING
    ======================================== */
	public static void main(String[] args) {

		char[] baseSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'} ;
		char[] decryptionKeys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26} ;

		Decryptor d = new Decryptor(baseSet, decryptionKeys);
		d.decrypt("test_file.txt");


	}



}