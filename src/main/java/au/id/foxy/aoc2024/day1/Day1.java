package au.id.foxy.aoc2024.day1;

import au.id.foxy.aoc2024.AdventOfCode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 implements AdventOfCode {
    private List<List<Integer>> locationIds;

    public Day1(Path filePath) throws IOException {
        this.locationIds = new ArrayList<>();
        var fileLines = Files.readAllLines(filePath);
        this.locationIds = IntStream.range(0,2)
            .mapToObj(index -> fileLines.stream()
                .map(line -> line.split("\\s+")[index])
                .map(Integer::parseInt)
                .collect(Collectors.toList())
            )
            .collect(Collectors.toList());
    }
    
    public String part1() {
        int score = 0;
        List<List<Integer>> sortedIds = this.locationIds.stream()
            .map(innerList -> innerList.stream()
                .sorted()
                .collect(Collectors.toList())
            )
            .collect(Collectors.toList());
        
        for (int idx = 0; idx < sortedIds.get(0).size(); idx++)
            score += Math.abs(sortedIds.get(0).get(idx) - sortedIds.get(1).get(idx));

        return String.valueOf(score);
    }

    public String part2() {
        int score = 0;
        Map<Integer, Long> locationFrequency = this.locationIds.get(1).stream()
            .collect(Collectors.groupingBy(
                item -> item,
                Collectors.counting()
            ));

        for (Integer id : locationIds.get(0))
            score += id * locationFrequency.getOrDefault(id, 0L);
        
        return String.valueOf(score);
    }
}
