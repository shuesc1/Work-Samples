/**
 * An implementation of a doubly linked node
 * @author haymakej
 *
 */
//public class MyGenericNode<K, V> {
//extends can mean inheritance or interfaces
//T super Number
//public class MyGenericNode<T extends Number> {
public class DoublyLinkedNode<T> {
	
	public T value;
	public DoublyLinkedNode<T> next, prev;
//	public DoublyLinkedNode<T> prev;
	
	/**
	 * The constructor for node, prev & next both point to null
	 * @param value the value of the node.
	 */
	public DoublyLinkedNode(T value) {
		this.value = value;
		next = null;
		prev = null;
	}

}
