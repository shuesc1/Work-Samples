import java.util.HashMap;

/**
 * An interface that provides method for accessing information for a given client
 * @author josephhaymaker
 *
 */
public interface Client {

	public String getIdentifier();
	
	public void setIdentifier(String id);
	
	public HashMap<String,Double> getRatings();
	
}
