package au.id.foxy.aoc2024.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;
import au.id.foxy.aoc2024.lib.Coord;
import au.id.foxy.aoc2024.lib.Grid;

public class Day8 implements AdventOfCode {
    private Grid<Character> grid;

    public static String gridString(Grid<Character> grid) {
        var string = new StringBuilder(grid.getHeight() * (grid.getWidth() + 1));
        int count = 0;
        for (var c : grid) {
            string.append(c);
            if (++count % grid.getWidth() == 0)
                string.append("\n");
        }
        return string.toString();
    }

    public Day8(Path filePath) throws IOException {
        var lines = Files.readAllLines(filePath);
        grid = new Grid<Character>(lines.get(0).length(), lines.size());
        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();
            for (int x = 0; x < chars.length; x++) {
                grid.set(new Coord(x, y), chars[x]);
            }
        }
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        var antinodes = new HashSet<Coord>();

        for (var frequency : grid.getUniqueValues()) {
            if (frequency == '.') continue;
            var coords = new ArrayList<>(grid.getCoordsOfValue(frequency));
            for (int x = 0; x < coords.size(); x++) {
                for (int y = 0; y < coords.size(); y++) {
                    if (x == y) continue;
                    var newCoord = coords.get(x).add(coords.get(x).subtract(coords.get(y)));
                    if (grid.inBounds(newCoord))
                        antinodes.add(newCoord);
                }
            }
        }
        // for (var coord : antinodes) System.out.println(coord);
        part.setOutput(String.valueOf(antinodes.size()));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        var antinodes = new HashSet<Coord>();

        for (var frequency : grid.getUniqueValues()) {
            if (frequency == '.') continue;
            var coords = new ArrayList<>(grid.getCoordsOfValue(frequency));
            for (int x = 0; x < coords.size(); x++) {
                if (coords.size() > 1) {
                    antinodes.add(coords.get(x));
                }
                for (int y = 0; y < coords.size(); y++) {
                    if (x == y) continue;
                    var coordDiff = coords.get(x).subtract(coords.get(y));
                    var newCoord = coords.get(x).add(coordDiff);
                    while (grid.inBounds(newCoord)) {
                        antinodes.add(newCoord);
                        newCoord = newCoord.add(coordDiff);
                    }
                }
            }
        }
        // for (var coord : antinodes) System.out.println(coord);
        part.setOutput(String.valueOf(antinodes.size()));
        return part;
    }
}
