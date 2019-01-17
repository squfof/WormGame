package wormgame.domain;

public class Piece {
    
    // attributes
    private int xCoordinate ;
    private int yCoordinate ;
    
    // constructor with inputs
    public Piece(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate ;
        this.yCoordinate = yCoordinate ;
    }
    
    // return the x-coordinate
    public int getX() {
        return this.xCoordinate ;
    }
    
    // return the y-coordinate
    public int getY() {
        return this.yCoordinate ;
    }
    
    // returns true if this piece and that piece has the same x- and y-coordinates
    public boolean runsInto(Piece otherPiece) {
        if (this.xCoordinate == otherPiece.getX()
                && this.yCoordinate == otherPiece.getY()) {
            return true ;
        }
        return false ;
    }
    
    // return the (x,y) coordinates as a string
    @Override
    public String toString() {
        return "(" + this.xCoordinate + "," + this.yCoordinate + ")" ;
    }   
}