import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FamTree<T> {

	private ArrayList<GenericNode<String>> allNodes;
	private GenericNode<String> parent, child, root;
	private String aParent;
	private String aChild;
	private int count = 0;
	private Scanner in;
	private boolean result;

	/**
	 * The constructor for the class
	 * it initializes arraylists to store the nodes
	 */
	public FamTree(){
		allNodes = new ArrayList<>();
	}

	public void addNode(GenericNode<String> g){
		allNodes.add(g);
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
		int size = 0;
		if(root == null){
			size = 0;
		}else if (root.children.size() == 0){
			size = 1 + root.children.size(); 
		} else {
			size = 1 + size();
			//			for(int i = 0; i < root.children.size() ; i++){
			//				size = 1 + size(root.children.get(i));
			//			}
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
		//		for(GenericNode<String> g : allNodes)
		//			if((g.name).equalsIgnoreCase(a)){
		GenericNode<String> start = new GenericNode<String>(null);
		start = root;
		int v = 0;

		for(GenericNode<String> gn : allNodes){
			if(gn.name.equalsIgnoreCase(a) && gn.children.toString().contains(b)){
//				if(start.name.equalsIgnoreCase(a) && start.children.toString().contains(b)){
					result = true;
				} else {
					result = false;
				}
			}
		
		
		
		
//		if(start.children.size() == 0){
//			result = false;
//		} else if(start.name.equalsIgnoreCase(a) && start.children.toString().contains(b)){
//			result = true;
//		} else {
//			isParent(a,b);
//		}
		
//		if((start.name).equalsIgnoreCase(a)){
//			for(GenericNode<String> n : root.children){
//				if((n.name).equalsIgnoreCase(b)){
//					result = true;
//				} else {
//					result =  false;
//					root = n;
//					isParent(a, b);
//				}
//			}
//		} else {
//			//			for(int i = 0; i < start.children.size(); i++){
//			start = start.children.get(v);
//			v++;
////			isParent(a,b);
//		}
		return result;
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
	public void preorderTraversal(){
		//Preorder (Root, Left, Right) : 1 2 4 5 3
		if(root == null){
			System.out.println("Tree is empty");
		} else if(root != null){
			System.out.println(g.name + "," + preorderTraversal(g.left) + "," + preorderTraversal(g.right) + ",");
		}

	}

	/**#10
	 * 
	 */
	public void postorderTraversal(){
		//Postorder (Left, Right, Root) : 4 5 2 3 1
		if(root == null){
			System.out.println("Tree is empty");
		} else if(root != null){
			System.out.println(preorderTraversal(g.left) + "," + preorderTraversal(g.right) + "," + g.name + ",");
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
		for(GenericNode<String> n : allNodes){
			if(!n.name.equals(a) && isSibling((String) n.name, a) == true){
				result = false;
			} else {
				result = true;
			}
		}
		return result;
	}
}
