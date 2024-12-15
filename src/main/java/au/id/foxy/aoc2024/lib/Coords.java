package au.id.foxy.aoc2024.lib;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coords {
    public static List<Coord> findBounds(Set<Coord> coords) {
        Integer minX = null;
        Integer minY = null;
        Integer maxX = null;
        Integer maxY = null;

        if (coords.size() == 0) {
            throw new java.lang.IllegalArgumentException("coords cannot be an empty set");
        }

        for (var coord : coords) {
            if (minX == null || coord.getX() < minX) {
                minX = coord.getX();
            }
            if (minY == null || coord.getY() < minY) {
                minY = coord.getY();
            }
            if (maxX == null || coord.getX() > maxX) {
                maxX = coord.getX();
            }
            if (maxY == null || coord.getY() > maxY) {
                maxY = coord.getY();
            }
        }

        return List.of(new Coord(minX, minY), new Coord(maxX, maxY));
    }

    public static Set<Edge> findEdges(Set<Coord> coords) {
        Set<Edge> edges = new HashSet<>();

        for (var coord : coords) {
            for (var direction : List.of(new Direction("N"), new Direction("E"), new Direction("S"), new Direction("W"))) {
                var neighbour = coord.add(direction.getDirAsVector());
                if (!coords.contains(neighbour)) {
                    edges.add(
                        switch (direction.getDir()) {
                            case "N" -> new Edge(coord, coord.add(new Coord(1, 0)), direction);
                            case "E" -> new Edge(coord.add(new Coord(1, 0)), coord.add(new Coord(1, 1)), direction);
                            case "S" -> new Edge(coord.add(new Coord(0, 1)), coord.add(new Coord(1, 1)), direction);
                            case "W" -> new Edge(coord, coord.add(new Coord(0, 1)), direction);
                            default -> throw new IllegalStateException();
                        }
                    );
                }
            }
        }
        return edges;
    }

    public static Set<Coord> addToCoords(Set<Coord> coords, Coord addCoord) {
        var newCoords = new HashSet<Coord>();
        for (var coord : coords) {
            newCoords.add(coord.add(addCoord));
        }
        return newCoords;
    }
}

