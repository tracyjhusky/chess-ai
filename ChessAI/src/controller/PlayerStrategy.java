package controller;

import model.Board;
import model.Color;
import model.Location;
import model.pieces.Piece;

import java.util.Scanner;

public class PlayerStrategy implements Strategy{
    Board board;
    Color color;

    public PlayerStrategy(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    public void takeTurn() {
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();
        System.out.println(this.execute(cmd));
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    private String execute(String cmd) {

        if(cmd.length() < 5) {
            return "Invalid command.";
        }
        if(cmd.length() == 5) {
            int file0 = Location.getFileNumber(cmd.charAt(0));
            int rank0 = Character.getNumericValue(cmd.charAt(1));
            int file1 = Location.getFileNumber(cmd.charAt(3));
            int rank1 = Character.getNumericValue(cmd.charAt(4));

            Location loc0 = new Location(file0, rank0);
            Location loc1 = new Location(file1, rank1);
            try {
                board.moveFromTo(loc0, loc1);
                return "Moved.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
        if(cmd.length() > 6) {
            int file0 = Location.getFileNumber(cmd.charAt(0));
            int rank0 = Character.getNumericValue(cmd.charAt(1));
            int file1 = Location.getFileNumber(cmd.charAt(3));
            int rank1 = Character.getNumericValue(cmd.charAt(4));

            Location loc0 = new Location(file0, rank0);
            Location loc1 = new Location(file1, rank1);
            String type = cmd.substring(6);
            try {
                board.promote(loc0, loc1, type);
                return "Promoted.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
        return "Invalid command.";
    }
}
