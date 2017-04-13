import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class User implements Client {

	public int id;
	public int indexLocation;
	public String title;
	//	public ArrayList<Movie> ratedMovies;
	public HashMap<String, Double> ratedMovies;


	@Override
	public HashMap<String, Double> getRatings() {
		return ratedMovies;
	}

	@Override
	public int getIdentifier() {
		return id;
	}

	@Override
	public void setIdentifier(int id) {
		this.id = id;		
	}

}
