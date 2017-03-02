import java.util.Iterator;

public interface Tree<T>  extends Iterable<T>{
	GenericNode<T> root();
	GenericNode<T> parent(GenericNode<T> n) throws IllegalArgumentException;
	Iterable<GenericNode<T>> children(GenericNode<T> n) throws IllegalArgumentException;
	int numChildren(GenericNode<T> n) throws IllegalArgumentException;
	boolean isInternal(GenericNode<T> n) throws IllegalArgumentException;
	boolean isExternal(GenericNode<T> n) throws IllegalArgumentException;
	boolean isRoot(GenericNode<T> n) throws IllegalArgumentException;
	int size();
	boolean isEmpty();
	//	Iterator<T> iterator;
	Iterable<GenericNode<T>> positions();
}
