import java.util.HashMap;

/**
 * A class that generates a rating prediction for an item i that isn't yet rated by user u
 * @author josephhaymaker
 *
 */
public class PredictionCalculator {

	private double prediction, fraction, numerator, denominator, indivNum, indivDenom;

	/**
	 * The constructor for the class
	 */
	public PredictionCalculator(){
		prediction = 0;
	}

	/**
	 * A method that calculates the rating prediction of an item i for a user u given a neighborhood of other users
	 * @param u the target user
	 * @param neighborhood a list of users with high correlations to user u who all have already stored correlation and deviation data
	 * @return
	 */
	public double calculatePrediction(User u, HashMap<String, User> neighborhood){
		fraction = 0;
		numerator = 0;
		denominator = 0;
		indivNum = 0;
		indivDenom = 0;

		for(User neighbor : neighborhood.values()){
			indivNum = (neighbor.correlation * neighbor.deviation);
			indivDenom = Math.abs(neighbor.correlation);

			numerator = numerator + indivNum;
			denominator = denominator + indivDenom;
		}

		if(denominator != 0){
			fraction = (numerator / denominator);
		}

		prediction = u.ratingAvg + fraction;

		return prediction;
	}



}
