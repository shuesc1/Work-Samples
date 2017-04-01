import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class DFS {
	private Map<String, LinkedList<Node<String>>> adjacencies;
	private HashMap<String, Node<String>> allNodes;
	//	private ArrayList<Node<String>> traversedNodes; //essentially 'seenList' once nodes have been popped off queue
	private int distance;
	private Node<String> startNode = new Node<String>("");
	private List<Node<String>> list;
	private int time;
	private String startLocation;

	public DFS(Map<String, LinkedList<Node<String>>> adjacencyList, HashMap<String, Node<String>> graphNodes){
		adjacencies = adjacencyList;
		allNodes = graphNodes;
		list = new ArrayList<Node<String>>(graphNodes.values());
	}

	//	public void runDFS(List<Node<String>> listOfAllNodes){
	public void runDFS() throws FileNotFoundException{
		for(Node<String> node : list){
			node.color = "white";
			node.predecessor = null;
		}
		time = 0;

		DFSvisit(list, startNode);

		for(Node<String> node : list){
			if(node.color.equalsIgnoreCase("white")){
				DFSvisit(list, node);
			}
		}
		System.out.println("DFS has finished!");
	}

	//currentNode = 'u' ; thisNode = 'v'
	public void DFSvisit(List<Node<String>> listOfNodes, Node<String> currentNode) throws FileNotFoundException{
		time = time++;
		currentNode.start = time;
		currentNode.color = "gray";

		//need to iterate over all adjacencies to current node
		LinkedList nodeAdjacencies = adjacencies.get(currentNode.value);
		//		ListIterator<Node<String>> iterator = nodeAdjacencies.listIterator();
		//		while(iterator.hasNext()){
		//			Node<String> thisNode = iterator.next();
		//			if(thisNode.color.equalsIgnoreCase("white")){
		//				thisNode.predecessor = currentNode;
		//				DFSvisit(listOfNodes, thisNode);
		//			}
		//		}

		for(int i = 0; i < nodeAdjacencies.size(); i++){
			Node<String> thisNode = (Node<String>) nodeAdjacencies.get(i);
			if(thisNode.color.equalsIgnoreCase("white")){
				thisNode.predecessor = currentNode;
				DFSvisit(listOfNodes, thisNode);
			}
		}
		currentNode.color = "black";
		time = time++;
		currentNode.finish = time;
	}

	public Node<String> getStartNode() {
		return startNode;
	}

	public void setStart(String targetNode) {
		startLocation = targetNode;
		startNode = allNodes.get(startLocation);
	}

	//**********TESTING**********///////
	public static void main(String[] args) throws FileNotFoundException{
		GraphCreator gc = new GraphCreator("simple_graph.txt");
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = gc.getSeenList();

		DFS dfs = new DFS(adjacencyList, seenList);
		Scanner in = new Scanner(System.in);
		String userDefinedStart = null;

		System.out.println("What node would you like to start at? (values 0 - 4031):");
		userDefinedStart = in.next();
//		do {
//			try {
//				System.out.println("What node would you like to start at? (values 0 - 4031):");
//				userDefinedStart = in.next();
//			} catch (InputMismatchException e) {
//				System.out.print("Invalid node value! ");
//			} catch (NoSuchElementException nsee) {
//				System.out.print("Invalid node value-- node doesn't exist!! ");
//			}
//			in.nextLine(); // clears the buffer
//		} while (!userDefinedStart.matches("^[0-9]{1,4}$"));

		dfs.setStart(userDefinedStart);
		dfs.runDFS();
	}

}
