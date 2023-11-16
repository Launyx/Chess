package Application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) {
        
        Scanner tec = new Scanner(System.in);
        ChessMatch chessmatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        
        while(!chessmatch.getCheckMate()){

            try{

                UI.clearScreen();
                UI.printMatch(chessmatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(tec);   // Call a method that read the chessposition given by the user and stores as a source position

                boolean[][] possibleMoves = chessmatch.possibleMoves(source);
                UI.printBoard(chessmatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(tec);   // Call a method that read the chessposition given by the user and stores as a target position

                ChessPiece capturedPiece = chessmatch.performChessMove(source, target); // uses past given positions to perform a move and store a possible captured piece

                if (capturedPiece != null){
                    captured.add(capturedPiece);
                }

                if (chessmatch.getPromoted() != null){

                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    String type = tec.nextLine().toUpperCase();
                    while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")){
                        System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        type = tec.nextLine().toUpperCase();
                    }   
                    chessmatch.replacePromotedPiece(type);
                }
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
        UI.clearScreen();
        UI.printMatch(chessmatch, captured);
    }
}
