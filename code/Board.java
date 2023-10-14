package code;
import java.util.Arrays;
public class Board {

    private static int[] Squares;

    public static void makeBoard(){
        Squares = new int[64];
        Arrays.fill(Squares, 0);

        Squares[0] = Piece.Black | Piece.Rook;
        Squares[1] = Piece.Black | Piece.Knight;
        Squares[4] = Piece.Black | Piece.King;
        Squares[5] = Piece.Black | Piece.Bishop;
        Squares[5] = Piece.Black | Piece.Bishop;

        Squares[8] = Piece.Black | Piece.Pawn;
        Squares[11] = Piece.Black | Piece.Pawn;
        Squares[15] = Piece.Black | Piece.Pawn;


        Squares[48] = Piece.White | Piece.Pawn;
        Squares[51] = Piece.White | Piece.Pawn;
        Squares[55] = Piece.White | Piece.Pawn;


        Squares[59] = Piece.White | Piece.Queen;
        Squares[63] = Piece.White | Piece.Rook;
    }

    public static int getSquare(int square){
        return Squares[square];
    }

    public static int[] getSquares(){
        return Squares;
    }
}