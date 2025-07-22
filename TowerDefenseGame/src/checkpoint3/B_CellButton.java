package checkpoint3;

/**
 * Class to represent tower spawning button
 *
 * @Version April 17, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class B_CellButton extends GameObject implements Clickable
{
    //Fields
    private Control control;
    private GameState state;
    private boolean isMoving;
    private boolean clicked;
    //Color fade variable
    private float errorTint;
    private int errorDisplayTime;
    private long errorStartTime;
    public B_CellButton(Control control, GameState state)
    {
        super();
        //Initialize instance fields
        this.control = control;
        this.state = state;
        isMoving = false;
        clicked = false;
        errorTint = 0.1f;
        errorDisplayTime = 1200;
    }
    /**
     * method to determine if object is being clicked and for each object
     * to respond on their own
     * @return
     */
    public boolean consumeClick()
    {
        //test to see if click is over tower button
        Point mouseLoc = state.getMouseLocation();
        if (mouseLoc.x >= 620 && mouseLoc.x <= 695 && mouseLoc.y >= 400 && mouseLoc.y <= 475
        && state.getMoney() >= 15 && state.gettowerCount() <= 20)
        {
            //If clicked add new satellite object
            state.addGameObject(new B_Cell(control, state));
            state.inctowerCount();
            return true;
        }
        else if(mouseLoc.x >= 620 && mouseLoc.x <= 695 && mouseLoc.y >= 400 && mouseLoc.y <= 475
                && state.getMoney() <= 15)
        {
            errorStartTime = System.currentTimeMillis();
            clicked = true;
            return false;
        }
        clicked = false;
        return false;
    }
    public void update(double elapsedTime) {

    }
    public void draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRoundRect(620, 400, 75, 75, 10, 10);
        g.setColor(Color.GRAY);
        g.fillRoundRect(622, 402, 71, 71, 10, 10);
        drawCenteredImage(g, control.getImage("B_Cell.png"), 657, 437, 0.69);
        if(clicked)
        {
            g.setColor(new Color(1.0f, 0, 0,errorTint));
            g.setFont(new Font("Arial", Font.BOLD, 9));
            g.drawString("NOT ENOUGH MONEYS", 600, 495);
            long time = System.currentTimeMillis() - errorStartTime;
            // increase the tint level until it reaches 1.0
            while (time < errorDisplayTime && errorTint < 0.9)
            {
                errorTint += 0.01;
            }
            if(errorTint > 0.0)
            { // Decrease the tint level until it reaches 0
                errorTint -= 0.01;
                errorTint = Math.max(0.0f, errorTint); // Ensure that alpha is at least 0
                errorTint = Math.min(1.0f, errorTint);
            }
        }
    }
}
