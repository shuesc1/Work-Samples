
public class MyGenericNode<T> {

	public T name;
	public MyGenericNode<T> left, right, parent, sibling;
	
	
	public MyGenericNode(T name){
		this.name = name;
		left = null;
		right = null;
		parent = null;
		sibling = null;
	}
	
}
