import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphCreator {
//	private ArrayList<String> neighbors;
//	private HashMap<Node<String>, String> adjacencies;
//	private ArrayList<LinkedList> adjacencies;
	private HashMap<String, Node<String>> seenList;

	
	/**
	 * The constructor for the class
	 * @param file a valid .txt file name/path
	 * @throws FileNotFoundException
	 */
	public GraphCreator(String file) throws FileNotFoundException{
//		adjacencies = new ArrayList<>();
		seenList = new HashMap<>();
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

				//either USER NODE is already in adj list & seen list or it isn't
				if(seenList.containsKey(split[0])){
					Node<String> user = seenList.get(split[0]); 
//					Node<Integer> user = seenList.get(split[0]);
				} else if(!seenList.containsKey(split[0])){
					Node<String> user =new Node<>(split[0]);
					seenList.put(user.value, user);
//					ArrayList<String> neighbors = new ArrayList<String>();
//					adjacencies.put(user, neighbors);
				}
				
				
				//either FRIEND NODE is in adj list & seen list or it isn't
				
				
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
