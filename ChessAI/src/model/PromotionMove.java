package model;

import model.pieces.Piece;

public class PromotionMove extends Move {
    private Piece promotion;

    public PromotionMove(Piece piece, Location dest, Piece capture) {
        super(piece, dest, capture);
    }

    public void perform() {
        this.promote(Piece.pawn);
    }

    public void promote(String type) {
        super.perform();

        this.promotion = Piece.makePiece(piece.getBoard(), piece.getLocation(), piece.getColor(), type);
        piece.getBoard().place(promotion);
    }

    public void undo() {
        super.undo();

        piece.getBoard().remove(promotion);
    }

    public int getValue() {
        return super.getValue() + Piece.queenValue;
    }

    public boolean isPromotionMove() {
        return true;
    }
}
