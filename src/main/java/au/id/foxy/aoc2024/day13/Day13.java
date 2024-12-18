package au.id.foxy.aoc2024.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day13 implements AdventOfCode {
    private List<Map<String, List<Integer>>> clawList = new ArrayList<>();

    public Day13(Path filePath) throws IOException {
        Pattern pattern = Pattern.compile("^(.*): X[\\+=](\\d+), Y[\\+=](\\d+)$");
        Matcher matcher;
        Map<String, List<Integer>> buttonInput = null;
        for (var line : Files.readAllLines(filePath)) {
            if (buttonInput == null) {
                buttonInput = new HashMap<>();
            }
            matcher = pattern.matcher(line);
            if (matcher.matches()) {
                buttonInput.put(matcher.group(1), List.of(Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3))));
            } else {
                clawList.add(buttonInput);
                buttonInput = null;
            }
        }
        if (buttonInput != null) {
            clawList.add(buttonInput);
        }
    }

    public static List<Long> findSolution(Map<String, List<Integer>> input, boolean corrected) {
        long denominator =
            input.get("Button A").get(0) * input.get("Button B").get(1)
            -
            input.get("Button B").get(0) * input.get("Button A").get(1);
        long x =
            (
                input.get("Button B").get(1) * (input.get("Prize").get(0) + (corrected ? 10000000000000L : 0L))
                -
                input.get("Button B").get(0) * (input.get("Prize").get(1) + (corrected ? 10000000000000L : 0L))
            );
        long y =
            (
                input.get("Button A").get(0) * (input.get("Prize").get(1) + (corrected ? 10000000000000L : 0L))
                -
                input.get("Button A").get(1) * (input.get("Prize").get(0) + (corrected ? 10000000000000L : 0L))
            );

        if (x % denominator != 0 || y % denominator != 0) {
            return null;
        }
        return List.of(x / denominator, y / denominator);
}

    @Override
    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        long tokens = 0;
        for (var input : clawList) {
            var result = findSolution(input, false);
            if (result != null)
                tokens += result.get(0) * 3 + result.get(1);
        }
        part.setOutput(String.valueOf(tokens));
        return part;
    }

    @Override
    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        long tokens = 0;
        for (var input : clawList) {
            var result = findSolution(input, true);
            if (result != null)
                tokens += result.get(0) * 3 + result.get(1);
        }
        part.setOutput(String.valueOf(tokens));
        return part;
    }

}
