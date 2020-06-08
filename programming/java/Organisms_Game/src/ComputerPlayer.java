import java.util.Random;

public class ComputerPlayer implements Player {

	private final String name;
	private GameConfiguration gc;
	private Random rand = new Random();
	private int state;

	public ComputerPlayer(){
		name = assignName();
		gc = new GameConfiguration();
		register(gc, 0);
	}

	@Override
	public void register(GameConfig game, int key) {
		gc = (GameConfiguration) game;
		
	}

	@Override
	public String name() {
		return name;
	}

	public String assignName(){
		String[] names = {"Buford", "Mary Jo", "Bobby Sue", "Cletus", "Old Rusty", "Young Rusty", "Bubba", "Billy Ray", "Jon Boy", "Harley"} ;
		Random rand = new Random();
		int index = rand.nextInt(10);
		return names[index];
	}

	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {

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
