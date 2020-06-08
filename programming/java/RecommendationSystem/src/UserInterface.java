import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) throws IOException {
		int userID = 0;
		int itemID;
		Scanner in = new Scanner(System.in);
		PredictionCalculator pc;
		SimilarityCalculator sc = null;
		NeighborhoodCalculator nc;
		fileReader fr = null;
		fileReader2 fr2 = null;
		BaselinePredictor bp = null;
		CosineSimilarity cs = null;

		boolean movieAnalysis = false;
		System.out.println("Do you wish to use:"
				+ " \n1. 10m movielens file with Cosine Similarity, \nOR"
				+ " , \n2. Book Crossing file with the Baseline Predictor");
		if(in.next().contains("1")){
			movieAnalysis = true;
		} else if(in.next().contains("2")){
			movieAnalysis = false;
		}

		if(movieAnalysis == true){
			fr = new fileReader("rating_condensed.txt");
			fr.readFile();
		} else if(movieAnalysis == false){
			fr2 = new fileReader2("book_rating_sample.txt");
			fr2.readFile();
		}
		in.nextLine();

		System.out.println("What would you like to know? Select from the following options: ");
		String[] userChoice;
		userChoice = new String[2];
		userChoice[0] = "1. (a.) Given a user u and item i, what is the system's prediction for the user's likely"
				+ " preference of that item?";
		userChoice[1] = "2. (b.) Given a user u and a threshhold n, what are the n-highest predicted preferences "
				+ "for that user?";

		for (int i = 0; i < userChoice.length; i++)
		{		System.out.println(userChoice[i]); }

		boolean end = false;
		boolean valid = false;
		while (end == false){
			System.out.println("Which option would you like to choose?:\n");
			int userSelection = in.nextInt();
			System.out.println("**Loading**");
			switch (userSelection){

			//<<<<<<<<<<1ST CHOICE>>>>>>>>>>
			case 1:
				System.out.println("Which user would you like to choose?:\n");
				while (valid == false) {
					if (in.hasNextInt()) {
						userID = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a user :" + in.next());
						valid = false;
					}
				}	

				System.out.println("And what item ID would you like to know the rating for?:");
				valid = false;
				itemID = 0;
				while (valid == false) {
					if (in.hasNextInt()) {
						itemID = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a movie :" + in.next());
						valid = false;
					}
				}	

				double prediction = 0;
				HashMap<String, User> neighborhood = new HashMap<String, User>();
				Collection<User> usersWAvgsAndPearson = new ArrayList<User>();
				sc = new SimilarityCalculator();

				//*****IF using movie data/objects with Cosine Similarity**********
				if(movieAnalysis == true){
					//					usersWAvgsAndPearson = sc.calcAggSimilarity(fr.getUserList().get(userID), sc.calcAverage(fr.getUserList())); 
					for(User v : fr.getUserList().values()){
						cs = new CosineSimilarity(fr.getUserList().get(userID), v);
						v.correlation = cs.calculateSimilarity();
						usersWAvgsAndPearson.add(v);
					}
					nc = new NeighborhoodCalculator(usersWAvgsAndPearson);
					neighborhood = nc.createNeighborhood();
					pc = new PredictionCalculator();
					prediction = pc.calculatePrediction(fr.getUserList().get(userID), neighborhood);
					System.out.println("For user " + userID + " the predicted rating for item " 
							+ itemID + " using cosine similarity is: " + prediction);


				//*****IF using book data/objects with Baseline Predictor**********
				} else if(movieAnalysis == false){
					bp = new BaselinePredictor(fr2.getUserList().get(userID), fr2.getBookList().get(itemID), fr2.getUserList());
					double pred = bp.calculateBaseline();
					System.out.println("For user " + userID + " the predicted rating for item " 
							+ itemID + " using the Baseline Predictor is: " + pred);
				}
				break;
				//<<<<<<<<<<END 1ST CHOICE>>>>>>>>>>


				//<<<<<<2ND CHOICE>>>>>>>>
			case 2: 
				System.out.println("Which user would you like to choose?:\n");
				while (valid == false) {
					if (in.hasNextInt()) {
						userID = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a user :" + in.next());
						valid = false;
					}
				}	
				System.out.println("And what threshhold would you like to set?:");
				valid = false;
				while (valid == false) {
					if (in.hasNextInt()) {
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a movie :" + in.next());
						valid = false;
					}
				}	
				HashMap<String, Movie> notYetSeenList = new HashMap<String, Movie>();
				for(Movie movie : fr.getMovieList().values()){
					if(!fr.userList.get(userID).ratedItems.containsKey(movie.id)){
						notYetSeenList.put(movie.id, movie);
					}
				}
				Collection<User> userWPearsonAndAvg = new ArrayList<User>();
				break;
				//<<<<<<<<<<END 2ND CHOICE>>>>>>>>>>
			default: 
				end = true;
				break;		
			}
		}
		in.close();
	}

}
