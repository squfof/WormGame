package wormgame.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import wormgame.game.WormGame;

public class UserInterface implements Runnable {

    // attributes
    private JFrame frame;
    private WormGame game;
    private int sideLength;
    private DrawingBoard drawingBoard ;

    // constructor with inputs
    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }

    // run the interface
    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (game.getWidth() + 1) * sideLength + 10;
        int height = (game.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    // create the components of the interface
    public void createComponents(Container container) {
        
        // Create drawing board first which then is added into container-object.
        this.drawingBoard = new DrawingBoard(this.game, this.sideLength) ;
        container.add(this.drawingBoard) ;
        
        // After this, create keyboard listener which is added into frame-object
        KeyboardListener keyboardListener = new KeyboardListener(this.game.getWorm()) ;
        this.frame.addKeyListener(keyboardListener) ;
    }

    // return the frame
    public JFrame getFrame() {
        return frame;
    }
    
    // return the drawing board (updatable)
    public Updatable getUpdatable() {
        return this.drawingBoard ;
    }
}