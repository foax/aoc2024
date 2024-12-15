package au.id.foxy.aoc2024.lib;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Direction {
    private static final Map<String, Vector> DIRECTIONS = Map.of(
        "N", new Vector(new Coord(0, -1), 1),
        "NE", new Vector(new Coord(1, -1), 1),
        "E", new Vector(new Coord(1, 0), 1),
        "SE", new Vector(new Coord(1, 1), 1),
        "S", new Vector(new Coord(0, 1), 1),
        "SW", new Vector(new Coord(-1, 1), 1),
        "W", new Vector(new Coord(-1, 0), 1),
        "NW", new Vector(new Coord(-1, -1), 1));
    private final String direction;

    public Direction(String dir) {
        if (!DIRECTIONS.containsKey(dir)) {
            throw new java.lang.IllegalArgumentException("dir must be one of %s".formatted(DIRECTIONS.keySet().stream().collect(Collectors.joining(", "))));
        }
        this.direction = dir;
    }

    public String getDir() {
        return this.direction;
    }

    public Vector getDirAsVector() {
        return DIRECTIONS.get(this.direction);
    }

    @Override
    public String toString() {
        return "Direction{%s (%s)}".formatted(this.direction, this.getDir());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Direction dir = (Direction) obj;
        return this.direction == dir.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.direction);
    }


}
