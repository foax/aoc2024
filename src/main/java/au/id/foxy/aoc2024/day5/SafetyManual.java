package au.id.foxy.aoc2024.day5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SafetyManual {
    private List<Integer> pageList;

    public SafetyManual(String pageString) {
        this.pageList = Arrays.stream(pageString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public int getMiddlePage() {
        return this.pageList.get(this.pageList.size() / 2);
    }

    public boolean checkPageOrder(SafetyManualRules rules) {
        for (int i = 0; i < this.pageList.size() - 1; i++) {
            for (int j = i+1; j < this.pageList.size(); j++) {
                if (!rules.checkPageLessThan(this.pageList.get(i), this.pageList.get(j)))
                    return false;
            }
        }
        return true;
    }

    public void sortPages(SafetyManualRules rules) {
        boolean swapped = false;
        // System.out.println(this.pageList);
        do {
            swapped = false;
            for (int i = 0; i < this.pageList.size() - 1; i++) {
                    int j = i + 1;
                    if (!rules.checkPageLessThan(this.pageList.get(i), this.pageList.get(j))) {
                        int k = this.pageList.get(j);
                        this.pageList.set(j, this.pageList.get(i));
                        this.pageList.set(i, k);
                        swapped = true;
                    }
                }
            }
        while (swapped);
    }


    // @Override
    // public String toString() {
    //     return "SafetyManual{" + pageList.stream().map(String::valueOf).collect(Collectors.joining(",")) + "}";
    // }
}
