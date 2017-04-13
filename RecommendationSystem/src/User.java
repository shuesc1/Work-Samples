import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class User implements Client {

	public String id;
	public int indexLocation;
	public String title;
	//	public ArrayList<Movie> ratedMovies;
	public HashMap<String, Double> ratedMovies;


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
