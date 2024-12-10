package au.id.foxy.aoc2024.day6;

public class FloorMapLoopException extends Exception {
    private int row;
    private int col;


    public FloorMapLoopException(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

}
