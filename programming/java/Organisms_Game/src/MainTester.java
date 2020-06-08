import java.util.Random;

public class MainTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		for(int i = 0; i < 20; i++){
		//			String[] names = {"Buford", "Mary Jo", "Bobby Sue", "Cletus", "Old Rusty", "Young Rusty", "Bubba", "Billy Ray", "Jon Boy", "Harley"} ;
		//			Random rand = new Random();
		//			int index = rand.nextInt(10);
		//			String name = names[index];
		//			System.out.println(name);
		//		}

		//		for (int i = 0; i < 100; i++){
				Random r = new Random();
		//		double p = r.nextDouble();
		////		p = p / 10;
		//		while(p < 0.001 || p > 0.1){
		//			p = r.nextDouble();
		//		}
		//		System.out.println(p);
		//		}

		for (int i = 0; i < 100; i++){
			double q = r.nextDouble();
			while(q < 0.002 || q > 0.2){
				q = r.nextDouble();
			}
			System.out.println(q);
		}
	}

}
