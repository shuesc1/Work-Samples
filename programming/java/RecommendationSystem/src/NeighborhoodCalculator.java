import java.util.Collection;
import java.util.HashMap;

/**
 * A class that uses similarity values to create a 'neighborhood' for a specific user u in order to calculate
 * the predicted rating for a specific item i
 * @author josephhaymaker
 *
 */
public class NeighborhoodCalculator {

	static final int NEIGHBORHOOD_SIZE = 30;
	private HashMap<String, User> neighborhood;
	private Collection<User> allUsers;

/**
 * The constructor for the class
 * @param usersWAvgsAndPearson a collection of user objects with averages and similarities
 */
	public NeighborhoodCalculator(Collection<User> usersWAvgsAndPearson){
		allUsers = usersWAvgsAndPearson;
		neighborhood = new HashMap<String, User>();
	}

	/**
	 * A method for creating a neighborhood to calculate a predicted rating for a given user u and item i
	 * @return a HM of String keys and User objects that make up the neighborhood
	 */
	public HashMap<String, User> createNeighborhood(){
		double lowerBound1 = 0.5;
		double lowerBound2 = -0.5;
		double upperBound = 1.0;
		double pearson = 0;

		for(User user : allUsers){
			if (neighborhood.size() <= NEIGHBORHOOD_SIZE) {
				pearson = user.correlation;
				if(pearson >= lowerBound1 && pearson <= upperBound || pearson >= lowerBound2 && pearson <= upperBound){
					neighborhood.put(user.id, user);
				}
			}
		}
		return neighborhood;
	}

}
