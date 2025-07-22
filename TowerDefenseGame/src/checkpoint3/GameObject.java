package checkpoint3;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * GameObject superclass for game object classes to extend
 *
 * @Author Brian Keller and Wyatt Young
 * @Version April 6,2023
 */
abstract class GameObject implements Animatable
{
    //Superclass fields
    protected boolean hasExpired;
    //Constructor
    public GameObject()
    {
        hasExpired = false;
    }
    /**
     * accessor method for hasExpired variable
     */
    public boolean hasExpired()
    {
        return hasExpired;
    }

    /**
     * Helper function to center images placed
     * @param k
     * @param image
     * @param x
     * @param y
     * @param scale
     */
    protected void drawCenteredImage (Graphics k, BufferedImage image, int x, int y, double scale)
    {
        int width = (int)(image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        int nx = x - width/2;
        int ny = y - height/2;
        k.drawImage(image, nx, ny, width, height, null);
    }
    protected void displayInvalid(Point loc){

    }
}


