
public class PaternityTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		GenericNode<String> root = new GenericNode<String>("Adam");
		GenericNode<String> daughter = new GenericNode<String>("Sara");
		GenericNode<String> grandson = new GenericNode<String>("Abraham");
		
		
		root.children.add(daughter);
		daughter.children.add(grandson);
		
		FamTree<GenericNode<String>> ft = new FamTree<GenericNode<String>>();
		ft.setRoot(root);
		ft.addNode(root);
		ft.addNode(daughter);
		ft.addNode(grandson);
		daughter.parent = root;
		grandson.parent = daughter;
		
		System.out.println("*****Family tree: Adam-->Sara-->Abraham****");
		boolean result = ft.isParent("Adam", "Sara");
		System.out.println("Adam, you claim sara is your daughter. The paternity test proves that this is:\n" + result);
		
		boolean r2 = ft.isParent("Sara", "Abraham");
		System.out.println("Sara, you claim Abraham is your son. The paternity test proves that this is:\n" + r2);
		
		boolean r3 = ft.isParent("Adam", "Abraham");
		System.out.println("Adam, you claim Abraham is your son. The paternity test proves that this is:\n" + r3);
		
		boolean r4 = ft.isAncestor("Adam", "Abraham");
		System.out.println("Abraham, you claim Adam is your ancestor. The paternity test proves that this is:\n" + r4);
		
		
		System.out.println("Tree size: " + ft.size());
		System.out.println("Height: " + ft.getHeight(root));
		System.out.print("Postorder traversal: ");
		ft.postorderTraversal(root);
		System.out.print("\nPreorder traversal: ");
		ft.preorderTraversal(root);
//		int childrensize = ft.getNode("Adam").parent.children.size();
//		System.out.println("Adam has " + childrensize + " children");
	}

}
