import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        var fileProcessor = new Day2FileProcessor("day2_input.txt");
        int safeCount = 0;
        try {
            Iterator<List<Integer>> iterator = fileProcessor.getNumberLists().iterator();

            while (iterator.hasNext()) {
                List<Integer> report = iterator.next();
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
                if (safe) {
                    safeCount++;
                }
            }
            System.out.println("Part 1: " + safeCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }
}
