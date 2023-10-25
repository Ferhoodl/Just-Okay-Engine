package code;

import java.util.ArrayList;
class Movement{


    public static final int[] slideDirectionOffsets = {8,-8, -1, 1, 7, -7, 9, -9};
    public static final int[][] numSquaresToEdge = new int[64][8];

    public static void precomputeMoveData(){
        for (int file = 0; file < 8; file ++){
            for (int rank = 0; rank < 8; rank ++){

                int distUp = 7 - rank;
                int distDown = rank;
                int distLeft = file;
                int distRight = 7 - file;
                
                int squareIndex = rank * 8 + file;

                numSquaresToEdge[squareIndex][0] = distUp;
                numSquaresToEdge[squareIndex][1] = distDown;
                numSquaresToEdge[squareIndex][2] = distLeft;
                numSquaresToEdge[squareIndex][3] = distRight;
            }
        }
    }


    public static ArrayList<Move> generateMoves(Board bd){
        Movement.precomputeMoveData();
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int startSquare = 0; startSquare < 64; startSquare ++){
            int piece = bd.getPieceOnSquare(startSquare);
            if (Piece.isColor(piece, Board.colorToMove)){
                if(Piece.isSlidingPiece(piece)){
                    moves.addAll(generateSlidingMoves(startSquare, piece, bd));
                }
            }
        }
        return moves;
    }

    private static ArrayList<Move> generateSlidingMoves (int startSquare, int pieceId, Board bd){
        ArrayList<Move> slidingMoves = new ArrayList<Move>();
        for (int directionIndex = 0; directionIndex < 8; directionIndex++){

            for (int n = 0; n < numSquaresToEdge[startSquare][directionIndex]; n++){
                int targetSquare = startSquare + slideDirectionOffsets[directionIndex] * (n + 1);
                int pieceOnTargetSquare = bd.getPieceOnSquare(targetSquare);
                if (Piece.isColor(pieceOnTargetSquare, Board.colorToMove)){
                    break;
                }
                Move moove;
                moove = new Move(startSquare, targetSquare);
                slidingMoves.add(moove);
                System.out.println("Hello, we've got a move. the start square is: " + startSquare);
                System.out.println("Hello, we've got a move. the target square is: " + targetSquare);
            }
        }
        return slidingMoves;
    }

    public static boolean isLegal(Board bd, int startSq, int threatSq){
        ArrayList<Move> moves = Movement.generateMoves(bd);
        for(Move move : moves){
            if (move.getStartSquare() == startSq && move.getTargetSquare() == threatSq){
                return true;
            }
        }
        return false;
    }
}