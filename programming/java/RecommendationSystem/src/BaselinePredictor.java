import java.util.HashMap;

/**
 * A class that gives a predicted rating for a user u and an item i
 * formula: << http://files.grouplens.org/papers/FnT%20CF%20Recsys%20Survey.pdf >>
 * @author josephhaymaker
 *
 */
public class BaselinePredictor {

	private HashMap<String, User> userList;
	private User u;
	private Item i;
	private double baselinePred, biasUser, biasItem;

	/**
	 * The constructor for the class
	 * @param u a User object with key variables
	 * @param i a given item that has been rated
	 * @param allUsers a HP of all users with Keys as Strings(IDs) and Values as User objects
	 */
	public BaselinePredictor(User u, Item i, HashMap<String, User> allUsers){
		this.u = u;
		this.i = i;
		userList = allUsers;
	}

	/**
	 * A method that employs helper methods to calculate the baseline prediction for item i for user u
	 * @return a double value representing the prediction
	 */
	public double calculateBaseline(){
		baselinePred = getAggRatingAvg(userList) + biasUser(u) + biasItem(userList);
		return baselinePred;
	}

	/**
	 * A helper method to calculate the user's bias, which is to say their harshness or generosity
	 * in rating compared to the average user
	 * @param u a user 
	 * @return the user's bias expressed as a double
	 */
	public double biasUser(User u){
		biasUser = 0;
		double ratingVarianceSum = 0;
		double itemCount = 0;
		double currentBias = 0;
		double avg = getAggRatingAvg(userList);

		for(Double rating : u.ratedItems.values()){
			currentBias = rating - avg;
			ratingVarianceSum += currentBias;
			itemCount++;
		}
		biasUser = ratingVarianceSum / itemCount;

		return biasUser;
	}

	/**
	 * A helper method to calculate the item's bias, ie if the item is rated higher or lower than
	 * the average item
	 * @param users a HM of Keys of strings(user IDs) and Values of User objects
	 * @return the item bias value expressed as a double
	 */
	public double biasItem(HashMap<String, User> users){
		biasItem = 0;
		double userVarianceSum = 0;
		double userCount = 0;
		double currentBias = 0;
		double userBias = biasUser(u);
		double universalAvg = getAggRatingAvg(userList);

		for(User x : users.values()){
			if(x.ratedItems.containsValue(i)){
				currentBias = x.ratedItems.get(i) - userBias - universalAvg;
				userVarianceSum += currentBias;
				userCount++;
			}
		}
		biasItem = userVarianceSum / userCount;

		return biasItem;
	}

	/**
	 * A helper method that calculates the overall average rating of all users
	 * @param allUsers
	 * @return an average rating expressed as a double
	 */
	public double getAggRatingAvg(HashMap<String, User> allUsers){
		double baseline = 0;
		double totalItems = 0;
		double grossSum = 0;

		for(User u : allUsers.values()){
			for(Double rating : u.ratedItems.values()){
				grossSum = grossSum + rating;
				totalItems++;
			}
		}
		baseline = grossSum / totalItems;
		return baseline;
	}

}
