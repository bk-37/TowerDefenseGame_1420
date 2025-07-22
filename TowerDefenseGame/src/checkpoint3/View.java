package checkpoint3;
/**
 * View method for loading jpanel and drawing animatables
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import javax.swing.*;
import java.awt.*;

public class View extends JPanel
{
    //Fields
    private GameState state;
    private Control control;
    private float alphaTint = 0.0f;

    /**
     * View constructor to hold object reference and create GUI
     *
     * @param control Control class object reference
     * @param state GameState class object reference
     */
    public View(Control control, GameState state)
    {
        //Initialize control and gamestate object references
        this.control = control;
        this.state = state;
        // Build a JFrame and have it terminate when closed.
        JFrame frame = new JFrame("SpaceBalls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Access this pane to edit
        frame.setContentPane(this);
        //set size of Jframe
        Dimension d = new Dimension(800, 600);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        //Pack frame and make visible
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Paint method to draw objects and background etc
     */
    public void paint(Graphics g)
    {
        for(GameObject a: state.getCurrentFrameObjects())
        {
            a.draw(g);
        }
        if(state.getLives() == 0)
        {
            g.setColor(new Color(1.0f, 0, 0, alphaTint));
            g.setFont(new Font("Arial", Font.BOLD, 80));
            g.drawString("GAME OVER", 80, 300);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Score: " + state.getScore(), 220, 350);

            // increase the tint level until it reaches 1.0
            if (alphaTint < 0.9) {
                alphaTint += 0.005;
            }
        }
    }
}
