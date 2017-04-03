import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

//import src.String;

public class GraphRunner {

	public static void main(String[] args) throws FileNotFoundException{

		Scanner userIn = new Scanner(System.in);
		System.out.println("Please enter a valid .txt file with values representing nodes and edges:");
		String filename = userIn.nextLine();
		GraphCreator gc = new GraphCreator(filename);
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = gc.getSeenList();

		BFS bfs = new BFS(adjacencyList, seenList);
		bfs.setAllNodes(seenList);
		DFS dfs = new DFS(adjacencyList, seenList);

		//BEGIN user choice section
		String[] userChoice;
		Scanner in = new Scanner(System.in);
		System.out.println("This program uses Facebook friendship data, Breadth First Search, and Depth First Search to build and explore a graph.");
		System.out.println("Please select what you would like to know about the file and friendships from the following list:\n");

		userChoice = new String[7];
		userChoice[0] = "1. (a.) What's the distance between nodes 75 and 2190?";
		userChoice[1] = "2. (b.) Is the graph connected, i.e., is there a path from any given node u to any other given node v? Why?";
		userChoice[2] = "3. (c.) Running BFS: how many frontiers/steps does it need to visit all nodes?\n"
				+ "Does this number differ over three random trials? If so, why?";
		userChoice[3] = "4. (d.) Running DFS: set start node to 10, then get start/finish time for 2 other random nodes,\n"
				+ "4020 and 1029, for example. Do the times change over various trials? Why?";
		userChoice[4] = "5. (e.) How many nodes are within a distance of 3 from node 1912?";
		userChoice[5] = "6. (f.) A clique of size n is a set of n nodes that are all adjacent to each other.\n"
				+ "The goal is to find the largest size clique in the graph.\n"
				+ "i. Can you use BFS or DFS for this? Why?";
		userChoice[6] = "7. Attempt to find max clique number";

		for (int i = 0; i < userChoice.length; i++)
		{		System.out.println(userChoice[i]); }

		boolean end = false;
		while (end == false){
			System.out.println("Which option would you like to choose?:\n");
			int userSelection = in.nextInt();
			System.out.println("**Loading**");
			switch (userSelection){
			
			case 1:
				ArrayList<Node<String>> nodes = bfs.runBFS("75");
//				int distance = 0;
				Node<String> twentyOneNinety = new Node<String>(null);
//				for (Node<String> node : nodes) {
//					if (node.value.equalsIgnoreCase("2190")) {
//						twentyOneNinety = node;
//						break;
//					}
//				}
				twentyOneNinety = bfs.getAllNodes().get("2190");
				int distance = twentyOneNinety.distance;
				System.out.println("The distance between nodes 75 and 2190 is " + distance + "\n");
				break;
			
			case 2: 
				boolean connected = true;
				List<Node<String>> traversedNodes = dfs.runDFS("0");
				for(Node<String> node : traversedNodes){
					if(node.color.equalsIgnoreCase("white")){
						connected = false;
						break;
					} else {
						connected = true;
					}
				}

				if(connected == false){
					System.out.println("Found a node that wasn't reached by DFS--graph isn't connected!");
				} else if (connected == true){
					System.out.println("All nodes reached by DFS--graph is connected!");
				}
				break;
			
			case 3: 
				int trial = 1;
				do{
					Random rand = new Random();
					int start = rand.nextInt(4030);
					String sNode = Integer.toString(start);
					ArrayList<Node<String>> allNodes = bfs.runBFS(sNode);
					int max = 0;
					for (Node<String> node : allNodes) {
						if (node.distance > max) {
							max = node.distance;
						}
					}
					System.out.println("Start node: " + sNode + " , Trial " + trial + ", Steps/frontiers needed: " + max);
					trial = trial + 1;
				} while(trial < 4);
				break;
			
			case 4: 
				int trialNum = 1;
				do{
					List<Node<String>> traversed = dfs.runDFS("10");
					for (Node<String> n : traversed){
						if(n.value.equalsIgnoreCase("4020") || n.value.equalsIgnoreCase("1029"))
							System.out.print("Trial " + trialNum + " , Node #: " + n.value + ", start: " + n.start + ", finish:" + n.finish + "\n");
					}
					trialNum = trialNum + 1;
				} while(trialNum < 4);
				break;
		
			case 5: 
				int total = 0;
				ArrayList<Node<String>> allNodes = bfs.runBFS("1912");
				System.out.print("Nodes that are a distance of 3 or less from node 1912: ");
				ArrayList<Node<String>> distThree = new ArrayList<>();
				for (Node<String> node : allNodes) {
					total = total + 1;
					if (node.distance <= 3) {
						System.out.println("node " + node.value);
						distThree.add(node);
					}
				}
				if(distThree.isEmpty()){
					System.out.println("No nodes of distance 3 exist!");
				}
				System.out.println("Total nodes: " + total + " , nodes within a distance 3 or less: " + distThree.size());
				break;
			case 6: 
				System.out.println("i. you could hypothetically use BFS/DFS for this based on the \n"
						+ "knowledge that all neighbors would be 1 step away. Memory constraints make this an impossibility, however,\n"
						+ "as time to find the max clique number would be exponential.");
				break;
			case 7: 
				//				iag.ques7getter();
				//				System.out.println("\n");
				break;
			default: 
				end = true;
				break;		
			}
		}
		in.close();
	}
}