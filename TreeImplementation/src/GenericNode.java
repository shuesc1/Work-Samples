import java.util.ArrayList;

public class GenericNode<T> {

	public T name;
	public GenericNode<T> child;
	public ArrayList<GenericNode<T>> children;
//	public GenericNode<T> left, right;

	
	public GenericNode(T name){
		this.name = name;
		child = null;
		children = new ArrayList<>();
//		left = null;
//		right = null;
	}
	
}
