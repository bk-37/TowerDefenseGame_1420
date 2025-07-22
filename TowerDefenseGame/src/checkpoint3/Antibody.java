package checkpoint3;

import java.awt.*;

/**
 * Class to represent shots fired from towers
 *
 * @Authors Brian Keller and Wyatt Young
 * @version April 17, 2023
 */
public class Antibody extends GameObject
{
    //Fields
    private Control control;
    private GameState state;
    //source of shot and target fields
    private Point source;
    private Point target;
    private Path targetPath;
    private double targetPercentage;
    public Antibody(Control control, GameState state, Point source, Point target)
    {
        super();
        //Constructor parameters
        this.control = control;
        this.state = state;
        this.source = source;
        this.target = target;
        //Create and hold path object
        targetPath = new Path();
        targetPath.addPoint(source);
        targetPath.addPoint(target);
        //Initialize percentage
        targetPercentage = 0;
    }
    public void update(double elapsedTime)
    {
        // Calculate new target percentage based on elapsed time and speed
        double speed = 250;
        double distance = source.distance(target);
        double timeToTarget = distance / speed;
        double percentageChange = elapsedTime / timeToTarget;
        targetPercentage += percentageChange;
        Point loc = targetPath.convertToCoordinates(targetPercentage);
        Targetable t = state.getNearestTargetable(loc, 0);
        if(loc.distance(t.getLocation(elapsedTime)) < 30.0)
        {
            if(t instanceof respawnTargetable)
            {
                ((respawnTargetable) t).spawnBacteriaChild(control,state, (Bacteria) t);
            }
            ((GameObject)t).hasExpired = true;
            state.adjustMoney(5);
            state.adjustScore(10);
            hasExpired = true;
        }
        if(targetPercentage >= 1.0)
        {
            hasExpired = true;
        }
    }
    public void draw(Graphics g)
    {
        Point location = targetPath.convertToCoordinates(targetPercentage);
        drawCenteredImage(g,control.getImage("Antibody.png"), location.x,location.y,0.37);
    }
}
