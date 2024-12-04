package au.id.foxy.aoc2024.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileProcessor {
    private Path filePath;
    
    public FileProcessor(String filename) {
        this.filePath = Paths.get(filename);
    }

    public List<String> getLines() throws IOException {
        return Files.readAllLines(filePath);
    }
}