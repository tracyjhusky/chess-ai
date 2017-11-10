package model;

import model.pieces.Pawn;
import model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int MAX_FILE = 8;
    public static final int MAX_RANK = 8;
    private Piece highlight;
    private Color turn;

    public static Color WHITE = new Color("w");
    public static Color BLACK = new Color("b");

    Map<Location, Piece> pieces;
    List<Piece> whitePieces;
    List<Piece> blackPieces;

    public Board() {
        pieces = new HashMap<Location, Piece>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        turn = Board.WHITE;
    }

    public void highlight(Piece piece) {
        this.highlight = piece;
    }

    public void unhighlight() {
        this.highlight = null;
    }

    public void place(Piece piece) {
        pieces.put(piece.getLocation(), piece);
        if(piece.getColor() == Board.WHITE) {
            this.whitePieces.add(piece);
        }
        else {
            this.blackPieces.add(piece);
        }
    }

    public void setUpRegularGame() {
        for(int i = 1; i <= Board.MAX_FILE; i++) {
            Piece wp = new Pawn(this, new Location(i,2), Board.WHITE);
            this.place(wp);
        }

        for(int i = 1; i <= Board.MAX_FILE; i++) {
            Piece bp = new Pawn(this, new Location(i,7), Board.BLACK);
            this.place(bp);
        }
    }

    public Color turn() {
        return this.turn;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void remove(Piece piece) {
        pieces.remove(piece.getLocation());
        if (piece.getColor() == Board.WHITE) {
            this.whitePieces.remove(piece);
        } else {
            this.blackPieces.remove(piece);
        }
    }

    public void moveFromTo(Location init, Location dest) throws IllegalArgumentException {
        Piece piece = pieceAt(init);
        if(piece == null) {
            throw new IllegalArgumentException("Square is empty.");
        }
        if(piece.getColor() == turn) {
            piece.moveTo(dest);
            if(turn == Board.WHITE) {
                turn = Board.BLACK;
            }
            else {
                turn = Board.WHITE;
            }
        }
        else {
            throw new IllegalArgumentException("Opposing color's turn.");
        }
    }

    public void promote(Location init, Location dest, String type) throws IllegalArgumentException {
        Piece piece = pieceAt(init);
        if(piece == null) {
            throw new IllegalArgumentException("Square is empty.");
        }
        if(piece.getColor() == turn) {
            piece.promote(dest, type);
            if(turn == Board.WHITE) {
                turn = Board.BLACK;
            }
            else {
                turn = Board.WHITE;
            }
        }
        else {
            throw new IllegalArgumentException("Opposing color's turn.");
        }
    }

    public Piece pieceAt(Location location) {
        return pieces.get(location);
    }

    public String toString() {
        String board = "WHITE: " + Board.WHITE.getMaterial() +
                " | BLACK: " + Board.BLACK.getMaterial() + "\n";
        Map<Location, Move> moves = new HashMap<>();
        if(highlight != null) {
            moves = highlight.legalMoves();
        }
        for(int i = MAX_RANK; i > 0; i--) {
            board += " -" + i + "- ";
            for(int j = 1; j <= MAX_FILE; j++) {
                Location loc = new Location(j, i);
                Piece piece = this.pieceAt(loc);

                if(moves.get(loc) != null) {
                    if(piece == null) {
                        board += " _O_ ";
                    }
                    else {
                        board += " _X_ ";
                    }
                }
                else {
                    if (piece == null) {
                        board += " ___ ";
                    }
                    else {
                        board += " " + this.pad(3, piece.toString()) + " ";
                    }
                }
            }
            board += "\n\n";
        }

        board += "     ";
        for(int i = 1; i <= MAX_FILE; i++) {
            board += " -" + Location.getFileLetter(i) + "- ";
        }

        return board;
    }

    private String pad(int goal, String string) {
        if(string.length() >= goal) {
            return string.substring(0, goal);
        }
        else {
            while(string.length() < goal) {
                string += " ";
            }
            return string;
        }
    }
}
