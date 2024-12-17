package au.id.foxy.aoc2024.lib;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Direction {
    private static final Map<String, Vector> DIRECTION_TO_VECTOR_MAP = Map.of(
        "N", new Vector(new Coord(0, -1), 1),
        "NE", new Vector(new Coord(1, -1), 1),
        "E", new Vector(new Coord(1, 0), 1),
        "SE", new Vector(new Coord(1, 1), 1),
        "S", new Vector(new Coord(0, 1), 1),
        "SW", new Vector(new Coord(-1, 1), 1),
        "W", new Vector(new Coord(-1, 0), 1),
        "NW", new Vector(new Coord(-1, -1), 1));
    private static final List<String> DIRECTIONS = List.of("N", "NE", "E", "SE", "S", "SW", "W", "NW");
    private final String direction;

    public Direction(String dir) {
        if (!DIRECTION_TO_VECTOR_MAP.containsKey(dir)) {
            throw new java.lang.IllegalArgumentException("dir must be one of %s".formatted(DIRECTIONS.stream().collect(Collectors.joining(", "))));
        }
        this.direction = dir;
    }

    public String getDir() {
        return this.direction;
    }

    public Vector getDirAsVector() {
        return DIRECTION_TO_VECTOR_MAP.get(this.direction);
    }

    public Direction next(boolean diagonals, boolean clockwise) {
        int pos = DIRECTIONS.indexOf(this.direction);
        int increment = diagonals ? 1 : 2;
        if (!clockwise)
            increment *= -1;
        if (increment < 0)
            increment = DIRECTIONS.size() + increment;
        return new Direction(DIRECTIONS.get((pos + increment) % DIRECTIONS.size()));
    }

    @Override
    public String toString() {
        return "Direction{%s}".formatted(this.direction);
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
