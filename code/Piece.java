package code;
public class Piece{
    public static final int None = 0;
    public static final int King = 1;
    public static final int Pawn = 2;
    public static final int Knight = 3;
    public static final int Bishop = 4;
    public static final int Rook = 5;
    public static final int Queen = 6;

    public static final int White = 8;
    public static final int Black = 16;
    public static int[] getPieceTypeFromFenSymbol;

    public static int getPieceTypeFromFenSymbol(String fSymbol){
        int returnedPiece = 0;
        switch(fSymbol) {
            case "k": // king
                returnedPiece = King;
                break;
            case "p": // pawn
                returnedPiece = Pawn;
                break;
            case "n": // knight
                returnedPiece = Knight;
                break;
            case "b": // bishop
                returnedPiece = Bishop;
                break;
            case "r": // rook
                returnedPiece = Rook;
                break;
            case "q": // queen
                returnedPiece = Queen;
                break;
        }
        return returnedPiece;
    }

    public static boolean isColor(int pieceId, String color){
        if (((pieceId-8 > 8) && color.equals("black")) || 
            (!(pieceId-8 > 8) && color.equals("white"))){
            return true;  
        }if (pieceId == 0){
            return false;
        }else{
            return false;
        }

    }
    public static boolean isSlidingPiece(int pieceId){
        boolean bool = true;
        switch (pieceId){
            case 12:
            case 13:
            case 20:
            case 21:
                bool = true;
        }
        return bool;
    }

}