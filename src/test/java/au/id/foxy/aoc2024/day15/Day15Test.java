package au.id.foxy.aoc2024.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import au.id.foxy.aoc2024.lib.Coord;
import au.id.foxy.aoc2024.lib.Direction;
import au.id.foxy.aoc2024.lib.Grid;

public class Day15Test {
    @Test
    void testMoveBox() {
        List<String> testBox = new ArrayList<>();
        testBox.add("#####");
        testBox.add("#O..#");
        testBox.add("#OO.#");
        testBox.add("#.O.#");
        testBox.add("#...#");
        testBox.add("#.O.#");
        testBox.add("#####");
        Grid<Character> grid = new Grid<Character>(5, 7);
        for (int row = 0; row < testBox.size(); row++) {
            var line = testBox.get(row);
            for (int col = 0; col < testBox.get(0).length(); col++) {
                grid.set(new Coord(col, row), line.charAt(col));
            }
        }

        assertTrue(Day15.moveBox(grid, new Coord(1, 3), new Direction("W")));
        assertEquals('.', grid.get(new Coord(1,3)));
        assertFalse(Day15.moveBox(grid, new Coord(0, 3), new Direction("W")));
        assertTrue(Day15.moveBox(grid, new Coord(1, 3), new Direction("N")));
        assertEquals('.', grid.get(new Coord(1,3)));
        assertFalse(Day15.moveBox(grid, new Coord(1, 2), new Direction("N")));
        assertEquals('O', grid.get(new Coord(1,2)));
        assertTrue(Day15.moveBox(grid, new Coord(2, 3), new Direction("N")));
        assertEquals('.', grid.get(new Coord(2,3)));
        assertEquals('O', grid.get(new Coord(2,2)));
        assertEquals('O', grid.get(new Coord(2,1)));
    }

    @Test
    void testPart1() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day15_test_input_1.txt").toURI());
        var day = new Day15(path);
        assertEquals("2028", day.part1().getOutput());

        path = Paths.get(getClass().getClassLoader().getResource("day15_test_input_2.txt").toURI());
        day = new Day15(path);
        assertEquals("10092", day.part1().getOutput());
    }
}
