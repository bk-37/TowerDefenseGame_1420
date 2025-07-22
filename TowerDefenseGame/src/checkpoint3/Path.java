package checkpoint3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the path that objects will follow in the Tower Defense
 * game.
 *
 * @Version April 3, 2023
 * @author Wyatt Young and Brian Keller
 */
public class Path
{
    //Instance Variables and array list for points
    private ArrayList<Point> steps;
    private double totalPathLength;
    /**
     * Constructor for path class
     */
    public Path()
    {
        steps = new ArrayList<Point>();
    }

    /**
     * Method to read in and compute the total path length
     * @param in scanner object
     */
    public Path(Scanner in)
    {
        steps = new ArrayList<Point>();
        //Read in points from txt file enter into array list
        int size = in.nextInt();
        for (int i = 0; i < size; i++)
            steps.add(new Point(in.nextInt(), in.nextInt()));
        // Compute the path length.
        totalPathLength = 0;
        //Loop through path steps and summate segment lengths
        for (int i = 1; i < steps.size(); i++)
        {
            // Extract segment start/end
            Point start = steps.get(i-1);
            Point end   = steps.get(i);
            totalPathLength += start.distance(end);
        }
    }
    /**
     * Accessor method for retrieving segments between points in path
     */
    public int getSegments()
    {
        return steps.size();
    }
    /**
     * accessor function that returns the nth point in the path.
     *
     * @Return a path object
     */
    public Point getPoint(int n)
    {
        return steps.get(n);
    }
    /**
     * Our function to add point objects to the path
     *
     * @params Point object
     */
    public void addPoint(Point a)
    {
        steps.add(a);
    }
    /**
     * Function to draw the path to the graphics object
     */
    public void drawPath(Graphics g)
    {
        //Declare point segments
        Point one;
        Point two;
        // Draw all of the segments
        for (int i = 0; i < steps.size() - 1; i++)
        {
            one = steps.get(i);
            two = steps.get(i + 1);
            g.drawLine(one.x, one.y, two.x, two.y);
        }
    }
    /**
     * Method to return length of segment at index i
     * @Param segment position of segment
     */
    public double getSegLength(int segment)
    {
        double deltaX;
        double deltaY;
        deltaX = steps.get(segment + 1).x - steps.get(segment).x;
        deltaY = steps.get(segment + 1).y - steps.get(segment).y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }
    /**
     * To string method that returns a representation of the path in the file format
     *
     * @return String path in file form
     */
    public String toString()
    {
        String text = "" + steps.size() + "\n";
        for(Point step : steps)
        {
            text += step.x +" "+ step.y + "\n";
        }
        return text;
    }

    /**returns a distance from a given point to the path
     *
     * @param loc
     * @return a double representing the distance from the path
     */
    public double distanceToPath(Point loc){
        double distance = 1000;

        for(Point p : steps){
            if(distance >= loc.distance(p))
                distance = loc.distance(p);

        }
        return distance;
    }
    /**
     * Returns a Point, or x, y coordinates, of some position along this
     * path.  The position is given as a percentage.  0.0 means the
     * first position on the path, and 1.0 means the last position on the
     * path.
     *
     * @param percentage   a distance along the path, as a percentage
     * @return the x, y coordinate (as a Point object) of this position on the path
     */
    public Point convertToCoordinates (double percentage)
    {
        // Compute the path length.
        totalPathLength = 0;
        //Loop through path steps and summate segment lengths
        for (int i = 1; i < steps.size(); i++)
        {
            // Extract segment start/end
            Point start = steps.get(i-1);
            Point end   = steps.get(i);
            totalPathLength += start.distance(end);
        }
        //If the percentage is at or before the start of the path,
        //return the first path coordinate.  If the percentage is past
        //the end, return the last path coordinate.
        if (percentage <= 0.0)
            return new Point(steps.get(0));
        if (percentage >= 1.0)
            return new Point(steps.get(steps.size()-1));
        //Convert the percentage to a distance
        double distanceToTravel = totalPathLength * percentage;
        //Walk through the segments and keep track of the distance traveled as
        //we go.If the distance traveled is greater than or equal to
        //points for the current segment.
        Point start = steps.get(0);
        Point end   = steps.get(0);
        //Accumulated distance
        double totalDistance = 0;
        //Length of the current segment.
        double segmentLength = 1;
        //Loop through path array to find remainder of path length
        for (int i = 1; i < steps.size(); i++)
        {
            // Extract segment start/end points
            start = steps.get(i-1);
            end   = steps.get(i);
            // Compute the length of this segment, combine it with the total.
            segmentLength = start.distance(end);
            totalDistance += segmentLength;
            // If we've gone far enough (or too far), exit the loop immediately.
            if (totalDistance > distanceToTravel)
                break;
        }
        // Consider the current segment, not the entire path.  The distance we are
        //   seeking is in this segment somewhere.  Calculate how much too far
        //   the end of the segment is.  Then, see what percentage of this segment
        //   the excess distance is.
        double excessDistance    = totalDistance - distanceToTravel;
        double segmentPercentage = excessDistance / segmentLength;
        double targetX = (segmentPercentage)*start.x + (1-segmentPercentage)*end.x;
        double targetY = (segmentPercentage)*start.y + (1-segmentPercentage)*end.y;
        return new Point ((int) targetX, (int) targetY);
    }
    public double findPercentage(Path path, Point loc)
    {
        double minDist = Double.MAX_VALUE;
        double percentage = 0.0;

        for(double i = 0.0; i <= 1.0; i += 0.01)
        {
            Point p = path.convertToCoordinates(i);
            double dist = Math.sqrt(Math.pow(loc.x - p.x, 2) + Math.pow(loc.y - p.y, 2));
            if(dist < minDist)
            {
                minDist = dist;
                percentage = i;
            }
        }

        return percentage;
    }
}
