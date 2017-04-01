import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DFS {
	private Map<String, LinkedList<Node<String>>> adjacencies;
	private HashMap<String, Node<String>> allNodes;
	//	private ArrayList<Node<String>> traversedNodes; //essentially 'seenList' once nodes have been popped off queue
	private int distance;
	private Node<String> startNode;
	private List<Node<String>> list;
	private int time;

	public DFS(Map<String, LinkedList<Node<String>>> adjacencyList, HashMap<String, Node<String>> graphNodes){
		adjacencies = adjacencyList;
		list = new ArrayList<Node<String>>(graphNodes.values());
	}

	//	public void runDFS(List<Node<String>> listOfAllNodes){
	public void runDFS(){
		for(Node<String> node : list){
			node.color = "white";
			node.predecessor = null;
		}
		time = 0;

		for(Node<String> node : list){
			if(node.color.equalsIgnoreCase("white")){
				DFSvisit(list, 
			}
		}

	}

	public void DFSvisit(List<Node<String>> listOfNodes, String startingNode) throws FileNotFoundException{
		GraphCreator gc = new GraphCreator("simple_graph.txt");
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = gc.getSeenList();

		DFS dfs = new DFS(adjacencyList, seenList);
	}

}
