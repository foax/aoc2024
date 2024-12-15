package au.id.foxy.aoc2024.lib;

import java.util.HashSet;
import java.util.Set;

public class Edges {
    public static Set<Edge> consolidateEdges(Set<Edge> edges) {
        Set<Edge> newEdges = new HashSet<>();
        Set<Edge> copyOfEdges = new HashSet<>(edges);

        for (var edge: edges) {
            if (!copyOfEdges.contains(edge)) {
                continue;
            }
            var edgeCoordsSet = new HashSet<Coord>();
            edgeCoordsSet.addAll(edge.getCoords());
            copyOfEdges.remove(edge);
            
            var dirVector = edge.getFacingDirection().getDirAsVector().rotateCounterClockwise();
            var tmpEdge = new Edge(Coords.addToCoords(edge.getCoords(), dirVector.direction()), edge.getFacingDirection());
            while (copyOfEdges.contains(tmpEdge)) {
                edgeCoordsSet.addAll(tmpEdge.getCoords());
                copyOfEdges.remove(tmpEdge);
                tmpEdge = new Edge(Coords.addToCoords(tmpEdge.getCoords(), dirVector.direction()), edge.getFacingDirection());
            }

            dirVector = edge.getFacingDirection().getDirAsVector().rotateClockwise();
            tmpEdge = new Edge(Coords.addToCoords(edge.getCoords(), dirVector.direction()), edge.getFacingDirection());
            while (copyOfEdges.contains(tmpEdge)) {
                edgeCoordsSet.addAll(tmpEdge.getCoords());
                copyOfEdges.remove(tmpEdge);
                tmpEdge = new Edge(Coords.addToCoords(tmpEdge.getCoords(), dirVector.direction()), edge.getFacingDirection());
            }

            newEdges.add(new Edge(new HashSet<Coord>(Coords.findBounds(edgeCoordsSet)), edge.getFacingDirection()));

        }
        return newEdges;
    }
}
