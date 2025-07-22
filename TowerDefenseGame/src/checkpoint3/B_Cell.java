package checkpoint3;

import java.awt.*;



/**
 * First tower class for placing defense towers
 * @Authors Brian Keller and Wyatt Young
 * @Version April 9,2023
 */
public class B_Cell extends GameObject implements Clickable
{
    //Boolean field for if user is dragging tower
    private boolean isMoving;
    //State and Control fields
    private Control control;
    private GameState state;
    //Mouse coordinate fields
    private Point loc;
    //last shot time field
    private double lastShot;
    private boolean invalidPlacement;
    private float errorTint;
    private int errorDisplayTime;
    private long errorStartTime;
    private int invalidDisplayTime;

    public B_Cell(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
        isMoving = true;
        this.lastShot = 0;
        invalidPlacement = false;
        errorTint = 0.1f;
        errorDisplayTime = 1200;
        invalidDisplayTime = 800;

    }
    /**
     * Update method for draggable/placeable tower one
     */
    public void update(double timeElapsed) {
        //Increment shot variable
        lastShot += 0.01;
        //Only track mouse if satellite object is moving or not
        if (isMoving) {
            loc = state.getMouseLocation();
        }
        if (!isMoving && lastShot > 1.5) {
            // Target an entity
            double futureTime = 0.75;
            Targetable t = state.getNearestTargetable(loc, futureTime);
            if (t != null) {
                Point targetLoc = t.getLocation(futureTime);
                double dist = targetLoc.distance(loc);
                if (dist < 300) {
                    // Fire a shot from here to the target
                    lastShot = 0;
                    state.addGameObject(new Antibody(control, state, loc, targetLoc));
                }
            }
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

        public void draw (Graphics g)
        {
            if (loc != null)
                drawCenteredImage(g, control.getImage("B_Cell.png"), loc.x, loc.y, 0.69);
            if (invalidPlacement) {
                g.setColor(new Color(1.0f, 0, 0, errorTint));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("CANNOT BE PLACED HERE", loc.x - 111, loc.y - 33);
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
    public boolean consumeClick() {
        Point homeCell = new Point(600, 600);
        int homeCellRadius = 250;
        if (isMoving) {
            Point mouseLoc = state.getMouseLocation();
            //Prevent placement off the screen
            if (mouseLoc.x < 0 || mouseLoc.y < 0 || mouseLoc.x > 600 || mouseLoc.y > 600) {
                hasExpired = true;
            }
                //prevent placement within main cell (bottom right)
            else
                {
                    double cellDistance = mouseLoc.distance(homeCell);
                    if (cellDistance <= homeCellRadius) {
                        invalidPlacement = true;
                        errorStartTime = System.currentTimeMillis();
                        state.adjustMoney(+15);
                    }
                }
                state.adjustMoney(-15);
                isMoving = false;
                return true;
            }
            else {
            return false;
        }
    }
}
