package au.id.foxy.aoc2024.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import au.id.foxy.aoc2024.lib.Coord;

public class RobotsTest {
    @Test
    void testGetRobots() {
        List<String> input = List.of("p=0,4 v=3,-3", "p=6,3 v=-1,-3", "p=10,3 v=-1,2");
        Robots robots = new Robots(input, 11, 7);
        List<Robot> robotList = robots.getRobots();
        assertTrue(robotList.size() == 3);
        assertEquals(0, robotList.get(0).getX());
        assertEquals(4, robotList.get(0).getY());
    }

    @Test
    void testTeleportRobots() throws IOException, URISyntaxException {
        Map<Coord, Integer> expected = new HashMap<>();
        expected.put(new Coord(6, 0), 2);
        expected.put(new Coord(9, 0), 1);
        expected.put(new Coord(1, 3), 1);
        expected.put(new Coord(0, 2), 1);
        expected.put(new Coord(2, 3), 1);
        expected.put(new Coord(5, 4), 1);
        expected.put(new Coord(3, 5), 1);
        expected.put(new Coord(4, 5), 2);
        expected.put(new Coord(1, 6), 1);
        expected.put(new Coord(6, 6), 1);
        
        Path path = Paths.get(getClass().getClassLoader().getResource("day14_test_input.txt").toURI());
        var input = Files.readAllLines(path);
        Robots robots = new Robots(input, 11, 7);
        robots.teleportRobots(100);
        Map<Coord, Integer> actual = new HashMap<>();
        for (var robot : robots.getRobots()) {
            Coord c = new Coord(robot.getX(), robot.getY());
            actual.put(c, actual.getOrDefault(c, 0) + 1);
        }
        assertTrue(expected.equals(actual));
    }

    @Test
    void testCountRobotsInQuadrants() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader().getResource("day14_test_input.txt").toURI());
        var input = Files.readAllLines(path);
        Robots robots = new Robots(input, 11, 7);
        robots.teleportRobots(100);
        var count = robots.countRobotsInQuadrants();
        assertEquals(1, count[0]);
        assertEquals(3, count[1]);
        assertEquals(4, count[2]);
        assertEquals(1, count[3]);
    }
}
