package model.pieces;

import model.*;

import java.util.HashMap;
import java.util.Map;

public class Pawn extends Piece{
    private boolean hasMoved;

    public Pawn(Board board, Location location, Color color) {
        super(board, location, color);
        this.hasMoved = false;
    }

    @Override
    public int getValue() {
        return pawnValue;
    }

    @Override
    public Map<Location, Move> legalMoves() {
        Map<Location, Move> moves = new HashMap<>();
        Location myLoc = this.getLocation();
        Location dest;
        Piece piece;

        // white pawn
        if(getColor() == Board.WHITE) {
            // one space north is free, can move
            dest = myLoc.n();
            if(dest.onBoard() && getBoard().pieceAt(dest) == null) {
                if(dest.n().onBoard()) {
                    moves.put(dest, new Move(this, dest, null));
                }
                else {
                    moves.put(dest, new PromotionMove(this, dest, null));
                }

                // if hasnt moved check two spaces north
                dest = myLoc.n().n();
                if(!hasMoved && dest.onBoard() && getBoard().pieceAt(dest) == null) {
                    if(dest.n().onBoard()) {
                        moves.put(dest, new Move(this, dest, null));
                    }
                    else {
                        moves.put(dest, new PromotionMove(this, dest, null));
                    }
                }
            }

            // ne diag has enemy, can move
            dest = myLoc.ne();
            piece = getBoard().pieceAt(dest);
            if(dest.onBoard()
                    && piece != null
                    && piece.isEnemy(this)) {
                if(dest.n().onBoard()) {
                    moves.put(dest, new Move(this, dest, piece));
                }
                else {
                    moves.put(dest, new PromotionMove(this, dest, piece));
                }
            }

            // nw diag ...
            dest = myLoc.nw();
            piece = getBoard().pieceAt(dest);
            if(dest.onBoard()
                    && piece != null
                    && piece.isEnemy(this)) {
                if(dest.n().onBoard()) {
                    moves.put(dest, new Move(this, dest, piece));
                }
                else {
                    moves.put(dest, new PromotionMove(this, dest, piece));
                }
            }
        }

        // black pawn
        if(getColor() == Board.BLACK) {
            // one space north is free, can move
            dest = myLoc.s();
            if(dest.onBoard() && getBoard().pieceAt(dest) == null) {
                if(dest.s().onBoard()) {
                    moves.put(dest, new Move(this, dest, null));
                }
                else {
                    moves.put(dest, new PromotionMove(this, dest, null));
                }

                // if hasnt moved check two spaces north
                dest = myLoc.s().s();
                if(!hasMoved && dest.onBoard() && getBoard().pieceAt(dest) == null) {
                    if(dest.s().onBoard()) {
                        moves.put(dest, new Move(this, dest, null));
                    }
                    else {
                        moves.put(dest, new PromotionMove(this, dest, null));
                    }
                }
            }

            // ne diag has enemy, can move
            dest = myLoc.se();
            piece = getBoard().pieceAt(dest);
            if(dest.onBoard()
                    && piece != null
                    && piece.isEnemy(this)) {
                if(dest.s().onBoard()) {
                    moves.put(dest, new Move(this, dest, piece));
                }
                else {
                    moves.put(dest, new PromotionMove(this, dest, piece));
                }
            }

            // nw diag ...
            dest = myLoc.sw();
            piece = getBoard().pieceAt(dest);
            if(dest.onBoard()
                    && piece != null
                    && piece.isEnemy(this)) {
                if(dest.s().onBoard()) {
                    moves.put(dest, new Move(this, dest, piece));
                }
                else {
                    moves.put(dest, new PromotionMove(this, dest, piece));
                }
            }
        }

        return moves;
    }

    @Override
    public void moveTo(Location location) {
        super.moveTo(location);
        this.hasMoved = true;
    }

    @Override
    public String getName() {
        return Piece.pawn;
    }


    public String toString() {
        return this.getColor().toString() + this.getName();
    }

}
