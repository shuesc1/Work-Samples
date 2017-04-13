import java.util.HashMap;

/**
 * An interface that provides method for accessing information for a given client
 * @author josephhaymaker
 *
 */
public interface Client {

	public int getIdentifier();
	
	public void setIdentifier(int id);
	
	public HashMap<String,Double> getRatings();
	
}
