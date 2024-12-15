package au.id.foxy.aoc2024.lib;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Edge {
    private final Set<Coord> edgeCoords;
    private final Direction edgeFacingDirection;
    
    public Edge(Coord coordA, Coord coordB, Direction facing) {
        this.edgeCoords = new HashSet<>(List.of(coordA, coordB));
        this.edgeFacingDirection = facing;
    }

    public Edge(Set<Coord> coords, Direction facing) {
        this.edgeCoords = coords;
        this.edgeFacingDirection = facing;
    }

    public Set<Coord> getCoords() {
        return this.edgeCoords;
    }

    public Direction getFacingDirection() {
        return this.edgeFacingDirection;
    }

    @Override
    public String toString() {
        return "Edge{%s, %s}".formatted(edgeCoords, edgeFacingDirection.getDir());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return this.edgeCoords.equals(edge.edgeCoords) && this.edgeFacingDirection.equals(edge.edgeFacingDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.edgeCoords, this.edgeFacingDirection);
    }
}
