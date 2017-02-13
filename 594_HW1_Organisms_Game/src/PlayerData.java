import java.util.ArrayList;
import java.util.Collections;

public class PlayerData implements PlayerRoundData {

	private ArrayList<Integer> id;
	private Player p;
	private int energy;
	private int count;

	public PlayerData(){
		idListMaker();
	}
	
	public ArrayList<Integer> idListMaker(){
		id = new ArrayList<>();
		for(int i = 0; i < 100; i++){
			id.add(i);
		}
		Collections.shuffle(id);
		return id;
	}


	/**
	 * A Unique Player ID to distinguish different players
	 * @return player id
	 */
	@Override
	public int getPlayerId() {
		int playerId = id.get(id.size()-1);
		id.remove(id.size()-1);
		return playerId;
	}

	/**
	 * Total Energy Left for this type of organism
	 * @return the total energy
	 */
	@Override
	public int getEnergy() {
		return 0;
	}

	/**
	 * The Total Count for this type of organism
	 * @return the total count
	 */
	@Override
	public int getCount() {
		return 0;
	}

}
