import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FamilyTreeSimulatorEC {

	public static void main(String[] args) throws FileNotFoundException {
	
		boolean result;
		GenericNode<String> root = new GenericNode<String>(null);
		FamTreeEC ft = new FamTreeEC();
		String filename = "family_tree.txt";
		ft.parseMembers(filename);
		root = ft.getRoot();
		System.out.println("Size:" + ft.size(root));
		System.out.println("Height: " + ft.getHeight());

		System.out.println("**********FAMILY TREE SIMULATOR*********");
		System.out.println("1a). Is Andrew the parent of Kevin? (yes)"); //#1
		result = ft.isParent("Andrew", "Kevin");
		System.out.println("Answer: " + result);

		System.out.println("1b). Is Brandon the parent of Tom? (no)"); //#1
		result = ft.isParent("Brandon", "Tom");
		System.out.println("Answer: " + result);

		System.out.println("2a). Is Will the child of Adam? (yes)"); //#2
		result = ft.isChild("Will", "Adam");
		System.out.println("Answer: " + result);

		System.out.println("2b). Is Mary the child of Sally? (no)"); //#2
		result = ft.isChild("Mary", "Sally");
		System.out.println("Answer: " + result);

		System.out.println("3a). Is Will the ancestor of Theodore? (yes)"); //#3
		System.out.println("Answer: " + ft.isAncestor("Will", "Theodore"));

//		System.out.println("4a). Is Kevin the descendant of Adam? (yes)"); //#4
//		System.out.println("Answer: " + ft.isDescendant("Kevin", "Adam"));

		System.out.println("5a). Is Stacy the sibling of Lucy? (yes)"); //#5
		System.out.println("Answer: " + ft.isSibling("Stacy", "Lucy"));

		System.out.println("5b). Is Theodore the sibling of Tom? (no)"); //#5
		System.out.println("Answer: " + ft.isSibling("Theodore", "Tom"));

		System.out.println("6a). Is Mary a cousin to Kevin? (yes)"); //#6
		System.out.println("Answer: " + ft.isCousin("Mary", "Kevin"));

		System.out.println("6b). Is Camilla a cousin to Sally? (no)"); //#6
		System.out.println("Answer: " + ft.isCousin("Camilla", "Sally"));

		System.out.println("7a). Is Kevin an uncle to Lucy? (yes)"); //#7
		System.out.println("Answer: " + ft.isUncle("Kevin", "Lucy"));

		System.out.println("7b). Is Mary an aunt to Sally? (no)"); //#7
		System.out.println("Answer: " + ft.isUncle("Mary", "Sally"));

		System.out.println("DISPLAY STATISTICS:"); //#8
		ft.displayStatistics();

		System.out.println("PREORDER TRAVERSAL:"); //#9
		ft.preorderTraversal(root);

		System.out.println("\nPOSTORDER TRAVERSAL:"); //#10
		ft.postorderTraversal(root);

		System.out.println("\n11a). Is Mary a girl? (yes)"); //#11
		System.out.println("Answer: " + ft.isFemale("Mary"));

		System.out.println("11b). Is Theodore a girl? (no)"); //#11
		System.out.println("Answer: " + ft.isFemale("Kevin"));

		System.out.println("12a). Is Steve an only child? (yes)"); //#12
		System.out.println("Answer: " + ft.isOnlyChild("Steve"));

		System.out.println("12b). Is Elisa an only child? (no)"); //#12
		System.out.println("Answer: " + ft.isOnlyChild("Elisa"));

		//		System.out.println("root children toString: " + root.children.toString());
		int childrensize = ft.getNode("Elisa").parent.children.size();
		System.out.println("Elisa has " + childrensize + " children (should be 2)");

		int c2 = ft.getNode("Sarah").parent.children.size();
		System.out.println("Sarah has " + c2 + " children (should be 2)");

		int c3 = ft.getNode("Mary").parent.children.size();
		System.out.println("Mary has " + c3 + " children (should be 1)");

		int c4 = ft.getNode("Camilla").parent.children.size();
		System.out.println("Camilla has " + c4 + " children (should be 0)");
//
//		ArrayList<GenericNode<String>> list = new ArrayList<GenericNode<String>>();
//		list = ft.getNode("Camilla").children;
//		for(GenericNode<String> g : list){
//			System.out.println(g.name + ",");
//		}
	}
	
}
