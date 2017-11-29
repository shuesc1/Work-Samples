 import java.awt.geom.Point2D;

public class Airplane {
	
	protected Point2D.Double location;
	protected double bearing; 
	protected double velocity;
	
	public Airplane(double x, double y, double b, double v) {
		location = new Point2D.Double(x, y);
		bearing = b;
		velocity = v;
	} 
		

}
