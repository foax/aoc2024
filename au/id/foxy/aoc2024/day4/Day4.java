package au.id.foxy.aoc2024.day4;

import java.io.IOException;

public class Day4 {
    public static void main(String[] args) {
        var part1 = new Part1();
        var part2 = new Part2();
        System.out.println("Part 1: " + part1.run("day4_input.txt"));
        System.out.println("Part 2: " + part2.run("day4_input.txt"));
    }
}

class Part1 {
    public int run(String filename) {
        var fileProcessor = new FileProcessor(filename);
        int wordCount = 0;
        try {
            var wordSearch = new WordSearch(fileProcessor.getLines());
            wordCount += wordSearch.countWords("XMAS");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return wordCount;
    }
}

class Part2 {
    public int run(String filename) {
        var fileProcessor = new FileProcessor(filename);
        int wordCount = 0;
        try {
            var wordSearch = new WordSearch(fileProcessor.getLines());
            wordCount += wordSearch.countCrossMasses();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return wordCount;
    }
}