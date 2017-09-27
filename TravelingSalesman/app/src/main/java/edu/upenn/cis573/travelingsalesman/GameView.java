package edu.upenn.cis573.travelingsalesman;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.Toast;
import java.util.*;


public class GameView extends View {

//    protected ArrayList<Point> strokePoints = new ArrayList<Point>() ; //Step 3
    protected boolean isValidStroke = false;
    private Stroke stroke; //step 4

//    protected ArrayList<Point[]> segments = new ArrayList<Point[]>(); //step 5
//    protected ArrayList<Segments> segments = new ArrayList<Point[]>(); //step 5

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
        mapPositions[7] = new Point(750,  670);
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
        for (int i = 0; i < points.size()-1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i+1);
            double dx = p1.x - p2.x;
            double dy = p1.y - p2.y;
            double dist = Math.sqrt(dx*dx + dy*dy);
            total += dist;
        }

        // then need to go back to the beginning
        Point p1 = points.get(points.size()-1);
        Point p2 = points.get(0);
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        double dist = Math.sqrt(dx*dx + dy*dy);
        total += dist;

        return total;
    }

    /**
     * A method that sets the background resource png and populates mapPoints (array of Point objs) randomly with all locations
     * mapPositions values all hardcoded above
     */
    protected void init() {
//        spinnerNum = MainActivity.numLocations ; //step 1
        setBackgroundResource(R.drawable.campus); //use and set the capmus.png resource as background
        Log.v("GAME VIEW", "init"); //creates a log with the tag "GAME VIEW", and msg "init"
        stroke = new Stroke();
        isValidStroke = stroke.getValidStroke() ;
    }

    /**
     * A helper method to establish the initial location points to be drawn based on the number specified by the user
     * Method is called once when view is first rendered
     */
    public void drawInitialPoints(){
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
//        Paint paint = new Paint(); //becomes fully encapsulated in step 4 (Stroke class)

        // draws the stroke in yellow while still drawing
        if (isValidStroke) {
            if (stroke.strokePoints.size() > 1) { //step 3
                for (int i = 0; i < stroke.strokePoints.size()-1; i++){
                    int x1 = stroke.strokePoints.get(i).x ;
                    int y1 = stroke.strokePoints.get(i).y ;
                    int x2 = stroke.strokePoints.get(i+1).x ;
                    int y2 = stroke.strokePoints.get(i+1).y ; //step 3
                    stroke.setColorAndWidth(Color.YELLOW, 10); //step 4
//                    paint.setColor(Color.YELLOW); //step 4
//                    paint.setStrokeWidth(10); //step 4
                    canvas.drawLine(x1, y1, x2, y2, stroke.getPaint());
                }
            }
        }

        // draws the line segments
        for (int i = 0; i < segments.size(); i++) {
            Point[] points = segments.get(i);
            stroke.setColorAndWidth(Color.RED, 10); //step 4
//            paint.setColor(Color.RED); //step 4
//            paint.setStrokeWidth(10); //step 4
            canvas.drawLine(points[0].x, points[0].y, points[1].x, points[1].y, stroke.getPaint());
        }

        // draws the points on the map
//        paint.setColor(Color.RED); //step 4
        stroke.setColorAndWidth(Color.RED, 10); //step 4
        for (int i = 0; i < mapPoints.length; i++) {
            int x = mapPoints[i].x;
            int y = mapPoints[i].y;
            canvas.drawRect(x, y, x+20, y+20, stroke.getPaint());
        }

        //=================BUG SECTION========================
        // detects whether the segments form a circuit - but there's a bug!
        // bug activated by single action_down and immediate action_up after recieving the "Nope, not quite! Your path is about.." message
        //assumes circuit
//        boolean isCircuit = true;
        boolean isCircuit = false;
        HashMap<Point, Integer> connections = new HashMap<Point, Integer>(); //keeps track of the degrees of the nodes

        for (Point[] pair : segments) { //number of Point objs in segments should be # of nodes exactly for circuit to exist
            Point p1 = pair[0];
            Point p2 = pair[1];
            Integer value = connections.get(p1);
            if (value == null)
                value = 0;
            value++;
            connections.put(p1, value); //updates value of 1st indexed value (Point) in segments

            value = connections.get(p2);
            if (value == null)
                value = 0;
            value++;
            connections.put(p2, value);
        }

        if (segments.size() == 0) {
            isCircuit = false;
        } else {
            for (int v : connections.values()) {
                if (v != 2) { //uses fact that node degree must be 2 and only 2 for a circuit to exist
                    isCircuit = false;
                    break;
                } else {
                    isCircuit = true;
                }
            }
        }

        // see if user has solved the problem ==> if there are no more points to connect AND there exists a circuit
        if ((segments.size() == mapPoints.length) && isCircuit) {
            ArrayList<Point> shortestPath = ShortestPath.shortestPath(mapPoints);
            double shortestPathLength = calculatePathDistance(shortestPath);

            double myPathLength = 0;

            //get the length of the current path as to be able to compare it to the shortest path
            for (Point[] pair : segments) {
                Point p1 = pair[0];
                Point p2 = pair[1];
                double dx = p1.x - p2.x;
                double dy = p1.y - p2.y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                myPathLength += dist;
            }

            Log.v("RESULT", "Shortest path length is " + shortestPathLength + "; my path is " + myPathLength);

            //compare shortest path and the one at hand
            //if they are the same win message displays
            double diff = shortestPathLength - myPathLength;
            if (Math.abs(diff) < 0.01) {
                Toast.makeText(getContext(), "You found the shortest path!", Toast.LENGTH_LONG).show();
                attempt = 0;
            }

            else { //if you made a circuit but didn't find the shortest calculated path
                // after the 3rd failed attempt, show the solution
//                if (attempt >= 3) { //should probably switch this section to after "Your path is about xx% too big now that I moved attempt++ there
                  if (attempt < 3) {
                      int offset = (int) (Math.abs(diff) / shortestPathLength * 100);
                      // so that we don't say that the path is 0% too long
                      if (offset == 0) {
                          offset = 1;
                      }
                      Toast.makeText(getContext(), "Nope, not quite! Your path is about " + offset + "% too long.", Toast.LENGTH_LONG).show();
                      attempt++; //message displays only on 'incorrect' attempts, so makes sense to put counter right after
                }

                    if (attempt >= 3){
                      // draw the solution
                      for (int i = 0; i < shortestPath.size() - 1; i++) {
                          Point a = shortestPath.get(i);
                          Point b = shortestPath.get(i + 1);
                          stroke.setColorAndWidth(Color.YELLOW, 10);
//                          paint.setColor(Color.YELLOW);
//                          paint.setStrokeWidth(10);
                          canvas.drawLine(a.x+10, a.y+10, b.x+10, b.y+10, stroke.getPaint());
                      }
                      Point a = shortestPath.get(shortestPath.size()-1);
                      Point b = shortestPath.get(0);
                      stroke.setColorAndWidth(Color.YELLOW, 10);
//                      paint.setColor(Color.YELLOW);
//                      paint.setStrokeWidth(10);
                      canvas.drawLine(a.x+10, a.y+10, b.x+10, b.y+10, stroke.getPaint());

                      Toast.makeText(getContext(), "Nope, sorry! Here's the solution.", Toast.LENGTH_LONG).show();
                      invalidate();
                    }
//                attempt++; //moved into "Nope, not quite!" section
            }
        }
        else if (segments.size() == mapPoints.length && !isCircuit) {
            Toast.makeText(getContext(), "That's not a circuit! Select Clear from the menu and start over", Toast.LENGTH_LONG).show();
        }

    }

    public void setData(Bundle data) {
        Bundle bundle = data;
    }


    /*
     * This method is automatically called when the user touches the screen.
     */
    public boolean onTouchEvent(MotionEvent event) {

        Point p = new Point();
        p.x = ((int)event.getX());
        p.y = ((int)event.getY());

        if (event.getAction() == MotionEvent.ACTION_DOWN) { //action_down == initial touch event

            // only add the segment if the touch point is within 30 of any of the other points
            for (int i = 0; i < mapPoints.length; i++) {
                double dx = p.x - mapPoints[i].x;
                double dy = p.y - mapPoints[i].y;
                double dist = Math.sqrt(dx*dx + dy*dy);
                if (dist < 30) {
                    // the "+10" part is a bit of a fudge factor because the point itself is the
                    // upper-left corner of the little red box but we want the center
                    p.x = mapPoints[i].x+10;
                    p.y = mapPoints[i].y+10;

                    stroke.strokePoints.add(p) ; //step 3

                    firstPoint = p;

//                    stroke.setValidStroke(true);
                    isValidStroke = true; //within reasonable limit (30 pixels) to a point on the map
                    invalidate();
                    break;
                }
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) { //action_move occurs after action_down and BEFORE action_up
            if (isValidStroke) {

                stroke.strokePoints.add(p) ; //step 3

                invalidate();
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) { //ends sequence of action_down, action_move and action_up
            if (isValidStroke) {

                stroke.strokePoints.clear(); //step 3

                // only add the segment if the release point is within 30 of any of the other points
                for (int i = 0; i < mapPoints.length; i++) {
                    double dx = p.x - mapPoints[i].x;
                    double dy = p.y - mapPoints[i].y;
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < 30) {
                        p.x = mapPoints[i].x + 10;
                        p.y = mapPoints[i].y + 10;
                        Point[] points = {firstPoint, p};

                        //adds 2 Point objects - start point and end point (nodes) to 'segments'
                        if (firstPoint.x != p.x && firstPoint.y != p.y) {
                            segments.add(points);
                        }
                        invalidate();
                        break;
                    }
                }
            }
            isValidStroke = false;
        }
        else {
            return false;
        }
        // forces a redraw of the View
        //invalidate(); //took this out and moved the forced redraw into each DOWN, MOVE, and UP section
        //per Android documentation you should try and limit excessive use of invalidate()/reDraw when possible
            return true;

    }

    public void setSpinnerNum(int spinnerNum) {
        this.spinnerNum = spinnerNum;
    }


}
