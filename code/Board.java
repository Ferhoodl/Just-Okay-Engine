package code;
import java.util.Arrays;
public class Board {

    private static int[] Squares;

    public static void makeBoard(){
        Squares = new int[64];
        Arrays.fill(Squares, 0);

        Squares[48] = Piece.Black | Piece.Pawn;
        Squares[51] = Piece.Black | Piece.Pawn;
        Squares[55] = Piece.Black | Piece.Pawn;


        Squares[59] = Piece.Black | Piece.Queen;
        Squares[63] = Piece.Black | Piece.Rook;


        Squares[8] = Piece.White | Piece.Pawn;
        Squares[11] = Piece.White | Piece.Pawn;
        Squares[15] = Piece.White | Piece.Pawn;

        Squares[0] = Piece.White | Piece.Rook;
        Squares[1] = Piece.White | Piece.Knight;
        Squares[4] = Piece.White | Piece.King;
        Squares[5] = Piece.White | Piece.Bishop;
        Squares[5] = Piece.White | Piece.Bishop;
    }

    public static int getSquare(int square){
        return Squares[square];
    }

    public static int[] getSquares(){
        return Squares;
    }
}