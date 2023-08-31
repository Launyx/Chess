package Application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static ChessPosition readChessPosition(Scanner tec){     // Method to get the position given by the user (ex: the user gives the position with "a1", "b5")
        try{
            String s = tec.nextLine();  // gets the chess position given by the user through the scanner and stores it in a string
            char column = s.charAt(0);  // Gets the first char of the chess position ("a1") and stores it as a column
            int row = Integer.parseInt(s.substring(1)); // Gets the string from the first position onward parsing it to a integer and stores it as a row
            return new ChessPosition(column, row);  
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8"); // Throws a exception if the input is wrong
        }
    }

    public static void printBoard(ChessPiece[][] pieces){   // Method that shows the board on the terminal
        for(int i = 0; i < pieces.length; i++){
            System.out.print((8 - i ) + " ") ;  // prints the side edge of the board
            for(int j = 0; j < pieces.length; j++){
                printPiece(pieces[i][j]);   // Call the method that prints the pieces in the UI based on the matrix of the board
            }
            System.out.println();   // breaks the line
        }
        System.out.print("  a b c d e f g h");  // prints the bottom edge of the board
    }

    private static void printPiece(ChessPiece piece) {
    	if (piece == null) {    // Checks which piece is the given one
            System.out.print("-");  // if the position in the board's matrix is null, this line is shown
        }
        else {
            if (piece.getColor() == Color.WHITE) {  // checks the color of the piece
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");  // print spaces between the positions of the board
	}
}
