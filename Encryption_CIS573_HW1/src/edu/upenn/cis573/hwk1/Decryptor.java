package edu.upenn.cis573.hwk1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Decryptor {

	private char[] baseCharsByFreq;
	private char[] encryptionKeysByFreq;
	private Scanner in;

	public Decryptor(char[] baseCharsByFreq, char[] encryptionKeysByFreq) {
		this.baseCharsByFreq = baseCharsByFreq;
		this.encryptionKeysByFreq = encryptionKeysByFreq;
	}

	public void decrypt(String encryptedFilename){
		File file = new File(encryptedFilename) ;
		try {
			in = new Scanner(file).useDelimiter("");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!") ;
			e.printStackTrace();
		}
		while(in.hasNextLine()) {
			String currentEncryptedChar = in.next();
			System.out.println(currentEncryptedChar) ;
			if(baseCharsByFreq.toString().contains(currentEncryptedChar)) { 
		    //if current char is part of the encrypted char set sorted by frequency (if it is in fact a letter)

				//write out to file all chars swa

			}
		}
	}



	public static void main(String[] args) {

		char[] baseSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'} ;
		char[] decryptionKeys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26} ;

		Decryptor d = new Decryptor(baseSet, decryptionKeys);
		d.decrypt("test_file.txt");


	}

}