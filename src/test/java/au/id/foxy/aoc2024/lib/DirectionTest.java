package au.id.foxy.aoc2024.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class DirectionTest {
    @Test
    void testNewException() {
        assertThrows(IllegalArgumentException.class, () -> new Direction("X"));
    }

    @Test
    void testGetDir() {
        for (var dirStr : List.of("N", "NE", "E", "SE", "S", "SW", "W", "NW")) {
            Direction d = new Direction(dirStr);
            assertEquals(dirStr, d.getDir());
        }
    }

    @Test
    void testGetDirAsVector() {
        final Map<String, Vector> dirMapToVector = Map.of(
            "N", new Vector(new Coord(0, -1), 1),
            "NE", new Vector(new Coord(1, -1), 1),
            "E", new Vector(new Coord(1, 0), 1),
            "SE", new Vector(new Coord(1, 1), 1),
            "S", new Vector(new Coord(0, 1), 1),
            "SW", new Vector(new Coord(-1, 1), 1),
            "W", new Vector(new Coord(-1, 0), 1),
            "NW", new Vector(new Coord(-1, -1), 1)
        );

        for (var element : dirMapToVector.entrySet()) {
            assertEquals(element.getValue(), new Direction(element.getKey()).getDirAsVector());
        }
    }

    @Test
    void testEquals() {
        Direction directionA = new Direction("NE");
        Direction directionB = new Direction("NE");
        Direction directionC = directionA;
        Direction directionD = new Direction("SE");
        assertTrue(directionA.equals(directionB));
        assertTrue(directionB.equals(directionA));
        assertTrue(directionA.equals(directionC));
        assertFalse(directionA.equals(directionD));
    }

    @Test
    void testNext() {
        for (var dir : List.of(List.of("N", "E"), List.of("E", "S"), List.of("S", "W"), List.of("W", "N"))) {
            assertEquals(new Direction(dir.get(1)), new Direction(dir.get(0)).next(false, true));
        }
        for (var dir : List.of(List.of("N", "W"), List.of("E", "N"), List.of("S", "E"), List.of("W", "S"))) {
            assertEquals(new Direction(dir.get(1)), new Direction(dir.get(0)).next(false, false));
        }
        for (var dir : List.of(List.of("N", "NE"), List.of("NE", "E"), List.of("E", "SE"), List.of("SE", "S"), List.of("S", "SW"), List.of("SW", "W"), List.of("W", "NW"), List.of("NW", "N"))) {
            assertEquals(new Direction(dir.get(1)), new Direction(dir.get(0)).next(true, true));
        }
        for (var dir : List.of(List.of("N", "NW"), List.of("NW", "W"), List.of("W", "SW"), List.of("SW", "S"), List.of("S", "SE"), List.of("SE", "E"), List.of("E", "NE"), List.of("NE", "N"))) {
            assertEquals(new Direction(dir.get(1)), new Direction(dir.get(0)).next(true, false));
        }
    }

    @Test
    void testToString() {
        assertEquals("Direction{NE}", new Direction("NE").toString());
    }
}
