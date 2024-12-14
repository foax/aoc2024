package au.id.foxy.aoc2024.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day11 implements AdventOfCode {
    private Map<String, Long> stoneCount;

    public Day11(Path filePath) throws IOException {
        var stones = List.of(Files.readAllLines(filePath).get(0).split("\\s+"));
        stoneCount = new HashMap<>();
        for (var stone : stones) {
            stoneCount.put(stone, stoneCount.getOrDefault(stone, 0L) + 1L);
        }
    }

    public void blink() {
        Map<String, Long> newCount = new HashMap<>();
        for (var stone : stoneCount.keySet()) {
            if (stone.equals("0")) {
                newCount.put("1", newCount.getOrDefault("1", 0L) + stoneCount.get("0"));
            } else if (stone.length() % 2 == 0) {
                var halfStone = stone.substring(0, stone.length() / 2);
                newCount.put(halfStone, newCount.getOrDefault(halfStone, 0L) + stoneCount.get(stone));
                halfStone = String.valueOf(Long.valueOf(stone.substring(stone.length() / 2, stone.length())));
                newCount.put(halfStone, newCount.getOrDefault(halfStone, 0L) + stoneCount.get(stone));
            } else {
                var newStone = String.valueOf(Long.valueOf(stone) * 2024);
                newCount.put(newStone, newCount.getOrDefault(newStone, 0L) + stoneCount.get(stone));
            }
        }
        this.stoneCount = newCount;
    }

    public long getStoneCount() {
        return stoneCount.values().stream().mapToLong(Long::longValue).sum();
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        for (int count = 1; count <= 25; count++) {
            this.blink();
        }
        part.setOutput(String.valueOf(this.getStoneCount()));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        for (int count = 26; count <= 75; count++) {
            this.blink();
        }
        part.setOutput(String.valueOf(this.getStoneCount()));
        return part;
    }
}
