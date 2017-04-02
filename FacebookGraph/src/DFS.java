import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that runs Depth First Search on a graph representation
 * It takes in a start node and then records start(discovery) time, finish time, and color 
 * (white= undiscovered, gray = first discovered, black = finished/no longer visitable) 
 * for each node in a graph.
 * @author josephhaymaker
 *
 */
public class DFS {
	private Map<String, LinkedList<Node<String>>> adjacencies;
	private static HashMap<String, Node<String>> allNodes;
	private Node<String> startNode = new Node<String>("");
	private List<Node<String>> list;
	private int time;
	private String startLocation;

	/**
	 * The constructor for the class that initializes the adjacency list, hashmap of all nodes and easily iterable version of said hashmap
	 * @param adjacencyList a hashmap of the node names and their corresponding linked lists of adjacent nodes
	 * @param graphNodes a hashmap of the node names and corresponding node objects
	 */
	public DFS(Map<String, LinkedList<Node<String>>> adjacencyList, HashMap<String, Node<String>> graphNodes){
		adjacencies = adjacencyList;
		allNodes = graphNodes;
		list = new ArrayList<Node<String>>();
		list.addAll(graphNodes.values());
	}

	/**
	 * The primary method that implements the DFSvisit method
	 * @param start a string corresponding to the start node value
	 * @return a list of all nodes with corresponding start & finish times
	 * @throws FileNotFoundException
	 */
	public List<Node<String>> runDFS(String start) throws FileNotFoundException{
		startNode = allNodes.get(start);
		for(Node<String> node : list){
			node.color = "white";
			node.predecessor = null;
		}
		time = 0;

		if(startNode != null){
			DFSvisit(list, startNode);
		}
		for(Node<String> node : list){
			if(node.color.equalsIgnoreCase("white")){
				DFSvisit(list, node);
			}
		}
		System.out.println("DFS has finished!");
		return list;
	}

	/**
	 * 
	 * @param listOfNodes
	 * @param currentNode
	 * @throws FileNotFoundException
	 * 
	 * currentNode = 'u' ; thisNode = 'v'
	 */
	public void DFSvisit(List<Node<String>> listOfNodes, Node<String> currentNode) throws FileNotFoundException{
		time = time + 1;
		currentNode.start = time;
		currentNode.color = "gray";

		//need to iterate over all adjacencies to current node
		LinkedList<Node<String>> nodeAdjacencies = adjacencies.get(currentNode.value);
				ListIterator<Node<String>> iterator = nodeAdjacencies.listIterator();
				while(iterator.hasNext()){
					Node<String> thisNode = iterator.next();
					if(thisNode.color.equalsIgnoreCase("white")){
						thisNode.predecessor = currentNode;
						DFSvisit(listOfNodes, thisNode);
					}
				}

//		for(int i = 0; i < nodeAdjacencies.size(); i++){
//			Node<String> thisNode = (Node<String>) nodeAdjacencies.get(i);
//			if(thisNode.color.equalsIgnoreCase("white")){
//				thisNode.predecessor = currentNode;
//				DFSvisit(listOfNodes, thisNode);
//			}
//		}
		currentNode.color = "black";
		time = time + 1;
		currentNode.finish = time;
	}

	public Node<String> getStartNode() {
		return startNode;
	}

	public void setStart(String targetNode) {
		startLocation = targetNode;
		startNode = allNodes.get(startLocation);
	}

	public static HashMap<String, Node<String>> getAllNodes() {
		return allNodes;
	}

	public void setAllNodes(HashMap<String, Node<String>> allNodes) {
		this.allNodes = allNodes;
	}

	//**********TESTING**********///////
	public static void main(String[] args) throws FileNotFoundException{
		//test graph is example from class-- nodes 1-7
		GraphCreator gc = new GraphCreator("simple_graph.txt");
//		GraphCreator gc = new GraphCreator("facebook_combined2.txt");
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = gc.getSeenList();

		DFS dfs = new DFS(adjacencyList, seenList);
		Scanner in = new Scanner(System.in);
		String userDefinedStart = null;

		do {
			try {
				System.out.println("What node would you like to start at? (values 1 - 7):");
				userDefinedStart = in.next();
			} catch (InputMismatchException e) {
				System.out.print("Invalid node value! ");
			} catch (NoSuchElementException nsee) {
				System.out.print("Invalid node value-- node doesn't exist!! ");
			}
			in.nextLine(); // clears the buffer
		} while (!userDefinedStart.matches("^[0-9]{1,4}$"));

		dfs.runDFS(userDefinedStart);
		boolean quit = false;
		do{

			System.out.println("What node would you like to know the start and finish time of?:");
			Scanner input = new Scanner(System.in);
			String nodeName = input.next();
			Node<String> questionNode = getAllNodes().get(nodeName);
			System.out.println("Node: " + nodeName + " , Start time: " + questionNode.start + " , Finish time: " + questionNode.finish + " , Color: " + questionNode.color);
			input.nextLine();
			System.out.println("Would you like to quit? (y/n)");
			if(input.next().equalsIgnoreCase("y")){
				quit = true;
			} 
		} while (quit == false);
	}

}
