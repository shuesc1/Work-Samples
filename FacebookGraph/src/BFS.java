import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

public class BFS {

	private Map<String, LinkedList<Node<String>>> adjacencies;
	private HashMap<String, Node<String>> allNodes;
	private Queue<Node<String>> q;
	private ArrayList<Node<String>> traversedNodes; //essentially 'seenList' once nodes have been popped off queue
	private int distance;
	private Node<String> startNode;

	public BFS(Map<String, LinkedList<Node<String>>> adjacencyList){
		adjacencies = adjacencyList;
		q = new LinkedList<>();
		traversedNodes = new ArrayList<>();
	}

	public void runBFS(String startNodeS){
		distance = 0;
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
			
			
			ListIterator<Node<String>> iter = adjacencies.get(u).listIterator();
			while(iter.hasNext()){
				
			}
//			for(Node<String> node : adjacencies.get(u)){ //need to iterate through all nodes in LL of adjacencies
//
//			}
		}
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Map<String, LinkedList<Node<String>>> getAdjacencies() {
		return adjacencies;
	}

	public HashMap<String, Node<String>> getAllNodes() {
		return allNodes;
	}

	public Queue<Node<String>> getQ() {
		return q;
	}

	public ArrayList<Node<String>> getTraversedNodes() {
		return traversedNodes;
	}

	public Node<String> getStartNode() {
		return startNode;
	}

	public static void main(String[] args){
		GraphCreator gc = new GraphCreator("facebook_combined2.txt");
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = null;

		BFS bfs = new BFS(adjacencyList);
		seenList = bfs.setAllNodes;
		bfs.runBFS("3");

	}


}
