package edu.upenn.cis573.travelingsalesman;

import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * A class that wraps/represents a collection of the individual line segments
 */
public class Segments {

//    private Point startPoint, finishPoint;
//    private Path;
    public ArrayList<LineSegment> lineSegments;
    private boolean hasCircuit;


    public Segments(){
        lineSegments = new ArrayList<>();
        hasCircuit = false ;
    }

    public void addLineSegment(LineSegment ls){
        this.lineSegments.add(ls) ;
    }

    public boolean detectCircuit(){



    }







}
