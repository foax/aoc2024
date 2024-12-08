package au.id.foxy.aoc2024.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import au.id.foxy.aoc2024.AdventOfCode;

public class Day4 implements AdventOfCode {
    private WordSearch wordSearch;

    public Day4(Path filePath) throws IOException {
        this.wordSearch = new WordSearch(Files.readAllLines(filePath));
    }

    public String part1() {
        return String.valueOf(wordSearch.countWords("XMAS"));
    }

    public String part2() {
        return String.valueOf(wordSearch.countCrossMasses());
    }
}
