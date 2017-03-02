import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FamTree<T> {

	private ArrayList<GenericNode<T>> allNodes;
	private GenericNode<String> parent, child, root;
	private String aParent;
	private String aChild;
	int count = 0;
	private Scanner in;

	/**
	 * The constructor for the class
	 * it initializes arraylists to store the nodes
	 */
	public FamTree(){
		allNodes = new ArrayList<>();
	}

	/**
	 * A method to take in a file representing family members and store them as nodes
	 * @param filename a .txt file with family relationships
	 * @throws FileNotFoundException
	 */
	public void parseMembers(String filename) throws FileNotFoundException{
		File file = new File(filename);
		in = new Scanner(file);
		in.useDelimiter(",");
		//while there are more lines in .txt file of family members..
		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			while(st.hasMoreTokens()){
				aParent = st.nextToken(","); //gets name which is a string
				aChild = st.nextToken(); //gets name which is a string

				parent = new GenericNode<String>(aParent);
				allNodes.add((GenericNode<T>) parent); //add parent node to list of all parent nodes
				child = new GenericNode<String>(aChild);
				parent.children.add(child); //adds child to parent node's personal children arraylist
				if(count == 0){
					root = parent;
				}
			}
			count++;
		}
	}

	/**
	 * A method to return the number of nodes in a tree
	 * @param root
	 * @return
	 */
	public int size(GenericNode<T> root){
		int size = 0;
		if(root == null){
			size = 0;
		}else {
			for(int i = 0; i < root.children.size() ; i++){
				size = 1 + size(root.children.get(i));
			}
		}
		return size;
	}

	/**#1
	 * A method that returns a boolean denoting if a is a parent of b
	 * @param a a string name of a possible parent
	 * @param b a string name of a possible child
	 * @return a boolean indicating if a is a parent of b
	 */
	public boolean isParent(String a, String b){
		if(root.name.equals(a)){
			for(GenericNode n : root.children){
				if(n.name.equals(b)){
					return true;
				} else {
					return false;
				}
			}
		}

//		if(isChild(b, a) == true){
//			return true;
//		} else {
//			return false;
//		}
	}

	/**#2
	 * A method to tell if a is a child of b
	 * @param a a string name of a possible child
	 * @param b a string name of a possible parent
	 * @return a boolean value denoting if a is a child of b
	 */
	public boolean isChild(String a, String b){
		if(isParent(b, a) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#3
	 * A method to see if a is an ancestor of b
	 * @param a a string value for a possible ancestor
	 * @param b a string value of a possible descendant
	 * @return a boolean value denoting if a is an ancestor of b
	 */
	public boolean isAncestor(String a, String b){
		if(isDescendant(b,a) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#4
	 * A method telling if a is a descendant of b
	 * @param a a string value of a possible descendant 
	 * @param b a string value o a possible ancestor
	 * @return a boolean if a is a descendant of b
	 */
	public boolean isDescendant(String a, String b){
		if(isAncestor(b,a) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#5
	 * a method denoting if a is a sibling of b
	 * @param a a string value name of a possible sibling
	 * @param b a string value name of a possible sibling
	 * @return a boolean value if a is a sibling of b
	 */
	public boolean isSibling(String a, String b){
		if(isSibling(b,a) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#6
	 * A method to tell if a is a cousin to b
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isCousin(String a, String b){
		if(isCousin(b,a) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#7
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isUncle(String a, String b){
		if(isCousin(a.getLeftChild, b) == true || isCousin(a.getRightChild, b) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#8
	 * 
	 */
	public void displayStatistics(){
		int height = getHeight();
		int size = size((GenericNode<T>) parent);
		GenericNode<T> root = getRoot();
		System.out.println("Height:" + height + ", number of nodes:" + size + " , Root node:" + root.name);
	}

	/**#9
	 * 
	 */
	public void preorderTraversal(GenericNode<T> g){
		//Preorder (Root, Left, Right) : 1 2 4 5 3
		if(g == null){
			System.out.println("Tree is empty");
		} else if(g != null){
			System.out.println(g.name + "," + preorderTraversal(g.left) + "," + preorderTraversal(g.right) + ",");
		}

	}

	/**#10
	 * 
	 */
	public void postorderTraversal(GenericNode<T> g){
		//Postorder (Left, Right, Root) : 4 5 2 3 1
		if(g == null){
			System.out.println("Tree is empty");
		} else if(g != null){
			System.out.println(preorderTraversal(g.left) + "," + preorderTraversal(g.right) + "," + g.name + ",");
		}
	}



	public GenericNode<T> getLeftChild(GenericNode<T> parent){
		child = (GenericNode<String>) parent.left;
		return (GenericNode<T>) child;
	}

	public GenericNode<T> getRightChild(GenericNode<T> parent){
		child = (GenericNode<String>) parent.right;
		return (GenericNode<T>) child;
	}

	//	public void addChild(GenericNode child) {
	//        child.setParent(this);
	//        this.children.add(child);
	//    }
	//
	//    public void addChild(String name) {
	//    	GenericNode<T> newChild = new GenericNode<>(name);
	//        newChild.setParent(parent);
	//        children.add(newChild);
	//    }
	//
	//    public void addChildren(ArrayList<GenericNode> children) {
	//        for(GenericNode t : children) {
	//            t.setParent(parent);
	//        }
	//        this.children.addAll(children);
	//    }
	//
	//    public List<GenericNode> getChildren() {
	//        return children;
	//    }
	//
	//    public T getData() {
	//        return data;
	//    }
	//
	//    public void setData(T data) {
	//        this.data = data;
	//    }
	//
	private void setParent(GenericNode parent) {
		this.parent = parent;
	}

	public GenericNode getParent() {
		return parent;
	}


}
