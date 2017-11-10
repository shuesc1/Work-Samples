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
		Thread dfsThread = new Thread () {
			public void run () {
				//1. run dfs
				dfsList = pf.dfs(src, dest) ;
				//check acceptance test -- if true return list
				dfsOutcome = checkPath(src, dest, dfsList) ;
				if(dfsOutcome) {
					validPath = dfsList ;
				} else {
					//if false try bfs
					bfsList = pf.bfs(src, dest) ;
					bfsOutcome = checkPath(src, dest, bfsList) ;
					if(bfsOutcome) {
						validPath = bfsList ;
					}
				}
			}
		};
		Thread bfsThread = new Thread () {
			public void run () {
				//run bfs
				bfsList = pf.bfs(src, dest) ;
				//check acceptance test -- if true return list
				bfsOutcome = checkPath(src, dest, bfsList) ;
				if(bfsOutcome) {
					validPath = bfsList ;
				} 

			}
		};
		dfsThread.start();
		bfsThread.start();
		try {
			bfsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			dfsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//======== RETRY BLOCK =========
		// if both DFS/BFS fail (fail acceptance test), try and use BFS or DFS, then run acceptance test again
		// if acceptance test passes, you need to reverse what returned from bfs/dfs
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

			//			Iterator<Element> myIterator = ... //some iterator
			//					List<Element> myList = Lists.newArrayList(myIterator);

			//			Iterator<Element> myIterator = ...//some iterator
			//			List<Element> myList = IteratorUtils.toList(myIterator);   

			// Used Guava library to create an ArrayList from an iterable
			ArrayList<Integer> adjs = Lists.newArrayList(g.adj(current)) ;
			//			ArrayList<Integer> adjs = (ArrayList<Integer>) g.adj(current) ;
			//			for(Integer index : g.adj(current)) {
			if(adjs.contains(next)) {
				result = true ;
			} else {
				result = false ;
			}
			//			}
		}
		return result;

	}
}



