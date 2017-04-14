import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A class that calculates a Pearson correlation between two users' common ratings to determine their similarity
 * The aggregate results also determine what users are most apt to comprise a target user's neighborhood 
 * in order to calculate a possible rating for an unrated item. 
 * @author josephhaymaker
 *
 */
public class SimilarityCalculator {

	private double numerator, denominator, similarity, deviationU, deviationV;
	private HashMap<String, User> userList;

	public SimilarityCalculator(){
	}

	/**
	 * A method that calculates the Pearson correlation between a specific user and other users
	 * @param u a target user
	 * @param usersWRatings a HM of all users with their corresponding ratings
	 */
	public Collection<User> calcAggSimilarity(User u, HashMap<String, User> usersWRatings){
		Collection<User> allOtherUsers = usersWRatings.values();
		Iterator<User> userVIterator = allOtherUsers.iterator();
		double correlation = 0;
		double indivSimilarity = 0;

		while(userVIterator.hasNext()){ //iterate over all other users
			User userV = userVIterator.next();
			HashMap<String, Double> ratingsUserV = userV.ratedMovies;
			userV.correlation = 0;

			if(u.ratedMovies.keySet() != null){
				for(String movieKey : u.ratedMovies.keySet()){ //iterate over all movies u has rated to see if current user has also rated them
					if(ratingsUserV.containsKey(movieKey)){ //if target user and currently examined user have both rated same movie
						indivSimilarity = calcIndivSimilarity(u, userV, movieKey);
						correlation = correlation + indivSimilarity;
					}
				}
			}

			userV.correlation = correlation;
		}
		return allOtherUsers;
	}

	/**
	 * A helper method that calculates a single data point/similarity for users u & v given they have both rated item i
	 * @param u our target user
	 * @param v any other user not target user
	 * @param movieI a String ID of a movie that both users have rated
	 * @return
	 */
	public double calcIndivSimilarity(User u, User v, String movieI){
		denominator = 0;
		similarity = 0;
		deviationU = 0;
		deviationV = 0;

		deviationU = u.ratedMovies.get(movieI) - u.ratingAvg;
		deviationV = v.ratedMovies.get(movieI) - v.ratingAvg;

		v.deviation = deviationV;

		numerator = deviationU * deviationV;
		denominator = (Math.sqrt(deviationU * deviationU)) * (Math.sqrt(deviationV * deviationV));

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
	public HashMap<String, User> calcAverage(HashMap<String, User> usersWRatings){
		userList = usersWRatings;
		double sum = 0;
		int numOfRatings = 0;
		double average = 0;

		for(User individual : userList.values()){ //iterate over all user objects
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
		return userList;
	}

}
