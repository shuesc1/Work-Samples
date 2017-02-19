/**
 * A class that uses a doubly linked list to implement a stack 
 * @author josephhaymaker
 *
 * @param <T>
 */
public class MyStack<T> {

	private DoublyLinkedList<T> dll;
//	private DoublyLinkedNode<T> itemNode;

	/**
	 * The constructor for the class
	 */
	public MyStack(){
		dll = new DoublyLinkedList<T>();
	}

	
	public DoublyLinkedList<T> getDoublylinkedlist() {
		return dll;
	}


	public void setDoublylinkedlist(DoublyLinkedList<T> dll) {
		this.dll = dll;
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
//	public <E> void push(E item){
//		dll.add((T) item);
//	}
	public void push(T item){
		dll.add(item);
	}

}
