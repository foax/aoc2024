package au.id.foxy.aoc2024.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
// import java.util.Map;
import java.util.Queue;
import java.util.Set;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;
import au.id.foxy.aoc2024.lib.Coord;
import au.id.foxy.aoc2024.lib.Grid;

public class Day14 implements AdventOfCode {
    List<String> input;
    int width;
    int height;

    public Day14(Path filePath, int width, int height) throws IOException {
        this.input = Files.readAllLines(filePath);
        this.width = width;
        this.height = height;
    }

    public Day14(Path filePath) throws IOException {
        this(filePath, 101, 103);
    }


    @Override
    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        var robots = new Robots(this.input, this.width, this.height);
        robots.teleportRobots(100);
        var quadrantCount = robots.countRobotsInQuadrants();
        var safetyFactor = Arrays.stream(quadrantCount).reduce(1, (a, b) -> a * b);
        part.setOutput(String.valueOf(safetyFactor));
        return part;
    }

    @Override
    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        var robots = new Robots(this.input, this.width, this.height);
        // Map<Integer, Set<Coord>> robotVisitedSet = new HashMap<>();
        // Map<Integer, Integer> robotLoopCount = new HashMap<>();
        
        int count = 1;

        // This was me checking how many iterations each robot took to loop back on itself
        // int robotCount = robots.getRobots().size();
        // do {
        //     robots.teleportRobots(1);
        //     var robotsList = robots.getRobots();
        //     for (int idx = 0; idx < robotsList.size(); idx++) {
        //         var robot = robotsList.get(idx);
        //         if (robotLoopCount.containsKey(idx))
        //             continue;
        //         if (robotVisitedSet.containsKey(idx) && robotVisitedSet.get(idx).contains(robot.getCoord())) {
        //             robotLoopCount.put(idx, count);
        //             continue;
        //         }
        //         if (!robotVisitedSet.containsKey(idx))
        //             robotVisitedSet.put(idx, new HashSet<Coord>());
        //         robotVisitedSet.get(idx).add(robot.getCoord());
        //     }
        //     count++;
        // } while (robotLoopCount.size() < robotCount );

        int maxClusterSize = 0;
        int maxClusterCount = 0;

        // TODO Move this find regions code into Grid class
        // This isn't very efficient, but it works. For each teleport iteration it will find the largest
        // cluster of robots and compare this to the largest cluster found so far in the hope that robots
        // will form a large cluster when the easter egg appears.
        do {
            robots.teleportRobots(1);
            var robotsList = robots.getRobots();
            var grid = new Grid<Boolean>(this.width, this.height, false);
            var regionsList = new ArrayList<Set<Coord>>();
            var coordsInRegionSet = new HashSet<Coord>();
            for (var robot : robotsList) {
                grid.set(robot.getCoord(), true);
            }
            var coordIterator = grid.coordIterator();

            while (coordIterator.hasNext()) {
                var coord = coordIterator.next();
                if (coordsInRegionSet.contains(coord)) {
                    continue;
                }
                var region = new HashSet<Coord>();
                Queue<Coord> queue = new LinkedList<>();
                queue.add(coord);
                while (!queue.isEmpty()) {
                    var queuedCoord = queue.poll();
                    if (!grid.inBounds(queuedCoord) || coordsInRegionSet.contains(queuedCoord) || !grid.get(queuedCoord)) {
                        continue;
                    }
                    region.add(queuedCoord);
                    coordsInRegionSet.add(queuedCoord);
                    for (var neighbour : queuedCoord.getNeighbours(false)) {
                        queue.add(neighbour);
                    }
                }
                regionsList.add(region);
            }

            int ourSize = regionsList.stream().mapToInt(Set::size).max().orElse(0);
            if (ourSize > maxClusterSize) {
                // System.out.println("Count: %d; Found new max cluster size: %d".formatted(count, ourSize));
                // robots.printRobots();
                maxClusterSize = ourSize;
                maxClusterCount = count;
            }
            count++;
    
        } while (count <= this.height * this.width);

        part.setOutput(String.valueOf(maxClusterCount));
        return part;
    }
}