package checkpoint3;

import java.awt.*;

/**
 * Astronaut tower class for placing astronaut towers
 * @Authors Brian Keller and Wyatt Young
 * @Version April 9,2023
 */
public class Macrophage extends GameObject implements Clickable {
    //Boolean field for if user is dragging tower
    private boolean isMoving;
    //State and Control fields
    private Control control;
    private GameState state;
    //Mouse coordinate fields
    private Point loc;
    private double lastShot;
    private boolean invalidPlacement;
    private float errorTint;
    private int errorDisplayTime;
    private long errorStartTime;
    private int invalidDisplayTime;
    private int hitCount;

    public Macrophage(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
        isMoving = true;
        this.lastShot = 0;
        invalidPlacement = false;
        errorTint = 0.1f;
        errorDisplayTime = 1200;
        invalidDisplayTime = 800;
        hitCount = 0;
    }

    /**
     * Update method for draggable/placeable tower one
     */
    public void update(double timeElapsed) {
        //set collision detection & track the number of collisions
        double currentTime = System.currentTimeMillis();
        Targetable t = (state.getNearestTargetable(loc, currentTime));
        if (t != null && hitCount <= 7){
            Point targetLoc = t.getLocation(currentTime);
            double distance = targetLoc.distance(loc);
            if(distance < 20) {
                ((GameObject)t).hasExpired = true;
                hitCount++;
            }
        }
        if(hitCount == 7)
            hasExpired = true;

        //Only track mouse if satellite object is moving or not
        if(isMoving)
        {
            loc = state.getMouseLocation();
        }

        if (invalidPlacement) {
            long time = System.currentTimeMillis() - errorStartTime;
            hasExpired = time >= invalidDisplayTime;
        }

    }
    /**
     * Draw function for first tower class
     * @param g graphics object
     */
    public void draw(Graphics g)
    {
        if(loc != null)
            drawCenteredImage(g,control.getImage("WBC.png"), loc.x,loc.y,0.69);
        if (invalidPlacement) {
            g.setColor(new Color(1.0f, 0, 0, errorTint));
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("CANNOT BE PLACED HERE", loc.x - 95, loc.y - 25);
            long time = System.currentTimeMillis() - errorStartTime;
            // increase the tint level until it reaches 1.0
            while (time < errorDisplayTime && errorTint < 0.9) {
                errorTint += 0.01;
            }
            if (errorTint > 0.0) { // Decrease the tint level until it reaches 0
                errorTint -= 0.01;
                errorTint = Math.max(0.0f, errorTint); // Ensure that alpha is at least 0
                errorTint = Math.min(1.0f, errorTint);
            }

        }
    }
    /**
     * method to determine if object is being clicked and for each object
     * to respond on their own
     * @return
     */
    public boolean consumeClick()
    {
        if(isMoving)
        {
            Point mouseLoc = state.getMouseLocation();
            //Prevent placement off the screen
            if (mouseLoc.x < 0 || mouseLoc.y < 0 || mouseLoc.x > 600 || mouseLoc.y > 600) {
                hasExpired = true;
            }
                //Prevent placement off path
            else{
                Path path = control.getPath();
                if(path.distanceToPath(mouseLoc) > 10.0) {
                    invalidPlacement = true;
                    errorStartTime = System.currentTimeMillis();
                    state.adjustMoney(+5);
                    }
                }
            state.adjustMoney(-5);
            isMoving = false;
            return true;
        }
        else
            return false;
    }
}
