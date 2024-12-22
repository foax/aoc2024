package au.id.foxy.aoc2024.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;
import au.id.foxy.aoc2024.lib.Coord;
import au.id.foxy.aoc2024.lib.Direction;
import au.id.foxy.aoc2024.lib.Grid;
import au.id.foxy.aoc2024.lib.Grids;

public class Day15 implements AdventOfCode {
    private List<String> gridLines;
    private List<Direction> movements;

    public Day15(Path filePath) throws IOException {
        this.movements = new ArrayList<>();
        gridLines = new ArrayList<>();
        var lines = Files.readAllLines(filePath).iterator();
        var line = lines.next();
        while (!line.equals("")) {
            gridLines.add(line);
            line = lines.next();
        }

        do {
            line = lines.next();
            this.movements.addAll(
                line.chars()
                    .mapToObj(c -> switch ((char) c) {
                        case '^' -> 'N';
                        case '>' -> 'E';
                        case 'v' -> 'S';
                        case '<' -> 'W';
                        default -> throw new IllegalArgumentException("Invalid direction: " + (char) c);
                    })
                    .map(d -> new Direction(String.valueOf(d)))
                    .collect(Collectors.toList()));
        } while (lines.hasNext());
    }

    public static boolean moveBox(Grid<Character> grid, Coord boxCoord, Direction dir) {
        if (grid.get(boxCoord) == '#') {
            return false;
        } else if (grid.get(boxCoord) == '.') {
            return true;
        }
        var nextCoord = boxCoord.add(dir.getDirAsVector());
        if (moveBox(grid, nextCoord, dir)) {
            grid.set(nextCoord, 'O');
            grid.set(boxCoord, '.');
            return true;
        }
        return false;
    }

    public static boolean moveDoubleBox(Grid<Character> grid, Coord boxCoord, Direction dir, Queue<Coord> coordQueue) {
        if (dir.getDir().equals("E") || dir.getDir().equals("W") ) {
            if (grid.get(boxCoord) == '#') {
                return false;
            } else if (grid.get(boxCoord) == '.') {
                return true;
            }
            var nextCoord = boxCoord.add(dir.getDirAsVector());
            var doubleNextCoord = nextCoord.add(dir.getDirAsVector());
            if (moveDoubleBox(grid, doubleNextCoord, dir, null)) {
                grid.set(doubleNextCoord, grid.get(nextCoord));
                grid.set(nextCoord, grid.get(boxCoord));
                grid.set(boxCoord, '.');
                return true;
            }
            return false;
        }

        if (coordQueue == null) {
            if (grid.get(boxCoord) == '#') {
                return false;
            } else if (grid.get(boxCoord) == '.') {
                return true;
            }
        }

        if (grid.get(boxCoord) == ']') {
            boxCoord = boxCoord.add(new Coord(-1, 0));
        }
        var neighbourCoord = boxCoord.add(new Coord(1, 0));
        var nextCoord = boxCoord.add(dir.getDirAsVector());
        var neighbourNextCoord = neighbourCoord.add(dir.getDirAsVector());
        // System.out.println("boxCoord: %s; neighbourCoord: %s; nextCoord: %s; neighbourNextCoord: %s".formatted(boxCoord, neighbourCoord, nextCoord, neighbourNextCoord));
        Queue<Coord> nextQueue = (coordQueue == null ? new LinkedList<>() : coordQueue);
        if (grid.get(nextCoord) == '#' || grid.get(neighbourNextCoord) == '#') {
            return false;
        }

        if (!(grid.get(nextCoord) == '.' && grid.get(neighbourNextCoord) == '.')) {
            if (grid.get(nextCoord) == '[') {
                if (!nextQueue.contains(nextCoord) && !moveDoubleBox(grid, nextCoord, dir, nextQueue)) {
                    return false;
                }
            } else if (grid.get(nextCoord) == ']') {
                if (!nextQueue.contains(nextCoord.add(new Coord(-1, 0))) && !moveDoubleBox(grid, nextCoord, dir, nextQueue)) {
                    return false;
                }
            }

            if (grid.get(neighbourNextCoord) == '[') {
                if (!nextQueue.contains(nextCoord) && !moveDoubleBox(grid, neighbourNextCoord, dir, nextQueue)) {
                    return false;
                }
            }
        }

        if (coordQueue == null) {
            nextQueue.add(boxCoord);
            // System.out.println(nextQueue);
            for (var coord : nextQueue) {
                grid.set(coord.add(dir.getDirAsVector()), '[');
                grid.set(coord.add(new Coord(1, 0).add(dir.getDirAsVector())), ']');
                grid.set(coord, '.');
                grid.set(coord.add(new Coord(1, 0)), '.');
            }
        } else {
            coordQueue.add(boxCoord);
        }
        return true;
    }

    public static String widenGrid(String input) {
        return switch (input) {
            case "#" -> "##";
            case "O" -> "[]";
            case "." -> "..";
            case "@" -> "@.";
            default -> "XX";
        };
    }

    @Override
    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        var grid = Grids.newCharacterGrid(gridLines);
        var robotCoord = grid.getCoordsOfValue('@').iterator().next();
        for (var movement: this.movements) {
            var nextCoord = robotCoord.add(movement.getDirAsVector());
            if (Day15.moveBox(grid, nextCoord, movement)) {
                grid.set(nextCoord, '@');
                grid.set(robotCoord, '.');
                robotCoord = nextCoord;
            }
        }

        long boxCoordSum = 0L;
        for (var c: grid.getCoordsOfValue('O')) {
            boxCoordSum += c.x() + c.y() * 100;
        }
        part.setOutput(String.valueOf(boxCoordSum));
        return part;
    }

    @Override
    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        Grid<Character> grid = Grids.newCharacterGrid(gridLines.stream()
                .map(s -> s.chars()
                    .mapToObj(c -> switch ((char) c) {
                        case '#' -> "##";
                        case 'O' -> "[]";
                        case '.' -> "..";
                        case '@' -> "@.";
                        default -> "XX";
                    })
                .collect(Collectors.joining()))
            .collect(Collectors.toList()));

        var robotCoord = grid.getCoordsOfValue('@').iterator().next();
        // System.out.println(Grids.toString(grid));
        for (var movement: this.movements) {
            var nextCoord = robotCoord.add(movement.getDirAsVector());
            if (Day15.moveDoubleBox(grid, nextCoord, movement, null)) {
                grid.set(nextCoord, '@');
                grid.set(robotCoord, '.');
                robotCoord = nextCoord;
            }
            // System.out.println("Movement: %s".formatted(movement.getDir()));
            // System.out.println(Grids.toString(grid));
        }

        long boxCoordSum = 0L;
        for (var c: grid.getCoordsOfValue('[')) {
            boxCoordSum += c.x() + c.y() * 100;
        }
        // System.out.println(Grids.toString(grid));
        part.setOutput(String.valueOf(boxCoordSum));
        return part;
    }

}
