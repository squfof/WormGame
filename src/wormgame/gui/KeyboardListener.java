package wormgame.gui;

import wormgame.domain.* ;

import java.awt.event.KeyEvent ;
import java.awt.event.KeyListener ;
import wormgame.Direction;

public class KeyboardListener implements KeyListener {
    
    // attributes
    private Worm worm ;
    
    // constructor with inputs
    public KeyboardListener(Worm worm) {
        this.worm = worm ;
    }
    
    // set the direction of the worm according to the key pressed
    // required to implement KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.worm.setDirection(Direction.LEFT) ;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.worm.setDirection(Direction.RIGHT) ;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.worm.setDirection(Direction.UP) ;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.worm.setDirection(Direction.DOWN) ;
        }
    }

    // required to implement KeyListener
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    // required to implement KeyListener
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}