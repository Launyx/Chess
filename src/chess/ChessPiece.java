package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{ // this class became bastract because it extends Piece but is too generic to implement the possibleMoves() method

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);  // Gets the piece that is in the given position and stores it in a variable
        return p != null && p.getColor() != this.color;
    }
}
