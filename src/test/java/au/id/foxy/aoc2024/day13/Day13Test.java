package au.id.foxy.aoc2024.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day13Test {
    @Test
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day13_test_input.txt").toURI());
        var day13 = new Day13(path);
        assertEquals("480", day13.part1().getOutput());
    }

    @Test
    void testPart2() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day13_test_input.txt").toURI());
        var day13 = new Day13(path);
        assertEquals("875318608908", day13.part2().getOutput());
    }
}
