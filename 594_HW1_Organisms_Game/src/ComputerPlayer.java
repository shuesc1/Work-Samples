import java.util.Random;

public class ComputerPlayer implements Player {

	private final String name;
	
	public ComputerPlayer(){
		name = assignName();
	}
	
	@Override
	public void register(GameConfig game, int key) {

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
		// TODO Auto-generated method stub
		return null;
	}

}
