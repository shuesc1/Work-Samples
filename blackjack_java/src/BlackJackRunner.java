import java.util.Scanner;

public class BlackJackRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to BlackJack. The computer gets the first turn.");
		
		BlackJack blj = new BlackJack();
		blj.playBlackJack();
	}

}
