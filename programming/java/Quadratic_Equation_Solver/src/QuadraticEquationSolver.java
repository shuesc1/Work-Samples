
public class QuadraticEquationSolver {

//	QuadraticEquation qe = new QuadraticEquation(a, b, c);
	
	private QuadraticEquation eq;
	
	public QuadraticEquationSolver(QuadraticEquation qe) {
		eq = qe;
		qe.hasRealSolutions(); //will automatically tell user if there is a real solution or not
	}
	
	public double getSolution1(){
		double sol1 = 0;
		double a = eq.getA();		
		double b = eq.getB();
		double c = eq.getC();
		
//		if (eq.hasRealSolutions() == false){
////			System.out.println("No real solutions can be generated");
//			break;
//		} 
//		else {

		while (eq.hasRealSolutions() == true){
			sol1 = (-b + Math.sqrt(b * b - 4 * a * c))/ (2 * a);
			break;
		}
//		while (eq.hasRealSolutions() == false){
//			break;
//		}
		return sol1; 
	}
	
	public double getSolution2(){
		double sol1 = 0;
		double a = eq.getA();		
		double b = eq.getB();
		double c = eq.getC();
		

		while (eq.hasRealSolutions() == true){
			sol1 = (-b - Math.sqrt(b * b - 4 * a * c))/ (2 * a);
			break;
		}
//		while (eq.hasRealSolutions() == false){
//			break;
//		}
		return sol1; 
	}
	
//	public void solutionPrinter(){
//		while (eq.hasRealSolutions() == true){
//		System.out.println("First solution is: " + eq.getSolution1() + " and the second solution \nis: " + eq.getSolution2());
//		}
//	}
	
	
}
