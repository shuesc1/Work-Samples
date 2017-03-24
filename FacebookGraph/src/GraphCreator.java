import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphCreator {
	/**
	 * The constructor for the class
	 * @param file a valid .txt file name/path
	 * @throws FileNotFoundException
	 */
	public GraphCreator(String file) throws FileNotFoundException{
		readFile(file);
	}

/**
 * A method that takes in a .txt file and parses it, creating nodes and an adjacency matrix
 * @param <T>
 * @param filename
 */
	public <T> void readFile(String filename){
		File file = new File(filename);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");

				//either user node is already in adj list & seen list or it isn't
				//either friend node is in adj list & seen list or it isn't
				
				
				//need to check if user is already in adj list to avoid repeats
				//check SEEN 
				Node<String> user =new Node<String>(split[0]);
				
				
//				for(String element : split){
//					System.out.println(element);
//				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}






	public static void main(String[] args) throws FileNotFoundException{
		GraphCreator gc = new GraphCreator("facebook_combined.txt");

	}
}
