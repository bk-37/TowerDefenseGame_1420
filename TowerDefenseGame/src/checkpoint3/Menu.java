package checkpoint3;

/**
 * class to create the menu area
 *
 * @Version April 17, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public class Menu extends GameObject
{
    //Fields
    private Control control;
    private GameState state;
    public Menu(Control control, GameState state)
    {
        //Initialize instance fields
        this.control = control;
        this.state = state;
    }
    public void update(double elapsedTime) {

    }

    /**
     * Draw function for menu
     * @param g
     */
    public void draw(Graphics g)
    {
        //Set menu background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(600,0,200,600);
        //Draw menu title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Menu",Font.ITALIC,16));
        g.drawString("Menu",630,30);
        //Draw game parameters
        String score = "Score: " + state.getScore();
        String lives = "Lives: " + state.getLives();
        String money = "Money: " + state.getMoney();
        g.setColor(Color.DARK_GRAY);
        g.drawString(score,630,110);
        g.drawString(lives,630,130);
        g.drawString(money,630,150);
    }
}
