import java.util.ArrayList;

public class GenericNode<T> {

	public T name;
	public GenericNode<T> parent;
	public ArrayList<GenericNode<T>> children;
	
	public GenericNode(T name){
		this.name = name;
		parent = null;
		children = new ArrayList<>();
	}
	
}
