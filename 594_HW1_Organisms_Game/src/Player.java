/**
 * This is the Player Interface.
 * Every Organism Player must implement this interface.
 * Every Player gets to choose a name and how to move at each turn.
 * @author swapneel
 *
 */
public interface Player {

	/**
	 * This method will get called once when the game starts.
	 * It will provide the GameConfig information and the "key"
	 * @param game The GameConfig Interface that contains game-related information
	 * @param key the key passed down from the parent.
	 */
	public void register(GameConfig game, int key); //key will always only be one int

	/**
	 * The name of the Organism
	 * @return the name
	 */
	public String name();

	/**
	 * /*
	 * This is called by the Game to determine how this Organism should move.
	 * @param food a five-element array that indicates whether any food is in adjacent squares
	 * @param neighbors a five-element array that holds the details for any organism in an adjacent square. -1 is no organism present, any value >= 0 if organism present
	 * 
	 * @param foodleft how much food is left on the current square
	 * @param energyleft the organism's remaining energy
	 * @return
	 */
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft);

}
