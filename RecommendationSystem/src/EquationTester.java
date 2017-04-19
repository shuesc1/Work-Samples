import java.util.HashMap;

public class EquationTester {

	public static void main(String[] args) {
		HashMap<String, Movie> movieList = new HashMap<String, Movie>();
		HashMap<String, User> userList = new HashMap<String, User>();
		
		Movie item1 = new Movie();
		item1.id = "item1";
		movieList.put(item1.id, item1);
		Movie item2 = new Movie();
		item2.id = "item2";
		movieList.put(item2.id, item2);
		Movie item3 = new Movie();
		item3.id = "item3";
		movieList.put(item3.id, item3);
		Movie item4 = new Movie();
		item4.id = "item4";
		movieList.put(item4.id, item4);
		Movie item5 = new Movie();
		item5.id = "item5";
		movieList.put(item5.id, item5);
		
		User A = new User();//ratings: item1 - 5, item2 - 3, item3 - 4, item4 - 3, item5 - 4
		A.id = "A";
		userList.put(A.id, A);
		User B = new User();//ratings: item1 - 4, item2 - 3, item3 - 5, item4 - 5, item5 - 4
		B.id = "B";
		userList.put(B.id, B);
		User C = new User();//ratings: item1 - 1, item2 - 2, item3 - 1, item4 - 1, item5 - 3
		C.id = "C";
		userList.put(C.id, C);
		User D = new User();//ratings: item1 - 2, item2 - 0, item3 - 3, item4 - 1, item5 - 2
		D.id = "D";
		userList.put(D.id, D);
		User E = new User();//ratings: item1 - 4, item2 - 0, item3 - 1, item4 - 4, item5 - 5
		E.id = "E";
		userList.put(E.id, E);
		User F = new User();//ratings: item1 - 4, item2 - 2, item3 - 5, item4 - 4, item5 - 1
		F.id = "F";
		userList.put(F.id, F);

	
		//will only add some ratings and see how predictions compare to actual values
		A.ratedItems.put("item1", 5.0);
		A.ratedItems.put("item3", 4.0);
		A.ratedItems.put("item5", 4.0);
		
		B.ratedItems.put("item1", 4.0);
		B.ratedItems.put("item2", 3.0);
		B.ratedItems.put("item3", 5.0);
		B.ratedItems.put("item5", 4.0);
		
		C.ratedItems.put("item2", 2.0);
		C.ratedItems.put("item5", 3.0);
		
		D.ratedItems.put("item1", 2.0);
		D.ratedItems.put("item3", 3.0);
		D.ratedItems.put("item4", 1.0);
		D.ratedItems.put("item5", 2.0);
		
		E.ratedItems.put("item1", 4.0);
		E.ratedItems.put("item4", 4.0);
		E.ratedItems.put("item5", 5.0);
		
		F.ratedItems.put("item1", 4.0);
		F.ratedItems.put("item2", 2.0);
		F.ratedItems.put("item3", 5.0);
		F.ratedItems.put("item4", 4.0);
		
		double pred = 0;
		BaselinePredictor bp = new BaselinePredictor(A, item4, userList);
		pred = bp.calculateBaseline();
		System.out.println("Actual value: 3, Predicted: " + pred);
		
		
	}

}
