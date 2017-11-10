package controller;

import model.Color;

public interface Strategy {
    void takeTurn();
    Color getColor();
}
