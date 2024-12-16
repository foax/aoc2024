package au.id.foxy.aoc2024.day5;

public record SafetyManualPage(int pageNum, SafetyManualRules rules) implements Comparable<SafetyManualPage> {

    @Override
    public int compareTo(SafetyManualPage page) {
        if (this.pageNum == page.pageNum)
            return 0;
        if (rules.checkPageLessThan(this.pageNum, page.pageNum))
            return -1;
        else
            return 1;
    }

}
