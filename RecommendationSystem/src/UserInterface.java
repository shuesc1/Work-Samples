import java.io.File;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("What file would you like to use?:");
		
//		File file = new File(in.next());
		
		fileReader fr = new fileReader(in.next());
		
		
	}

}
