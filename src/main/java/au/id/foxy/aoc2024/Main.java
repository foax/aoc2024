package au.id.foxy.aoc2024;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import au.id.foxy.aoc2024.day1.Day1;
import au.id.foxy.aoc2024.day2.Day2;
import au.id.foxy.aoc2024.day3.Day3;

public class Main {
    public static void main(String[] args) {
        String filename;
        Path filePath;
        AdventOfCode day = null;

        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java au.id.foxy.aoc2024.Main dayX [input_file]");
            System.exit(1);
        }

        if (args.length == 2)
            filename = args[1];
        else
            filename = args[0] + "_input.txt";
        filePath = Paths.get(filename);

        try {
            switch (args[0]) {
                case "day1":
                    day = new Day1(filePath);
                    break;
                case "day2":
                    day = new Day2(filePath);
                    break;
                case "day3":
                    day = new Day3(filePath);
                    break;
                default:
                    System.err.println("ERROR: No handler for " + args[0]);
                    System.exit(1);
            }
            System.out.printf("%s: Part 1: %s; Part 2: %s\n", args[0], day.part1(), day.part2());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
