package checkpoint3;
/**
 * Asteroid object class implementing animatable object
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class Virus extends GameObject implements Targetable
{
    //fields
    private Control control;
    private GameState state;
    //intialize more fields for movement/position
    private double percentage;
    private double age;
    private boolean gameOver = false;
    //asteriod constructor
    public Virus(Control control, GameState state)
    {
        super();
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
        percentage = 0;
        age = 0.0;
    }
    //animatable traits copied over
    //update method
    public void update(double elapsedTime) {
        //Update percentage
        percentage += (0.3/10.0) * elapsedTime;
        //Update age
        age += elapsedTime;
        if (percentage >= 1.00)
        {
            //add new Asteroid if expired
            hasExpired = true;
            state.addGameObject(new Virus(control, state));
            state.adjustLives(-1);
        }
    }
    //draw method
    public void draw(Graphics g)
    {
        //Draw objects on path
        Point loc = control.getPath().convertToCoordinates(percentage);
        drawCenteredImage(g,control.getImage("Virus.png"),loc.x,loc.y, 0.69);
    }

    public Point getLocation(double futureTime)
    {
        return control.getPath().convertToCoordinates(percentage);
    }
}
