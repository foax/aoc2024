package au.id.foxy.aoc2024.day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day1Test {
    
    @Test
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day1_test_input.txt").toURI());
        var day1 = new Day1(path);
        assertEquals("11", day1.part1().getOutput());
    }

    @Test
    void testPart2() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day1_test_input.txt").toURI());
        var day1 = new Day1(path);
        assertEquals("31", day1.part2().getOutput());
    }
}
