import java.util.Scanner;


public class QuadraticEquationTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		System.out.println("Please enter values for a, b, and c");
		System.out.println("Value a: ");
		double userA = in.nextDouble();
		System.out.println("Value b: ");
		double userB = in.nextDouble();
		System.out.println("Value c: ");
		double userC = in.nextDouble();
		
		QuadraticEquation qe = new QuadraticEquation(userA, userB, userC);
		QuadraticEquationSolver qes = new QuadraticEquationSolver(qe);
		System.out.println("First solution is: " + qes.getSolution1() + " and the second solution is: " + qes.getSolution2());
//		qes.solutionPrinter();
	}

}
