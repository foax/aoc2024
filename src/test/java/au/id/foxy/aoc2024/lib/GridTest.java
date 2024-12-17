package au.id.foxy.aoc2024.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GridTest {
    Grid<Integer> g = new Grid<>(3, 2);
    List<List<Integer>> input = List.of(
        List.of(1, 2, 3),
        List.of(2, 4, 6)
    );

    public GridTest() {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                g.set(new Coord(col, row), input.get(row).get(col));
            }
        }
    }

    @Test
    void testSet() {
        Grid<Integer> g = new Grid<Integer>(2, 2);

        g.set(new Coord(0, 0), 1);
        assertEquals(1, g.get(new Coord(0, 0)));
        assertTrue(g.getUniqueValues().equals(new HashSet<Integer>(List.of(1))));
        assertTrue(g.getCoordsOfValue(1).equals(new HashSet<Coord>(List.of(new Coord(0, 0)))));

        g.set(new Coord(1, 0), 2);
        assertEquals(2, g.get(new Coord(1, 0)));
        assertTrue(g.getUniqueValues().equals(new HashSet<Integer>(List.of(1, 2))));
        assertTrue(g.getCoordsOfValue(2).equals(new HashSet<Coord>(List.of(new Coord(1, 0)))));

        g.set(new Coord(0, 1), 2);
        assertEquals(2, g.get(new Coord(0, 1)));
        assertTrue(g.getUniqueValues().equals(new HashSet<Integer>(List.of(1, 2))));
        assertTrue(g.getCoordsOfValue(2).equals(new HashSet<Coord>(List.of(new Coord(1, 0), new Coord(0, 1)))));

        g.set(new Coord(0, 0), 3);
        assertEquals(3, g.get(new Coord(0, 0)));
        assertTrue(g.getUniqueValues().equals(new HashSet<Integer>(List.of(2, 3))));
        assertTrue(g.getCoordsOfValue(3).equals(new HashSet<Coord>(List.of(new Coord(0, 0)))));

        g.set(new Coord(1, 0), 4);
        assertEquals(4, g.get(new Coord(1, 0)));
        assertTrue(g.getUniqueValues().equals(new HashSet<Integer>(List.of(2, 3, 4))));
        assertTrue(g.getCoordsOfValue(4).equals(new HashSet<Coord>(List.of(new Coord(1, 0)))));
        assertTrue(g.getCoordsOfValue(2).equals(new HashSet<Coord>(List.of(new Coord(0, 1)))));
    }

    @Test
    void testGet() {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals(input.get(row).get(col), g.get(new Coord(col, row)));
            }
        }
    }

    @Test
    void testGetCoordsOfValue() {
        Set<Coord> coords = this.g.getCoordsOfValue(2);
        assertTrue(coords.equals(new HashSet<Coord>(List.of(new Coord(1, 0), new Coord(0, 1)))));
    }

    @Test
    void testGetHeight() {
        assertEquals(2, g.getHeight());
    }

    @Test
    void testGetWidth() {
        assertEquals(3, g.getWidth());
    }

    @Test
    void testGetUniqueValues() {
        assertTrue(this.g.getUniqueValues().equals(new HashSet<Integer>(List.of(1, 2, 3, 4, 6))));
    }

    @Test
    void testInBounds() {
        assertTrue(this.g.inBounds(new Coord(2, 1)));
        assertFalse(this.g.inBounds(new Coord(2, 3)));
    }

    @Test
    void testCoordIterator() {

    }

    @Test
    void testIteratorHasNext() {
        Iterator<Integer> iterator = this.g.iterator();
        for (int i = 0; i <= 5; i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorNext() {
        Iterator<Integer> iterator = this.g.iterator();
        List<Integer> expected = List.of(1, 2, 3, 2, 4, 6);
        for (int i = 0; i <= 5; i++) {
            assertEquals(expected.get(i), iterator.next());
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testCoordIteratorHasNext() {
        Iterator<Coord> iterator = this.g.coordIterator();
        for (int i = 0; i <= 5; i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void testCoordIteratorNext() {
        Iterator<Coord> iterator = this.g.coordIterator();
        List<Coord> expected = List.of(
            new Coord(0, 0), new Coord(1, 0), new Coord(2, 0),
            new Coord(0, 1), new Coord(1, 1), new Coord(2, 1)
        );
        for (int i = 0; i <= 5; i++) {
            assertEquals(expected.get(i), iterator.next());
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

}
