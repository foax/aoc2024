package au.id.foxy.aoc2024.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day3Test {
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day3_test_input.txt").toURI());
        var day3 = new Day3(path);
        assertEquals("161", day3.part1().getOutput());
    }

    @Test
    void testPart2() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day3_test_input_part2.txt").toURI());
        var day3 = new Day3(path);
        assertEquals("48", day3.part2().getOutput());
    }
}
