import java.util.ArrayList;

/**
 * This is the Game Interface.
 * A Game simulator will implement this interface and will be the starting point of running the game. 
 * @author swapneel
 *
 */
public interface OrganismsGameInterface {
	
	/**
	 * This method will initialize the game.
	 * Each game will run for 5000 rounds.
	 * Each player will start with an energy of 500 at the start of the game.
	 * @param game the GameConfig to run
	 * @param p the secret parameter p - probability of spontaneous appearance of food
	 * @param q the secret parameter q - probability of food doubling
	 * @param players the list of players
	 */
	public void initialize(GameConfig game, double p, double q, ArrayList<Player> players);
	
	/**
	 * This method will play the game for the given configuration
	 * @return true if the game ended normally, false if exceptions were thrown or unexpected behavior
	 */
	public boolean playGame();
	
	/**
	 * The list of results for all the players
	 * @return an ArrayList of PlayerRoundData objects
	 */
	public ArrayList<PlayerRoundData> getResults();

}
