public interface IterableGraph {

    public void addEdge(String v, String w);

    public Iterable<String> adjacentTo(String v);

    public Iterable<String> vertices();

}

/* Javadoc for GraphPrinter

Prints graph to stdout
public void printGraph(InterableGraph g);

*/
