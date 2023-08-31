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

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){    // Methods that checks the matrix of moves if there is a possible one
        boolean[][] mat = possibleMoves();

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
