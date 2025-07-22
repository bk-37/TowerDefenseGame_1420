package checkpoint3;
/**
 * Control method in order to control game objects
 *
 * @Version April 3, 2023
 * @authors Wyatt Young and Brian Keller
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Timer;
import java.util.TreeMap;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener
{
    //Instance fields
    private GameState state;
    private View view;
    //Path field
    private Path path;
    //Boolean mouse drag field
    private boolean isMoving;
    //Image tree map
    TreeMap<String, BufferedImage> images = new TreeMap<>();
    //Boolean for end of game
    private boolean gameOver;
    /**
     * Control class constructor
     */
    public Control()
    {
        SwingUtilities.invokeLater(this);
    }
    /**
     * Method to load path
     * @Param Filename path txt file
     */
    private Path loadPath(String Filename)
    {
        try
        {
            Scanner in = new Scanner(new File(Filename));
            //load the path
            Path path = new Path(in);
            in.close();
            return path;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not load path.");
            return null;
        }
    }
    /**
     * Accessor method in order to get current path object
     */
    public Path getPath()
    {
        return path;
    }
    /**
     * Method for loading images
     */
    private BufferedImage loadImage(String filename)
    {
        // Try catch for loading image
        try
        {
            return javax.imageio.ImageIO.read(new File(filename));
        }
        catch (IOException e)
        {
            System.out.println("Could not read image.");
            return null;
        }
    }
    /**
     * getImage method to check image key is in image treemap
     * and return associated filename
     */
    public BufferedImage getImage(String Filename)
    {
        if(!images.containsKey(Filename))
        {
            BufferedImage image = loadImage(Filename);
            images.put(Filename,image);
            System.out.println("loaded image");
            return images.get(Filename);
        }
        else
        {
            return images.get(Filename);
        }
    }
    /**
     * Run method to execute GUI thread
     */
    public void run()
    {
        //Initialize game flag
        gameOver = false;
        //Build path
        path = loadPath("cell_path_2.txt");
        //Build gamestate object
        state = new GameState();
        //Build view object after loading path
        view = new View(this, state);
        //Add mouse listeners to view
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        //Initialize isMoving boolean field
        isMoving = false;
        //prepare creation of next frame
        state.startFrame();
        //Add one background, asteroid, and comet to frame list
        state.addGameObject(new Background(this,state));
        //Add tinting background
        state.addGameObject(new GameOver(this,state));
        //Add Menu area
        state.addGameObject(new Menu(this,state));
        //Add menu buttons
        state.addGameObject(new B_CellButton(this,state));
        state.addGameObject(new MacrophageButton(this,state));
        //Add foe generators
        state.addGameObject(new VirusGenerator(this,state));
        state.addGameObject(new BacteriaGenerator(this,state));
        //Add tinting background
        state.addGameObject(new GameTint(this,state));
        //Mark next frame as ready
        state.nextFrame();
        //Paint updated frame, choi babies!
        view.repaint();
        //add a timer which triggers every 16 ms
        Timer t = new Timer(16, this);
        t.start();
    }
    /**
     * Action performed method which uses a loop to update and repaint frame
     * @param e the event to be processed
     */
    public void actionPerformed (ActionEvent e)
    {
        //Main loop to update the frame
        state.startFrame();
        if(!gameOver)
        {
            for (GameObject a : state.getCurrentFrameObjects()) {
                a.update(state.getElapsedTime());
                //Loop until lives = 0
                if (state.getLives() == 0)
                {
                    gameOver = true;
                    return;
                }
            }
        }
        //If mouse was pressed, let objects decide what to do
        if(isMoving)
            for (int pos=state.getCurrentFrameObjects().size()-1;pos >= 0;pos--)
            {
                Animatable a = state.getCurrentFrameObjects().get(pos);
                if (a instanceof Clickable) {
                    Clickable c = (Clickable) a;
                    if (c.consumeClick())
                        break;
                }
            }
        isMoving = false;
        state.nextFrame();
        view.repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e)
    {
        state.setMouseLocation(e.getX(),e.getY());
        isMoving = true;
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e)
    {
        state.setMouseLocation(e.getX(),e.getY());
    }
    public void mouseMoved(MouseEvent e)
    {
        state.setMouseLocation(e.getX(),e.getY());
    }
}

