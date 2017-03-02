import java.io.FileNotFoundException;
import java.util.Scanner;

public class FamilyTreeSimulator {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		FamTree ft = new FamTree();
		Scanner in = new Scanner(System.in);
		System.out.println("What family tree would you like to work with? Please enter the filename:");
		
		String filename = in.nextLine();
		
		ft.parseMembers(filename);
		
	}

}
