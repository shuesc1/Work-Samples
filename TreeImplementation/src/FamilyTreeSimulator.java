import java.io.FileNotFoundException;
import java.util.Scanner;

public class FamilyTreeSimulator {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		boolean result;
		FamTree ft = new FamTree();
		String filename = "family_tree.txt";
		ft.parseMembers(filename);
//		System.out.println("Size:" + ft.size());
//		System.out.println("Height: " + ft.getHeight());
		
		System.out.println("**********FAMILY TREE SIMULATOR*********");
		System.out.println("1a). Is Andrew the parent of Kevin?"); //#1
		result = ft.isParent("Andrew", "Kevin");
		System.out.println("Answer: " + result);
		
		System.out.println("1b). Is Brandon the parent of Tom?"); //#1
		result = ft.isParent("Brandon", "Tom");
		System.out.println("Answer: " + result);
		
		System.out.println("2a). Is Will the child of Adam?"); //#2
		result = ft.isChild("Will", "Adam");
		System.out.println("Answer: " + result);

		System.out.println("2b). Is Adam the child of Elisa?"); //#2
		result = ft.isChild("Adam", "Elisa");
		System.out.println("Answer: " + result);
		
//		System.out.println("3a). Is Will the ancestor of Steve?"); //#3
//		System.out.println("Answer: " + ft.isAncestor("Will", "Steve"));
		
//		System.out.println("3b). Is Sally the ancestor of Will?");  //#3
//		System.out.println("Answer: " + ft.isAncestor("Sally", "Will"));
//		
//		System.out.println("4a). Is Kevin the descendant of Adam?"); //#4
//		System.out.println("Answer: " + ft.isDescendant("Kevin", "Adam"));
		
		System.out.println("5a). Is Stacy the sibling of Lucy?"); //#5
		System.out.println("Answer: " + ft.isSibling("Stacy", "Lucy"));
		
		System.out.println("5b). Is Theodore the sibling of Tom?"); //#5
		System.out.println("Answer: " + ft.isSibling("Theodore", "Tom"));
		
		System.out.println("6a). Is Mary a cousin to Kevin?"); //#6
		System.out.println("Answer: " + ft.isCousin("Mary", "Kevin"));
		
		System.out.println("6b). Is Camilla a cousin to Sally?"); //#6
		System.out.println("Answer: " + ft.isCousin("Camilla", "Sally"));
		
		System.out.println("7a). Is Kevin an uncle to Lucy?"); //#7
		System.out.println("Answer: " + ft.isUncle("Kevin", "Lucy"));
		
		System.out.println("7b). Is Mary an aunt to Sally?"); //#7
		System.out.println("Answer: " + ft.isUncle("Mary", "Sally"));
		
		System.out.println("DISPLAY STATISTICS:"); //#8
		ft.displayStatistics();
		
		System.out.println("PREORDER TRAVERSAL:"); //#9
		ft.preorderTraversal();
//		
		System.out.println("\nPOSTORDER TRAVERSAL:"); //#10
		ft.postorderTraversal();
		
		System.out.println("11a). Is Mary a girl?"); //#11
		System.out.println("Answer: " + ft.isFemale("Mary"));
		
		System.out.println("11b). Is Theodore a girl?"); //#11
		System.out.println("Answer: " + ft.isFemale("Kevin"));
		
		System.out.println("12a). Is Steve an only child?"); //#12
		System.out.println("Answer: " + ft.isOnlyChild("Steve"));
		
		System.out.println("12b). Is Elisa an only child?"); //#12
		System.out.println("Answer: " + ft.isOnlyChild("Elisa"));
	}

}
