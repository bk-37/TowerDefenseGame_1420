package checkpoint3;
/**
 * tower defense application interface for animatable game objects
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public interface Animatable
{
    //Methods to update frame and draw objects
    void update(double elapsedTime);
    void draw(Graphics g);
}
