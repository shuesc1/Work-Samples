import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FamTree<T> {

	private ArrayList<GenericNode<String>> allNodes;
	private GenericNode<String> parent, child, root;
	private String aParent, aChild;
	private int count = 0;
	private Scanner in;
	private boolean result;
	HashMap<String, Integer> nodes;

	/**
	 * The constructor for the class
	 * it initializes an arraylist to store the nodes
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
				aParent = st.nextToken(",").trim(); //gets name which is a string
				aChild = st.nextToken().trim(); //gets name which is a string

				parent = new GenericNode<String>(aParent);
				allNodes.add(parent); //add parent node to list of all parent nodes
				child = new GenericNode<String>(aChild);
				allNodes.add(child);
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
	public int size(){
		nodes = new HashMap<>();
		for(GenericNode<String> n : allNodes){
			nodes.put(n.name, n.children.size());
		}
		int size = nodes.size();
		return size;
	}

	/**#1
	 * A method that returns a boolean denoting if a is a parent of b
	 * @param a a string name of a possible parent
	 * @param b a string name of a possible child
	 * @return a boolean indicating if a is a parent of b
	 */
	public boolean isParent(String a, String b){
		GenericNode<String> parentNode = new GenericNode<String>(null);
		parentNode = getParent(b);
		if(parentNode.name.equalsIgnoreCase(a)){
			return true;
		} else {
			return false;
		}
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
		if(getParent(a).name.equalsIgnoreCase(getParent(b).name) ){
			//		if(getParent(a) == getParent(b)){
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
		if(isSibling(getParent(a).name,getParent(b).name) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#7
	 * A method that tells whether a is an uncle/aunt of b
	 * @param a a string name of a possible uncle/aunt
	 * @param b a string name of a possible nephew/niece
	 * @return a boolean true is a is an uncle/aunt of b, false if not
	 */
	public boolean isUncle(String a, String b){
		if(isSibling(a,getParent(b).name) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#8
	 * A method that prints out the height, size, and name of the root node
	 */
	public void displayStatistics(){
		int height = getHeight();
		int size = size();
		System.out.println("Height:" + height + ", number of nodes:" + size + " , Root node: " + getRoot().name);
	}

	/**#9
	 * A method that traverses all nodes and prints them out according to preorder traversal(Root Node, Left, Right)
	 */
	public void preorderTraversal(){
		if(root == null){	//bc empty
			System.out.println("Tree is empty");
		} else if(root.children.size() != 0){ //bc children are leaves
			System.out.println(root.name);	
			for(GenericNode<String> g :root.children){
				System.out.print(g.name + ", ");
			}
		} else if(root.children.get(1).children.size() != 0){ //case of children having children
			for(GenericNode<String> g :root.children){
				preorderTraversal();
			}
		}

	}

	/**#10
	 * A method that traverses all nodes and prints them out according to postorder traversal(Left, Right, Root Node)
	 */
	public void postorderTraversal(){
		if(root == null){	//bc empty
			System.out.println("Tree is empty");
		} else if(root.children.size() != 0){ //bc children are leaves
			for(GenericNode<String> g :root.children){
				System.out.print(g.name + ", ");
			}
			System.out.println(root.name);	
		} else if(root.children.get(1).children.size() != 0){ //case of children having children
			//			System.out.println(postorderTraversal(g.left) + "," + postorderTraversal(g.right) + "," + g.name + ","); //theoretical implementation
			for(GenericNode<String> g :root.children){
				postorderTraversal();
			}
		}
	}

	/**#11
	 * A method that uses census data to tell whether a family member is female or not
	 * @param a a string name
	 * @return a boolean true if female, false if male
	 * @throws FileNotFoundException
	 */
	public boolean isFemale(String a) throws FileNotFoundException{
		result = false;
		File file = new File("census_female_names.csv");
		Scanner in = new Scanner(file);
		while(in.hasNext()){
			String name = in.next();
			if(name.equalsIgnoreCase(a)){
				result = true;
				break;
			} else {
				result = false;
			}
		}
		in.close();
		return result;
	}

	/**#12
	 * A method that tells is a person is an only child or not
	 * @param a a string name
	 * @return a boolean true if person is only child, false otherwise
	 */
	public boolean isOnlyChild(String a){
		if(getParent(a).children.size() == 1){
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * A method to get the height of the tree
	 * @return an int height of tree
	 */
	public int getHeight(){
		int height = 0;
		if(root == null){
			height = 0;
		}else if (root != null && root.children.size() == 0){
			height = 1; 
		} else if (root.children.size() != 0){
			height = 2;
			for(GenericNode<String> g : root.children){
				while(g.children.size() != 0){
					height = 1 + height;
					g = g.children.get(0);
				}
			}
		}
		return height;
	}

	/**
	 * A method to add nodes to the list of all nodes (for testing purposes)
	 * @param g a node object
	 */
	public void addNode(GenericNode<String> g){
		allNodes.add(g);
	}

	/**
	 * A getter method for setting the root 
	 * @return the root node
	 */
	public GenericNode<String> getRoot(){
		return root;
	}

	/**
	 * A setter method for setting the root (for testing)
	 * @param g
	 */
	public void setRoot(GenericNode<String> g){
		root = g;
	}

	/**
	 * A getter method for a node's parent
	 * @param a string name of a child
	 * @return parent a parent node
	 */
	public GenericNode<String> getParent(String a){
		for(GenericNode<String> gn : allNodes){
			for(GenericNode<String> n : gn.children){
				if(n.name.equalsIgnoreCase(a)){
					parent = gn;
				}
			}
		}
		return parent;
	}


}
