package au.id.foxy.aoc2024.lib;

// import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Coord(int x, int y) {
    public Coord add(Coord a) {
        return new Coord(this.x() + a.x(), this.y() + a.y());
    }

    public Coord subtract(Coord a) {
        return new Coord(this.x() - a.x(), this.y() - a.y());
    }

    public Coord add(Vector a) {
        return new Coord(this.x() + a.direction().x() * a.magnitude(), this.y() + a.direction().y() * a.magnitude());
    }

    public Coord subtract(Vector a) {
        return new Coord(this.x() - a.direction().x() * a.magnitude(), this.y() - a.direction().y() * a.magnitude());
    }

    public Set<Coord> getNeighbours(boolean countDiagonals) {
        Set<Coord> neighbours = new HashSet<>();
        for (var nextCoord : List.of(new Coord(0, -1), new Coord(1, -1), new Coord(1, 0), new Coord(1, 1), new Coord(0, 1), new Coord(-1, 1), new Coord(-1, 0), new Coord(-1, -1))) {
            if (!countDiagonals && Math.abs(nextCoord.x()) == Math.abs(nextCoord.y())) {
                continue;
            }
            neighbours.add(this.add(nextCoord));
        }
        return neighbours;
    }
}
