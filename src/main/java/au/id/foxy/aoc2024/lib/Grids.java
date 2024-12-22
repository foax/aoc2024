package au.id.foxy.aoc2024.lib;

import java.util.List;

public class Grids {
    public static Grid<Character> newCharacterGrid(List<String> input) {
        var grid = new Grid<Character>(input.get(0).length(), input.size());
        for (int row = 0; row < input.size(); row++) {
            var line = input.get(row);
            for (int col = 0; col < input.get(0).length(); col++) {
                grid.set(new Coord(col, row), line.charAt(col));
            }

        }
        return grid;
    }

    public static String toString(Grid<Character> grid) {
        var s = new StringBuilder();
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                s.append(grid.get(new Coord(col, row)));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
