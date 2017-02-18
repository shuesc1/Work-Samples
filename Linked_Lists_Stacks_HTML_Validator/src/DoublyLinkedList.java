/**
 * Our implementation of Linked List using MyGenericNode
 * 
 * @author swapneel
 *
 */
public class DoublyLinkedList<T> {

	private DoublyLinkedNode<T> head;
	private int size;

	
	
	public DoublyLinkedNode<T> getHead() {
		return head;
	}

	/**
	 * The constructor Will initialize head to null
	 */
	public DoublyLinkedList() {
		head = null;
		size = 0;
	}

	/** #1
	 * A method to add a value to the end of the list
	 * @param value the value of the node to be added
	 * @return a boolean true if it was successful
	 * @throws NullPointerException
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws IllegalArgumentException
	 */
	public boolean add(T value) throws NullPointerException, UnsupportedOperationException, ClassCastException,IllegalArgumentException{
		DoublyLinkedNode<T> tail = new DoublyLinkedNode<T>(value);
		DoublyLinkedNode<T> dummy = head;

		if(head.value == null){
			head = tail;
		}

		while(dummy.next != null){
			dummy = dummy.next;
		}
		//		tail = head;
		dummy.next = tail;
		tail.prev = dummy;

		return true;
	}

	/** #2
	 * A method that adds an element at a specific index
	 * @param index
	 * @param element
	 * @throws UnsupportedOperationException
	 * @throws ClassCastException
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int index, T element) throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException, IndexOutOfBoundsException{
		DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>((T) element);
		DoublyLinkedNode<T> currentNode = head;

		int targetIndex = index - 1;
		int indexTracker = 0;

		while(indexTracker != targetIndex && currentNode.next != null){ //advance until we reach target index
			currentNode = currentNode.next;
			indexTracker++;
		}
		//break out when we are at index to insert node
		//step 1: prev node now points to newNode
		if(currentNode.prev != null){
			currentNode.prev.next = newNode; //prev node now points to new node
		}
		//step 2: newNode points to currentNode
		newNode.next = currentNode;

		//step 3:newNode prev points to prev node
		newNode.prev = currentNode.prev;

		//step 4:currentNode.prev points to newNode
		currentNode.prev = newNode;
	}

	/** #3
	 * A method that removes all elements from a list
	 */
	public void clear(){
		if(head != null){
			while(head.next != null){
				head.value = null;
				head = head.next;
			}
		}
	}

	/** #4
	 * A method to test if a linked list is empty or not
	 * @return a boolean true if the linked list is empty, false if not
	 */
	public boolean isEmpty(){
		if(head == null){
			return true;
		} else {
			return false;
		}
	}

	/** #5
	 * 
	 * @param index
	 */
	public void remove(int index) throws UnsupportedOperationException, IndexOutOfBoundsException{
		DoublyLinkedNode<T> currentNode = head;

		int targetIndex = index - 1;
		int indexTracker = 0;

		while(indexTracker != targetIndex){ //advance until we reach target index
			currentNode = currentNode.next;
			indexTracker++;
		}
		currentNode.prev.next = currentNode.next; //prev node points to next node
		currentNode.next.prev = currentNode.prev; //next node's prev points to prev node
		currentNode = null; //set current node to null
	}

	/**
	 * A method that removes the first instance of an object if present
	 * @param o the object to be removed
	 * @return true if the object was found and removed
	 */
	public boolean remove(Object o){
		DoublyLinkedNode<T> currentNode = head;
		int index = 0;

		while(currentNode != o && currentNode.next != null){
			currentNode = currentNode.next;
			index++;
		}

		if(currentNode == o){
			remove(index);
			return true;
		} else {
			return false;
		}
	}


	/** #7
	 * A method for getting the size of the linked list
	 * @return size an int of the number of nodes in the linked list
	 */
	public int size(){
		if(head == null){
			size = 0;
		} else {
			while(head.next != null){
				head = head.next;
				size++;
			}
		}
		return size;
	}



	/**
	 * This method will create a new node. It will add it as the first node of
	 * the linked list.
	 * 
	 * @param value
	 *            the value to be added.
	 */
	public void addFirst(T value) {
		DoublyLinkedNode<T> node = new DoublyLinkedNode<T>(value);

		if(size == 0){
			head = node;
		} else {
			head.prev = node;
			node.next = head;
			//		node.next.prev = node;
			//		node.prev = null;
			head = node;
		}
		size++;
	}

	/**
	 * This method will delete the first node of the linked list.
	 */
	public void deleteFirst() {
		if (size != 0) {
			head = head.next;
			head.prev = null;
			size--;
		}
	}

	/**
	 * This method will delete the last node of the linked list.
	 */
	public void deleteLast() {
		if (size >= 2) {
			DoublyLinkedNode<T> i = head;

			while (i.next != null && i.next.next != null) {
				i = i.next; //i gets redefined until 2nd to last node
			}

			i.next.value = null;
			i.next = null; //last node set to null
			size--;
		} else {
			deleteFirst();
		}
	}

	/**
	 * This method will create a new node.
	 * It will add it as the last node of the linked list.
	 * @param value the value to be added
	 */
	public void addLast(T value) {
		if (size == 0) {
			addFirst(value);
		} else {
			DoublyLinkedNode<T> node = new DoublyLinkedNode<T>(value);

			DoublyLinkedNode<T> i = head;

			while (i.next != null) {
				i = i.next; //go until i equals last node
			}

			i.next = node;
			node.prev = i;

			size++;
		}
	}

	/**
	 * This method displays the contents of the linked list.
	 */
	public void display() {

		if (size == 0) {
			System.out.println("List is empty");
		} else {

			DoublyLinkedNode<T> i = head;

			System.out.println("Printing out the contents:");

			while (i.next != null) {
				// while(i != null) {
				// print node
				// head = head.next;
				System.out.print(i.value + " ---> ");
				i = i.next;
			}

			System.out.print(i.value + "\n");
		}
	}

}
