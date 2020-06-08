import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of basic graph using only primitive arrays!
 */
public class MyIterableAdjacencyMatrix implements IterableGraph {
	private ArrayList<ArrayList<String>> matrix;



    @Override
    public void addEdge(String v, String w) {
            /*
             * //TODO Implement this!
             */
    }


    /**
     * @param v vertex
     * @return all vertices with an edge to/from v
     */
    @Override
    public Iterable<String> adjacentTo(String v) {

            /*
             * //TODO Implement this!
             */
        return null;
    }

    /**
     * @return all the vertices in the graph
     */
    @Override
    public Iterable<String> vertices() {

        /*
         * //TODO Implement this!
         */
        return null;
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

        private MyVerticesIterator(String[] arr) {
            this.arr = arr;
        }

        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next() would return an element rather than throwing an exception.)
         *
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {

            /*
             * //TODO Implement this!
             */

            return false;
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

            /*
             * //TODO Implement this!
             */

            return null;
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator (optional operation).
         *
         * @throws UnsupportedOperationException if the remove operation is not supported by this iterator
         */
        @Override
        public void remove() throws UnsupportedOperationException {

            /*
             * //TODO Implement this!
             */
        }

    }

}
