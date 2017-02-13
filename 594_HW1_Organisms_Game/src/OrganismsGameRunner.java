import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class OrganismsGameRunner {


	public static void main(String[] args) {
		Scanner rd = new Scanner(System.in);
		ComputerPlayer cp = new ComputerPlayer();
		HumanPlayer hp = new HumanPlayer();

		hp.setName();
		ArrayList<Player> players = new ArrayList<>();
		players.add(cp);
		players.add(hp);

		GameConfiguration gc = new GameConfiguration();
		OrganismsGame game = new OrganismsGame();

		double p = game.getNewP();
		double q = game.getNewQ();

		System.out.println("How many rounds would you like to play?:");
		int rounds = rd.nextInt();
		for(int i = 0; i <= rounds; i++){
			game.initialize(gc, p, q, players);
			game.playGame();
			game.getResults();
		}
	}

}
