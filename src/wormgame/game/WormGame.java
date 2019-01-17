package wormgame.game ;

import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.util.Random ;
import javax.swing.Timer ;
import wormgame.Direction ;
import wormgame.gui.Updatable ;
import wormgame.domain.* ;

public class WormGame extends Timer implements ActionListener {

    // attributes
    private int width ;
    private int height ;
    private boolean continues ;
    private Updatable updatable ;
    private Worm worm ;
    private Random randomizer ;
    private Apple apple ;
    
    // constructor with inputs
    public WormGame(int width, int height) {
        
        // intialize the Timer
        super(1000, null) ;

        // initialize the game board dimensions
        // and boolean for continuing the game
        this.width = width ;
        this.height = height ;
        this.continues = true ;

        // attach an action listener
        addActionListener(this) ;
        setInitialDelay(2000) ;

        // initialize the worm with starting position and direction
        this.worm = new Worm((this.width / 2), (this.height / 2), Direction.DOWN) ;
        
        // initialize a random number generator and an apple with random position
        // make sure apple isn't located inside the worm
        this.randomizer = new Random() ;
        
        while (true) {
            this.apple = new Apple(this.randomizer.nextInt(width), 
                this.randomizer.nextInt(height)) ;
            
            if (!this.appleInWorm()) {
                break ;
            }
        }
    }
    
    // return the value of continues
    public boolean continues() {
        return continues;
    }

    // set the updatable
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }
    
    // set the worm
    public void setWorm(Worm worm) {
        this.worm = worm ;
    }
    
    // set the apple
    public void setApple(Apple apple) {
        this.apple = apple ;
    }
    
    // return the value of height
    public int getHeight() {
        return height;
    }
    
    // return the value of width
    public int getWidth() {
        return width;
    }
    
    // return the worm
    public Worm getWorm() {
        return this.worm ;
    }
    
    // return the apple
    public Apple getApple() {
        return this.apple ;
    }
    
    // return true if piece (apple) is contained in list (worm body)
    public boolean appleInWorm() {
        for (int i = 0 ; i < this.worm.getPieces().size() ; i++) {
            if (this.worm.getPieces().get(i).runsInto(this.apple)) {
                return true ;
            }
        }
        return false ;
    }

    // required to implement ActionListener
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        // check to see if the game should continue
        if (!continues) {
            return ;
        }
        
        // move the worm
        this.worm.move() ;
        
        // check to see if the worm ate an apple; if so, grow the worm
        // and create a new apple
        if (this.worm.runsInto(this.apple)) {
            this.worm.grow() ;
            
            // make sure apple is not placed on the worm
            while (true) {
                this.apple = new Apple(this.randomizer.nextInt(width), 
                    this.randomizer.nextInt(height)) ;
                
                if (!this.appleInWorm()) {
                    break ;
                }
            }
        }
        
        // check to see if the worm ran into itself; if so, assign false
        // to the continues variable
        if (this.worm.runsIntoItself()) {
            this.continues = false ;
        }
        
        // check to see if the worm ran into a wall
        // seems like a bit of a hack, but the execise didn't say anything
        // about implementing walls and how the worm should interact with it!
        int wormLength = this.worm.getLength() ;
        Piece wormHead = this.worm.getPieces().get(wormLength - 1) ;
        int headX = wormHead.getX() ;
        int headY = wormHead.getY() ;
        if (headX <= 0 || this.width <= headX 
                || headY <= 0 || this.height <= headY) {
        this.continues = false ;
        }
        
        // call the update method
        this.updatable.update() ;
        
        // set the delay so that the game gets faster as the worm gets longer
        super.setDelay(1000 / this.worm.getLength()) ;
    }
}