package au.id.foxy.aoc2024.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.id.foxy.aoc2024.lib.Coord;

public class Robots {
    private List<Robot> robots;
    private final int width;
    private final int height;

    public Robots(List<String> input, int width, int height) {
        robots = new ArrayList<>();
        this.width = width;
        this.height = height;
        Pattern inputPattern = Pattern.compile("^p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)$");
        for (var line : input) {
            Matcher inputMatcher = inputPattern.matcher(line);
            if (inputMatcher.matches())
                robots.add(new Robot(Integer.valueOf(inputMatcher.group(1)), Integer.valueOf(inputMatcher.group(2)), Integer.valueOf(inputMatcher.group(3)), Integer.valueOf(inputMatcher.group(4)), width, height));
        }
    }

    public List<Robot> getRobots() {
        return robots;
    }
    
    public void teleportRobots(int seconds) {
        for (var r : robots) {
            r.teleport(seconds);
        }
    }
    public void printRobots() {
        var s = new StringBuilder();
        Map<Coord, Integer> robotCount = new HashMap<>();
        for (var robot : robots) {
            var c = robot.getCoord();
            robotCount.put(c, robotCount.getOrDefault(c, 0) + 1);
        }
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < this.width; col++) {
                var c = new Coord(col, row);
                if (robotCount.containsKey(c)) {
                    s.append(String.valueOf(robotCount.get(c)));
                } else {
                    s.append(".");
                }
            }
            s.append("\n");
        }
        System.out.println(s.toString());
    }

    public int[] countRobotsInQuadrants() {
        int[] count = new int[]{0, 0, 0, 0};
        for (var robot : robots) {
            if (robot.getX() < this.width / 2 && robot.getY() < this.height / 2) {
                count[0]++;
            } else if (robot.getX() > this.width / 2 && robot.getY() < this.height / 2) {
                count[1]++;
            } else if (robot.getX() < this.width / 2 && robot.getY() > this.height / 2) {
                count[2]++;
            } else if (robot.getX() > this.width / 2 && robot.getY() > this.height / 2) {
                count[3]++;
            }
        }
        return count;
    }

    
}
