import controller.ComputerStrategy;
import controller.PlayerStrategy;
import controller.Strategy;
import model.Board;
import model.Color;
import model.Location;
import model.pieces.Pawn;
import model.pieces.Piece;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Strategy white = new PlayerStrategy(board, Board.WHITE);
        Strategy black = new ComputerStrategy(board, Board.BLACK, 5);
        Scanner scanner = new Scanner(System.in);

        board.setUpRegularGame();
        System.out.println(board  + "\n\n");

        while(true) {
            white.takeTurn();
            System.out.println(board);
            black.takeTurn();
            System.out.println(board);
        }
    }
}
