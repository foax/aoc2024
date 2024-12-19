package au.id.foxy.aoc2024.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day14Test {
    @Test
    void testPart1() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day14_test_input.txt").toURI());
        var day = new Day14(path, 11, 7);
        assertEquals("12", day.part1().getOutput());
    }

}
