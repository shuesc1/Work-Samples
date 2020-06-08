import java.util.ArrayList;

public class Book implements Item {

	public String id;
	public double rating;
	public String name;
	//	public int indexLocation;
	//	public String title;
	//	public ArrayList<String> genre;

	@Override
	public double getRating() {
		return rating;
	}

	@Override
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public void setID(String id) {
		this.id = id;
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
