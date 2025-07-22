package checkpoint3;

/**
 * Interface for game objects that are targetable
 *
 * @Version April 17, 2023
 * @authors Wyatt Young and Brian Keller
 */
import java.awt.*;

public interface Targetable
{
    public Point getLocation(double futureTime);
}
