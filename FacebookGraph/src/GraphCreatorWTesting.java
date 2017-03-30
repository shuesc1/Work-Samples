import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphCreatorWTesting {

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
	public GraphCreatorWTesting(String file) throws FileNotFoundException{
		//		adjacencies = new ArrayList<>();
		seenList = new HashMap<>();
		adjacencyList = new HashMap<>();
		user = new Node("");
		friend = new Node("");
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
				System.out.println(line);
				String[] split = line.split(" ");
				String userName = split[0];
				String friendName = split[1];

				for(String element : split){
					System.out.println("Split element: " + element);
				}
				//either USER NODE is an existing node or it isn't
				//				if(adjacencyList.containsKey(userName) || seenList.containsKey(userName)) {
				if(seenList.containsKey(userName)) {
					user = seenList.get(userName); 
					//				} else if(!adjacencyList.containsKey(userName) && !seenList.containsKey(userName)){
				} else {
					//					if(!adjacencyList.containsKey(split[0])){
					user = new Node<>(userName);
					System.out.println("Just user name: " + user.value);
					//					seenList.put(user.value, user);
					adjacencyList.put(user.value, new LinkedList<Node<String>>());
					//					} else if(!seenList.containsKey(split[0])){
					//						user = new Node<>(split[0]);
					seenList.put(user.value, user);
					//					adjacencyList.put(user.value, new LinkedList<Node<String>>());
				}

				//either FRIEND NODE is an existent node or it isn't
				//				if(adjacencyList.containsKey(split[1]) || seenList.containsKey(split[1])) {
				if(seenList.containsKey(split[1])) {
					friend = seenList.get(split[1]); 
					//				} else if(!adjacencyList.containsKey(split[1]) && !seenList.containsKey(split[1])) {
				} else {
					//					if(!adjacencyList.containsKey(split[0])){
					friend = new Node<>(split[1]);
					//					seenList.put(user.value, user);
					//				adjacencyList.put(friend.value, new LinkedList<Node<String>>());
					//					} else if(!seenList.containsKey(split[0])){
					//						user = new Node<>(split[0]);
					seenList.put(friend.value, friend);
					//					adjacencyList.put(user.value, new LinkedList<Node<String>>());
				}

				if(adjacencyList.containsKey(user.value)){
					adjacencyList.get(user.value).addLast(friend);
				} else {
					adjacencyList.put(user.value, new LinkedList<Node<String>>());
					adjacencyList.get(user.value).add(friend);
				}
				System.out.println("User: " + user.value + " , friend: " + friend.value);

				for(String element : split){
					System.out.println(element);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static Map<String, LinkedList<Node<String>>> getAdjacencyList() {
		return adjacencyList;
	}

	public static HashMap<String, Node<String>> getSeenList() {
		return seenList;
	}


	public static void main(String[] args) throws FileNotFoundException{
		GraphCreatorWTesting gc = new GraphCreatorWTesting("facebook_combined2.txt");
		LinkedList<Node<String>> one = getAdjacencyList().get("1");


		for(int i = 0; i < 100; i++){
			System.out.println("Adjacency list- value: " + i + " , Key: " + getAdjacencyList().get(Integer.toString(i)));
		}
		for(int i = 0; i < 100; i++){
			System.out.println("Seen list- value: " + i + " , Key: " + getSeenList().get(Integer.toString(i)).value);
		}

		LinkedList<Node<String>> oneAdjacents = getAdjacencyList().get("1");
		while(oneAdjacents.isEmpty() == false){
			System.out.println("Adj list for '1'- user: 1" + " , friends: " + oneAdjacents.peekFirst().value);
			oneAdjacents.removeFirst();
		}

		//		while(one.isEmpty() == false){
		//			System.out.println("One's friends: " + one.peekFirst().value);
		//			one.removeFirst();
		//		}
	}
}
