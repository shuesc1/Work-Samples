import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class FlightSimulator {
	
	public static boolean simulateFlights(Airplane[] planes, int steps, double safeDistance) {
		
		if (planes == null || steps < 1 || safeDistance < 0) 
			throw new IllegalArgumentException();
		
		for (int i = 0; i < planes.length; i++) {
			if (planes[i] == null) throw new IllegalArgumentException();
			if (planes[i].velocity < 0) throw new IllegalArgumentException();
		}
		
		// first, make sure they're all a safe distance apart before simulating
		for (int i = 0; i < planes.length; i++) {
			Point2D.Double point1 = planes[i].location;
			for (int j = i+1; j < planes.length; j++) {
				Point2D.Double point2 = planes[j].location;
				if (point1.distance(point2) < safeDistance)
					return false;
			}
		}
		
		// now step through the simulation
		for (int step = 1; step < steps+1; step++) {
			Line2D.Double[] segments = new Line2D.Double[planes.length];
			
			// update the line segment for each airplane's path
			for (int i = 0; i < planes.length; i++) {
				Point2D.Double origin = new Point2D.Double(planes[i].location.x, planes[i].location.y);
				planes[i].location.x += planes[i].velocity * Math.cos(planes[i].bearing/360.0 * 2 * Math.PI);
				planes[i].location.y += planes[i].velocity * Math.sin(planes[i].bearing/360.0 * 2 * Math.PI);
				Point2D.Double newLocation = new Point2D.Double(planes[i].location.x, planes[i].location.y);
				segments[i] = new Line2D.Double(origin, newLocation);
			}
			
			// iterate over all the segments to check the distance to each other segment
			for (int i = 0; i < segments.length; i++) {
				Line2D.Double segment = segments[i];
				Point2D.Double point1 = (Point2D.Double) segment.getP1();
				Point2D.Double point2 = (Point2D.Double) segment.getP2();
				for (int j = i+1; j < segments.length; j++) {
					Line2D.Double other = segments[j];
					// compare the four endpoints
					Point2D.Double other1 = (Point2D.Double) other.getP1();
					Point2D.Double other2 = (Point2D.Double) other.getP2();
					if (point1.distance(other1) < safeDistance) return false;
					if (point1.distance(other2) < safeDistance) return false;
					if (point2.distance(other1) < safeDistance) return false;
					if (point2.distance(other2) < safeDistance) return false;
				}
			}

			
		}
		
		return true;
	}


}
