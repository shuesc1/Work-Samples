package edu.upenn.cis573.travelingsalesman;

import android.graphics.Point;

/**
 * Created by josephhaymaker on 9/27/17.
 */
public class LineSegment {


    private Point startPoint, finishPoint;

    public LineSegment() {
        startPoint = new Point();
        finishPoint = new Point();
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(Point finishPoint) {
        this.finishPoint = finishPoint;
    }

    public double calculateDistance(){
        double dx = finishPoint.x - startPoint.x;
        double dy = finishPoint.y - startPoint.y;
        double distance = Math.sqrt(Math.abs(dx * dx + dy * dy));

        return distance ;
    }
}
