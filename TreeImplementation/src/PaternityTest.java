
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
		
		boolean result = ft.isParent("Adam", "Sara");
		System.out.println("You claim sara is your daughter. The paternity test proves that this is:\n" + result);
		
		boolean r2 = ft.isParent("Sara", "Abraham");
		System.out.println("You claim Abraham is your son. The paternity test proves that this is:\n" + r2);
		
		boolean r3 = ft.isParent("Adam", "Abraham");
		System.out.println("You claim Abraham is your son. The paternity test proves that this is:\n" + r3);
	}

}
