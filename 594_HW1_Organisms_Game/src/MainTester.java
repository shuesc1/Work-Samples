import java.util.Random;

public class MainTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i = 0; i < 20; i++){
			String[] names = {"Buford", "Mary Jo", "Bobby Sue", "Cletus", "Old Rusty", "Young Rusty", "Bubba", "Billy Ray", "Jon Boy", "Harley"} ;
			Random rand = new Random();
			int index = rand.nextInt(10);
			String name = names[index];
			System.out.println(name);
		}

	}

}
