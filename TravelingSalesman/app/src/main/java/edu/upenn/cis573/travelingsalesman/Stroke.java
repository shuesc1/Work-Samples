package edu.upenn.cis573.travelingsalesman;

import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * A class that encapsulates aspects of a user's finger strokes while using the app
 */
public class Stroke {
    private boolean isValidStroke;
    public ArrayList<Point> strokePoints; //left public for ease of access and readability
    private String color;
    private int width;
    private Paint paint;

    /**
     * The constructor for the class
     * It initializes all the variables
     */
    public Stroke(){
//        p = new Point() ;
        isValidStroke = false;
        strokePoints = new ArrayList<>();
        color = "" ;
        paint = new Paint();
        width = 10;
    }

    /**
     * A getter method for the boolean of if the method OnTouchEvent handled the touch event or not
     * @return a boolean value describing the actions of the OnTouchEvent method
     */
    public boolean getValidStroke() {
        return isValidStroke;
    }

    /**
     * A setter method for the boolean value of if the OnTouchMethod handled a touch event
     * @param validStroke a boolean value
     */
    public void setValidStroke(boolean validStroke) {
        isValidStroke = validStroke;
    }

//    public ArrayList<Point> getStrokePoints() {
//        return strokePoints;
//    }
//
//    public void setStrokePoints(ArrayList<Point> strokePoints) {
//        this.strokePoints = strokePoints;
//    }

    /**
     * A getter method for the Paint object encapsulated in this class
     * @return a Paint object
     */
    public Paint getPaint() {
        return paint;
    }

    /**
     * A setter method for a stroke's (Paint object's) color and width
     * @param strokeColor an int corresponding to a valid color
     * @param strokeWidth an int corresponding to a stroke's width
     */
    public void setColorAndWidth(int strokeColor, int strokeWidth){
        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
    }
}
