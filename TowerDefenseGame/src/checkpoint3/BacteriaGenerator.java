package checkpoint3;

/**
 * class which ramps up the addition of comets to the game over time
 *
 * @Version April 17, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class BacteriaGenerator extends GameObject
{
    private Control control;
    private GameState state;
    // Time generation variable
    private double countdownToNextBacterium;
    public BacteriaGenerator(Control control, GameState state)
    {
        super();
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
        countdownToNextBacterium = 4.0;

    }
    public void update(double elapsedTime)
    {
        countdownToNextBacterium -= elapsedTime;
        if(countdownToNextBacterium <= 0)
        {
            countdownToNextBacterium = 4.0;
            if(state.getScore() > 500){
                countdownToNextBacterium = 2;
            }
            state.addGameObject(new Bacteria(control,state));
        }
    }
    public void draw(Graphics g)
    {
    }
}
