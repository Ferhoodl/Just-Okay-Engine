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

        String fenBoard = fen.split(" ")[0]; // do I need this? I think it is to get rid of extra spaces in a string. Not sure.
        int file = 0;
        int rank = 0;

        for (int i = 0; i < fenBoard.length(); i++){
            char symbol = fenBoard.charAt(i);
            if (symbol == '/'){
                file = 0;
                
            }
        }
    }
}