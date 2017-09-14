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

public class Encryptor {
	private char[] baseChars;
	private char[] encryptionKeys;
	private Scanner in;
	private HashMap<Character, Character> origCharsWithCiphers = new HashMap<>();
	private String currentEncryptedChar;
	private PrintWriter out;
	private File encryptedFile, decryptedFile ;
	private String filepath;

	/**
	 * The constructor for the class
	 * It sets instance variables and creates a HashMap of character values matched with their cipher keys
	 * @param baseChars
	 * @param encryptionKeys
	 * @param filepath the filepath to the corpus files
	 */
	public Encryptor(char[] baseChars, char[] encryptionKeys, String filepath) {
		this.baseChars = baseChars ;
		this.encryptionKeys = encryptionKeys ;
		this.filepath = filepath ;
		setCharWithCipher(createEncryptionKey());
	}

	//TODO flip all this so it is encrypting the target file
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
				currentEncryptedChar = in.next();
				if(origCharsWithCiphers.containsKey(currentEncryptedChar)) { 	
					currentEncryptedChar = origCharsWithCiphers.get(currentEncryptedChar).toString() ;
				}
				currentLine = currentLine + currentEncryptedChar ;
			}
			currentLine = currentLine + "/n" ;
			//TODO check if this is adding a newline correctly
			//============GENERATED STRING OF 1 LINE OF ENCRYPTED FILE============

			//===========CREATE DECRYPTED FILE OR APPEND LINE TO EXISTING FILE=====================	
			try {
				File decryptedFile = new File(filepath, nameEncryptedFile(encryptedFilename));
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
			System.out.println("No encrypted file match found!");
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
	public HashMap<Character, Character> createEncryptionKey(){
		HashMap<Character, Character> baseCharsWithCiphers = new HashMap<>();
		for(int i = 0; i < baseChars.length; i++) {
			baseCharsWithCiphers.put(baseChars[i], encryptionKeys[i]) ;
		}
		return baseCharsWithCiphers;
	}

	/**
	 * A getter method for the hashmap used to decode the text
	 * @return
	 */
	public HashMap<Character, Character> getCharWithCipher() {
		return origCharsWithCiphers;
	}

	/**
	 * A setter method for the HashMap used to decode the text
	 * @param encryptedCharWithKey
	 */
	public void setCharWithCipher(HashMap<Character, Character> encryptedCharWithKey) {
		this.origCharsWithCiphers = encryptedCharWithKey;
	}

}
