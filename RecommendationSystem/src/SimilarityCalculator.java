import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
	double numerator, numHelper, denominator, denomHelper, similarity;
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
	public ArrayList<Double> calcAggSimilarity(User u, HashMap<String, User> usersWRatings){
		ArrayList<Double> allCorrelations = new ArrayList<Double>();
		Collection<User> allOtherUsers = usersWRatings.values();
		Iterator<User> userVIterator = allOtherUsers.iterator();
		double correlation = 0;
		double indivSimilarity = 0;

		while(userVIterator.hasNext()){ //iterate over all other users
			User userV = userVIterator.next();
			HashMap<String, Double> ratingsUserV = userV.ratedMovies;
			for(String movieKey : u.ratedMovies.keySet()){ //iterate over all movies u has rated to see if current user has also rated them
				if(ratingsUserV.containsKey(movieKey)){ //if target user and currently examined user have both rated same movie
					indivSimilarity = calcIndivSimilarity(u, userV, movieKey);
					correlation = correlation + indivSimilarity;
				}
			}
		}
		return allCorrelations;
	}

	public double calcIndivSimilarity(User u, User v, String movieI){
		numerator = 0;
		numHelper = 0;
		denominator = 0;
		denomHelper = 0;
		similarity = 0;



		if(denominator != 0){
			similarity = (numerator / denominator);
		} else {
			similarity = 0;
		}

		return similarity;
	}


	/**
	 * A helper method for the Pearson correlation 
	 * @param usersWRatings
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
