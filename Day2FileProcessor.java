import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2FileProcessor {
        private Path filePath;
        
        public Day2FileProcessor(String filename) {
            this.filePath = Paths.get(filename);
        }

        public Stream<List<Integer>> getNumberLists() throws IOException {
            return Files.lines(filePath)
                .map(line -> Arrays.stream(line.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
        }
}
