package code;

import java.util.*;

public class Fen{
    public static void LoadPositionFromFen (String fen){
        Dictionary<Character, Integer> pieceTypeSymbolPairing = new Hashtable<>();
        
        pieceTypeSymbolPairing.put('k', Piece.King);
        pieceTypeSymbolPairing.put('p', Piece.Pawn);
        pieceTypeSymbolPairing.put('n', Piece.Knight);
        pieceTypeSymbolPairing.put('b', Piece.Bishop);
        pieceTypeSymbolPairing.put('r', Piece.Rook);
        pieceTypeSymbolPairing.put('q', Piece.Queen);

        String fenBoard = fen.split(" ")[0];
        int file = 0;
        int rank = 0;

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
                    int pieceType = pieceTypeFromSymbol[String.valueOf(symbol).toLowerCase()];
                    String.valueOf(symbol).toLowerCase();
                }
                
            }
        }
    }
}