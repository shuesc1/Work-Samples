/**
 * This is our implementation of a Node.
 * Initially we'll use type String for the value.
 * Later we'll use generics.
 * @author swapneel
 *
 */
public class MyGenericNode<T> {

	public T value, startTime, endTime;
	
	/**
	 * The constructor for node
	 * @param value the value of the node.
	 */
	public MyGenericNode(T value) {
		this.value = value;
		startTime = null;
		endTime = null;
	}

}
