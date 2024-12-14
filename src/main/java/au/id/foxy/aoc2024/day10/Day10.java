package au.id.foxy.aoc2024.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;
import au.id.foxy.aoc2024.lib.Grid;
import au.id.foxy.aoc2024.lib.Coord;

public class Day10 implements AdventOfCode {
    private Grid<Integer> grid;

    public Day10(Path filePath) throws IOException {
        var lines = Files.readAllLines(filePath);
        grid = new Grid<Integer>(lines.get(0).length(), lines.size());
        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();
            for (int x = 0; x < chars.length; x++) {
                grid.set(new Coord(x, y), Character.getNumericValue(chars[x]));
            }
        }
    }

    Map<Coord, Integer> checkTrailhead(Coord start) {
        int startValue = grid.get(start);
        Map<Coord, Integer> trailCountMap = new HashMap<>();
        
        for (var nextCoord : start.getNeighbours(false)) {
            if (!this.grid.inBounds(nextCoord))
                continue;
            if (this.grid.get(nextCoord) == startValue + 1) {
                if (this.grid.get(nextCoord) == 9) {
                    trailCountMap.put(nextCoord, 1);
                } else {
                    var checkedTrailsCountMap = checkTrailhead(nextCoord);
                    for (var checkedCoordEntry: checkedTrailsCountMap.entrySet()) {
                        if (trailCountMap.containsKey(checkedCoordEntry.getKey())) {
                            trailCountMap.put(checkedCoordEntry.getKey(), trailCountMap.get(checkedCoordEntry.getKey()) + checkedTrailsCountMap.get(checkedCoordEntry.getKey()));
                        } else {
                            trailCountMap.put(checkedCoordEntry.getKey(), checkedTrailsCountMap.get(checkedCoordEntry.getKey()));
                        }
                    }
                }
            }
        }
        return trailCountMap;
    }

    int findTrailheadScores() {
        int score = 0;
        for (var trailheadStart : this.grid.getCoordsOfValue(0)) {
            var trailendsFound = checkTrailhead(trailheadStart);
            int count = trailendsFound.size();
            score += count;
        }
        return score;
    }
    int findTrailheadRatings() {
        int ratings = 0;
        for (var trailheadStart : this.grid.getCoordsOfValue(0)) {
            for (var trailheadEntry : checkTrailhead(trailheadStart).entrySet()) {
                ratings += trailheadEntry.getValue();
            }
        }
        return ratings;
    }



    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(findTrailheadScores()));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(findTrailheadRatings()));
        return part;
    }

}
