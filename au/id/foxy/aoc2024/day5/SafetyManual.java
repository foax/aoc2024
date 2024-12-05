package au.id.foxy.aoc2024.day5;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class SafetyManual {
    private List<Integer> pageList;
    private Map<Integer, Integer> pageMap;

    public SafetyManual(String pageString) {
        this.pageList = new ArrayList<>();
        this.pageMap = new HashMap<>();

        String[] parts = pageString.split(",");
        for (String x : parts)
            this.pageList.add(Integer.parseInt(x));

        for (int i = 0; i < this.pageList.size(); i++)
            pageMap.put(this.pageList.get(i), i);
    }

    public int getMiddlePage() {
        return this.pageList.get(this.pageList.size() / 2);
    }

    private boolean checkPageOrder(SafetyManualRule rule) {
        // Check if page a occurs beore page b
        if (!this.pageMap.containsKey(rule.getRule(0)) || !this.pageMap.containsKey(rule.getRule(1)))
            return true;
        if (this.pageMap.get(rule.getRule(0)) < this.pageMap.get(rule.getRule(1)))
            return true;
        else
            return false;
    }

    public boolean checkPageOrderingRules(List<SafetyManualRule> pageOrderingRules) {
        for (var rule : pageOrderingRules) {
            if (!checkPageOrder(rule))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SafetyManual{" + pageList.stream().map(String::valueOf).collect(Collectors.joining(",")) + "}";
    }
}
