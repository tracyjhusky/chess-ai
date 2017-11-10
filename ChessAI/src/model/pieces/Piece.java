package model.pieces;

import model.Board;
import model.Color;
import model.Location;
import model.Move;

import java.util.List;
import java.util.Map;

public abstract class Piece {

    public static String queen = "Q";
    public static String king = "K";
    public static String rook = "R";
    public static String knight = "Kn";
    public static String bishop = "B";
    public static String pawn = "p";

    public static String[] names =
            {queen,
                    king,
                    rook,
                    knight,
                    bishop,
                    pawn};

    public static int queenValue = 9;
    public static int kingValue = 999;
    public static int rookValue = 5;
    public static int knightValue = 3;
    public static int bishopValue = 3;
    public static int pawnValue = 1;

    private Board board;

    private Location location;
    private Color color;

    public Piece(Board board, Location location, Color color) {
        this.board = board;
        this.location = location;
        this.color = color;
    }

    public static Piece makePiece(Board board, Location location, Color color, String type) {
        if(type.equals(pawn)) {
            return new Pawn(board, location, color);
        }
        throw new IllegalArgumentException("Invalid piece!");
    }

    public Board getBoard() {
        return this.board;
    }

    public Location getLocation() {
        return location;
    }

    public Color getColor() {
        return color;
    }

    public abstract int getValue();

    public abstract Map<Location, Move> legalMoves();

    public void moveTo(Location location) throws IllegalArgumentException {
        Move move = this.legalMoves().get(location);
        if(move == null) {
            throw new IllegalArgumentException("Invalid move");
        }
        move.perform();
    }

    public void promote(Location location, String type) throws IllegalArgumentException {
        Move move = this.legalMoves().get(location);
        if(move == null) {
            throw new IllegalArgumentException("Invalid move");
        }
        move.promote(type);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isEnemy(Piece other) {
        return this.color != other.color;
    }

    public abstract String getName();

    public String toString() {
        return this.getName();
    }
}
