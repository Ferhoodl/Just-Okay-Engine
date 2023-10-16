package code;
/* 
 * Main file of JOE. JOE stands for JOE Open Engine.
 * Yes, ladies and gentlemen, you just saw a redundant
 * acronym. Now you really know I'm cool.
 */

 // import necessary gui things
 // for drawing board
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

// for moving pieces 
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.*;
import java.io.FileFilter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// don't know what this is here for, should take it out
import java.util.HashMap;

// for piece image extraction
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import java.util.*;




// should be the main logic of game.
public class Game {
    
    //define square colors here
    static Color darkSquareColor = new Color(140,162,173); // theme from Lichess website
    static Color lightSquareColor = new Color(222,227,230);

    public static void main(String[] args) throws IOException{

        //Draw pieces here
        BufferedImage imageReader = ImageIO.read(new File("assets/pieces.png"));
        Image pieceImageList[] = new Image[12];
        
        
        int iter = 0;
        for (int y=0; y<400; y+=200){
            for(int x=0; x<1200; x+=200){
                pieceImageList[iter] = imageReader.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                iter++;
            }
        }

        // create board here
        JFrame chessBoard = new JFrame();
        chessBoard.setBounds(10, 10, 512, 512);
        Board.makeBoard();
        JPanel thing = new JPanel(){ // called "thing" because I don't know what it is
            @Override
            public void paint(Graphics g){
                for(int y=0; y<8; y++){
                    for(int x=0; x<8; x++){ // iterate through each square on the board
                        if(((y+x)%2) == 1){ // decide if we're coloring a dark or light square
                            g.setColor(darkSquareColor);
                        }else{
                            g.setColor(lightSquareColor);
                        }
                        g.fillRect(x*64, y*64, 64, 64); // place colored square on board
                    }
                }
                for(int sq = 0; sq < 64; sq++){
                    int pImg = 0; // img from pieceImageList
                    switch(Board.getSquare(sq)) {
                        case 9: // king
                        case 17:
                            pImg = 0;
                            break;
                        case 10: // pawn
                        case 18:
                            pImg = 5;
                            break;
                        case 11: // knight
                        case 19:
                            pImg = 3;
                            break;
                        case 12: // bishop
                        case 20:
                            pImg = 2;
                            break;
                        case 13: // rook
                        case 21:
                            pImg = 4;
                            break;
                        case 14: // queen
                        case 22:
                            pImg = 1;
                            break;
                        case 0:
                            pImg = -1;
                            break;
                    }
                    if (Board.getSquare(sq) > 16){
                        pImg += 6;
                    }
                    if(pImg != -1){
                        g.drawImage(pieceImageList[pImg], ((sq)%8)*64, (448-((int)(sq)/8)*64), this);
                        
                    }
                }
            }
        };
        chessBoard.add(thing);
        chessBoard.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }
        });

        chessBoard.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent arg0) {
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {
            }
        });


        chessBoard.setDefaultCloseOperation(3);
        chessBoard.setVisible(true);
        
    }
    /*
    public static int getPiece(int x, int y, Board bd){
        int xPiece = x/64;
        int yPiece = y/64;
        //int piece = bd.getSquare(((int)yPiece/8)+(xPiece-1))
        int piece;
        piece = Board.getSquare(((int)yPiece/8)+(xPiece-1));
        return piece;
    }
    */
}