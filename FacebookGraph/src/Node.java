import java.util.ArrayList;

/**
 * This is our implementation of a Node.
 * Initially we'll use type String for the value.
 * Later we'll use generics.
 * @author swapneel
 *
 */
//public class MyGenericNode<K, V> {
//extends can mean inheritance or interfaces
//T super Number
//public class MyGenericNode<T extends Number> {
public class Node<T> {
	
	//should normally be private
	public T value;
	public ArrayList<Node<T>> adjacencies;
	public int start, finish, distance;
//	public Node<T> next;
	
	/**
	 * The constructor for node
	 * @param value the value of the node.
	 */
//	public MyGenericNode(T value1, T value2) {
	public Node(T value) {
		this.value = value;
		adjacencies = new ArrayList<>();
		start = (Integer) null;
		finish = (Integer) null;
		distance = (Integer) null;
//		next = null;
	}

}
