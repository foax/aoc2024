package au.id.foxy.aoc2024.day7;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day7 implements AdventOfCode {
    private List<CalibrationEquation> equations;

    public Day7(Path filePath) throws IOException {
        equations = new ArrayList<>();
        for (var line : Files.readAllLines(filePath)) {
            equations.add(new CalibrationEquation(line));
        }
    }

    @Override
    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        long sum = 0L;

        for (var equation : equations) {
            if (equation.evaluate(false))
                sum += equation.getAnswer();
        }

        part.setOutput(String.valueOf(sum));
        return part;
    }

    @Override
    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        long sum = 0L;

        for (var equation : equations) {
            if (equation.evaluate(true))
                sum += equation.getAnswer();
        }

        part.setOutput(String.valueOf(sum));
        return part;
    }
}
