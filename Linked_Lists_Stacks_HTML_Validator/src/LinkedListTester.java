/**
 * This will test our generic node and linked list.
 * @author swapneel
 *
 */
public class LinkedListTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyGenericNode<String> node = new MyGenericNode<String>("PHL");
		MyGenericNode<Integer> node2 = new MyGenericNode<Integer>(3);
		
//		node.next = node2;
		
		MyGenericLinkedList<String> linkedList = new MyGenericLinkedList<String>();
		
		linkedList.display();
		
		linkedList.addFirst("PHL");
		linkedList.addFirst("LAX");
		linkedList.addFirst("LHR");
		linkedList.addLast("BOM");
		
		linkedList.display();
		
		linkedList.deleteFirst();
		linkedList.deleteLast();
		linkedList.deleteLast();
		
		linkedList.display();
		
		MyGenericLinkedList<Integer> integerLinkedList = new MyGenericLinkedList<>();
		
		integerLinkedList.display();
		integerLinkedList.addFirst(3);
		integerLinkedList.addFirst(10);
		integerLinkedList.addFirst(100);
		integerLinkedList.addLast(5);
		
		integerLinkedList.display();
		
		MyGenericLinkedList<? extends Number> anotherLinkedList = new MyGenericLinkedList<>();
		
	}

}
