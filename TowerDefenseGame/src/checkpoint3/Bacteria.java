package checkpoint3;
/**
 * Comet object class which implements the animatable interface and associated methods
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class Bacteria extends GameObject implements Targetable, respawnTargetable
{
    //Fields
    private Control control;
    private GameState state;
    //Position along path
    private double percentage;
    public Bacteria(Control control, GameState state)
    {
        super();
        //Initialize instance fields
        this.control = control;
        this.state = state;
        //Initialize percentage
        percentage  = 0;
    }
    /**
     * update method inherited from Animatable class
     * @param elapsedTime
     */
    public void update(double elapsedTime)
    {
        percentage += (0.2/10.0) * elapsedTime;
        if (percentage >= 1.00)
        {
            hasExpired = true;
            state.adjustLives(-2);
        }
    }
    /**
     * Draw function inherited from Animatable class
     * @param g Graphics object
     */
    public void draw(Graphics g)
    {
        //Draw objects on path
        Point loc = control.getPath().convertToCoordinates(percentage);
        drawCenteredImage(g,control.getImage("Bacteria.png"),loc.x,loc.y,1);
    }
    public Point getLocation(double futureTime)
    {
        return control.getPath().convertToCoordinates(percentage);
    }
    public double getPercentage(double cometExpire, Bacteria down)
    {
        Point cometLocation = down.getLocation(cometExpire);
        return control.getPath().findPercentage(control.getPath(), cometLocation);
    }
    public void spawnBacteriaChild(Control control, GameState state, Bacteria down) {
        Point cometLocation = down.getLocation(0);
        double cometPercentage = down.getPercentage(0, down);
        state.addGameObject(new BacteriaRespawn(control, state, cometLocation, cometPercentage));
        state.addGameObject(new BacteriaRespawn(control, state, cometLocation, cometPercentage + 0.02));
    }
}
