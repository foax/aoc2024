import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        var part1 = new Part1();
        var part2 = new Part2();
        System.out.println("Part 1: " + part1.run("day2_input.txt"));
        System.out.println("Part 2: " + part2.run("day2_input.txt"));

    }
}

class Utils {
    public static boolean checkReport(List<Integer> report) {
        boolean ascending = false;
        boolean safe = true;
        for (int x = 0; x < report.size() - 1; x++) {
            if (report.get(x) == report.get(x+1)) {
                safe = false;
                break;
            }
            
            if (x == 0) {
                ascending = report.get(x) < report.get(x+1);
            } else if ((report.get(x) < report.get(x+1)) != ascending) {
                safe = false;
                break;
            }

            if (Math.abs(report.get(x) - report.get(x+1)) > 3) {
                safe = false;
                break;
            }
        }
        return safe;
    }
}

class Part1 {
    public int run(String filename) {
        var fileProcessor = new Day2FileProcessor(filename);
        int safeCount = 0;
        try {
            Iterator<List<Integer>> iterator = fileProcessor.getNumberLists().iterator();

            while (iterator.hasNext()) {
                List<Integer> report = iterator.next();
                if (Utils.checkReport(report)) {
                    safeCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return safeCount;
    }
}

class Part2 {
    public int run(String filename) {
        var fileProcessor = new Day2FileProcessor(filename);
        int safeCount = 0;
        try {
            Iterator<List<Integer>> iterator = fileProcessor.getNumberLists().iterator();

            while (iterator.hasNext()) {
                List<Integer> report = iterator.next();
                if (Utils.checkReport(report)) {
                    safeCount++;
                    continue;
                }

                for (int removeIdx = 0; removeIdx < report.size(); removeIdx++) {
                    List<Integer> reportModified = new ArrayList<>(List.copyOf(report));
                    reportModified.remove(removeIdx);
                    if (Utils.checkReport(reportModified)) {
                        safeCount++;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return safeCount;
    }
}

