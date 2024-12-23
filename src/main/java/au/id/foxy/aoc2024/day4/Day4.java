package au.id.foxy.aoc2024.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day4 implements AdventOfCode {
    private WordSearch wordSearch;

    public Day4(Path filePath) throws IOException {
        this.wordSearch = new WordSearch(Files.readAllLines(filePath));
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(wordSearch.countWords("XMAS")));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        part.setOutput(String.valueOf(wordSearch.countCrossMasses()));
        return part;
    }
}
