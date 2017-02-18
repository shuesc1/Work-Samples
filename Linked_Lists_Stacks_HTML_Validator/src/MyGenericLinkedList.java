/**
 * Our implementation of Linked List using MyGenericNode
 * 
 * @author swapneel
 *
 */
public class MyGenericLinkedList<T> {

	private MyGenericNode<T> head;
	private int size;

	/**
	 * The constructor Will initialize head to null
	 */
	public MyGenericLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * This method will create a new node. It will add it as the first node of
	 * the linked list.
	 * 
	 * @param value
	 *            the value to be added.
	 */
	public void addFirst(T value) {
		MyGenericNode<T> node = new MyGenericNode<T>(value);
		node.next = head;
		head = node;

		size++;
	}
	
	/**
	 * This method will delete the first node of the linked list.
	 */
	public void deleteFirst() {
		if (size != 0) {
			head = head.next;
			size--;
		}
	}
	
	/**
	 * This method will delete the last node of the linked list.
	 */
	public void deleteLast() {
		if (size >= 2) {
			MyGenericNode<T> i = head;
			
			while (i.next != null && i.next.next != null) {
				i = i.next;
			}
			
			i.next = null;
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
			MyGenericNode<T> node = new MyGenericNode<T>(value);
			
			MyGenericNode<T> i = head;
			
			while (i.next != null) {
				i = i.next;
			}
			
			i.next = node;
			
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

			MyGenericNode<T> i = head;

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
