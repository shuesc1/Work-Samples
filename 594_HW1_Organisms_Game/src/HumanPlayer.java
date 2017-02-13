import java.util.Scanner;

public class HumanPlayer implements Player {

	private static String name;
	private GameConfig game1;
	private int state;
	
//	public HumanPlayer(String name){ //redundant, not needed
//		this.name= name;
//		state = setEnergy();
//	}
	
	@Override
	public void register(GameConfig game, int key) {
		game1 = game;
		state = setEnergy();

	}

	public int setEnergy(){
		state = game1.M();
		return state;
	}
	
	public int getEnergy(){
		return state;
	}
	
	@Override
	public String name() {
		return name;
	}

	public void setName(){
		System.out.println("Please provide a name for your player:");
		Scanner in = new Scanner(System.in);
		name = in.next();
	}
	
	public void reproduce(){
		Object objectToReplicate = new HumanPlayer();
		try {
			Object o = objectToReplicate .getClass( ).newInstance( );
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {
		System.out.println("Where would you like to move: stay put(0), west(1), east(2), north(3), south(4), or reproduce(5)");
		Scanner in = new Scanner(System.in);
		Move m = null;
		
		int direction = in.nextInt();

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
//			direction = rand.nextInt(4);
		return null;
	}
		return m;

	}
}
