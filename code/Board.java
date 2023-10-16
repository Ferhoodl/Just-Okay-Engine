package code;
import java.util.*;
public class Board {

    private static int[] squares;
    public final static String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static void makeBoard(){
        squares = new int[64];
        Arrays.fill(squares, 0);

        squares[48] = Piece.Black | Piece.Pawn;
        squares[51] = Piece.Black | Piece.Pawn;
        squares[55] = Piece.Black | Piece.Pawn;


        squares[59] = Piece.Black | Piece.Queen;
        squares[63] = Piece.Black | Piece.Rook;


        squares[8] = Piece.White | Piece.Pawn;
        squares[11] = Piece.White | Piece.Pawn;
        squares[15] = Piece.White | Piece.Pawn;

        squares[0] = Piece.White | Piece.Rook;
        squares[1] = Piece.White | Piece.Knight;
        squares[4] = Piece.White | Piece.King;
        squares[5] = Piece.White | Piece.Bishop;
        squares[5] = Piece.White | Piece.Bishop;
    }

    public static int getSquare(int square){
        return squares[square];
    }

    public static int[] getSquares(){
        return squares;
    }

    public static void setBoardFromFen (String fen){

        squares = new int[64];
        Arrays.fill(squares, 0);

        Dictionary<Character, Integer> pieceTypeSymbolPairing = new Hashtable<>();
        
        pieceTypeSymbolPairing.put('k', Piece.King);
        pieceTypeSymbolPairing.put('p', Piece.Pawn);
        pieceTypeSymbolPairing.put('n', Piece.Knight);
        pieceTypeSymbolPairing.put('b', Piece.Bishop);
        pieceTypeSymbolPairing.put('r', Piece.Rook);
        pieceTypeSymbolPairing.put('q', Piece.Queen);

        String fenBoard = fen.split(" ")[0];
        int file = 0;
        int rank = 7;

        for (int i = 0; i < fenBoard.length(); i++){
            char symbol = fenBoard.charAt(i);
            if (symbol == '/'){
                file = 0;
                rank --;
            } else {
                if (Character.isDigit(String.valueOf(symbol).charAt(0))){ // backup code for this: (symbol >= '0' && symbol <= '9')
                    file += (int)symbol;
                }else {
                    int pieceColor = (Character.isUpperCase(symbol)) ? Piece.White : Piece.Black;
                    int pieceType = Piece.getPieceTypeFromFenSymbol(String.valueOf(symbol).toLowerCase());
                    squares[rank * 8 + file] = pieceType | pieceColor;
                    file++;
                }
                
            }
        }
    }

}