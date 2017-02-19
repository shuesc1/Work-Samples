/**
 * A class that uses a doubly linked list to implement a stack 
 * @author josephhaymaker
 *
 * @param <T>
 */
public class MyStack<T> {

	private DoublyLinkedList<T> dll;

	/**
	 * The constructor for the class
	 * initializes the doubly linked list
	 */
	public MyStack(){
		dll = new DoublyLinkedList<T>();
	}

	/**#1
	 * A method for checking if the stack is empty
	 * @return a boolean true if stack is empty
	 */
	public boolean empty(){
		boolean empty = dll.isEmpty();
		return empty;
	}

	/**#2
	 * a method to see the object at the top of the stack
	 * @return 
	 * @return a doubly linked list node at the top of the stack
	 */
	public T peek(){
		if(dll.isEmpty() == true){
			return null;
		} else {
			DoublyLinkedNode<T> topOfStack = dll.getTail();
			T value = topOfStack.value;
			return value;
		}
	}

	/**#3
	 * A method that deletes the last element on the stack
	 * @return
	 */
	public T pop(){
		if(dll.isEmpty() == true){
			return null;
		} else {
			DoublyLinkedNode<T> last = dll.getTail();
			T lastValue = last.value;
			dll.deleteLast();
			return lastValue;
		}
	}

	/**#4
	 * A method that puts an item onto the top of the stack
	 * @param item
	 */
	public void push(T item){
		dll.add(item);
	}

	/**
	 * A getter method for the linked list
	 * @return the doubly linked list
	 */
	public DoublyLinkedList<T> getDoublylinkedlist() {
		return dll;
	}

	/**
	 * A setter method for the doubly linked list
	 * @param dll
	 */
	public void setDoublylinkedlist(DoublyLinkedList<T> dll) {
		this.dll = dll;
	}

	/**
	 * A method for getting the size of the stack
	 * @return an int of the size
	 */
	public int size(){
		int stackSize = dll.size();
		return stackSize;
	}
	
}
