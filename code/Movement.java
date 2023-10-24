package code;

import java.util.ArrayList;
class Movement{


    public static final int[] slideDirectionOffsets = {8,-8, 1, -1, 7, -7, 9, -9};
    public static final int[][] numSquaresToEdge = new int[8][8];

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
                numSquaresToEdge[squareIndex][4] = distRight;
            }
        }
    }


    public ArrayList<Move> generateMoves(Board bd){
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int startSquare = 0; startSquare < 64; startSquare ++){
            int piece = bd.getPieceOnSquare(startSquare);
            if (Piece.isColor(piece, Board.colorToMove)){
                if(Piece.isSlidingPiece(piece)){
                    generateSlidingMoves(startSquare, piece, bd);
                }
            }
        }
        return moves;
    }

    private static ArrayList<Move> generateSlidingMoves (int startSquare, int pieceId, Board bd){
        ArrayList<Move> slidingMoves = new ArrayList<Move>();
        for (int direction : slideDirectionOffsets){
            for (int n = 0; n < numSquaresToEdge[startSquare][direction]; n++){
                int targetSquare = startSquare + direction * (n + 1);
                int pieceOnTargetSquare = bd.getPieceOnSquare(targetSquare);
                if (Piece.isColor(pieceOnTargetSquare, Board.colorToMove)){
                    break;
                }
                slidingMoves.add(new Move (startSquare, targetSquare));
            }
        }
        return slidingMoves;
    }
}