import java.util.HashMap;
import java.util.LinkedList;

/**
 * An interface for a user
 * @author josephhaymaker
 *
 */
public class User implements Client {

	public String id;
	public int indexLocation;
	public String title;
	//	public ArrayList<Movie> ratedMovies;
	public HashMap<String, Double> ratedMovies;
	public HashMap<String, Double> ratedBooks;
	public double ratingAvg, correlation, deviation;

	/**
	 * The constructor for the class
	 * It initializes the HM of rated movies
	 */
	public User(){
		ratedMovies = new HashMap<String, Double>();
	}

	@Override
	public HashMap<String, Double> getRatings() {
		return ratedMovies;
	}

	@Override
	public String getIdentifier() {
		return id;
	}

	@Override
	public void setIdentifier(String id) {
		this.id = id;		
	}

}
