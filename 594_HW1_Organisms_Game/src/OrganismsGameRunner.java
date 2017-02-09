import java.util.Scanner;

public class OrganismsGameRunner {

	
	public static void main(String[] args) {
		ComputerPlayer cp = new ComputerPlayer();
		
		System.out.println("What would you like to name your player?:");
		Scanner in = new Scanner(System.in);
		HumanPlayer hp = new HumanPlayer(in.nextLine());
		
		
	}

}
