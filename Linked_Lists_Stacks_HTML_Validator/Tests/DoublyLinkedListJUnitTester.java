import static org.junit.Assert.*;

import javax.swing.text.html.parser.Element;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListJUnitTester {

	DoublyLinkedList<String> linkedList1;
	DoublyLinkedList<Integer> linkedList2;
	
	@Before
	public void setUp() throws Exception {
		DoublyLinkedNode<String> node1A = new DoublyLinkedNode<String>("Hi");
		linkedList1 = new DoublyLinkedList<String>();
		linkedList1.addFirst("A");
		linkedList1.addFirst("Am");
		linkedList1.addFirst("I");
		linkedList1.addLast("Linked");
		linkedList1.addLast("List");
		
		DoublyLinkedNode<Integer> node1B = new DoublyLinkedNode<Integer>(4);
		linkedList2 = new DoublyLinkedList<Integer>();
		linkedList2.addFirst(3);
		linkedList2.addFirst(2);
		linkedList2.addFirst(1);
		linkedList2.addLast(5);
		linkedList2.addLast(6);
	}

	//#1
	@Test
	public void testAdd() {
		assertTrue("Should be able to append value",linkedList2.add(5));
	}
	
	//#2
	@Test
	public void testAddAtIndex(){
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		Integer h = 1;
		Integer g = 2;
		
		ll.addFirst(h);
		ll.addFirst(g);
		Integer i = 11;
		Integer j = 0;
		ll.add(j, i);
		assertEquals("First node should now be 'beginning'", ll.getHead(), i);
	}
	
	//#3
	@Test
	public void testClear(){
		linkedList2.clear();
		assertNull("Should delete all elements", linkedList2.getHead());
	}
	
	//#4
	@Test
	public void testIsEmpty() {
		assertFalse("List has elements, should be false",linkedList1.isEmpty());
	}
	
	//#5
	@Test
	public void testRemove(){
		Integer i = 1;
		linkedList2.remove(i);
		assertNotSame("First value shouldn't be 1", linkedList2, 1);
	}
	
	//#6
	@Test
	public void testIndexRemove(){
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		Integer j = 2;
		ll.addFirst(j);
		ll.addFirst(null);
		
		Integer i = 0;
		ll.remove(i);
		assertNotNull("First value shouldn't be 1", ll);
	}
	
	//#7
	@Test
	public void testSize(){
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
//		ll.addFirst(1);
//		ll.addLast(3);
		assertEquals("Size should be 0", 0, ll.size());
	}
	
	@Test
	public void testDeleteLast(){
		linkedList1.deleteLast();
		assertNotEquals("node should be deleted", "list", linkedList1.getTail());
	}
}
