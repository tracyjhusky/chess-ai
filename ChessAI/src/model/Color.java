package model;

public class Color {
    private String symbol;
    private int material;

    public Color(String symbol) {
        this.symbol = symbol;
        this.material = 0;
    }

    public void addMaterial(int points) {
        this.material += points;
    }

    public int getMaterial() {
        return material;
    }

    public String toString() {
        return symbol;
    }
}
