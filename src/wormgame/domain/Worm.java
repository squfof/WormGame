package wormgame.domain;

import wormgame.Direction ;

import java.util.List ;
import java.util.ArrayList ;

public class Worm {
    
    // attributes
    private Direction direction ;
    private List<Piece> wormBody ;
    private Piece growth ;
    
    //constructor with inputs
    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.direction = originalDirection ; 
        this.wormBody = new ArrayList<Piece>() ;
        this.wormBody.add(new Piece(originalX, originalY)) ;
        this.growth = null ;
    }
    
    // return the worm's direction
    public Direction getDirection() {
        return this.direction ;
    }
    
    //set the worm's direction
    public void setDirection(Direction direction) {
        this.direction = direction ;
    }
     
    // return the worm's length
    public int getLength() {
        return this.wormBody.size() ;
    }
    
    // return the list of Piece objects that form the worm
    // head is at the *end* of the list?
    public List<Piece> getPieces() {
        return this.wormBody ;
    }
    
    // moves the worm one piece forward
    public void move() {
        
        // if the worm has length 1 or 2, then it also grows by one piece
        if (this.wormBody.size() < 3) {
            this.grow() ;
        }
        
        // get the coordinates of the head, which is at the *end* of the list
        int x = this.wormBody.get(this.wormBody.size()-1).getX() ;
        int y = this.wormBody.get(this.wormBody.size()-1).getY() ;
        
        // add a new piece at the end of the list using the current direction
        if (this.direction == Direction.UP) {
            this.wormBody.add(new Piece(x, y-1)) ;
        }
        else if (this.direction == Direction.DOWN) {
            this.wormBody.add(new Piece(x, y+1)) ;
        }
        else if (this.direction == Direction.RIGHT) {
            this.wormBody.add(new Piece(x+1, y)) ;
        }
        else if (this.direction == Direction.LEFT) {
            this.wormBody.add(new Piece(x-1, y)) ;
        }
        
        // remove the first piece in the list, which simulates movement when
        // combined with the previous step of adding a piece to the end of the list 
        this.wormBody.remove(0) ;
        
        // check for growth
        if (this.growth != null) {
            // grow the word by adding the value of growth to the beginning 
            // of the list, then set growth to null
            this.wormBody.add(0, this.growth) ;
            this.growth = null ;
        }
    } 
    
    // grow the worm by one piece
    public void grow() {
        // copy piece at the beginning of the list into the growth variable
        this.growth = new Piece(this.wormBody.get(0).getX(), this.wormBody.get(0).getY()) ;
    }
    
    // decide whether or not worm has run into the given piece
    public boolean runsInto(Piece piece) {
        
        for (Piece bodyPiece : this.wormBody) {
            if (bodyPiece.runsInto(piece)) {
                return true ;
            }
        }
        return false ;
        
        // need only check whether or not the head runs into the given piece
        //return this.wormBody.get(this.wormBody.size()-1).runsInto(piece) ;
        
        //if (this.wormBody.get(this.wormBody.size()-1).getX() == piece.getX()
        //        && this.wormBody.get(this.wormBody.size()-1).getY() == piece.getY()) {
        //    return true ;
        //}
        //return false ;
    }
    
    // decide whether or not worm has run into itself
    public boolean runsIntoItself() {
        
        // if only one piece in the worm, then it cannto run into itself
        if (this.wormBody.size() == 1) {
            return false ;
        }
        
        // need only check whether or not the head runs into any other piece
        // in the worm's body
        List<Piece> headlessBody = this.wormBody.subList(0, this.wormBody.size() - 2) ;
        for (Piece bodyPiece : headlessBody) {
            if (this.wormBody.get(this.wormBody.size()-1).runsInto(bodyPiece)) {
                return true ;
            }
        }
        return false ;
    }
}