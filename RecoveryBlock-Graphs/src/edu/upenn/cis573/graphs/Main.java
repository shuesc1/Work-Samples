package edu.upenn.cis573.graphs;

import java.util.Random;

public class Main {
	
	private static Random rand = new Random(System.currentTimeMillis());
	
	public static void main(String[] args) {
	
		// basically a Scanner
		In in = new In("graph.txt");
	
		// construct a Graph from the input file
		System.out.println("Building the graph, please wait a moment...");
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
