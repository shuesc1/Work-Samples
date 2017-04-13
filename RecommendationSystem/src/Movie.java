import java.util.ArrayList;

/**
 * A class that creates a movie object
 * @author josephhaymaker
 *
 */
public class Movie implements Item {

	public int id;
	public double rating;
	public String name;
	public int indexLocation;
	public String title;
	public ArrayList<String> genre;

	@Override
	public double getRating() {
		return rating;
	}

	@Override
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void setID(int number) {
		id = number;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;		
	}



}
