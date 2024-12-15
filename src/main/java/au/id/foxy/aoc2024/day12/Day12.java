package au.id.foxy.aoc2024.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;
import au.id.foxy.aoc2024.lib.Coord;
import au.id.foxy.aoc2024.lib.Coords;
import au.id.foxy.aoc2024.lib.Edges;
import au.id.foxy.aoc2024.lib.Grid;

public class Day12 implements AdventOfCode {
    private Grid<Character> grid;

    public Day12(Path filePath) throws IOException {
        var lines = Files.readAllLines(filePath);
        grid = new Grid<Character>(lines.get(0).length(), lines.size());
        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();
            for (int x = 0; x < chars.length; x++) {
                grid.set(new Coord(x, y), chars[x]);
            }
        }
    }

    static int getPerimeter(Set<Coord> region) {
        int perimeter = 0;
        for (var coord : region) {
            for (var neighbour : coord.getNeighbours(false)) {
                if (!region.contains(neighbour)) {
                    perimeter++;
                }
            }
        }
        return perimeter;
    }

    static int getArea(Set<Coord> region) {
        return region.size();
    }

    @Override
    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        var regionsList = new ArrayList<Set<Coord>>();
        var coordsInRegionSet = new HashSet<Coord>();
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
                if (!grid.inBounds(queuedCoord) || coordsInRegionSet.contains(queuedCoord) || !grid.get(coord).equals(grid.get(queuedCoord))) {
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

        int cost = 0;
        for (var region : regionsList) {
            cost += getArea(region) * getPerimeter(region);
        }
        part.setOutput(String.valueOf(cost));
        return part;

    }

    @Override
    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        var regionsList = new ArrayList<Set<Coord>>();
        var coordsInRegionSet = new HashSet<Coord>();
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
                if (!grid.inBounds(queuedCoord) || coordsInRegionSet.contains(queuedCoord) || !grid.get(coord).equals(grid.get(queuedCoord))) {
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

        int cost = 0;
        for (var region : regionsList) {
            var edges = Coords.findEdges(region);
            var consolidatedEdges = Edges.consolidateEdges(edges);
            cost += getArea(region) * consolidatedEdges.size();
        }
        part.setOutput(String.valueOf(cost));
        return part;
    }
    
}
