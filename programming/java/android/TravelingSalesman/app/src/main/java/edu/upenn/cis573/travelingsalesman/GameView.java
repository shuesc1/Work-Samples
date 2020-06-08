package edu.upenn.cis573.travelingsalesman;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import java.util.*;


public class GameView extends View {

    protected boolean isValidStroke = false;
    private Stroke stroke; //step 4
    protected Segments segments; //step 5
    private Point firstPoint;
    protected Point[] mapPoints;
    protected int spinnerNum;
    public int numLocations;
    protected int attempt = 0;
    protected static final Point[] mapPositions;
    private Context context;

    // These points are all hardcoded to fit the UPenn campus map on a Nexus 5
    static {
        mapPositions = new Point[13];
        mapPositions[0] = new Point(475, 134);
        mapPositions[1] = new Point(141, 271);
        mapPositions[2] = new Point(272, 518);
        mapPositions[3] = new Point(509, 636);
        mapPositions[4] = new Point(1324, 402);
        mapPositions[5] = new Point(1452, 243);
        mapPositions[6] = new Point(1667, 253);
        mapPositions[7] = new Point(750, 670);
        mapPositions[8] = new Point(1020, 380);
        mapPositions[9] = new Point(870, 250);
        mapPositions[10] = new Point(540, 477);
        mapPositions[11] = new Point(828, 424);
        mapPositions[12] = new Point(1427, 66);
    }

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }


    public static double calculatePathDistance(ArrayList<Point> points) {

        double total = 0;
        // get the distance between the intermediate points
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            double dist = distance(p1, p2); // step 6
            total += dist;
        }
        // then need to go back to the beginning
        Point p1 = points.get(points.size() - 1);
        Point p2 = points.get(0);
        double dist = distance(p1, p2); // step 6
        total += dist;

        return total;
    }

    /**
     * A method that sets the background resource png, creates a log entry and initializes variables
     */
    protected void init() {
//        spinnerNum = MainActivity.numLocations ; //step 1
        setBackgroundResource(R.drawable.campus); //use and set the capmus.png resource as background
        Log.v("GAME VIEW", "init"); //creates a log with the tag "GAME VIEW", and msg "init"
        stroke = new Stroke();
        isValidStroke = stroke.getValidStroke();
        segments = new Segments();
    }

    /**
     * A helper method to establish the initial location points to be drawn based on the number specified by the user
     * Method is called once when view is first rendered ; mapPositions values all hardcoded above
     */
    public void drawInitialPoints() {
        mapPoints = new Point[spinnerNum]; //creates a new array of Point objects of size[user's spinner number choice]
        Set set = new HashSet(); //creates new hashset to store randomNum variables
        Random rn = new Random(); //creates a new Random object

        for (int i = 0; i < spinnerNum; i++) { //iterates up to num of locations chosen
            int randomNum = rn.nextInt(mapPositions.length); //gets random int of value 0 to (mapPositions.length -1)
            while (set.contains(randomNum)) { //while set contains this new random int
                randomNum = rn.nextInt(mapPositions.length); //reset the value until the random int is a new number
            }
            set.add(randomNum); //then add random int to set
            mapPoints[i] = mapPositions[randomNum]; // sets random Point object in mapPositions to mapPoints obj at index i
        }
    }

    /*
     * This method is automatically invoked when the View is displayed.
     * It is also called after you call "invalidate" on this object.
     */
    protected void onDraw(Canvas canvas) {
        //================== user still drawing stroke ========================================
        // draws the stroke in yellow while still drawing/making a stroke
        if (isValidStroke) {
            if (stroke.strokePoints.size() > 1) { //step 3
                for (int i = 0; i < stroke.strokePoints.size() - 1; i++) {
                    int x1 = stroke.strokePoints.get(i).x;
                    int y1 = stroke.strokePoints.get(i).y;
                    int x2 = stroke.strokePoints.get(i + 1).x;
                    int y2 = stroke.strokePoints.get(i + 1).y; //step 3
                    stroke.setColorAndWidth(Color.YELLOW, 10); //step 4
                    canvas.drawLine(x1, y1, x2, y2, stroke.getPaint());
                }
            }
        }
        //===================== rendering in red valid stroke ================================
        // draws the line segments in red when finished
        for (int i = 0; i < segments.lineSegments.size(); i++) { //step 5
            LineSegment ls = segments.lineSegments.get(i); //step 5
            stroke.setColorAndWidth(Color.RED, 10); //step 4
            canvas.drawLine(ls.getStartPoint().x, ls.getStartPoint().y, ls.getFinishPoint().x, ls.getFinishPoint().y, stroke.getPaint());
        }
        // draws the points on the map
        stroke.setColorAndWidth(Color.RED, 10); //step 4
        for (int i = 0; i < mapPoints.length; i++) {
            int x = mapPoints[i].x;
            int y = mapPoints[i].y;
            canvas.drawRect(x, y, x + 20, y + 20, stroke.getPaint());
        }

        //========================= examine user input when # segments == # points==================
        // detects whether the segments form a circuit && proper Hamiltonian cycle
        boolean isCircuit = false;
//        boolean hasHamCycle = false ;
        isCircuit = segments.detectCircuit();
//        hasHamCycle = segments.existsHamPath();
//        Toast.makeText(getContext(), "Has Ham Cycle:" + hasHamCycle, Toast.LENGTH_LONG).show();

        // see if user has solved the problem ==> if there are no more points to connect AND there exists a circuit
        if ((segments.lineSegments.size() == mapPoints.length) && isCircuit) {
            ArrayList<Point> shortestPath = ShortestPath.shortestPath(mapPoints);
            double shortestPathLength = calculatePathDistance(shortestPath);
            double myPathLength = 0;

            //get the length of the current path as to be able to compare it to the shortest path
            for (LineSegment ls : segments.lineSegments) { //step 5
//                double dist = ls.calculateDistance(); //step 5
                double dist = distance(ls.getFinishPoint(), ls.getStartPoint()) ; //step 6
                myPathLength += dist; //step 5
            }
            Log.v("RESULT", "Shortest path length is " + shortestPathLength + "; my path is " + myPathLength);

            //compare shortest path and the one at hand--if they are the same win message displays
            double diff = shortestPathLength - myPathLength;
            //======================user drew correct answer/circuit================================
            if (Math.abs(diff) < 0.01) {
                Toast.makeText(getContext(), "You found the shortest path!", Toast.LENGTH_LONG).show();
                attempt = 0;
            } else { //if you made a CIRCUIT but DIDN't find the shortest calculated path
                // ======================user drew circuit but not shortest=======================
                if (attempt < 3) {
                    int offset = (int) (Math.abs(diff) / shortestPathLength * 100);
                    // so that we don't say that the path is 0% too long
                    if (offset == 0) {
                        offset = 1;
                    }
//                    if(hasHamCycle) {
                        Toast.makeText(getContext(), "Nope, not quite! Your path is about " + offset + "% too long.", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getContext(), "Nope, this isn't a proper Hamiltonian cycle!.", Toast.LENGTH_LONG).show();
//                    }
                    attempt++; //message displays only on 'incorrect' attempts, so makes sense to put counter right after
                }
                //================3rd attempt - visualize solution in yellow========================
                if (attempt >= 3) {
                    // draw the solution in yellow after 3 incorrect attempts
                    for (int i = 0; i < shortestPath.size() - 1; i++) {
                        Point a = shortestPath.get(i);
                        Point b = shortestPath.get(i + 1);
                        stroke.setColorAndWidth(Color.YELLOW, 10);
                        canvas.drawLine(a.x + 10, a.y + 10, b.x + 10, b.y + 10, stroke.getPaint());
                    }
                    Point a = shortestPath.get(shortestPath.size() - 1);
                    Point b = shortestPath.get(0);
                    stroke.setColorAndWidth(Color.YELLOW, 10);
                    canvas.drawLine(a.x + 10, a.y + 10, b.x + 10, b.y + 10, stroke.getPaint());
                    Toast.makeText(getContext(), "Here's the solution.", Toast.LENGTH_LONG).show();
                    invalidate();
                }
            }
            // ===============================user input not a circuit==================================
        } else if (segments.lineSegments.size() == mapPoints.length && !isCircuit) {
            Toast.makeText(getContext(), "That's not a circuit! Select Clear from the menu and start over", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * A method used to pass information from activity to activity via Bundle/Intent
     *
     * @param data
     */
    public void setData(Bundle data) {
        Bundle bundle = data;
    }

    /*
     * This method is automatically called when the user touches the screen.
     */
    public boolean onTouchEvent(MotionEvent event) {
        Point p = new Point();
        p.x = ((int) event.getX());
        p.y = ((int) event.getY());
        //==================================== DOWN touch event ====================================
        if (event.getAction() == MotionEvent.ACTION_DOWN) { //action_down == initial touch event
            // only add the segment if the touch point is within 30 of any of the other points
            for (int i = 0; i < mapPoints.length; i++) {

                double dist = distance(p, mapPoints[i]); // step 6
                if (dist < 30) {
                    // the "+10" part is a bit of a fudge factor because the point itself is the
                    // upper-left corner of the little red box but we want the center
                    p.x = mapPoints[i].x + 10;
                    p.y = mapPoints[i].y + 10;
                    stroke.strokePoints.add(p); //step 3
                    firstPoint = p;
                    isValidStroke = true; //within reasonable limit (30 pixels) to a point on the map
                    invalidate();
                    break;
                }
            }
            //==================================== MOVE touch event ====================================
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //action_move-- after action_down && BEFORE action_up
            if (isValidStroke) {
                stroke.strokePoints.add(p); //step 3
                invalidate();
            }
            //==================================== UP touch event ====================================
        } else if (event.getAction() == MotionEvent.ACTION_UP) { //ends sequence of action_down, action_move and action_up
            if (isValidStroke) {
                stroke.strokePoints.clear(); //step 3
                // only add the segment if the release point is within 30 of any of the other points
                for (int i = 0; i < mapPoints.length; i++) {
                    double dist = distance(p, mapPoints[i]) ; // step 6

                    if (dist < 30) {
                        p.x = mapPoints[i].x + 10;
                        p.y = mapPoints[i].y + 10;
                        Point lastPoint = p;
                        LineSegment thisSeg = new LineSegment();
                        thisSeg.setStartPoint(firstPoint);
                        thisSeg.setFinishPoint(lastPoint);
                        //adds 2 Point objects - start point and end point (nodes) to 'segments'
                        if (firstPoint.x != lastPoint.x && firstPoint.y != lastPoint.y) {
                            segments.lineSegments.add(thisSeg);
                        }
                        invalidate();
                        break;
                    }
                }
            }
            isValidStroke = false;
        } else {
            return false;
        }
        return true;
    }

    /**
     * A setter method for the user defined spinner number/number of locations
     *
     * @param spinnerNum
     */
    public void setSpinnerNum(int spinnerNum) {
        this.spinnerNum = spinnerNum;
    }

    /**
     * A helper method used to calculate distance
     *
     * @param end   Point object end of a line segment
     * @param start Point object beginning of a line segment
     * @return a double of the distance between the two points
     */
    public static double distance(Point end, Point start) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        return dist;
    }

}
