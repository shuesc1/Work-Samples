import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OrganismsGame implements OrganismsGameInterface {

	

	private final static int P1_SPECIES_MARK = 1;
	private final static int P2_SPECIES_MARK = -1;
	private final static int FOOD_MARK = 2;
	private double p;
	private double q;
	private Random rand = new Random();
	private GameConfiguration gc = new GameConfiguration();
	
	private HumanPlayer hp;
	private ComputerPlayer cp;
	private GameBoardCreator board = new GameBoardCreator(10, 10);
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<PlayerRoundData> prd = new ArrayList<>();
	private Boolean hasWinner = false;
	
	public ArrayList<Player> playerlist(){
		players.add(cp);
		players.add(hp);
		return players;
	}
	
	
	/**
	 * This method will play the game for the given configuration
	 * @return true if the game ended normally, false if exceptions were thrown or unexpected behavior
	 */
	@Override
	public boolean playGame() {
		Scanner in = new Scanner(System.in);
		boolean[] food = {false, false, false, false, false};
		int[] neighbors = {-1, -1, -1, -1, -1};
//		int humanfoodleft = gc.K();
		int humanfoodleft = 500;
//		int compfoodleft = gc.K();
		int compfoodleft = 500;
//		int humanenergyleft = gc.M();
//		int compenergyleft = gc.M();
		int humanenergyleft = 500;
		int compenergyleft = 500;
		
		
		while(hasWinner != true){
			
			/* Human turn */
			hp.move(food, neighbors, humanfoodleft, humanenergyleft);
			humanenergyleft =- gc.v();
			if(humanenergyleft <= 0){
				System.out.println("You died! The computer is the winner!");
				hasWinner = true;
				break;
			}
			
			
			/* Computer turn*/
			cp.move(food, neighbors, compfoodleft, compenergyleft);
			
			
			
			hasWinner = checkForWinner();
			
		}
		return false;
	}
	
	
	
	/**
	 * A method that generates a random value for p, the probability of spontaneous appearance of food
	 * @return p, a double value between 0.001 - 0.1
	 */
	public double getNewP(){
		double p = rand.nextDouble();
		while(p < 0.001 || p > 0.1){
			p = rand.nextDouble();
		}
		return p;
	}
	
	/**
	 * A method that generates a random value for q, the probability of food doubling
	 * @return q, a random double between 0.002 - 0.2
	 */
	public double getNewQ(){
		double q = rand.nextDouble();
		while(q < 0.002 || q > 0.2){
			q = rand.nextDouble();
		}
		return q;
	}

	/**
	 * This method will initialize the game.
	 * Each game will run for 5000 rounds.
	 * Each player will start with an energy of 500 at the start of the game.
	 * @param game the GameConfig to run
	 * @param p the secret parameter p - probability of spontaneous appearance of food
	 * @param q the secret parameter q - probability of food doubling
	 * @param players the list of players
	 */
	@Override
	public void initialize(GameConfig game, double p, double q, ArrayList<Player> players) {
		this.p = p;
		this.q = q;
		this.players = players;
	}



	/**
	 * The list of results for all the players
	 * @return an ArrayList of PlayerRoundData objects
	 */
	@Override
	public ArrayList<PlayerRoundData> getResults() {
//		ArrayList<PlayerRoundData> prd = new ArrayList<>();
		
		return prd;
	}
	
	public Boolean checkForWinner(){
		prd = getResults();
		for(PlayerRoundData thisPlayer : prd){
			if(thisPlayer.getEnergy() <= 0){
				System.out.println("This player has died.");
				hasWinner = true;
			}
		}
		return hasWinner;
	}

}
