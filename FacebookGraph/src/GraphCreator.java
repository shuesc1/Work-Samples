import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.Line;

public class GraphCreator {
	//	private ArrayList<String> neighbors;
	//	private HashMap<Node<String>, String> adjacencies;
	//	private ArrayList<LinkedList> adjacencies;
	private static Map<String, LinkedList<Node<String>>> adjacencyList;
	private static HashMap<String, Node<String>> seenList;
	private Node<String> user, friend;

	/**
	 * The constructor for the class
	 * @param file a valid .txt file name/path
	 * @throws FileNotFoundException
	 */
	public GraphCreator(String file) throws FileNotFoundException{
		//		adjacencies = new ArrayList<>();
		seenList = new HashMap<>();
		adjacencyList = new HashMap<>();
		user = new Node("");
		friend = new Node("");
		readFile(file);
	}

	/**
	 * A method that takes in a .txt file and parses it, creating user nodes and an adjacency list, stored as a Linked List
	 * @param <T>
	 * @param filename a valid .txt file
	 */
	public <T> void readFile(String filename){
		File file = new File(filename);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
//				System.out.println(line);
				String[] split = line.split(" ");
				String userName = split[0];
				String friendName = split[1];

//				for(String element : split){
//					System.out.println("Split element: " + element);
//				}
				
				//either USER NODE is an existing node or it isn't
				//if it is then copy memory location
				//if not create node and store in AdjList & seenList
				//				if(adjacencyList.containsKey(userName) || seenList.containsKey(userName)) {
				if(seenList.containsKey(userName)) {
					user = seenList.get(userName); 
				} else {
					user = new Node<>(userName);
//					System.out.println("Just user name: " + user.value);
					adjacencyList.put(user.value, new LinkedList<Node<String>>());
					seenList.put(user.value, user);
				}

				//either FRIEND NODE is an existent node or it isn't
				//if it is then copy memory location from seenList
				//if not then create node and place in seenList
				if(seenList.containsKey(friendName)){
					friend = seenList.get(friendName); 
				} else {
					friend = new Node<>(friendName);
					seenList.put(friend.value, friend);
				}

				//if user node is in adj list get its Linked List of adjacencies and add friend node
				//if user node not in adjacency list add to list, then add friend node to LL of adjacencies
				//this is due to the fact that a node can show up in seenList without having been a key in the adjList (b/c it was a friend of some other node)
				if(adjacencyList.containsKey(user.value)){
					adjacencyList.get(user.value).addLast(friend);
				} else {
					adjacencyList.put(user.value, new LinkedList<Node<String>>());
					adjacencyList.get(user.value).add(friend);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *A method to get the adjacency list
	 * @return a map adjacency list with keys of a string, and value of a linked list of node objects
	 */
	public static Map<String, LinkedList<Node<String>>> getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * A method to get the seenList hashmap
	 * @return a hashmap of seen/created nodes with keys of strings, and values of Nodes carrying string values.
	 */
	public static HashMap<String, Node<String>> getSeenList() {
		return seenList;
	}


	public static void main(String[] args) throws FileNotFoundException{
		GraphCreator gc = new GraphCreator("facebook_combined2.txt");
		//		LinkedList<Node<String>> one = getAdjacencyList().get("1");
	}
}
