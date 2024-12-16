package au.id.foxy.aoc2024.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SafetyManualCollection {
    private List<SafetyManual> safetyManuals = new ArrayList<>();
    private SafetyManualRules safetyManualRules;

    public SafetyManualCollection(List<String> lines) {
        List<String> pageRules = new ArrayList<>();
        for (String line : lines) {
            if (Pattern.matches("^\\d+\\|\\d+$", line))
                pageRules.add(line);
            else if (line.equals(""))
                safetyManualRules = new SafetyManualRules(pageRules);
            else if (Pattern.matches("^(\\d+,)*\\d+$", line)) {
                safetyManuals.add(new SafetyManual(line, safetyManualRules));
            }
        }
    }

    public int checkPageOrdering() {
        int middlePageSum = 0;
        for (var s : safetyManuals) {
            if (s.checkPageOrder())
                middlePageSum += s.getMiddlePage().pageNum();
        }
        return middlePageSum;
    }

    public int orderIncorrectPages() {
        int middlePageSum = 0;
        for (var s : safetyManuals) {
            if (!s.checkPageOrder()) {
                s.sortPages();
                middlePageSum += s.getMiddlePage().pageNum();
            }
        }
        return middlePageSum;
    }
}
