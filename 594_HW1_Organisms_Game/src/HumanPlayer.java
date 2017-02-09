import java.util.Scanner;

public class HumanPlayer implements Player {

	private static String name;
	
	public HumanPlayer(String name){
		this.name= name;
	}
	
	@Override
	public void register(GameConfig game, int key) {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setName(String name){
		this.name= name;
//		System.out.println("Please provide a name for your player:");
//		Scanner in = new Scanner(System.in);
//		name = in.next();
//		return name;
	}
	
	public void reproduce(){
		Object objectToReplicate = new HumanPlayer(name);
		try {
			Object o = objectToReplicate .getClass( ).newInstance( );
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {
		// TODO Auto-generated method stub
		return null;
	}

}
