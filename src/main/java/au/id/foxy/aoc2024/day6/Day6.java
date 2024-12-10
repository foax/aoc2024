package au.id.foxy.aoc2024.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day6 implements AdventOfCode {
    private FloorMap floorMap;

    public Day6(Path filePath) throws IOException {
        this.floorMap = new FloorMap(Files.readAllLines(filePath));

    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(this.floorMap.countGuardPositions()));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(this.floorMap.getPhantomObstacleCount()));
        return part;
    }
}
