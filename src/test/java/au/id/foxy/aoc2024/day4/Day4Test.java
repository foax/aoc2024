package au.id.foxy.aoc2024.day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day4Test {
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day4_test_input.txt").toURI());
        var day4 = new Day4(path);
        assertEquals("18", day4.part1().getOutput());
    }

    @Test
    void testPart2() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day4_test_input.txt").toURI());
        var day4 = new Day4(path);
        assertEquals("9", day4.part2().getOutput());
    }
}
