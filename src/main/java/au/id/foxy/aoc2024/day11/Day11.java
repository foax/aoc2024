package au.id.foxy.aoc2024.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day11 implements AdventOfCode {
    private List<String> stones;

    public Day11(Path filePath) throws IOException {
        stones = List.of(Files.readAllLines(filePath).get(0).split("\\s+"));
    }

    public void blink() {
        List<String> newStones = new ArrayList<>();
        for (var stone : stones) {
            if (stone.equals("0")) {
                newStones.add("1");
            } else if (stone.length() % 2 == 0) {
                newStones.add(stone.substring(0, stone.length() / 2));
                newStones.add(String.valueOf(Long.valueOf(stone.substring(stone.length() / 2, stone.length()))));
            } else {
                newStones.add(String.valueOf(Long.valueOf(stone) * 2024));
            }
        }
        this.stones = newStones;
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        for (int count = 1; count <= 25; count++) {
            this.blink();
            // System.out.println("Blink %d:\n%s".formatted(count, this.stones.stream().collect(Collectors.joining(" "))));
        }
        part.setOutput(String.valueOf(this.stones.size()));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        part.setOutput("");
        return part;
    }
}
