package edu.upenn.cis573.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import com.google.common.collect.Lists;
//import org.apache.commons.collections.IteratorUtils;

import com.google.common.collect.Lists;


public class ReliablePathFinder extends PathFinder {

	private Graph g;
	private PathFinder pf ;
	List<Integer> bfsList = new ArrayList<Integer>() ;
	List<Integer> dfsList = new ArrayList<Integer>() ;
	List<Integer> validPath = new ArrayList<Integer>() ;
	boolean dfsOutcome = false ;
	boolean bfsOutcome = false ;

	public ReliablePathFinder(Graph g) {
		super(g);
		this.g = g ;
		pf = new PathFinder2(g);
		//		pf = new PathFinder2(g);
	}

	/*
	 * Implement this method using a Recovery Block and Retry Block as described
	 * in the assignment specification.
	 */
	public List<Integer> findPath(int src, int dest) throws PathNotFoundException {
		//================ Parallel recovery block ======================
		// run concurrently -- first block tries DFS, then if fails switches to BFS as per lecture (FaultyBubbleSort->FaultyMergeSort, etc.)
		Thread dfsThread = new Thread () {
			public void run () {
				//1. run dfs
				dfsList = pf.dfs(src, dest) ;
				//check acceptance test -- if true return list
				dfsOutcome = checkPath(src, dest, dfsList) ;
				if(dfsOutcome) {
					validPath = dfsList ;
//				}
//				} else {
//					//if false try bfs
//					bfsList = pf.bfs(src, dest) ;
//					bfsOutcome = checkPath(src, dest, bfsList) ;
//					if(bfsOutcome) {
//						validPath = bfsList ;
//					}
				}
			}
		};
		// This thread tries BFS. It will either pass acceptance test or wait for other thread to check DFS then BFS 
		// Trying to cut down on overhead of doing this by only having 1 thread trying both DFS & BFS
		Thread bfsThread = new Thread () {
			public void run () {
				//run bfs
				bfsList = pf.bfs(src, dest) ;
				//check acceptance test -- if true return list
				bfsOutcome = checkPath(src, dest, bfsList) ;
				if(bfsOutcome) {
					validPath = bfsList ;
				}
//				} else {
//					//if false try DFS
//					dfsList = pf.dfs(src, dest) ;
//					dfsOutcome = checkPath(src,dest, dfsList) ;
//					if(dfsOutcome) {
//						validPath = dfsList ;
//					}
//				}
			}
		};
		dfsThread.start();
//		bfsThread.start();
//		try {
//			bfsThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		try {
//			dfsThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		//======== RETRY BLOCK =========
		// if both DFS-BFS/BFS fail (fail acceptance test below), try and use DFS, then run acceptance test again
		// if acceptance test passes, you need to reverse list of indices that returned from dfs
		if(!bfsOutcome && !dfsOutcome) {
			dfsList = dfs(dest, src) ;
			if(this.checkPath(dest, src, dfsList)) {
				for (int k = dfsList.size()-1; k >= 0; k--) {
					int x = dfsList.get(k) ;
					validPath.add(x) ;
				}
			} else {
				throw new PathNotFoundException();
			}
		}
		return validPath;
	}

	/**
	 * Implement this <<ACCEPTANCE TEST>> as described in the assignment specification.
	 * A method that takes in a source vertex in a graph, a destination index in a graph, and a path from source and destination to verify
	 * @param src an integer representing the index of the source vertex in the undirected graph
	 * @param dest an integer representing the index of the destination vertex in the undirected graph
	 * @param path a list 
	 * @return
	 */
	public boolean checkPath(int src, int dest, List<Integer> path) {
		boolean result = false;
		if(!path.contains(src) || !path.contains(dest)) { // if src or destination are not in list we can verify false directly
			result = false ;
			return result ;
		} else if(src == dest && path.contains(src)) { //if source and destination are the same and they are in the list we can verify true directly
			result = true;
			return result ;
		}

		// otherwise get current vertex and next vertex, then check if next is in current's adjacency list
		int current = 0;
		int next = 0 ;
		for (int i = 0; i < path.size()-2; i++){ 
			current = path.get(i) ; //get current vertex in path
			next = path.get(i+1) ; // get next vertex in path

			// Used Guava library to create an adjacency ArrayList from an iterable
			// << http://www.java2s.com/Code/Jar/g/Downloadguavajar.htm >>
			ArrayList<Integer> adjs = Lists.newArrayList(g.adj(current)) ;
			if(adjs.contains(next)) { //if the next vertex in path is in adj list of the current vertex
				result = true ;
			} else {
				result = false ;
			}
		}
		return result;
	}
}



