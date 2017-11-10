package model;

public class Location {
    private int file; //letter
    private int rank; //num

    public Location(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public Location getAdjacent(int i, int j) {
        return new Location(this.file + i, this.rank + j);
    }

    public Location n() {
        return this.getAdjacent(0, 1);
    }

    public Location ne() {
        return this.getAdjacent(1, 1);
    }

    public Location e() {
        return this.getAdjacent(1, 0);
    }

    public Location se() {
        return this.getAdjacent(1, -1);
    }

    public Location s() {
        return this.getAdjacent(0, -1);
    }

    public Location sw() {
        return this.getAdjacent(1, 1);
    }

    public Location w() {
        return this.getAdjacent(-1, 0);
    }

    public Location nw() {
        return this.getAdjacent(-1, 1);
    }

    public static char getFileLetter(int file) {
        switch(file) {
            case 1:
                return 'a';
            case 2:
                return 'b';
            case 3:
                return 'c';
            case 4:
                return 'd';
            case 5:
                return 'e';
            case 6:
                return 'f';
            case 7:
                return 'g';
            case 8:
                return 'h';
            default:
                return '_';
        }
    }

    public static int getFileNumber(char file) {
        switch (file) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            default:
                return 0;
        }
    }

    public String toString() {
        return Character.toString(Location.getFileLetter(this.file)) + Integer.toString(this.rank);
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Location)) {
            return false;
        }

        Location other = (Location) o;

        return this.file == other.file && this.rank == other.rank;
    }

    public boolean onBoard() {
        return 0 < this.file && this.file <= Board.MAX_FILE
                && 0 < this.rank && this.rank <= Board.MAX_RANK;
    }

    public int hashCode() {
        return this.file * 10 + this.rank * 10;
    }
}
