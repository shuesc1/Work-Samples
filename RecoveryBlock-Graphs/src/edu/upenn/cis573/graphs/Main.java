package edu.upenn.cis573.graphs;

import java.util.Random;

public class Main {
	
	private static Random rand = new Random(System.currentTimeMillis());
	
	public static void main(String[] args) {
		// basically a Scanner
		In in = new In("graph.txt");
	
		// construct a Graph from the input file
		System.out.println("Building the graph, please wait a moment...");
		long start = System.currentTimeMillis();
		Graph G = new Graph(in);
		System.out.println("Done building the graph.");

		ReliablePathFinder finder = new ReliablePathFinder(G);
		
		// evaluate the reliability of ReliablePathFinder.findPath using 1000 trials
		int success = 0, fail = 0;
		System.out.println("Running the trials, please wait a moment...");
		for (int i = 0; i < 1000; i++)
			if (runTrial(finder)) success++;
			else fail++;	        	
		System.out.println("Success: " + success + "; Fail: " + fail);
		long end = System.currentTimeMillis();
		double timeInSeconds = (end - start) / (double)1000;
		System.out.println("Execution time (wall clock): " + timeInSeconds + " seconds");
		
		//trial1 - Full method (thread1 DFS & BFS, thread2 BFS & DFS): 287.343 seconds
		//trial2 - Full method: 293.772 seconds
		//trial3 - Full method: 295.529 seconds
		//trial4 - thread1 DFS & BFS, thread2 just BFS: 251.335 seconds (*** winner for sake of due diligence & accuracy***)
		//trial5 - thread1 just DFS, thread2 just BFS: 265.265 seconds
		//trial6 - (same): 261.433 seconds
		//trial7 - running just thread1 DFS then DFS retry block: 32.575 seconds (1000 successes)
		//trial8 - running just thread2 BFS then DFS retry block: 51.65 seconds (1000 successes)
		//trial9 - running just thread2 BFS then BFS retry block: 59.253 seconds (**Success: 997; Fail: 3)
		//trial10- running just thread1 DFS then BFS retry block: 32.917 seconds (**Success: 999; Fail: 1) 
	}	
	   
	private static boolean runTrial(ReliablePathFinder finder) {
		// randomly generate source and destination vertices
		int src = rand.nextInt(10000); 
		int dest = rand.nextInt(10000); 

		try {
			finder.findPath(src, dest);
			// if it returns, then it must have passed the acceptance test
			return true;
		}
		catch (PathNotFoundException e) {
			// if it throws an exception, it must have failed to find a path
			return false;
	    }
	        	        
	}
	
}
