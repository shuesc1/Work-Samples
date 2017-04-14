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
	public double ratingAvg, correlation, deviation;

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
