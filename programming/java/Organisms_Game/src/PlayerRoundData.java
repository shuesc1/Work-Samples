/**
 * The Interface for keeping track of how each organism does in a round
 * @author swapneel
 *
 */
public interface PlayerRoundData {
	
	/**
	 * A Unique Player ID to distinguish different players
	 * @return player id
	 */
	public int getPlayerId(); //ways to distinguish players when there are many competing. 
	
	/**
	 * Total Energy Left for this type of organism
	 * @return the total energy
	 */
	public int getEnergy();
	
	/**
	 * The Total Count for this type of organism
	 * @return the total count
	 */
	public int getCount(); //this will be for one specific "species", to be used for the "get results" method of the games interface

}
