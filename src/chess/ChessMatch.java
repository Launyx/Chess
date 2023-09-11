package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    
    private int turn;
    private Color currentPlayer;
    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++){
            for(int j = 0; j < board.getColumns(); j++){
                mat[i][j] = (ChessPiece)board.piece(i, j);
            }
        }
        return mat;
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){ // Method that perform the move of a piece
        Position source = sourcePosition.toPosition(); // ??
        Position target = targetPosition.toPosition(); // ??

        validateSourcePosition(source); // Calls a method to check there is a piece in the given source position
        validadeTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target); // calls a method to move a piece and stores a possible captured piece in the given target position
        nextTurn();
        return (ChessPiece)capturedPiece;   // returns the captured piece with downcast
    
    }

    private Piece makeMove(Position source, Position target){   // Method that moves the pieces
        Piece p = board.removePiece(source);    // remove the piece in the source position and stores it in a variable
        Piece capturedPiece = board.removePiece(target);    // remove the possible piece in the target position
        board.placePiece(p, target);    // place the piece of the source position in the target position
        return capturedPiece;   // return the possible captured piece
    }

    private void validateSourcePosition(Position position){     // Method to check if the source position
        if (!board.thereIsAPiece(position)){    // checks if there is a piece on the position
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()){ // if the color of the currentPlayer is different from the color of the piece in the source position
            throw new ChessException("The chosen piece is not yours");
        }    
        if (!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece");
        }  
    }

    private void validadeTargetPosition(Position source, Position target){  // Method to check if the piece can move to the target position
        if (!board.piece(source).possibleMove(target)){ // Verify if the piece in the source position can move to the target position
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; // if the currentPlayer is white, the next turn the player is black, if not, the next turn the player is white
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
