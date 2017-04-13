/**
 * This is the Item interface
 * Any item will implement methods declared here
 * Items may be books, movies, songs, businesses, etc.
 * @author josephhaymaker
 *
 */
public interface Item {
	
	public double getRating();
	
	public void setRating(double rating);
	
	public int getID();
	
	public void setID(int id);
	
	public String getName();
	
	public void setName(String name);
		
	
}
