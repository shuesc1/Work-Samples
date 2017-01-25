
public class QuadraticEquation {

	private double a;
	private double b;
	private double c;
	
	public QuadraticEquation(double inputA, double inputB, double inputC){
		a = inputA;
		b = inputB;
		c = inputC;
	}
	
	public double getA(){
		return a;
	}
	
	public double getB(){
		return b;
	}
	
	public double getC(){
		return c;
	}
	
	public boolean hasRealSolutions(){
//		if ((getB() * getB()) - (4 * getA() * getC()) >= 0) {
//			System.out.println("There is a real solution!");
//			return true;
//		} 
		if ((getB() * getB()) - (4 * getA() * getC()) < 0) {
			System.out.println("Sorry, no real solution exists. Please enter new values.");
			return false;
		} 
		return true;
	}
		
	
}
