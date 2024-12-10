package au.id.foxy.aoc2024.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day5 implements AdventOfCode {
    private SafetyManualCollection safetyManualCollection;

    public Day5(Path filePath) throws IOException {
        this.safetyManualCollection = new SafetyManualCollection(Files.readAllLines(filePath));
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(safetyManualCollection.checkPageOrdering()));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(safetyManualCollection.orderIncorrectPages()));
        return part;
    }
}
