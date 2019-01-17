package wormgame.gui;

import wormgame.game.* ;
import wormgame.domain.* ;

import javax.swing.JPanel ;
import java.awt.Graphics ;
import java.awt.Color ;

public class DrawingBoard extends JPanel implements Updatable {
    
    // attributes
    private WormGame wormGame ;
    private int pieceLength ;
    
    // constructor with figure input
    public DrawingBoard(WormGame wormGame, int pieceLength) {
        this.wormGame = wormGame ;
        this.pieceLength = pieceLength ;
    }
    
    // draw the worm and apple
    // required to inherit JPanel
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics) ;
        
        // background should be gray
        super.setBackground(Color.GRAY) ;
        
        // draw the apple in red
        graphics.setColor(Color.RED) ;
        graphics.fillOval(this.wormGame.getApple().getX() * this.pieceLength,
                this.wormGame.getApple().getY() * this.pieceLength,
                this.pieceLength, this.pieceLength) ;
        
        // draw the worm in black
        graphics.setColor(Color.BLACK) ;
        for (Piece piece : this.wormGame.getWorm().getPieces()) {
            graphics.fill3DRect(piece.getX() * this.pieceLength, 
                    piece.getY() * this.pieceLength,
                    this.pieceLength, this.pieceLength, true) ;
        }
    }
    
    // update the graphics
    // required to implement Updatable
    @Override
    public void update() {
        
        // only needs to call the JPanel method repaint()
        super.repaint() ;
    }
}