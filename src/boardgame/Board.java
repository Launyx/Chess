package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;
    
    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1){   // makes sure the board is at least 1x1
            throw new BoardException("Error creating board: there must be at least 1 row and 1 columns");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];  // initialize the matrix based on the given numbers of rows and columns
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    
    public Piece piece(int row, int column){
        if(!positionExists(row, column)){   // Call a method that checks if the given position exists
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column]; // Return the piece that there is in the given row and column
    }

    public Piece piece(Position position){
        if(!positionExists(position)){  // Call a method that checks if the given position exists
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()]; // Returns the piece that there is in the given position
    }

    public void placePiece(Piece piece, Position position){ // Method to place a piece on the board, given the piece and the position
        if(thereIsAPiece(position)){    // Call a method that checks if there is a piece on the given position
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece; // places a given piece on the board (matrix of pieces and nulls) by getting the row and column of the given position
        piece.position = position;  // sets the given position to the given piece

    }

    public Piece removePiece(Position position){    // Methos to remove a piece on the board, given the position
        if (!positionExists(position)){ // Call a method that checks if the given position exists
            throw new BoardException("Position not on the board");
        }
        if (piece(position) == null){   // Checks if there is a piece in the given position
            return null;
        }
        Piece aux = piece(position);    // aux receives the piece in the given position
        aux.position = null;    // gives null to the position of the piece, as if the piece  was removed
        pieces[position.getRow()][position.getColumn()] = null; // Makes the position in the matrix null, as if the piece was removed

        return aux; // ???
    }

    private boolean positionExists(int row, int column){    // Method that checks if the position exists, given the row and column
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position){   // Method that checks if the given position exists
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){    // Methods that checks if the given position has a piece
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }

    
}
