package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }
    
    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){ // Method to tell if the king can move to the given position
        ChessPiece p = (ChessPiece) getBoard().piece(position); // Get the piece in the given position
        return p == null || p.getColor() != getColor(); //Return true if the position is null (there is no piece) OR the color of the piece is different from the king
    
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);

        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

        // #Specialmove castling

        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            // #Specialmove castling Kingside rook
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posT1)){
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }
        }

        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            // #Specialmove castling Queenside rook
            Position posT1 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posT1)){
                // Getting position to the left of the king, making sure the King and the Rook can do a Castling
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){   // Verify if 3 columns to the left of the king are null (don't have pieces)
                    mat[position.getRow()][position.getColumn() - 2] = true;    
                }
            }
        }

        return mat;
    }
}
