import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day3 {
    public static void main(String[] args) {
        var part1 = new Part1();
        // var part2 = new Part2();
        System.out.println("Part 1: " + part1.run("day2_input.txt"));
        // System.out.println("Part 2: " + part2.run("day2_input.txt"));

    }
}

class FileProcessor {
    private Path filePath;
    
    public FileProcessor(String filename) {
        this.filePath = Paths.get(filename);
    }

    public String getString() throws IOException {
        return new String(Files.readAllBytes(filePath));
    }
}


class Part1 {
    public int run(String filename) {
        var fileProcessor = new FileProcessor("day3_input.txt");
        var pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        int result = 0;

        try {
            var instructions = fileProcessor.getString();
            var matcher = pattern.matcher(instructions);

            while (matcher.find()) {
                result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
