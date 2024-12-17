package au.id.foxy.aoc2024.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class CoordTest {
    @Test
    void testX() {
        Coord coord = new Coord(5, -7);
        assertEquals(5, coord.x());
    }

    @Test
    void testY() {
        Coord coord = new Coord(5, -7);
        assertEquals(-7, coord.y());
    }

    @Test
    void testEquals() {
        Coord coordA = new Coord(1, 2);
        Coord coordB = new Coord(1, 2);
        Coord coordC = new Coord(2, 1);
        assertTrue(coordA.equals(coordB));
        assertTrue(coordA.equals(coordA));
        assertTrue(coordB.equals(coordA));
        assertFalse(coordC.equals(coordA));
    }

    @Test
    void testAdd() {
        Coord coordA = new Coord(1, 2);
        Coord coordB = new Coord(6, 10);
        Coord addedCoords = coordA.add(coordB);
        assertEquals(7, addedCoords.x());
        assertEquals(12, addedCoords.y());
    }

    @Test
    void testAddVector() {
        Coord coordA = new Coord(1, 2);
        Vector vectorA = new Vector(new Coord(-1, 1), 1);
        Vector vectorB = new Vector(new Coord(0, -1), 5);
        assertEquals(new Coord(0, 3), coordA.add(vectorA));
        assertEquals(new Coord(1, -3), coordA.add(vectorB));
    }

    @Test
    void testSubtract() {
        Coord coordA = new Coord(1, 2);
        Coord coordB = new Coord(6, 10);
        Coord subtractedCoords = coordA.subtract(coordB);
        assertEquals(-5, subtractedCoords.x());
        assertEquals(-8, subtractedCoords.y());
    }

    @Test
    void testSubtractVector() {
        Coord coordA = new Coord(1, 2);
        Vector vectorA = new Vector(new Coord(-1, 1), 1);
        Vector vectorB = new Vector(new Coord(0, -1), 5);
        assertEquals(new Coord(2, 1), coordA.subtract(vectorA));
        assertEquals(new Coord(1, 7), coordA.subtract(vectorB));
    }

    @Test
    void testGetNeighbours() {
        Coord coord = new Coord(4, 7);
        Set<Coord> neighboursWithoutDiagonals = Set.of(new Coord(3, 7), new Coord(5, 7), new Coord(4, 6),
                new Coord(4, 8));
        Set<Coord> neighboursWithDiagonals = Set.of(new Coord(3, 7), new Coord(5, 7), new Coord(4, 6), new Coord(4, 8),
                new Coord(3, 6), new Coord(5, 6), new Coord(3, 8), new Coord(5, 8));
        assertEquals(neighboursWithoutDiagonals, coord.getNeighbours(false));
        assertEquals(neighboursWithDiagonals, coord.getNeighbours(true));
    }

    @Test
    void testToString() {
        Coord coord = new Coord(-4, 24);
        assertEquals("Coord[x=-4, y=24]", coord.toString());
    }

}
