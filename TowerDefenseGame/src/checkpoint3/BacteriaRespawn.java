package checkpoint3;
/**
 * Asteroid object class implementing animatable object
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class BacteriaRespawn extends GameObject implements Targetable
{
    //fields
    private Control control;
    private GameState state;
    //intialize more fields for movement/position
    private Point spawnLoc;
    private double percentage;
    //constructor
    public BacteriaRespawn(Control control, GameState state, Point spawnLoc, double percentage)
    {
        super();
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
        this.spawnLoc = spawnLoc;
        this.percentage = percentage;
    }
    //animatable traits copied over
    //update method
    public void update(double elapsedTime) {
        //Update percentage
        percentage += (0.50/10.0) * elapsedTime;
        if (percentage >= 1.00)
        {
            //Delete Ice if expired
            hasExpired = true;
            state.adjustLives(-1);
        }
    }
    //draw method
    public void draw(Graphics g)
    {
        //Draw objects on path
        Point loc = control.getPath().convertToCoordinates(percentage);
        drawCenteredImage(g,control.getImage("Bacteria.png"),loc.x,loc.y, 0.55);
    }

    public Point getLocation(double futureTime)
    {
        return control.getPath().convertToCoordinates(percentage);
    }
}
