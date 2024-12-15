package au.id.foxy.aoc2024.lib;

public record Vector(Coord direction, Integer magnitude) {

    public Vector(Coord direction, Integer magnitude) {
        if (Math.abs(direction.getX()) > 1 || Math.abs(direction.getY()) > 1) {
            throw new java.lang.IllegalArgumentException("Coord values must be between -1 and 1: %s".formatted(direction));
        }
        this.direction = direction;
        this.magnitude = magnitude;
    }

    public Vector rotateCounterClockwise() {
        return new Vector(new Coord(this.direction.getY(), -this.direction.getX()), this.magnitude);
    }

    public Vector rotateClockwise() {
        return new Vector(new Coord(-this.direction.getY(), this.direction.getX()), this.magnitude);
    }

}
