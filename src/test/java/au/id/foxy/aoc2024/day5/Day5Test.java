package au.id.foxy.aoc2024.day5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day5Test {
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day5_test_input.txt").toURI());
        var day5 = new Day5(path);
        assertEquals("143", day5.part1().getOutput());
    }

    @Test
    void testPart2() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day5_test_input.txt").toURI());
        var day5 = new Day5(path);
        assertEquals("123", day5.part2().getOutput());
    }
}
