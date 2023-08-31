package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece{ // this class became bastract because it extends Piece but is too generic to implement the possibleMoves() method

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
