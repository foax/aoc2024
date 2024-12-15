package au.id.foxy.aoc2024.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;


public class Day2Test {
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day2_test_input.txt").toURI());
        var day2 = new Day2(path);
        assertEquals("2", day2.part1().getOutput());
    }

    @Test
    void testPart2() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day2_test_input.txt").toURI());
        var day2 = new Day2(path);
        assertEquals("4", day2.part2().getOutput());
    }
}
