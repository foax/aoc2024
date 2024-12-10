package au.id.foxy.aoc2024.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day3 implements AdventOfCode {
    private String inputString;

    public Day3(Path filePath) throws IOException {
        this.inputString = new String(Files.readAllBytes(filePath));
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        int result = 0;
        var pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)", Pattern.DOTALL);
        var matcher = pattern.matcher(this.inputString);
        while (matcher.find())
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        part.setOutput(String.valueOf(result));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        long result = 0;
        var mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)", Pattern.DOTALL);
        var dontPattern = Pattern.compile("(.*?)don't\\(\\).*?do\\(\\)(.*)", Pattern.DOTALL);
        var dontOnlyPattern = Pattern.compile("(.*?)don't\\(\\).*", Pattern.DOTALL);
        StringBuilder realInstructions = new StringBuilder();
        Matcher matcher;

        var instructions = this.inputString;
        while (instructions != "") {
            matcher = dontPattern.matcher(instructions);
            if (matcher.find()) {
                realInstructions.append(matcher.group(1));
                instructions = matcher.group(2);
            } else {
                matcher = dontOnlyPattern.matcher(instructions);
                if (matcher.find()) {
                    realInstructions.append(matcher.group(1));
                } else {
                    realInstructions.append(instructions);
                }
                instructions = "";
            }
        }

        matcher = mulPattern.matcher(realInstructions.toString());
        while (matcher.find()) {
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        part.setOutput(String.valueOf(result));
        return part;
    }
}
