package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    public King(Board board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){ // Method to tell if the king can move to the given position
        ChessPiece p = (ChessPiece) getBoard().piece(position); // Get the piece in the given position
        return p == null || p.getColor() != getColor(); //Return true if the position is null (there is no piece) OR the color of the piece is different from the king
    
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];   // makes a matrix the same size of the board filled with falses
        
        Position p = new Position(0, 0); // Auxiliar position
        
        // Above
        p.setValues(this.position.getRow() - 1, this.position.getColumn()); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position above the king in the matrix os possible moves as true
        }

        // Below
        p.setValues(this.position.getRow() + 1, this.position.getColumn()); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position below the king in the matrix os possible moves as true
        }

        // Left
        p.setValues(this.position.getRow(), this.position.getColumn() - 1); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position to the left of the king in the matrix os possible moves as true
        }

        // Right
        p.setValues(this.position.getRow(), this.position.getColumn() + 1); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position to the right of the king in the matrix os possible moves as true
        }

        // NW
        p.setValues(this.position.getRow() -1, this.position.getColumn() - 1); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position to the north west of the king in the matrix os possible moves as true
        }

        // NE
        p.setValues(this.position.getRow() -1, this.position.getColumn() + 1); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position to the north east of the king in the matrix os possible moves as true
        }

        // SW
        p.setValues(this.position.getRow() + 1, this.position.getColumn() - 1); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position to the south west of the king in the matrix os possible moves as true
        }

        // SE
        p.setValues(this.position.getRow() + 1, this.position.getColumn() + 1); // Gets the position above the king
        if (getBoard().positionExists(p) && canMove(p)){    // if the position set to the auxiliar exists and the "canMove" method returns true, the king can move above
            mat[p.getRow()][p.getColumn()] = true;  // set the position to the south east of the king in the matrix os possible moves as true
        }

        return mat;
    }
}
