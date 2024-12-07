package au.id.foxy.aoc2024.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FloorMap {
    private boolean[][] obstacles;
    private boolean[][] travelled;
    private List<List<Set<String>>> pathMap;
    private int[] startPos = new int[2];
    private int[] currentPos = new int[2];
    private int[] currentDir = new int[2];
    private int numRows;
    private int numCols;
    private int phantomObstacleCount = 0;
    private boolean[][] phantomObstacles;

    public FloorMap(List<String> lines) {
        this.numRows = lines.size();
        this.numCols = lines.get(0).length();
        this.obstacles = new boolean[this.numRows][this.numCols];
        this.phantomObstacles = new boolean[this.numRows][this.numCols];
        this.travelled = new boolean[this.numRows][this.numCols];
        this.pathMap = new ArrayList<>();
        for (int row = 0; row < this.numRows ; row++) {
            var line = lines.get(row).toCharArray();
            this.pathMap.add(new ArrayList<>());
            for (int col = 0; col < this.numCols; col++) {
                this.pathMap.get(row).add(new HashSet<>());
                this.phantomObstacles[row][col] = false;
                switch (line[col]) {
                    case '#':
                        this.obstacles[row][col] = true;
                        break;
                    case '^':
                        this.startPos[0] = row;
                        this.startPos[1] = col;
                        this.currentPos[0] = row;
                        this.currentPos[1] = col;
                        this.currentDir[0] = -1;
                        this.currentDir[1] = 0;
                        break;
                }
            }
        }
    }

    public FloorMap(FloorMap f) {
        this.numRows = f.numRows;
        this.numCols = f.numCols;
        this.obstacles = new boolean[this.numRows][this.numCols];
        this.phantomObstacles = new boolean[this.numRows][this.numCols];
        this.travelled = new boolean[this.numRows][this.numCols];
        this.pathMap = new ArrayList<>();
        for (int row = 0; row < this.numRows ; row++) {
            this.pathMap.add(new ArrayList<>());
            for (int col = 0; col < this.numCols; col++) {
                this.pathMap.get(row).add(new HashSet<>());
                this.pathMap.get(row).get(col).addAll(f.pathMap.get(row).get(col));
                this.obstacles[row][col] = f.obstacles[row][col];
                this.phantomObstacles[row][col] = f.phantomObstacles[row][col];
                this.travelled[row][col] = f.travelled[row][col];
            }
        }
        this.startPos = Arrays.copyOf(f.startPos, 2);
        this.currentPos = Arrays.copyOf(f.currentPos, 2);
        this.currentDir = Arrays.copyOf(f.currentDir, 2);
        this.phantomObstacleCount = f.phantomObstacleCount;
    }

    private int[] rotateRight(int[] dir) {
        return new int[]{dir[1], -dir[0]};
    }

    private boolean inBounds(int row, int col) {
        return (row >= 0 && row < this.numRows && col >= 0 && col < this.numCols);
    }

    private String dirStr(int[] dir) {
        return dir[0] + " " + dir[1];
    }

    public void addObstacle(int row, int col) {
        this.obstacles[row][col] = true;
    }

    public boolean advanceGuard(boolean newObstacles) throws FloorMapLoopException {
        int newRow = this.currentPos[0] + this.currentDir[0];
        int newCol = this.currentPos[1] + this.currentDir[1];
        
        // Check if the next position would be off the floor
        if (!inBounds(newRow, newCol)) {
            this.pathMap.get(this.currentPos[0]).get(this.currentPos[1]).add(dirStr(this.currentDir));
            this.travelled[this.currentPos[0]][this.currentPos[1]] = true;
            return false;
        }

        // Check if the next position would be a path we've already travelled
        if (pathMap.get(newRow).get(newCol).contains(dirStr(this.currentDir)))
        // if (this.travelled[newRow][newCol] && pathMap.get(newRow).get(newCol).contains(dirStr(this.currentDir)))
            throw new FloorMapLoopException(newRow, newCol);

        // Rotate if the next position is an obstacle
        if (obstacles[newRow][newCol]) {
            int[] newDir = rotateRight(this.currentDir);
            this.pathMap.get(this.currentPos[0]).get(this.currentPos[1]).add(dirStr(this.currentDir));
            this.currentDir = newDir;
            return true;
        }

        // What if the next position was an obstacle?
        // If we end up on a path we've already been on and in the same direction, count it
        // as a possible option to put an obstacle to cause a loop.
        if (newObstacles && !(this.startPos[0] == newRow && this.startPos[1] == newCol) && !(this.phantomObstacles[newRow][newCol]) && !(this.travelled[newRow][newCol])) {
            var newFloor = new FloorMap(this);
            newFloor.addObstacle(newRow, newCol);
            boolean guardIn;
            try {
                do {
                    guardIn = newFloor.advanceGuard(false);
                } while (guardIn);
            } catch (FloorMapLoopException e) {
                this.phantomObstacles[newRow][newCol] = true;
                // System.out.println(newRow + "," + newCol);
                this.phantomObstacleCount++;
                if (newRow == 12 && newCol == 79)
                    System.out.println(newFloor.printMap(new int[]{newRow, newCol}, new int[]{e.getRow(), e.getCol()}));
                // System.out.println();
            }
        }

        // Move ahead and keep track of where we were
        this.pathMap.get(this.currentPos[0]).get(this.currentPos[1]).add(dirStr(currentDir));
        this.travelled[this.currentPos[0]][this.currentPos[1]] = true;
        this.currentPos[0] = newRow;
        this.currentPos[1] = newCol;
        return true;
    }

    public int countGuardPositions() {
        int count = 0;
        boolean guardIn;
        try {
            do {
                guardIn = this.advanceGuard(true);
            } while (guardIn);
        } catch (FloorMapLoopException e) {
            return 0;
        }

        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                if (travelled[row][col])
                    count++;
            }
        }
        return count;
    }

    public int getPhantomObstacleCount() {
        return this.phantomObstacleCount;
    }

    public String printMap(int[] obj, int[] loop) {
        List<String> lines = new ArrayList<>();
        for (int row = 0; row < this.numRows; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < this.numCols; col++) {
                if (obj != null && obj[0] == row && obj[1] == col)
                    line.append('O');
                else if (this.obstacles[row][col])
                    line.append('#');
                else if (loop != null && loop[0] == row && loop[1] == col)
                    line.append('X');
                else if (this.startPos[0] == row && this.startPos[1] == col)
                    line.append('^');
                else if (
                    (this.pathMap.get(row).get(col).contains("-1 0") || this.pathMap.get(row).get(col).contains("1 0"))
                    &&
                    (this.pathMap.get(row).get(col).contains("0 -1") || this.pathMap.get(row).get(col).contains("0 1")))
                    line.append('+');
                else if (this.pathMap.get(row).get(col).contains("-1 0") || this.pathMap.get(row).get(col).contains("1 0"))
                    line.append('|');
                else if (this.pathMap.get(row).get(col).contains("0 -1") || this.pathMap.get(row).get(col).contains("0 1"))
                    line.append('=');
                else
                    line.append('.');
            }
            lines.add(line.toString());
        }
        return String.join("\n", lines);
    }
}
