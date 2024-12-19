package au.id.foxy.aoc2024.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import au.id.foxy.aoc2024.lib.Coord;

public class RobotTest {
    @Test
    void testGetX() {
        Robot r = new Robot(0, 4, 3, -3, 10, 10);
        assertEquals(0, r.getX());
    }

    @Test
    void testGetY() {
        Robot r = new Robot(0, 4, 3, -3, 10, 10);
        assertEquals(4, r.getY());
    }

    @Test
    void testGetCoord() {
        Robot r = new Robot(0, 4, 3, -3, 10, 10);
        assertEquals(new Coord(0, 4), r.getCoord());
    }

    @Test
    void testTeleport() {
        Robot r = new Robot(2, 4, 2, -3, 11, 7);
        r.teleport(5);
        assertEquals(1, r.getX());
        assertEquals(3, r.getY());
    }
}
