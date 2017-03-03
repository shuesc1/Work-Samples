import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GeneralTesting {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		ArrayList<GenericNode<String>> allNodes = new ArrayList<>();
		GenericNode<String> parent, child, root;
		String aParent = null;
		String aChild = null;
		//		File file = new File("Bold_Tree.txt");
		File file = new File("family_tree.txt");
		Scanner in = new Scanner(file);
		in.useDelimiter(",");
		int count = 0;
		int counter = 1;


		//while there are more lines in .txt file of family members..
		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			while(st.hasMoreTokens()){
				aParent = st.nextToken(",").trim(); //gets name which is a string
				System.out.print("Parent:" + aParent + ", ");
				aChild = st.nextToken().trim(); //gets name which is a string
				System.out.println("Child:" + aChild);
				
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
		for(GenericNode<String> n : allNodes){
			System.out.println("Node number " + counter + ",name : " + n.name);
				for(GenericNode<String> x : n.children){
					System.out.println(n.name + "'s child:" + x.name);
				}
			counter++;
		}
}
	
}
