package code;
class Move{
    public int startSquare;
    public int targetSquare;

    Move(int start, int target){
        startSquare = start;
        targetSquare = target;
    }
    public int getStartSquare(){
        return startSquare;
    }
    public int getTargetSquare(){
        return targetSquare;
    }
}