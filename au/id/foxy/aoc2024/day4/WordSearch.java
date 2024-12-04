package au.id.foxy.aoc2024.day4;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class WordSearch {
    private List<List<Character>> grid = new ArrayList<>();

    public WordSearch(List<String> input) {
        for (String line : input) {
            this.grid.add(
                line.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList())
            );
        }
    }

    // check for a word in a given direction
    private boolean checkWordWithDirection(String word, int posX, int posY, int dirX, int dirY) {
        // System.out.printf("posX: %d; posY: %d; dirX: %d; dirY: %d\n", posX, posY, dirX, dirY);
        for (int i = 0; i < word.length(); i++) {
            if (posX < 0 || posY < 0 || posY >= this.grid.size() || posX >= this.grid.get(posY).size()) {
                return false;
            }
            // System.out.printf("posX: %d; posY: %d; grid letter: %s\n", posX, posY, this.grid.get(posY).get(posX));
            if (this.grid.get(posY).get(posX) != word.charAt(i)) {
                return false;
            }
            // System.out.printf("Found %s at (%d, %d)\n", word.charAt(i), posX, posY);
            posX += dirX;
            posY += dirY;
            // System.out.printf("New posX: %d; posY: %d\n", posX, posY);
        }
        // System.out.printf("Found word %s!\n", word);
        return true;
    }

    private int checkWords(String word, int posX, int posY) {
        int count = 0;
        for (int dirX = -1; dirX <= 1; dirX++) {
            for (int dirY = -1; dirY <= 1; dirY++) {
                if (dirX == 0 && dirY == 0) {
                    continue;
                }
                if (checkWordWithDirection(word, posX, posY, dirX, dirY)) {
                    // System.out.printf("## Found %s at (%d, %d) in direction (%d, %d)\n", word, posX, posY, dirX, dirY);
                    count++;
                }
            }
        }
        return count;
    }

    // Find an A
    // Check letters in (-1, -1), (-1, 1), (1, -1), (1, 1)
    // If there are two Ms and two Ss, and the Ms and Ss are not diagionally across from each other, we have a X-MAS

    private boolean checkCrossMas(int posX, int posY) {
        if (this.grid.get(posY).get(posX) != 'A') {
            return false;
        }
        if (posX < 1 || posY < 1 || posY >= this.grid.size() - 1 || posX >= this.grid.get(posY).size() - 1) {
            return false;
        }
        char[] letters = { this.grid.get(posY-1).get(posX-1), this.grid.get(posY-1).get(posX+1), this.grid.get(posY+1).get(posX-1), this.grid.get(posY+1).get(posX+1) };
        // System.out.printf("Letters at (%d, %d): %s\n", posX, posY, new String(letters));
        int countM = 0;
        int countS = 0;
        for (int i = 0; i < 4; i++) {
            if (letters[i] == 'M') {
                countM++;
            } else if (letters[i] == 'S') {
                countS++;
            }
        }
        if (countM != 2 || countS != 2) {
            return false;
        }
        if ((letters[0] == 'M' && letters[3] == 'M') || (letters[1] == 'M' && letters[2] == 'M')) {
            return false;
        }
        return true;
    }

    public int countCrossMasses() {
        int count = 0;
        for (int posY = 0; posY < this.grid.size(); posY++) {
            for (int posX = 0; posX < this.grid.get(posY).size(); posX++) {
                if (checkCrossMas(posX, posY)) {
                    // System.out.printf("Found X-MAS at (%d, %d)\n", posX, posY);
                    count++;
                }
            }
        }
        return count;
    }

    public int countWords(String word) {
        int count = 0;
        for (int posY = 0; posY < this.grid.size(); posY++) {
            for (int posX = 0; posX < this.grid.get(posY).size(); posX++) {
                count += checkWords(word, posX, posY);
            }
        }
        return count;
    }
}