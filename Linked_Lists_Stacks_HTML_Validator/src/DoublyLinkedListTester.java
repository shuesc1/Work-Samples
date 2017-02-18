
public class DoublyLinkedListTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DoublyLinkedNode<String> node = new DoublyLinkedNode<String>("SFO");
		DoublyLinkedNode<Integer> node2 = new DoublyLinkedNode<Integer>(3);
		
//		node.next = node2;
		
		DoublyLinkedList<String> linkedList = new DoublyLinkedList<String>();
		
		linkedList.display();
		
		linkedList.addFirst("EWR");
		linkedList.addFirst("BOS");
		linkedList.addFirst("MIA");
		linkedList.addLast("LGA");
		
		linkedList.display();
//		
//		linkedList.deleteFirst();
//		linkedList.deleteLast();
//		linkedList.deleteLast();
//		
//		linkedList.display();
//		
//		MyGenericLinkedList<Integer> integerLinkedList = new MyGenericLinkedList<>();
//		
//		integerLinkedList.display();
//		integerLinkedList.addFirst(3);
//		integerLinkedList.addFirst(10);
//		integerLinkedList.addFirst(100);
//		integerLinkedList.addLast(5);
//		
//		integerLinkedList.display();
		
	}

}
