import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of basic graph using only primitive arrays!
 */
public class MyIterableAdjacencyMatrix implements IterableGraph {
	private HashMap<Node<String>, ArrayList<String>> adjacencies;
	private HashMap<String, ArrayList<String>> seenList;

	//TODO Very likely some instance variables are needed

	public MyIterableAdjacencyMatrix() {
		matrix = new HashMap<Node<String>, ArrayList<String>>();
	}

	public void addEdge(int v, int w) {
		if (!matrix.containsKey(v)) {
			matrix.put(v, new ArrayList<>());
		} 

		matrix.get(v).add(w);

		if (!matrix.containsKey(w)) {
			matrix.put(w, new ArrayList<int>());
		} 

		matrix.get(w).add(v);
	}


	/**
	 * @param v vertex
	 * @return all vertices with an edge to/from v
	 */
	@Override
	public Iterable<String> adjacentTo(String v) {
		Iterable<String> iterable  = matrix.get(v);
		return iterable;
	}

	/**
	 * @return all the vertices in the graph
	 */
	@Override
	public Iterable<String> vertices() {
		ArrayList<String> result = new ArrayList<String>();
		for (String key : matrix.keySet()) {
			result.add(key);
		}   	
		return result;
	}


	/**
	 * This iterator will be returned by the adjacentTo method.
	 * Helpful operation for most graph traversals.
	 * Allows for this type of usage:
	 * e.g. for (String vertex : G.adjacentTo("myVertice")) {...}
	 *      for (String vertex : G.vertices()) {...}
	 */
	private class MyVerticesIterator implements Iterator<String> {

		private String[] arr;
		private int iteration;

		private MyVerticesIterator(String[] arr) {
			this.arr = arr;
			iteration = 0;
		}

		/**
		 * Returns true if the iteration has more elements.
		 * (In other words, returns true if next() would return an element rather than throwing an exception.)
		 *
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return iteration != arr.length;
		}

		/**
		 * Returns the next element in the iteration.
		 * the next element in the iteration
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */

		@Override
		public String next() throws NoSuchElementException {
			if (hasNext()) {
				String ans = arr[iteration];
				iteration++;
				return ans;
			} else {
				return null;
			}
		}

		/**
		 * Removes from the underlying collection the last element returned by this iterator (optional operation).
		 *
		 * @throws UnsupportedOperationException if the remove operation is not supported by this iterator
		 */
		@Override
		public void remove() throws UnsupportedOperationException {
			String[] newArr = new String[arr.length - 1];
			for (int i = 0; i < arr.length - 1; i++) {
				newArr[i] = arr[i];
			}
			arr = newArr;
		}

	}

}
