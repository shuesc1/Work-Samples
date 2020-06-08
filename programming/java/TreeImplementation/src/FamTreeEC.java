import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * A class that attempts to recreate all methods in FamTree class and, when applicable, implementing methods recursively 
 * instead of iteratively and viceversa.
 * @author josephhaymaker
 *
 */
public class FamTreeEC {

	private ArrayList<GenericNode<String>> allNodes;
	private GenericNode<String> parent, child, root;
	private String aParent, aChild;
	private int count = 0;
	private Scanner in;
	public boolean result;
	private HashMap<String, Integer> nodes;

	/**
	 * The constructor for the class
	 * it initializes an arraylist to store the nodes
	 */
	public FamTreeEC(){
		allNodes = new ArrayList<>();
	}

	/**#1 (iterative -- no change from original)
	 * A method that returns a boolean denoting if a is a parent of b
	 * @param a a string name of a possible parent
	 * @param b a string name of a possible child
	 * @return a boolean indicating if a is a parent of b
	 */
	public boolean isParent(String a, String b){
		GenericNode<String> parentNode = new GenericNode<String>(null);
		if(getNode(b).parent != null){
			parentNode = getNode(b).parent;
			if(parentNode.name.equalsIgnoreCase(a)){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**#2 (iterative -- no change from original)
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

	/**#3 (now iterative, but not functional)
	 * A method to see if a is an ancestor of b
	 * @param a a string value for a possible ancestor
	 * @param b a string value of a possible descendant
	 * @return a boolean value denoting if a is an ancestor of b
	 */
	public boolean isAncestor(String a, String b){
		result = false;
		for(GenericNode<String> g : allNodes){ //search list of all nodes
			if(g.name.equalsIgnoreCase(a)){ //enter if node's name is a
				if(g.children.isEmpty() == true){ //if it is a leaf we already know result is false
					result = false;
				} else { //if node a has children ; we want to see if it has a child b, or grandchild b, or...
					while(g.children.isEmpty() == false || result == false){ //go until you hit leaves or until you find b as a descendant
						for(GenericNode<String> x : g.children){ //go over a's children
							if(x.name.equals(b)){
								result = true;
								break;
							}
						}
						if(result = false){
							g = g.children.get(1);
						}
					}
				}
			}
			break;
		}
		return result;
	}

	/**#4 (now iterative)
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

	/**#5 (iterative -- no change)
	 * a method denoting if a is a sibling of b
	 * @param a a string value name of a possible sibling
	 * @param b a string value name of a possible sibling
	 * @return a boolean value if a is a sibling of b
	 */
	public boolean isSibling(String a, String b){
		if(getNode(a).parent.name.equalsIgnoreCase(getNode(b).parent.name) ){
			return true;
		} else {
			return false;
		}
	}

	/**#6 (iterative -- no change)
	 * A method to tell if a is a cousin to b
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isCousin(String a, String b){
		if(isSibling(getNode(a).parent.name,getNode(b).parent.name) == true){
			return true;
		} else {
			return false;
		}
	}

	/**#7 (iterative -- no change)
	 * A method that tells whether a is an uncle/aunt of b
	 * @param a a string name of a possible uncle/aunt
	 * @param b a string name of a possible nephew/niece
	 * @return a boolean true is a is an uncle/aunt of b, false if not
	 */
	public boolean isUncle(String a, String b){
		if(isSibling(a,getNode(b).parent.name) == true){
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
		int size = size(root);
		System.out.println("Height:" + height + ", number of nodes:" + size + " , Root node: " + getRoot().name);
	}

	/**#8a (now iterative)	
	 *"bad implementation"--worst case O(n^2)
	 * A method to get the height of the tree
	 * @return an int height of tree
	 */
	public int getHeight(){
		int height = 0;
		for(GenericNode<String> g : root.children){
			if(isOnlyChild(g.name) == true){
				height = Math.max(height, depth(g.name));
			}
		}
		return height;
	}

	/**#8b (now recursive)
	 * A method to return the number of nodes in a tree
	 * @param root
	 * @return
	 */
	public int size(GenericNode<String> p){
		int size  = 0;
		if(root.children.isEmpty()==true){
			size = 1;
		} else {
			for(GenericNode<String> g : p.children){
				size = 1 + size(g);
			}
		}
		return size;
	}

	/**#9 (didn't have time to change to iterative)
	 * A method that traverses all nodes and prints them out according to preorder traversal(Root Node, Left, Right)
	 */
	public void preorderTraversal(GenericNode<String> s){
		if(s == null){	//bc empty
			System.out.println("Tree is empty");
		} else if(s.children.isEmpty() == true){
			System.out.print(s.name + ", ");
		} else if(s != null && s.children.isEmpty() == false){ 
			System.out.print(s.name + ", ");		
			for(GenericNode<String> g :s.children){
				preorderTraversal(g);
			}
		}
	}

	/**#10 (didn't have time to change to iterative)
	 * A method that traverses all nodes and prints them out according to postorder traversal(Left, Right, Root Node)
	 */
	public void postorderTraversal(GenericNode<String> h){
		if(h == null){	//bc empty
			System.out.println("Tree is empty");
		} else if(h != null && h.children.isEmpty() == true){
			System.out.print(h.name + ", ");
		} else if(h.children.isEmpty() == false){ //bc children are leaves
			for(GenericNode<String> g :h.children){
				postorderTraversal(g);
			}
			System.out.print(h.name + ", ");	
		}
	}

	/**#11 (iterative -- no change)
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
		for(GenericNode<String> g : allNodes){
			if(isSibling(a, g.name) == true && !a.equals(g.name)){
				result = false;
				break;
			} else {
				result = true;
			}
		}
		return result;
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
	 * A method to get the node object associated with a string name
	 * @param a a string name
	 * @return a generic node whose name matched the string
	 */
	public GenericNode<String> getNode(String a){
		GenericNode<String> keyNode = null;
		for(GenericNode<String> g : allNodes){
			if(g.name.equals(a)){
				keyNode = g;
				break;
			}
		}
		return keyNode;
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
				boolean duplicate = false;
				aParent = st.nextToken(",").trim(); //gets name which is a string
				aChild = st.nextToken().trim(); //gets name which is a string

				for(GenericNode<String> g : allNodes){ //go over existing nodes and check to see if parent already here
					if(g.name.equals(aParent)){
						parent = g;
						duplicate = true;
					}
				}
				if(duplicate == false){ //if parent node not already in list of all nodes
					parent = new GenericNode<String>(aParent);//create new node and set to 'parent'
					allNodes.add(parent); //add parent node to list of all parent nodes
				}
				child = new GenericNode<String>(aChild);
				allNodes.add(child);
				parent.children.add(child); //adds child to parent node's personal children arraylist
				child.parent = parent;

				if(count == 0){
					GenericNode<String> dummy = new GenericNode<String>("null");
					parent.parent = dummy;
					root = parent;
				}
			}
			count++;
		}
	}

	/**
	 * A method that gets the depth of the tree
	 * "depth of p is the number of ancestors of p, other than p itself", p. 314
	 * @param a a string name of a node
	 * @return depth an int of the depth of a tree
	 */
	public int depth(String a){
		int depth = 0;
		if(a.equals(root.name)){
			depth = 0;
		} else {
			depth = 1 + depth(getNode(a).parent.name);
		}
		return depth;
	}

}
