package Application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {
        
        Scanner tec = new Scanner(System.in);
        ChessMatch chessmatch = new ChessMatch();
        
        while(true){
            UI.printBoard(chessmatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(tec);   // Call a method that read the chessposition given by the user and stores as a source position

            System.out.println();
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(tec);   // Call a method that read the chessposition given by the user and stores as a target position

            ChessPiece capturedPiece = chessmatch.performChessMove(source, target); // uses past given positions to perform a move and store a possible captured piece
        }
    }
}
