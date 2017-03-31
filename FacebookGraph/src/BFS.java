import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

/**
 * A class that runs Breadth First Search on a graph representation
 * @author josephhaymaker
 *
 */
public class BFS {
	private Map<String, LinkedList<Node<String>>> adjacencies;
	private HashMap<String, Node<String>> allNodes = new HashMap<>();
	private Queue<Node<String>> q;
	private ArrayList<Node<String>> traversedNodes; //essentially 'seenList' once nodes have been popped off queue
//	private int distance;
	private Node<String> startNode;

	/**
	 * The constructor for the class
	 * @param adjacencyList a map representation of a graph (adjacency list) with keys of a string, and values of a linked list of adjacent nodes
	 */
	public BFS(Map<String, LinkedList<Node<String>>> adjacencyList){
		adjacencies = adjacencyList;
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
//		distance = 0;
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
			ListIterator<Node<String>> iter = adjacencies.get(u).listIterator();
			while(iter.hasNext()){
				Node<String> currentNode = iter.next();
				if(currentNode.color.equals("white")){
					currentNode.color = "gray";
					currentNode.distance = u.distance + 1;
					currentNode.predecessor = u;
					q.add(currentNode);
				} 
			}
			u.color = "black";
		}
		return traversedNodes;
	}

	//	/**
	//	 * A method to get the distance calculated by BFS
	//	 * @return
	//	 */
	//	public int getDistance() {
	//		return distance;
	//	}

	//	/**
	//	 * 
	//	 * @param distance
	//	 */
	//	public void setDistance(int distance) {
	//		this.distance = distance;
	//	}

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
	public HashMap<String, Node<String>> getAllNodes() {
		return allNodes;
	}

	/**
	 * A setter method for a hashmap of all graph nodes
	 * @param allNodes
	 */
	public void setAllNodes(HashMap<String, Node<String>> allNodes) {
		this.allNodes = allNodes;
	}

	//	public Queue<Node<String>> getQ() {
	//		return q;
	//	}

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

	
	public static void main(String[] args) throws FileNotFoundException{
		GraphCreator gc = new GraphCreator("facebook_combined2.txt");
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = null;

		BFS bfs = new BFS(adjacencyList);
		bfs.setAllNodes(seenList);
		bfs.runBFS("3");

	}


}
