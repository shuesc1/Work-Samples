package edu.upenn.cis573.graphs;

import java.util.ArrayList;
import java.util.List;

public class tester {

	public static void main(String[] args) {
		In in = new In("testG.txt");
		
		System.out.println("Building the graph, please wait a moment...");
		Graph G = new Graph(in);
		System.out.println("Done building the graph.");

		ReliablePathFinder finder = new ReliablePathFinder(G);
		int src = 1 ;
		int dest = 7 ;
		List<Integer> path = new ArrayList<Integer>() ;
		path.add(1) ;
		path.add(4) ;
		path.add(5) ;
		path.add(3) ;
		path.add(6) ;
		path.add(7) ;
		
		boolean outcome = finder.checkPath(src, dest, path) ;
		System.out.println(outcome) ;
	}

}
