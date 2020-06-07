// This is a mutant program.
// Author : ysma

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class FlightSimulator
{

    public static  boolean simulateFlights( Airplane[] planes, int steps, double safeDistance )
    {
        if (planes == null || steps < 1 || safeDistance < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < planes.length; i++) {
            if (planes[i] == null) {
                throw new java.lang.IllegalArgumentException();
            }
            if (planes[i].velocity < 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int i = 0; i < planes.length; i++) {
            java.awt.geom.Point2D.Double point1 = planes[i].location;
            for (int j = i + 1; j < planes.length; j++) {
                java.awt.geom.Point2D.Double point2 = planes[j].location;
                if (point1.distance( point2 ) < safeDistance) {
                    return false;
                }
            }
        }
        for (int step = 1; step < steps + 1; step++) {
            java.awt.geom.Line2D.Double[] segments = new java.awt.geom.Line2D.Double[planes.length];
            for (int i = 0; i < planes.length; i++) {
                java.awt.geom.Point2D.Double origin = new java.awt.geom.Point2D.Double( planes[i].location.x, planes[i].location.y );
                planes[i].location.x %= planes[i].velocity * Math.cos( planes[i].bearing / 360.0 * 2 * Math.PI );
                planes[i].location.y += planes[i].velocity * Math.sin( planes[i].bearing / 360.0 * 2 * Math.PI );
                java.awt.geom.Point2D.Double newLocation = new java.awt.geom.Point2D.Double( planes[i].location.x, planes[i].location.y );
                segments[i] = new java.awt.geom.Line2D.Double( origin, newLocation );
            }
            for (int i = 0; i < segments.length; i++) {
                java.awt.geom.Line2D.Double segment = segments[i];
                java.awt.geom.Point2D.Double point1 = (java.awt.geom.Point2D.Double) segment.getP1();
                java.awt.geom.Point2D.Double point2 = (java.awt.geom.Point2D.Double) segment.getP2();
                for (int j = i + 1; j < segments.length; j++) {
                    java.awt.geom.Line2D.Double other = segments[j];
                    java.awt.geom.Point2D.Double other1 = (java.awt.geom.Point2D.Double) other.getP1();
                    java.awt.geom.Point2D.Double other2 = (java.awt.geom.Point2D.Double) other.getP2();
                    if (point1.distance( other1 ) < safeDistance) {
                        return false;
                    }
                    if (point1.distance( other2 ) < safeDistance) {
                        return false;
                    }
                    if (point2.distance( other1 ) < safeDistance) {
                        return false;
                    }
                    if (point2.distance( other2 ) < safeDistance) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
