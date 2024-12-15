package au.id.foxy.aoc2024.day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WordSearchTest {
    List<String> gridInput = new ArrayList<>();

    public WordSearchTest() {
        gridInput.add("BCXMAS");
        gridInput.add("GSAMXM");
        gridInput.add("IAJKAL");
        gridInput.add("XMASNS");
        gridInput.add("OXPQAT");
        gridInput.add("UVWMYM");   
    }

    @Test
    void testCountCrossMasses() {
        WordSearch ws = new WordSearch(gridInput);
        assertEquals(2, ws.countCrossMasses());
    }

    @Test
    void testCountWords() {
        WordSearch ws = new WordSearch(gridInput);
        assertEquals(5, ws.countWords("XMAS"));
    }
}
