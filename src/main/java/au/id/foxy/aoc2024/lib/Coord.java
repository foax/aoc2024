package au.id.foxy.aoc2024.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coord {
    private final int x;
    private final int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Coord add(Coord a) {
        return new Coord(this.getX() + a.getX(), this.getY() + a.getY());
    }

    public Coord subtract(Coord a) {
        return new Coord(this.getX() - a.getX(), this.getY() - a.getY());
    }

    public Coord add(Vector a) {
        return new Coord(this.getX() + a.direction().getX() * a.magnitude(), this.getY() + a.direction().getY() * a.magnitude());
    }

    public Coord subtract(Vector a) {
        return new Coord(this.getX() - a.direction().getX() * a.magnitude(), this.getY() - a.direction().getY() * a.magnitude());
    }

    @Override
    public String toString() {
        return "Coord{" + x + "," + y + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coord coord = (Coord) obj;
        return this.x == coord.x && this.y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    public List<Coord> getNeighbours(boolean countDiagonals) {
        List<Coord> neighbours = new ArrayList<>();
        for (var nextCoord : List.of(new Coord(0, -1), new Coord(1, -1), new Coord(1, 0), new Coord(1, 1), new Coord(0, 1), new Coord(-1, 1), new Coord(-1, 0), new Coord(-1, -1))) {
            if (!countDiagonals && Math.abs(nextCoord.getX()) == Math.abs(nextCoord.getY())) {
                continue;
            }
            neighbours.add(this.add(nextCoord));
        }
        return neighbours;
    }
}
