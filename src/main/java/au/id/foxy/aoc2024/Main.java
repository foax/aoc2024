package au.id.foxy.aoc2024;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import au.id.foxy.aoc2024.day1.Day1;
import au.id.foxy.aoc2024.day2.Day2;
import au.id.foxy.aoc2024.day3.Day3;
import au.id.foxy.aoc2024.day4.Day4;
import au.id.foxy.aoc2024.day5.Day5;
import au.id.foxy.aoc2024.day6.Day6;
import au.id.foxy.aoc2024.day8.Day8;

public class Main {
    private static double ms(long duration) {
        return duration / 1_000_000.0;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
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
                case "day4":
                    day = new Day4(filePath);
                    break;
                case "day5":
                    day = new Day5(filePath);
                    break;
                case "day6":
                    day = new Day6(filePath);
                    break;
                case "day8":
                    day = new Day8(filePath);
                    break;
                default:
                    System.err.println("ERROR: No handler for " + args[0]);
                    System.exit(1);
            }

            var part1 = day.part1();
            var part2 = day.part2();
            System.out.printf("%s: Part 1: %s (%.2f ms); Part 2: %s (%.2f ms)\n", args[0], part1.getOutput(), ms(part1.getDuration()), part2.getOutput(), ms(part2.getDuration()));
            System.out.printf("Total duration: %.2f ms\n", ms(System.nanoTime() - startTime));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
