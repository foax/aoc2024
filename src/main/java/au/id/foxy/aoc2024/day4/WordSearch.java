package au.id.foxy.aoc2024.day4;

import java.util.List;
// import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import au.id.foxy.aoc2024.lib.Direction;
import au.id.foxy.aoc2024.lib.Coord;
import au.id.foxy.aoc2024.lib.Grid;
import au.id.foxy.aoc2024.lib.Vector;

public class WordSearch {
    private Grid<Character> grid;

    public WordSearch(List<String> input) {
        grid = new Grid<>(input.get(0).length(), input.size());
        for (int row = 0; row < input.size(); row++) {
            var line = input.get(row).toCharArray();
            for (int col = 0; col < input.get(row).length(); col++) {
                grid.set(new Coord(col, row), line[col]);
            }
        }
    }

    // check for a word in a given direction
    private boolean checkWordWithDirection(String word, Coord coord, Vector direction) {
        for (int i = 0; i < word.length(); i++) {
            if (!this.grid.inBounds(coord))
                return false;
            if (this.grid.get(coord) != word.charAt(i))
                return false;
            coord = coord.add(direction);
        }
        return true;
    }

    private int checkWords(String word, Coord coord) {
        int count = 0;
        var dir = new Direction("N");
        do {
            if (checkWordWithDirection(word, coord, dir.getDirAsVector())) {
                count++;
            }
            dir = dir.next(true, true);
        } while (dir.getDir() != "N");
        return count;
    }

    // Find an A
    // Check letters in (-1, -1), (-1, 1), (1, -1), (1, 1)
    // If there are two Ms and two Ss, and the Ms and Ss are not diagionally across from each other, we have a X-MAS

    private boolean checkCrossMas(Coord coord) {
        if (this.grid.get(coord) != 'A')
            return false;
        if (coord.x() < 1 || coord.y() < 1 || coord.y() >= this.grid.getHeight() - 1 || coord.x() >= this.grid.getWidth() - 1)
            return false;

        List<Character> letters = Stream.of("NW", "NE", "SW", "SE")
            .map(x -> new Direction(x).getDirAsVector())
            .map(x -> coord.add(x))
            .map(x -> this.grid.get(x))
            .collect(Collectors.toList());
            // char[] letters = { this.grid.get(posY-1).get(posX-1), this.grid.get(posY-1).get(posX+1), this.grid.get(posY+1).get(posX-1), this.grid.get(posY+1).get(posX+1) };
        // System.out.printf("Letters at (%d, %d): %s\n", posX, posY, new String(letters));
        int countM = 0;
        int countS = 0;
        for (int i = 0; i < 4; i++) {
            if (letters.get(i) == 'M') {
                countM++;
            } else if (letters.get(i) == 'S') {
                countS++;
            }
        }
        if (countM != 2 || countS != 2) {
            return false;
        }
        if ((letters.get(0) == 'M' && letters.get(3) == 'M') || (letters.get(1) == 'M' && letters.get(2) == 'M')) {
            return false;
        }
        return true;
    }

    public int countCrossMasses() {
        int count = 0;
        var coordIterator = this.grid.coordIterator();
        while (coordIterator.hasNext()) {
            var coord = coordIterator.next();
            if (checkCrossMas(coord))
                count++;
        }
        return count;
    }

    public int countWords(String word) {
        int count = 0;
        var coordIterator = this.grid.coordIterator();
        while (coordIterator.hasNext()) {
            var coord = coordIterator.next();
            count += checkWords(word, coord);
        }
        return count;
    }
}