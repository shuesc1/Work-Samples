import java.util.*;

/**
 * A Random Player.
 * 
 * @author swapneel
 *
 */
public class RandomPlayer implements Player {

	private static final String NAME = "Random Player";
	private int state;
	private Random rand;
	private GameConfig game;

	/*
	 * This method is called when the Organism is created. The key is the value
	 * that is passed to this organism by its parent (not used here). The game
	 * is also not used here.
	 */
	public void register(GameConfig game, int key) {
		rand = new Random();
		state = rand.nextInt(256);
		this.game = game;
	}

	public String name() {
		return NAME;
	}

	public Move move(boolean[] foodpresent, int[] neighbors, int foodleft, int energyleft) {

		Move m = null; // placeholder for return value

		// this player selects randomly
		int direction = rand.nextInt(6);

		switch (direction) {
		case 0:
			m = new Move(Constants.STAYPUT);
			break;
		case 1:
			m = new Move(Constants.WEST);
			break;
		case 2:
			m = new Move(Constants.EAST);
			break;
		case 3:
			m = new Move(Constants.NORTH);
			break;
		case 4:
			m = new Move(Constants.SOUTH);
			break;
		case 5:
			direction = rand.nextInt(4);
			// if this organism will reproduce:
			// the second argument to the constructor is the direction to which
			// the offspring should be born
			// the third argument is the initial value for that organism's state
			// variable (passed to its register function)
			if (direction == 0)
				m = new Move(Constants.REPRODUCE, Constants.WEST, state);
			else if (direction == 1)
				m = new Move(Constants.REPRODUCE, Constants.EAST, state);
			else if (direction == 2)
				m = new Move(Constants.REPRODUCE, Constants.NORTH, state);
			else
				m = new Move(Constants.REPRODUCE, Constants.SOUTH, state);
		}
		return m;
	}

}
