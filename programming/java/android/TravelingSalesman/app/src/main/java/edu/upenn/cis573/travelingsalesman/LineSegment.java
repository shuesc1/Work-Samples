package edu.upenn.cis573.travelingsalesman;

import android.graphics.Point;

/**
 * A class that creates an object representing a linesegment
 */
public class LineSegment {

    private Point startPoint, finishPoint;
    public boolean visited;

    /**
     * The constructor for the class that instantiates variables
     */
    public LineSegment() {
        startPoint = new Point();
        finishPoint = new Point();
        visited = false ;
    }

    /**
     * A getter method for the linesegment's starting Point
     *
     * @return a Point where the linesegment started
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * A setter method for the linesegment's starting Point
     *
     * @param startPoint
     */
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * A getter method for the linesegment's finishing Point
     *
     * @return
     */
    public Point getFinishPoint() {
        return finishPoint;
    }

    /**
     * A setter method for the linesegment's finishing Point
     *
     * @param finishPoint
     */
    public void setFinishPoint(Point finishPoint) {
        this.finishPoint = finishPoint;
    }

    /**
     * A method that calculates the Euclidean distance between two points
     *
     * @return a double representing the distance between the starting and ending point of the line segment
     */
    public double calculateDistance() {
        double dx = finishPoint.x - startPoint.x;
        double dy = finishPoint.y - startPoint.y;
        double distance = Math.sqrt(Math.abs(dx * dx + dy * dy));

        return distance;
    }
}
