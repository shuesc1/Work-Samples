import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIOTest {

	File file2 = new File("/Users/josephhaymaker/desktop", "encrypted_test_file.txt") ;
	PrintWriter out = null ;
	Scanner in = new Scanner(file2) ;
	String currentEncryptedChar;

	public 	FileIOTest() throws FileNotFoundException {
	}

	public void writefile() {
		//		String encryptedFilename = "some_fileENCRYPTED.txt" ;
		//		File file = new File(encryptedFilename) ;

		//=======READ IN ENCRYPTED FILE==============
		try {
			in = new Scanner(file2).useDelimiter("");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!") ;
			e.printStackTrace();
		}
		while(in.hasNextLine()) {
			String currentLine = "";
			while(in.hasNext()) {
				currentEncryptedChar = in.next();
				//TODO add back in logic that swaps the char if it is encrypted
				currentLine = currentLine + currentEncryptedChar ;
			}
			currentLine = currentLine + "/n" ;
		//============Generated string of 1 line of encrypted file============

			//===========CREATE DECRYPTED FILE OR APPEND LINE TO EXISTING FILE=====================	
			try {
				File file = new File("/Users/josephhaymaker/desktop", "some_fileDECRYPTED.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				out = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true)));
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


	public static void main(String[] args) throws FileNotFoundException {
		FileIOTest fiot = new FileIOTest() ;
		fiot.writefile();

		//		BufferedWriter bw = null;
		//		FileWriter fw = null;
		//		String FILENAME = "/Users/josephhaymaker/desktop/output_file.txt" ;

		//		try {
		//			String data = " This is new content\n";
		//			File file = new File(FILENAME);
		//			// if file doesnt exists, then create it
		//			if (!file.exists()) {
		//				file.createNewFile();
		//			}
		//			// true = append file
		//			fw = new FileWriter(file.getAbsoluteFile(), true);
		//			bw = new BufferedWriter(fw);
		//			bw.write(data);
		//			System.out.println("Done");
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		} finally {
		//			try {
		//				if (bw != null)
		//					bw.close();
		//				if (fw != null)
		//					fw.close();
		//			} catch (IOException ex) {
		//				ex.printStackTrace();
		//			}
		//		}
	}
}



