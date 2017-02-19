import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyStackTester {

	MyStack<String> stack1;
	MyStack<Integer> stack2;
	
	@Before
	public void setUp() throws Exception {
		stack1 = new MyStack<>();
		stack2 = new MyStack<>();
	}

	@Test
	public void testEmpty() {
		assertTrue(stack1.empty());
	}
	
	@Test
	public void testPeek() {
		stack1.push("item");
		assertEquals("Should show us item", "item", stack1.peek());
	}
	
	@Test
	public void testPop() {
		Integer i = 2;
		Integer j = 3;
		stack2.push(i);
		stack2.push(j);
		stack2.pop();
		assertEquals("Popped off top element, leaving 2", i, stack2.peek());
	}
	
	@Test
	public void testPush() {
		DoublyLinkedList<String> dll = stack1.getDoublylinkedlist();
		dll.add("value1");
		stack1.push("push");
		assertFalse("There should be an element here now", stack1.empty());
	}

}
