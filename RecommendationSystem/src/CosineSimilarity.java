
/**
 * A class that calculates similarity via a vector-space approach based on linear algebra
 * formula: << http://files.grouplens.org/papers/FnT%20CF%20Recsys%20Survey.pdf >>
 * @author josephhaymaker
 *
 */
public class CosineSimilarity {
	private double numerator, denominator, similarity, indivDenom;
	private User u,v;

	/**
	 * The constructor for the class
	 * @param u a user
	 * @param v a user
	 */
	public CosineSimilarity(User u, User v){
		this.u = u;
		this.v = v;
	}

	/**
	 * A method that calculates similarity by taking the dot product of two users' ratings
	 * and dividing it by their L2 (Euclidean) norms
	 * This approach is a vector space approach based on linear algebra rather than a statistical approach.
	 * @return a double numerical value representing similarity between 2 users
	 */
	public double calculateSimilarity(){
		for(String i : u.ratedItems.keySet()){
			for(String j : v.ratedItems.keySet()){
				if(u.ratedItems.containsKey(j)){
					numerator = numerator + (u.ratedItems.get(j) * v.ratedItems.get(j));
				}
			}
		}
		denominator = getUserDenom(u) * getUserDenom(v);
		similarity = numerator / denominator; 

		return similarity;
	}

	/**
	 * A helper method for calculating similarity that retrieves part of the denominator
	 * of the equation
	 * @param x a user
	 * @return a double of the sum of all the user's ratings x ratings
	 */
	public double getUserDenom(User x){
		indivDenom = 0;
		double sumSquares = 0;

		for(Double d : x.ratedItems.values()){
			sumSquares = sumSquares + (d * d);
		}
		indivDenom = Math.sqrt(sumSquares);

		return indivDenom;
	}

}
