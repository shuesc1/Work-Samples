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
    private Point startingPoint, endingPoint;

    /**
     * The constructor for the class
     */
    public Segments() {
        lineSegments = new ArrayList<>();
        hasCircuit = false;
        startingPoint = new Point();
        endingPoint = new Point();
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


    /**
     * A method that visits all line segments to detect if a true single Hamiltonian cycle exists
     * @param segments
     * @return
     */
    public boolean existsHamPath(Segments segments) {
        //For a Ham path/cycle to exist all line segments must be connected and the start point must be the end point
        ArrayList<LineSegment> lineSegs = segments.lineSegments; //get array of all line segs in path
        LineSegment startingSeg = lineSegs.get(0); //get starting line segment
        startingSeg.visited = true;
        startingPoint = startingSeg.getStartPoint(); //arbitrary starting point will be last lineseg's ending point if HamCycle
        LineSegment currentSeg = startingSeg;
        boolean hasHamPath = false;

        //visit all line segments-- current line seg's endpoint will be another's start point until
        //end point ends back up at original start point if Ham Cycle exists
        while (currentSeg.visited == false) {
            currentSeg.visited = true;
            endingPoint = currentSeg.getFinishPoint();
            for (LineSegment thisLineSeg : lineSegs) {
                if (thisLineSeg.getStartPoint() == endingPoint && thisLineSeg.visited == false) {
                    currentSeg = thisLineSeg;
                    break;
                }
            }
        }

        if (endingPoint == startingPoint) { //if we find a circuit
            for (LineSegment thisLS : lineSegs) { //verify that all line segs have been visited
                if (thisLS.visited == false) { //otherwise multiple circuits exist
                    hasHamPath = false;
                    break;
                } else if (thisLS.visited) {
                    hasHamPath = true;
                }
            }
        }
        return hasHamPath;
    }
}
