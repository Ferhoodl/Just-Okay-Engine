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

    static int heldPieceId = -1;
    static int heldPieceX = -1;
    static int heldPieceY = -1;

    public static void main(String[] args) throws IOException{

        Board gameBoard = new Board();

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
        JFrame chessUI = new JFrame();
        chessUI.setBounds(10, 10, 512, 512);
        //Board.makeBoard();
        gameBoard.setBoardFromFen(Board.startFEN);
        JPanel chessBoard = new JPanel(){ //The JPanel is our representaiton of the board, and is drawn to the gameBoard
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
                    int id = gameBoard.getPieceOnSquare(sq);
                    int pImg = getPieceImageIndexFromId(id); // img from pieceImageList
                    if(pImg != -1){
                        g.drawImage(pieceImageList[pImg], ((sq)%8)*64, (448-((int)(sq)/8)*64), this);
                    }
                }
                if (heldPieceId != -1 || heldPieceX != -1 || heldPieceY != -1){
                    System.out.println("held piece id: " + heldPieceId);
                    System.out.println("held piece X: " + heldPieceX);
                    System.out.println("held piece Y: " + heldPieceY);
                    g.drawImage(pieceImageList[getPieceImageIndexFromId(heldPieceId)], heldPieceX, heldPieceY, this);
                }
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
                //setHeldLocation(mse.getX(), mse.getY());
                heldPieceX = mse.getX();
                heldPieceY = mse.getY();
                heldPieceId = getPieceUnderMouse(mse.getX(), mse.getY(), gameBoard);
                chessBoard.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mse) {
                gameBoard.setPieceOnSquare(getSquareFromCoords(getXSquareFromMouseX(mse.getX()), getYSquareFromMouseY(mse.getY())), heldPieceId);
                heldPieceX = -1;
                heldPieceY = -1;
                heldPieceId = -1;
                chessBoard.repaint();
                
/*
                System.out.println("we've released!!!");
                System.out.println("heldPieceX after release: " + heldPieceX);
                System.out.println("heldPieceY after release: " + heldPieceY);
                System.out.println("heldPieceId after release: " + heldPieceId);
                */
                
            }

            @Override
            public void mouseClicked(MouseEvent mse) {
            }
        });

        chessUI.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent mse) {
                heldPieceX = mse.getX();
                heldPieceY = mse.getY();
                chessBoard.repaint();
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
    private static int getpieceIdClicked(int mseX, int mseY, Board bd){
        return getPieceUnderMouse(mseX, mseY, bd);
    }
    /*
    private static void setHeldLocation(int mseXLoc, int mseYLoc){
        heldPieceX = mseXLoc;
        heldPieceY = mseYLoc;
    }
*/

    private static int getPieceImageIndexFromId(int id){
        int pImg = -1;
        switch(id) {
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
        if (id > 16){
            pImg += 6;
        }
        return pImg;
    }
    
}