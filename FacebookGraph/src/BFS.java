import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * A class that runs Breadth First Search on a graph representation
 * @author josephhaymaker
 *
 */
public class BFS {
	private Map<String, LinkedList<Node<String>>> adjacencies = new HashMap<>();
	private static HashMap<String, Node<String>> allNodes = new HashMap<>();
	private Queue<Node<String>> q;
	private ArrayList<Node<String>> traversedNodes; //essentially 'seenList' once nodes have been popped off queue
	private Node<String> startNode = new Node<String>(null);

	/**
	 * The constructor for the class
	 * @param adjacencyList a map representation of a graph (adjacency list) with keys of a string, and values of a linked list of adjacent nodes
	 */
	public BFS(Map<String, LinkedList<Node<String>>> adjacencyList, HashMap<String, Node<String>> listAllNodes){
		adjacencies = adjacencyList;
		allNodes = listAllNodes;
		q = new LinkedList<>();
		traversedNodes = new ArrayList<>();
	}

	/**
	 * The main method of this class
	 * Runs BFS on a graph
	 * @param startNodeS a node to be used as the start node (s)
	 * @return an arraylist of traversed nodes with corresponding internal colors, distances, and predecessors
	 */
	public ArrayList<Node<String>> runBFS(String startNodeS){
		for(Node<String> nodeName : allNodes.values()){
			nodeName.color = "white";
			nodeName.distance = 0;
			nodeName.predecessor = null;
		}
		startNode = allNodes.get(startNodeS);
		startNode.color = "white";
		startNode.distance = 0;
		startNode.predecessor = null;

		q.add(startNode);
		while(q.isEmpty() == false){
			Node<String> u = q.remove();
			traversedNodes.add(u);
			LinkedList<Node<String>> linked = adjacencies.get(u.value);
			if(linked != null){
				for(Node<String> node : linked){
					Node<String> currentNode = node;
					if(currentNode.color.equals("white")){
						currentNode.color = "gray";
						currentNode.distance = u.distance + 1;
						currentNode.predecessor = u;
//						System.out.println("node: " + currentNode.value + ", node distance:" + currentNode.distance + " , predecessor: " + currentNode.predecessor.value);
						if(!traversedNodes.contains(currentNode)){
							q.add(currentNode);
						}
					}
				}
			}
			u.color = "black";
		}
		System.out.println("BFS has finished running!");
		return traversedNodes;
	}

	/**
	 * A getter method for the adjacency list
	 * @return a map with keys of a string and values of linked lists of node objects that are adjacent to the key node
	 */
	public Map<String, LinkedList<Node<String>>> getAdjacencies() {
		return adjacencies;
	}

	/**
	 * A getter method for all nodes in the graph
	 * @return a hashmap: key = node value, value = node itself
	 */
	public static HashMap<String, Node<String>> getAllNodes() {
		return allNodes;
	}

	/**
	 * A setter method for a hashmap of all graph nodes
	 * @param allNodes
	 */
	public void setAllNodes(HashMap<String, Node<String>> allNodes) {
		this.allNodes = allNodes;
	}

	/**
	 * a getter method for the arraylist of traversed nodes
	 * @return an arraylist that stores the nodes in the order in which they were traversed
	 */
	public ArrayList<Node<String>> getTraversedNodes() {
		return traversedNodes;
	}

	/**
	 * A getter method for the start node
	 * @return
	 */
	public Node<String> getStartNode() {
		return startNode;
	}

	//**********TESTING**********///////
	public static void main(String[] args) throws FileNotFoundException{
		GraphCreator gc = new GraphCreator("facebook_combined.txt");
		//						GraphCreator gc = new GraphCreator("facebook_combined2.txt");
		//				GraphCreator gc = new GraphCreator("simple_graph.txt");
		//		GraphCreator gc = new GraphCreator("dag.txt");
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = gc.getSeenList();

		BFS bfs = new BFS(adjacencyList, seenList);
		//		bfs.setAllNodes(seenList);
		bfs.runBFS("0");

		boolean quit = false;
		do{
			System.out.println("What node would you like to know the distance of?:");
			Scanner input = new Scanner(System.in);
			String nodeName = input.next();
			Node<String> questionNode = getAllNodes().get(nodeName);
			System.out.println("Node: " + nodeName + " , Distance: " + questionNode.distance + " , Color: " + questionNode.color);
			input.nextLine();
			System.out.println("Would you like to quit? (y/n)");
			if(input.next().equalsIgnoreCase("y")){
				quit = true;
			} 
		} while (quit == false);
	}
}
