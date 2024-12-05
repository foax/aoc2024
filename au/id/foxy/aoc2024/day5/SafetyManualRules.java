package au.id.foxy.aoc2024.day5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SafetyManualRules {
    private Map<Integer, Set<Integer>> pageLessThan;

    public SafetyManualRules(List<String> ruleList) {
        pageLessThan = new HashMap<>();
        for (var line : ruleList) {
            int[] pages = new int[2];
            String[] parts = line.split("\\|");
            pages[0] = Integer.parseInt(parts[0]);
            pages[1] = Integer.parseInt(parts[1]);

            if (!pageLessThan.containsKey(pages[0]))
                pageLessThan.put(pages[0], new HashSet<>());

            pageLessThan.get(pages[0]).add(pages[1]);
        }
    }

    public boolean checkPageLessThan(int page1, int page2) {
        if (!pageLessThan.containsKey(page1))
            return false;
        return pageLessThan.get(page1).contains(page2);
    }

    @Override
    public String toString() {
        return pageLessThan.toString();
    }
}
