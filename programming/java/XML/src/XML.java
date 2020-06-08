import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class XML {

	String filename = "scalar.xml";
	String contents;

	public XML(String filename) throws FileNotFoundException{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()){
			contents = contents + in.nextLine();
		}


		in.close();
	}

}
