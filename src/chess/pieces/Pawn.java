package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

    public Pawn(Board board, Color color){
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE){ // If the current piece is white
            p.setValues(position.getRow() -1, position.getColumn());    // sets the position 1 line above the piece
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){  // checks if the position exists AND there is no piece there
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }

            p.setValues(position.getRow() -2, position.getColumn());    // sets the position 2 line above the piece
            Position p2 = new Position(position.getRow() -1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) &&getMoveCount() == 0){  // checks if both position above the pawn exists AND there is no piece there AND the pawn never moved before
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }

            p.setValues(position.getRow() -1, position.getColumn() -1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){  // checks if the position exists AND there is no piece there
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }

            p.setValues(position.getRow() -1, position.getColumn() +1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){  // checks if the position exists AND there is no piece there
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }
        }else{
            p.setValues(position.getRow() +1, position.getColumn());    // sets the position 1 line above the piece
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){  // checks if the position exists AND there is no piece there
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }

            p.setValues(position.getRow() +2, position.getColumn());    // sets the position 2 line above the piece
            Position p2 = new Position(position.getRow() +1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) &&getMoveCount() == 0){  // checks if both position above the pawn exists AND there is no piece there AND the pawn never moved before
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }

            p.setValues(position.getRow() +1, position.getColumn() -1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){  // checks if the position exists AND there is no piece there
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }

            p.setValues(position.getRow() +1, position.getColumn() +1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){  // checks if the position exists AND there is no piece there
                mat[p.getRow()][p.getColumn()] = true;  // set that position as a possible move for the pawn
            }
        }
        return mat;
    }
    
    @Override
    public String toString(){
        return "P";
    }
}
