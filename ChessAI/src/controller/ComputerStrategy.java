package controller;

import model.Board;
import model.Color;
import model.Location;
import model.Move;
import model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerStrategy implements Strategy{
    Board board;
    Color color;
    int level;

    public ComputerStrategy(Board board, Color color, int level) {
        this.board = board;
        this.color = color;
        this.level = level;
    }

    public void takeTurn() {
        long before  = System.currentTimeMillis();
        Move best = this.bestMove(this.color, level);
        long after = System.currentTimeMillis();
        System.out.println("Calculation time: " + (after - before) + "ms");
        board.moveFromTo(best.init, best.dest);
    }

    private List<Piece> getPieces(Color color) {
        if(color == Board.WHITE) {
            return board.getWhitePieces();
        }
        else {
            return board.getBlackPieces();
        }
    }

    private List<Move> getMoves(List<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        for(Piece piece : pieces) {
            moves.addAll(piece.legalMoves().values());
        }
        return moves;
    }

    private Move bestMove(Color color, int depth) {
        int maxValue = Integer.MIN_VALUE;
        Move bestMove = null;
        for(Move move : getMoves(getPieces(color))) {
            Color enemyColor;
            if(this.color == Board.WHITE) {
                enemyColor = Board.BLACK;
            }
            else {
                enemyColor = Board.WHITE;
            }
            move.perform();
            int value = move.getValue();
            if(depth > 0) {
                value -= bestMove(enemyColor, depth - 1).getValue();
            }
            if(value > maxValue) {
                maxValue = value;
                bestMove = move;
            }
            move.undo();
        }
        return bestMove;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
