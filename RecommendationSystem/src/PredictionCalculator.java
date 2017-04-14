/**
 * A class that generates a rating prediction for an item i that isn't yet rated by user u
 * @author josephhaymaker
 *
 */
public class PredictionCalculator {

	private double userAvgR;
	private int item, user;
	private SimilarityCalculator sc;
	private NeighborhoodCalculator nc;
	
	public PredictionCalculator(int userID, int itemID){
		item = itemID;
		user = userID;
	}
	
}
