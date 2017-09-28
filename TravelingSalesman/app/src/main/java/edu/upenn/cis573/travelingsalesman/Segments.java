package edu.upenn.cis573.travelingsalesman;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that wraps/represents a collection of the individual line segments
 */
public class Segments {

    public ArrayList<LineSegment> lineSegments;
    private boolean hasCircuit;
    private HashMap<Point, Integer> connections;

    /**
     * The constructor for the class
     */
    public Segments() {
        lineSegments = new ArrayList<>();
        hasCircuit = false;
    }

    /**
     * A method that adds a LineSegment object to this class' ArrayList of all LineSegments
     *
     * @param ls a new LineSegment object
     */
    public void addLineSegment(LineSegment ls) {
        this.lineSegments.add(ls);
    }

    /**
     * A method that abstracts from GV class circuit detection
     *
     * @return a boolean if a circuit has been detected, false otherwise
     */
    public boolean detectCircuit() {
        connections = new HashMap<Point, Integer>();
        for (LineSegment thisLS : this.lineSegments) {
            Integer value = connections.get(thisLS.getStartPoint());
            calculateVertexDegrees(thisLS.getStartPoint(), value);
            value = connections.get(thisLS.getFinishPoint());
            calculateVertexDegrees(thisLS.getFinishPoint(), value);
        }

        if (this.lineSegments.size() == 0) {
            hasCircuit = false;
        } else {
            for (int v : connections.values()) {
                if (v != 2) { //uses fact that node degree must be 2 and only 2 for a circuit to exist
                    hasCircuit = false;
                    break;
                } else {
                    hasCircuit = true;
                }
            }
        }
        return hasCircuit;
    }


    /**
     * A helper method for the detectCircuit method -- eliminates duplicate code
     *
     * @param currentPoint
     * @param val
     */
    public void calculateVertexDegrees(Point currentPoint, Integer val) {
        if (val == null)
            val = 0;
        val++;
        connections.put(currentPoint, val);
    }
}
