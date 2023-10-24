package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {
    
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

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

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
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
        

        if (testCheck(currentPlayer)){  // Calls the method to verify if the currentplayer's king is in check
            undoMove(source, target, capturedPiece);    // if the king is in check, calls the undoMove
            throw new ChessException("You can't put yourself in check");    // and throws an exception
        }

        check = (testCheck(opponent(currentPlayer))) ? true: false;
        
        if (testCheckMate(opponent(currentPlayer))){    // if the opponent of the piece that moved is in checkmate
            checkMate = true;                           // checkMate receives true
        }else{
            nextTurn();
        }
        
        return (ChessPiece)capturedPiece;   // returns the captured piece with downcast
    }

    private Piece makeMove(Position source, Position target){   // Method that moves the pieces
        ChessPiece p = (ChessPiece)board.removePiece(source);    // remove the piece in the source position and stores it in a variable
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);    // remove the possible piece in the target position
        board.placePiece(p, target);    // place the piece of the source position in the target position
        
        if (capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        
        return capturedPiece;   // return the possible captured piece
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){
        ChessPiece p = (ChessPiece)board.removePiece(target);    // Removes the piece in the target position and stores it in a variable
        p.decreaseMoveCount();
        board.placePiece(p, source);    // Return the piece to the source position

        if (capturedPiece != null){
            board.placePiece(capturedPiece, target);    // Place the piece
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
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

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == color).collect(Collectors.toList());  // Filter the list of pieces on the board
    
        for (Piece p: list){
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();    // Get the king's position of the given color
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());  // Get the list of opponent's pieces
    
        for (Piece p: opponentPieces){
            boolean[][] mat = p.possibleMoves();    // gets all the possible moves of the opponent's pieces
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]){     // verify the matrix if its has the position of the king
                return true;
            }  
        }
        return false;
    }

    private boolean testCheckMate(Color color){
        if (!testCheck(color)){     // if the color of the piece is not in check
            return false;           // returns false
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == color).collect(Collectors.toList());  // Filter the list of pieces on the board
        for (Piece p : list){
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i<board.getRows(); i++){
                for (int j = 0; j<board.getColumns(); j++){
                    if(mat[i][j]){      //checks if the position in the matrix is a possible move
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();  //Downcast to get the piece's position, cause "Position" is a protected atribute and isn't in the same package
                                                                                            //getting the chessposition and converting it with toPosition
                        Position target = new Position(i, j);
                        
                        // The logic below is that a given piece is moved to the target position, it's tested if the color of this piece is in check then the move is undone
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck){    //if the testcheck above returns false, it means the move of the given piece can stop a check
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));

    }
}
