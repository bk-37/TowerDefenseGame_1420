package checkpoint3;
/**
 * class which ramps up the addition of asteroids to the game over time
 *
 * @Version April 17, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class VirusGenerator extends GameObject
{
    private Control control;
    private GameState state;
    // Time generation variable
    private double countdownToNextVirus;
    private int virusCount;
    private double multiplier;
    public VirusGenerator(Control control, GameState state)
    {
        super();
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
        countdownToNextVirus = 2.0;
        virusCount = 0;
        multiplier = 1.0;
    }
    public void update(double elapsedTime)
    {
        //Generationg enemies in pattern
        countdownToNextVirus -= elapsedTime;
        if(countdownToNextVirus <= 0)
        {
            //Start spawning
            countdownToNextVirus = 3.0;
            state.addGameObject(new Virus(control,state));
            virusCount++;
            multiplier -= 0.01;
            if(virusCount >= 4 * multiplier)
            {
                countdownToNextVirus = 1.0;
                multiplier -= 0.1;
            }
            if(virusCount >= 6 * multiplier)
            {
                countdownToNextVirus = 2.0;
                multiplier -= 0.1;
            }
            if(state.getScore() > 1000) {
                countdownToNextVirus = 0.5;
            }
        }
    }
    public void draw(Graphics g)
    {
    }
}
