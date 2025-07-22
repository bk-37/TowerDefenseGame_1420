package checkpoint3;
/**
 * gameover graphic object class which extends Game object
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class GameTint extends GameObject
{
    private Control control;
    private GameState state;
    //Tint variable
    private float tintLevel = 0.0f;
    public GameTint(Control control, GameState state)
    {
        super();
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
    }
    public void update(double elapsedTime)
    {
        if(state.getLives() <= 2)
        {
            tintLevel = 0.15f;
        }
        if(state.getLives() <= 3)
        {
            tintLevel = 0.13f;
        }
        else if(state.getLives() <= 4)
        {
            tintLevel = 0.11f;
        }
        else if(state.getLives() <= 5)
        {
            tintLevel = 0.08f;
        }
        else
        {
            tintLevel = 0f;
        }
    }
    public void draw(Graphics g)
    {
        if(state.getLives() <= 5)
        {
            g.setColor(new Color( 1.0f, 0, 0, tintLevel));
            g.fillRect(0, 0, 600, 600);
        }
    }
}
