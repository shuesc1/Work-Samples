
public class StackTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyStack<String> stack1 = new MyStack<String>();
		MyStack<Integer> stack2 = new MyStack<Integer>();
		
		stack1.push("hi");
		String value = stack1.pop();
		System.out.println("just deleted value:" + value + " , element on top of stack is now:" + stack1.peek());
	}

}
