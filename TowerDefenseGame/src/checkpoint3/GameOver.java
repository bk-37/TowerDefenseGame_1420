package checkpoint3;
/**
 * gameover graphic object class which extends Game object
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class GameOver extends GameObject
{
    private Control control;
    private GameState state;
    //Tint variable
    private float alphaTint = 0.0f;
    public GameOver(Control control, GameState state)
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
        if(state.getLives() == 0)
        {
            g.setColor(new Color(1.0f, 0, 0, alphaTint));
            g.setFont(new Font("Arial", Font.BOLD, 80));
            g.drawString("GAME OVER", 80, 300);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Score: " + state.getScore(), 220, 350);

            // increase the tint level until it reaches 1.0
            if (alphaTint < 0.9)
            {
                alphaTint += 0.005;
            }
        }
    }
}
