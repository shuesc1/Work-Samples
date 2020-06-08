import java.util.ArrayList;

/**
 * An implementation of a node that holds values used in BFS & DFS
 * @author josephhaymaker
 *
 * @param <T>
 */
public class Node<T> {
	
	public T value, color;
	Node<String> predecessor;
	public int start, finish, distance;
	
	/**
	 * The constructor for node
	 * @param value the value of the node.
	 */
	public Node(T value) {
		this.value = value;
		start = 0;
		finish = 0;
		distance = 0;
		color = null;
		predecessor = null;
	}

}
