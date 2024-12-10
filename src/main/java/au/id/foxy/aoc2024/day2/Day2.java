package au.id.foxy.aoc2024.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day2 implements AdventOfCode {
    private List<List<Integer>> numberList;

    public Day2(Path filePath) throws IOException {
        this.numberList = Files.lines(filePath)
                .map(line -> Arrays.stream(line.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList()))
            .collect(Collectors.toList());
    }

    private static boolean checkReport(List<Integer> report) {
        boolean ascending = false;
        boolean safe = true;
        for (int x = 0; x < report.size() - 1; x++) {
            if (report.get(x) == report.get(x+1)) {
                safe = false;
                break;
            }
            
            if (x == 0) {
                ascending = report.get(x) < report.get(x+1);
            } else if ((report.get(x) < report.get(x+1)) != ascending) {
                safe = false;
                break;
            }

            if (Math.abs(report.get(x) - report.get(x+1)) > 3) {
                safe = false;
                break;
            }
        }
        return safe;
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        int count = 0;
        for (var report : this.numberList) {
            if (checkReport(report))
                count++;
        }
        part.setOutput(String.valueOf(count));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        int count = 0;
        for (var report : this.numberList) {
            if (checkReport(report)) {
                count++;
                continue;
            }
            for (int removeIdx = 0; removeIdx < report.size(); removeIdx++) {
                var reportModified = new ArrayList<>(List.copyOf(report));
                reportModified.remove(removeIdx);
                if (checkReport(reportModified)) {
                    count++;
                    break;
                }
            }
        }
        part.setOutput(String.valueOf(count));
        return part;
    }

}
