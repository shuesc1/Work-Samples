import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that calculates a Pearson correlation between two users' common ratings to determine their similarity
 * The aggregate results also determine what users are most apt to comprise a target user's neighborhood 
 * in order to calculate a possible rating for an unrated item. 
 * @author josephhaymaker
 *
 */
public class SimilarityCalculator {

	private HashMap<String, Double> correlations;
	private HashMap<String, User> usersList;
	private User user;
//	private double average;

	
	public SimilarityCalculator(){
//		user = targetUser;
		correlations = new HashMap<String, Double>();
		usersList = new HashMap<String, User>();
	}

	/**
	 * A method that calculates the Pearson correlation between a specific user and other users
	 * @param u a target user
	 * @param usersWRatings a HM of all users with their corresponding ratings
	 */
	public ArrayList<Double> calcSimilarity(User u, HashMap<String, User> usersWRatings){
		ArrayList<Double> allCorrelations = new ArrayList<Double>();
		double numerator = 0;
		double denominator = 0;
		for(double ratings : u.ratedMovies.values()){
			for(double otherRating : userWRatings)
		}
		
		
		
		return allCorrelations;
		
		
	}



	/**
	 * A helper method for the Pearson correlation 
	 * @param usersWRatings
	 * @return
	 */
	public void calcAverage(HashMap<String, User> usersWRatings){
		usersList = usersWRatings;
		double sum = 0;
		int numOfRatings = 0;
		double average = 0;

		for(User individual : usersWRatings.values()){ //iterate over all user objects
			for(double rating : individual.ratedMovies.values()){ //then iterate over all ratings of each user
				sum = sum + rating;
				numOfRatings++;
			}
			if(numOfRatings != 0){
				average = (sum / numOfRatings);
			} else {
				average = 0;
			}
			individual.ratingAvg = average;
		}
	}

}
