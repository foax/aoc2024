package au.id.foxy.aoc2024.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        Path filePath = Paths.get("day7_input.txt");

        try {
            List<CalibrationEquation> equations = new ArrayList<>();
            for (var line : Files.readAllLines(filePath)) {
                equations.add(new CalibrationEquation(line));
            }

            long part1 = 0;
            long part2 = 0;
            for (var equation : equations) {
                if (equation.evaluate(false))
                    part1 += equation.getAnswer();
                if (equation.evaluate(true))
                    part2 += equation.getAnswer();
            }


            System.out.println("Part 1: " + part1 );
            System.out.println("Part 2: " + part2 );
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
