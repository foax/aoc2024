package au.id.foxy.aoc2024.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day6 {
    
    public static void main(String[] args) {
        Path filePath = Paths.get("day6_input.txt");

        try {
            List<String> lines = Files.readAllLines(filePath);   
            var floorMap = new FloorMap(lines);
            var part1 = floorMap.countGuardPositions();
            System.out.println("Part 1: " + part1 );
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }




    }
}
