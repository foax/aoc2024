package au.id.foxy.aoc2024.day14;

import java.util.List;

import au.id.foxy.aoc2024.lib.Coord;

public class Robot {
    private int[] position = new int[2];
    private final int[] velocity = new int[2];
    public final int[] roomDimensions = new int[2];

    public Robot(int posX, int posY, int velX, int velY, int roomWidth, int roomHeight) {
        position[0] = posX;
        position[1] = posY;
        velocity[0] = velX;
        velocity[1] = velY;
        roomDimensions[0] = roomWidth;
        roomDimensions[1] = roomHeight;
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }

    public Coord getCoord() {
        return new Coord(position[0], position[1]);
    }

    public void teleport(int seconds) {
        for (int i : List.of(0, 1)) {
            position[i] = (position[i] + velocity[i] * seconds) % roomDimensions[i];
            if (position[i] < 0) {
                position[i] += roomDimensions[i];
            }
        }
    }
}
