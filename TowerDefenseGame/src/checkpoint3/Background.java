package checkpoint3;
/**
 * Background object class which implements the animatable interface
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class Background extends GameObject
{
    private Control control;
    private GameState state;
    public Background(Control control, GameState state)
    {
        super();
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
    }
    public void update(double elapsedTime)
    {

    }
    public void draw(Graphics g)
    {
        g.drawImage(control.getImage("background.png"),0,0,null);
    }
}
