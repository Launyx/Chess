package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }
    

    @Override
    public String toString(){
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {    // Method to check the possible move for this piece
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);    // Instantiate a new position p, to use as a checker for possible moves for the Rook

        // above
        p.setValues(this.position.getRow() - 1, this.position.getColumn());   // set new values for the position "p", in this case, to one row above

        // loop to check the matrix of the board
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){    // While the position "p" exists in the board AND there is no piece in that position
            mat[p.getRow()][p.getColumn()] = true;                              // Sets the position as true, making it a possible move to the Rook
            p.setRow(p.getRow() - 1);                                           // sets a new row to the position "p", until it finds the end of the board or a piece
        }

        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){    // checks if the position "p" stopped in the last loop exists AND if its an opponent piece
            mat[p.getRow()][p.getColumn()] = true;                   // if the position exists and it is an enemy, marks the position in the matrix as true
        }

        // left
        p.setValues(this.position.getRow(), this.position.getColumn() - 1);   // set new values for the position "p", in this case, to one column to the left

        // loop to check the matrix of the board
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){    // While the position "p" exists in the board AND there is no piece in that position
            mat[p.getRow()][p.getColumn()] = true;                              // Sets the position as true, making it a possible move to the Rook
            p.setColumn(p.getColumn() - 1);                                     // sets a new column to the position "p", until it finds the end of the board or a piece
        }

        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){    // checks if the position "p" stopped in the last loop exists AND if its an opponent piece
            mat[p.getRow()][p.getColumn()] = true;                   // if the position exists and it is an enemy, marks the position as true
        }
        
        // Right
        p.setValues(this.position.getRow(), this.position.getColumn() + 1);   // set new values for the position "p", in this case, to one column to the right

        // loop to check the matrix of the board
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){    // While the position "p" exists in the board AND there is no piece in that position
            mat[p.getRow()][p.getColumn()] = true;                              // Sets the position as true, making it a possible move to the Rook
            p.setColumn(p.getColumn() + 1);                                     // sets a new column to the position "p", until it finds the end of the board or a piece
        }

        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){    // checks if the position "p" stopped in the last loop exists AND if its an opponent piece
            mat[p.getRow()][p.getColumn()] = true;                   // if the position exists and it is an enemy, marks the position as true
        }

        // Below

        p.setValues(this.position.getRow() + 1, this.position.getColumn());   // set new values for the position "p", in this case, to one column to the right

        // loop to check the matrix of the board
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){    // While the position "p" exists in the board AND there is no piece in that position
            mat[p.getRow()][p.getColumn()] = true;                              // Sets the position as true, making it a possible move to the Rook
            p.setRow(p.getRow() + 1);                                           // sets a new row to the position "p", until it finds the end of the board or a piece
        }

        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){    // checks if the position "p" stopped in the last loop exists AND if its an opponent piece
            mat[p.getRow()][p.getColumn()] = true;                   // if the position exists and it has an opponent piece, marks the position as true so the piece can be caputered
        }

        return mat;
    }
}

