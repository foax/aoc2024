package au.id.foxy.aoc2024.day5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SafetyManual {
    private List<SafetyManualPage> pageList;

    public SafetyManual(String pageString, SafetyManualRules rules) {
        this.pageList = Arrays.stream(pageString.split(",")).map(Integer::parseInt).map(x -> new SafetyManualPage(x, rules)).collect(Collectors.toList());
    }

    public SafetyManualPage getMiddlePage() {
        return this.pageList.get(this.pageList.size() / 2);
    }

    public boolean checkPageOrder() {
        for (int i = 0; i < this.pageList.size() - 1; i++) {
            int j = i+1;
            if (pageList.get(i).compareTo(pageList.get(j)) >= 0)
                return false;
        }
        return true;
    }

    public void sortPages() {
        Collections.sort(pageList);
    }
}
