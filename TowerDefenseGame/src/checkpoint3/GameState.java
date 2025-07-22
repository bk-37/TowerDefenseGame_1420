package checkpoint3;
/**
 * gamestate method for updating game state objects frame by frame
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState
{
    //GameObject Fields
    private ArrayList<GameObject> currentFrameObjects;
    private ArrayList<GameObject> nextFrameObjects;
    //Mouse coordinate fields
    private int mouseX;
    private int mouseY;
    //private fields for money, lives and score
    public int money;
    public int lives;
    public int score;
    public int towerCount;
    //Boolean mouse drag field
    private boolean isMoving = false;
    //Time fields
    private long lastFrameStartTime;
    private double elapsedTime;
    //basic accessors/mutators for money/lives/scores
    public int getMoney()
    {
        return money;
    }
    public int getLives()
    {
        return lives;
    }
    public int getScore()
    {
        return score;
    }
    public int adjustMoney(int adjustment)
    {
        return money += adjustment;
    }
    public int adjustLives(int adjustment)
    {
        return lives += adjustment;
    }
    public int adjustScore(int adjustment)
    {
        return score += adjustment;
    }
    public int gettowerCount(){return towerCount;}
    public void inctowerCount(){towerCount += 1;}
    /**
     * GameState constructor for current frame
     */
    public GameState()
    {
        currentFrameObjects = new ArrayList<GameObject>();
        money = 50;
        lives = 10;
        score = 0;
        towerCount = 0;
        lastFrameStartTime = System.currentTimeMillis();
    }
    /**
     * Accessor method for Control and View classes to obtain current game objects
     */
    public List<GameObject> getCurrentFrameObjects()
    {
        return currentFrameObjects;
    }
    /**
     * Helper method to prepare next frame to copy objects current list
     */
    public void startFrame()
    {
        //Calculate how much time has elapsed since previous start frame
        long currentFrameStartTime = System.currentTimeMillis();
        elapsedTime = (currentFrameStartTime - lastFrameStartTime)/1000.0;
        lastFrameStartTime = currentFrameStartTime;
        //Creates empty list
        nextFrameObjects = new ArrayList<GameObject>();
        //Add all current frame object to next
        nextFrameObjects.addAll(currentFrameObjects);
    }
    /**
     * Helper method for adding game objects to next frames object list
     */
    public void nextFrame()
    {
        //loop through all game objects and remove the apprpriate ones
        for(int i = nextFrameObjects.size()-1;i >= 0;i--)
        {
            GameObject a = nextFrameObjects.get(i);
            if (a.hasExpired)
                nextFrameObjects.remove(a);
        }
        //Reinitialize current objects to next
        currentFrameObjects = nextFrameObjects;
        //Only current list now
        nextFrameObjects = null;
    }
    /**
     * Helper method for adding game objects to next frame object list
     *
     * @Param Animatable a Game object to add to next frame
     */
    public void addGameObject(GameObject a)
    {
        nextFrameObjects.add(a);
    }
    /**
     * Accessor method to get coordinates of mouse
     */
    public Point getMouseLocation()
    {
        return new Point(mouseX,mouseY);
    }
    /**
     * Mutator method to set coordinate of mouse
     */
    public void setMouseLocation(int mouseX, int mouseY)
    {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    /**
     * Accessor method for elapsedTime
     */
    public double getElapsedTime()
    {
        return elapsedTime;
    }

    /**
     * Class that finds the nearest targetable object from point here
     * @param here
     * @return
     */
    public Targetable getNearestTargetable(Point here, double additionalTime) {
        if (here != null) {
            Targetable temp = null;
            for (GameObject go : currentFrameObjects) {
                if (!(go instanceof Targetable))
                    continue;
                Targetable t = (Targetable) go;
                if (go.hasExpired())
                    continue;
                if (temp == null) {
                    temp = t;
                    continue;
                }
                double closestDist = here.distance(temp.getLocation(additionalTime));
                double tDist = here.distance(t.getLocation(additionalTime));
                if (tDist < closestDist)
                    temp = t;
            }
            return temp;
        }
        return null;
    }
}
