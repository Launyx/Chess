package boardgame;

public abstract class Piece {
    protected Position position;
    private Board board;


    public Piece(Board board) {
        this.board = board;
        this.position = null;
    }


    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();    // abstract method because this is a too generic class to work with the move of a piece
                                                    // This method is implemented in every different type of piece, as each one get their own possible moves

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){    // Methods that checks the matrix of moves if there is a possible one
        boolean[][] mat = possibleMoves();  // gets the matrix of trues and falses for the possible moves for the piece that is calling it

        for (int i = 0; i < mat.length; i++){   
            for (int j = 0; j < mat.length; j ++){  // Two loops to check every element in the matrix
                if (mat[i][j]){     // Checks if the place in the matrix is true
                    return true;    // returns true if any place in the matrix is a possible move
                }
            }
        }
        return false;
    }
}
