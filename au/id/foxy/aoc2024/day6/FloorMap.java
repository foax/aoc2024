package au.id.foxy.aoc2024.day6;

import java.util.List;

public class FloorMap {
    private boolean[][] obstacles;
    private boolean[][] travelled;
    private int[][][] pathMap;
    private int[] startPos = new int[2];
    private int[] currentPos = new int[2];
    private int[] currentDir = new int[2];
    private int numRows;
    private int numCols;
    private boolean guardOut = false;

    public FloorMap(List<String> lines) {
        this.numRows = lines.size();
        this.numCols = lines.get(0).length();
        this.obstacles = new boolean[this.numRows][this.numCols];
        this.travelled = new boolean[this.numRows][this.numCols];
        this.pathMap = new int[this.numRows][this.numCols][2];
        for (int row = 0; row < this.numRows ; row++) {
            var line = lines.get(row).toCharArray();
            for (int col = 0; col < line.length; col++) {
                switch (line[col]) {
                    case '#':
                        this.obstacles[row][col] = true;
                        break;
                    case '^':
                        this.startPos[0] = row;
                        this.startPos[1] = col;
                        this.currentPos = this.startPos;
                        this.currentDir[0] = -1;
                        this.currentDir[1] = 0;
                        break;
                }
            }
        }
    }

    private int[] rotateRight(int[] dir) {
        return new int[]{dir[1], -dir[0]};
    }

    public boolean advanceGuard() {
        if (guardOut)
            return false;
        
        int newRow = this.currentPos[0] + this.currentDir[0];
        int newCol = this.currentPos[1] + this.currentDir[1];
        if (newRow < 0 || newRow >= this.numRows || newCol < 0 || newCol >= this.numCols) {
            this.pathMap[this.currentPos[0]][this.currentPos[1]] = currentDir;
            this.travelled[this.currentPos[0]][this.currentPos[1]] = true;
            this.guardOut = true;
            return false;
        }

        if (obstacles[newRow][newCol]) {
            this.currentDir = rotateRight(this.currentDir);
            return true;
        }

        this.pathMap[this.currentPos[0]][this.currentPos[1]] = currentDir;
        this.travelled[this.currentPos[0]][this.currentPos[1]] = true;
        this.currentPos[0] = newRow;
        this.currentPos[1] = newCol;
        return true;
    }

    public int countGuardPositions() {
        int count = 0;
        boolean guardIn;
        do {
            guardIn = this.advanceGuard();
        } while (guardIn);

        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                if (travelled[row][col])
                    count++;
            }
        }
        return count;
    }
}
