import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

//import src.String;

public class GraphRunner {

	public static void main(String[] args) throws FileNotFoundException{

		Scanner userIn = new Scanner(System.in);
		System.out.println("Please enter a valid .txt file with values representing nodes and edges:");
		//		String filename ="";
		String filename = "facebook_combined.txt";
		//		filename = userIn.nextLine();
		GraphCreator gc = new GraphCreator(filename);
		Map<String, LinkedList<Node<String>>> adjacencyList = gc.getAdjacencyList();
		HashMap<String, Node<String>> seenList = gc.getSeenList();



		Set<String> keySet2 = seenList.keySet();
		Iterator<String> keySetIterator2 = keySet2.iterator();
		while (keySetIterator2.hasNext()) {
			String key = keySetIterator2.next();
			Node<String> n = seenList.get(key);
			System.out.println("key: " + key + " values: " + n.value);
		}
		//
		//		Set<String> keySet = adjacencyList.keySet();
		//		Iterator<String> keySetIterator = keySet.iterator();
		//		while (keySetIterator.hasNext()) {
		//			//		   System.out.println("------------------------------------------------");
		//			//		   System.out.println("Iterating Map in Java using KeySet Iterator");
		//			String key = keySetIterator.next();
		//			Object[] lls = (Node<String>[]) adjacencyList.get(key).toArray();
		//			//		   System.out.println("key: " + key + " values: " + adjacencyList.get(key));
		//			System.out.println("key: " + key + " values: ");
		//			for(Object node : lls){
		//				System.out.print(node.toString());
		//			}
		//		}





		BFS bfs = new BFS(adjacencyList);
		bfs.setAllNodes(seenList);
		DFS dfs = new DFS(adjacencyList, seenList);
	}
}
//BEGIN user choice section
//		String[] userChoice;
//		Scanner in = new Scanner(System.in);
//		System.out.println("This program uses Facebook friendship data, Breadth First Search, and Depth First Search to build and explore a graph.");
//		System.out.println("Please select what you would like to know about the file and friendships from the following list:\n");
//
//		userChoice = new String[10];
//		userChoice[0] = "1. (a.) What's the distance between nodes 75 and 2190?";
//		userChoice[1] = "2. (b.) Is the graph connected, i.e., is there a path from any given node u to any other given node v? Why?";
//		userChoice[2] = "3. (c.) Running BFS: how many frontiers/steps does it need to visit all nodes?/n"
//				+ "Does this number differ over three random trials? If so, why?";
//		userChoice[3] = "4. (d.) Running DFS: set start node to 10, then get start/finish time for 2 other random nodes,/n"
//				+ "4020 and 1029. Do the times change over various trials? Why?";
//		userChoice[4] = "5. (e.) How many nodes are within a distance of 3 from node 1912?";
//		userChoice[5] = "6. (f.) A clique of size n is a set of n nodes that are all adjacent to each other./n"
//				+ "The goal is to find the largest size clique in the graph./n"
//				+ "i. Can you use BFS or DFS for this? Why?";
//		userChoice[6] = "7. ";
//		userChoice[7] = "8. ";
//		userChoice[8] = "9. ";
//		userChoice[9] = "10. \n";
//
//		for (int i = 0; i < userChoice.length; i++)
//		{		System.out.println(userChoice[i]); }
//		System.out.println("**Loading**");
//
//		boolean end = false;
//		while (end == false){
//			System.out.println("Which option would you like to choose?:\n");
//			int userSelection = in.nextInt();
//			switch (userSelection){
//			case 1:
//				ArrayList<Node<String>> nodes = bfs.runBFS("75");
//				Node<String> twentyOneNinety = null;
//				for (Node<String> node : nodes) {
//					if (node.value.equalsIgnoreCase("2190")) {
//						twentyOneNinety = node;
//						break;
//					}
//				}
//				int distance = twentyOneNinety.distance;
//				System.out.println("The distance between nodes 75 and 2190 is " + distance + "\n");
//				break;
//			case 2: 
//
//				break;
//			case 3: 
//				//				System.out.println("A whopping " + RittenhousePercent + "% of all trips started in Rittenhouse Square\n");
//				break;
//			case 4: 
//				//				System.out.println("A total of " + roundtripPercent + "% of trips made by Indego30 riders are roundtrip\n");
//				break;
//			case 5: 
//				//				System.out.println("The ID of the bike that has traveled the longest (in terms of duration) is " + longestBikeID + "\n");
//				break;
//			case 6: 
//				//				System.out.println("On 8/3/16 at 7:00am, there were " + bikeNum + " bikes being used.\n");
//				break;
//			case 7: 
//				//				iag.ques7getter();
//				//				System.out.println("\n");
//				break;
//			case 8: 
//				//				System.out.println("List of trip IDs of all trips that involved a station which was the only station to go live on its respective go-live date: " + "\n");
//				//				iag.ques8getter();
//				break;
//			case 9: 
//				//				System.out.println("The shortest trip in terms of time was only " + shortestTime + " minutes long!\n");
//				break;
//			case 10:
//				//				String highestUseDay = iag.ques10getter();
//				//				System.out.println("The day that experienced the most usage was: " + highestUseDay);
//				break;
//			default: 
//				//	                userSelection = in.nextInt();
//				end = true;
//				break;		
//			}
//		}
//		in.close();
//	}
//}








//		DFS dfs = new DFS(adjacencyList, seenList);
//		Scanner in = new Scanner(System.in);
//		String userDefinedStart = null;
//
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
//
//		//		dfs.setStart(userDefinedStart);
//		dfs.runDFS(userDefinedStart);
//		boolean quit = false;
//		do{
//
//			System.out.println("What node would you like to know the start and finish time of?:");
//			Scanner input = new Scanner(System.in);
//			String nodeName = input.next();
//			Node<String> questionNode = getAllNodes().get(nodeName);
//			System.out.println("Node: " + nodeName + " , Start time: " + questionNode.start + " , Finish time: " + questionNode.finish + " , Color: " + questionNode.color);
//			input.nextLine();
//			System.out.println("Would you like to quit? (y/n)");
//			if(input.next().equalsIgnoreCase("y")){
//				quit = true;
//			} 
//		} while (quit == false);
//	}
//
//
//
//
//
//
//	in.close();
//}

