package au.id.foxy.aoc2024.lib;

public record Vector(Coord direction, Integer magnitude) {

    public Vector(Coord direction, Integer magnitude) {
        if (Math.abs(direction.x()) > 1 || Math.abs(direction.y()) > 1) {
            throw new java.lang.IllegalArgumentException("Coord values must be between -1 and 1: %s".formatted(direction));
        }
        this.direction = direction;
        this.magnitude = magnitude;
    }

    public Vector rotateCounterClockwise() {
        return new Vector(new Coord(this.direction.y(), -this.direction.x()), this.magnitude);
    }

    public Vector rotateClockwise() {
        return new Vector(new Coord(-this.direction.y(), this.direction.x()), this.magnitude);
    }

}
