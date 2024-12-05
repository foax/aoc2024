package au.id.foxy.aoc2024.day5;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day5 {
    
    public static void main(String[] args) {
        Path filePath = Paths.get("day5_input.txt");
        try {
            var safetyManualCollection = new SafetyManualCollection(filePath);
            System.out.println(safetyManualCollection);
            System.out.println("Part 1: " + safetyManualCollection.checkPageOrdering());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }            
    }
}
