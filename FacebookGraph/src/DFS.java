import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DFS {
	private Map<String, LinkedList<Node<String>>> adjacencies;
	private HashMap<String, Node<String>> allNodes;
//	private Queue<Node<String>> q;
//	private ArrayList<Node<String>> traversedNodes; //essentially 'seenList' once nodes have been popped off queue
	private int distance;
	private Node<String> startNode;
	
	public DFS(Map<String, LinkedList<Node<String>>> adjacencyList){
		adjacencies = adjacencyList;
//		q = new LinkedList<>();
//		traversedNodes = new ArrayList<>();
	}
	
	public void runDFS(HashMap<String, Node<String>> listOfAllNodes){
		
	}
}
