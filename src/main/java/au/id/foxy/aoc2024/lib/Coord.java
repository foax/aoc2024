package au.id.foxy.aoc2024.lib;

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
}
