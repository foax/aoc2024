package au.id.foxy.aoc2024.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class VectorTest {

    @Test void testNewVectorException() {
        assertThrows(IllegalArgumentException.class, () -> new Vector(new Coord(2, 0), 1) );
        assertThrows(IllegalArgumentException.class, () -> new Vector(new Coord(0, -2), 1) );
    }

    @Test
    void testDirection() {
        Vector v = new Vector(new Coord(1, 0), 5);
        assertEquals(new Coord(1, 0), v.direction());
    }

    @Test
    void testMagnitude() {
        Vector v = new Vector(new Coord(1, 0), 5);
        assertEquals(5, v.magnitude());
    }

    @Test
    void testEquals() {
        Vector vectorA = new Vector(new Coord(1, 0), 1);
        Vector vectorB = new Vector(new Coord(1, 0), 1);
        Vector vectorC = vectorA;
        Vector vectorD = new Vector(new Coord(1, 0), 2);
        assertTrue(vectorA.equals(vectorB));
        assertTrue(vectorA.equals(vectorC));
        assertFalse(vectorA.equals(vectorD));
    }

    @Test
    void testRotateClockwise() {
        for (var tests: List.of(
            Map.of("input", new Coord(0, -1), "result", new Vector(new Coord(1, 0), 1)),
            Map.of("input", new Coord(1, 0), "result", new Vector(new Coord(0, 1), 1)),
            Map.of("input", new Coord(0, 1), "result", new Vector(new Coord(-1, 0), 1)),
            Map.of("input", new Coord(-1, 0), "result", new Vector(new Coord(0, -1), 1))
        )) {
            assertEquals(tests.get("result"), new Vector((Coord) tests.get("input"), 1).rotateClockwise());
        }
    }

    @Test
    void testRotateCounterClockwise() {
        for (var tests: List.of(
            Map.of("input", new Coord(0, -1), "result", new Vector(new Coord(-1, 0), 1)),
            Map.of("input", new Coord(-1, 0), "result", new Vector(new Coord(0, 1), 1)),
            Map.of("input", new Coord(0, 1), "result", new Vector(new Coord(1, 0), 1)),
            Map.of("input", new Coord(1, 0), "result", new Vector(new Coord(0, -1), 1))
        )) {
            assertEquals(tests.get("result"), new Vector((Coord) tests.get("input"), 1).rotateCounterClockwise());
        }
    }

    @Test
    void testToString() {
        Vector v = new Vector(new Coord(1, -1), 15);
        assertEquals("Vector[direction=Coord[x=1, y=-1], magnitude=15]", v.toString());
    }
}
