package au.id.foxy.aoc2024.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class EdgeTest {
    @Test
    void testGetCoords() {
        Edge e = new Edge(new Coord(4, 0), new Coord(5, 0), new Direction("N"));
        assertEquals(Set.of(new Coord(4, 0), new Coord(5, 0)), e.getCoords());
    }

    @Test
    void testGetFacingDirection() {
        Edge e = new Edge(new Coord(4, 0), new Coord(5, 0), new Direction("N"));
        assertEquals(new Direction("N"), e.getFacingDirection());
    }
    @Test
    void testEquals() {
        Edge edgeA = new Edge(new Coord(4, 0), new Coord(5, 0), new Direction("N"));
        Edge edgeB = new Edge(new Coord(4, 0), new Coord(5, 0), new Direction("N"));
        Edge edgeC = edgeA;
        Edge edgeD = new Edge(new Coord(4, 1), new Coord(5, 1), new Direction("N"));
        assertTrue(edgeA.equals(edgeB));
        assertTrue(edgeA.equals(edgeC));
        assertFalse(edgeA.equals(edgeD));
    }

    @Test
    void testToString() {
        Edge e = new Edge(new Coord(4, 0), new Coord(5, 0), new Direction("N"));
        assertTrue("Edge{[Coord[x=4, y=0], Coord[x=5, y=0]], N}".equals(e.toString()) || "Edge{[Coord[x=5, y=0], Coord[x=4, y=0]], N}".equals(e.toString()));

    }
}
