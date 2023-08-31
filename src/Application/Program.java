package Application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {
        
        Scanner tec = new Scanner(System.in);
        ChessMatch chessmatch = new ChessMatch();
        
        while(true){

            try{

                UI.clearScreen();
                UI.printBoard(chessmatch.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(tec);   // Call a method that read the chessposition given by the user and stores as a source position

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(tec);   // Call a method that read the chessposition given by the user and stores as a target position

                ChessPiece capturedPiece = chessmatch.performChessMove(source, target); // uses past given positions to perform a move and store a possible captured piece
            }
            catch (ChessException e){
                System.out.println(e.getMessage());
                tec.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
                tec.nextLine();
            }
        }
    }
}
