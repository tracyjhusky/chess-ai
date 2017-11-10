package model;

import model.pieces.Piece;

public class Move {
    public final Piece piece;
    public final Location init;
    public final Location dest;
    public final Piece capture;

    public Move(Piece piece, Location dest, Piece capture){
        this.piece = piece;
        this.init = piece.getLocation();
        this.dest = dest;
        this.capture = capture;
    }

    public void perform() {
        Board board = this.piece.getBoard();
        board.remove(piece);
        piece.setLocation(this.dest);
        if(capture != null) {
            board.remove(capture);
        }
        board.place(piece);

        if(capture != null) {
            piece.getColor().addMaterial(this.capture.getValue());
        }
    }

    public void undo() {
        Board board = this.piece.getBoard();
        board.remove(piece);
        piece.setLocation(this.init);
        if(capture != null) {
            board.place(capture);
        }
        board.place(piece);
        if(capture != null) {
            piece.getColor().addMaterial(-this.capture.getValue());
        }
    }

    public int getValue() {
        if(capture != null) {
            return this.capture.getValue();
        }
        return 0;
    }

    public void promote(String type) {
        throw new IllegalArgumentException("Cannot promote via this move.");
    }

    public boolean isPromotionMove() {
        return false;
    }
}
