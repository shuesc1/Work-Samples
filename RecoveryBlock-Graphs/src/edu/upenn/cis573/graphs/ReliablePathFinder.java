package edu.upenn.cis573.graphs;

import java.util.ArrayList;
import java.util.List;

public class ReliablePathFinder extends PathFinder {

	private Graph g;

	public ReliablePathFinder(Graph g) {
		super(g);
		this.g = g ;
	}

	/*
	 * Implement this method using a Recovery Block and Retry Block as described
	 * in the assignment specification.
	 */
	public List<Integer> findPath(int src, int dest) throws PathNotFoundException {
		throw new PathNotFoundException();

	}

	/*
	 * Implement this acceptance test as described in the assignment specification.
	 */
	public boolean checkPath(int src, int dest, List<Integer> path) {
		boolean result = false;
		if(!path.contains(src) || !path.contains(dest)) {
			result = false ;
			return result ;
		} else if(src == dest && path.contains(src)) {
			result = true;
			return result ;
		}

		int current = 0;
		int next = 0 ;
		for (int i = 0; i < path.size()-2; i++){ 
			current = path.get(i) ;
			next = path.get(i+1) ;
			ArrayList<Integer> adjs = (ArrayList<Integer>) g.adj(current) ;
			if(adjs.contains(next)) {
				result = true ;
			} else {
				result = false ;
			}
		}
		return result;

	}
}



