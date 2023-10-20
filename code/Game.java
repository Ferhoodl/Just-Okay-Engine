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

        Board gameBoard = new Board();

        //Draw pieces here
        BufferedImage imageReader = ImageIO.read(new File("assets/pieces.png"));
        Image pieceImageList[] = new Image[12];
        
        int heldPieceId;
        

        int iter = 0;
        for (int y=0; y<400; y+=200){
            for(int x=0; x<1200; x+=200){
                pieceImageList[iter] = imageReader.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                iter++;
            }
        }

        // create board here
        JFrame chessUI = new JFrame();
        chessUI.setBounds(10, 10, 512, 512);
        //Board.makeBoard();
        gameBoard.setBoardFromFen(Board.startFEN);
        JPanel chessBoard = new JPanel(){ //The JPanel is our representaiton of the board, and is drawn to the
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
                    int pImg = getPieceIndexFromId(sq, gameBoard); // img from pieceImageList
                    if(pImg != -1){
                        g.drawImage(pieceImageList[pImg], ((sq)%8)*64, (448-((int)(sq)/8)*64), this);
                    }
                }
                //g.drawImage(pieceImageList[])
            }
        };
        chessUI.add(chessBoard);
        chessUI.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent mse) {
            }

            @Override
            public void mouseExited(MouseEvent mse) {
            }

            @Override
            public void mousePressed(MouseEvent mse) {
                System.out.println(getPieceUnderMouse(mse.getX(), mse.getY(), gameBoard));

                chessBoard.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mse) {
            }

            @Override
            public void mouseClicked(MouseEvent mse) {
            }
        });

        chessUI.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent mse) {
            }

            @Override
            public void mouseMoved(MouseEvent mse) {
            }
        });


        chessUI.setDefaultCloseOperation(3);
        chessUI.setVisible(true);
        
    }
    
    private static int getPieceUnderMouse(int x, int y, Board bd){
        int xPiece = getXSquareFromMouseX(x);
        int yPiece = getYSquareFromMouseY(y);
        int piece = bd.getPieceOnSquare(getSquareFromCoords(xPiece, yPiece));
        return piece;
    }

    private static int getSquareFromCoords(int x, int y){
        return ((int)y*8)+(x);
    }

    private static int getXSquareFromMouseX(int mouseX){
        return mouseX/64;
    }

    private static int getYSquareFromMouseY(int mouseY){
        return (7 - (mouseY/64));
    }


    private static int getPieceIndexFromId(int sq, Board bd){
        int pImg = -1;
        switch(bd.getPieceOnSquare(sq)) {
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
        if (bd.getPieceOnSquare(sq) > 16){
            pImg += 6;
        }
        return pImg;
    }
    
}