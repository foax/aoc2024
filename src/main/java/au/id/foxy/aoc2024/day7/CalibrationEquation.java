package au.id.foxy.aoc2024.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalibrationEquation {
    private long answer;
    private List<Long> valueList;
    
    public CalibrationEquation(String line) {
        String[] parts = line.split(":\\s*");
        this.answer = Long.parseLong(parts[0]);
        valueList = Arrays.stream(parts[1].split("\\s+"))
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }

    private boolean evaluatePart(long current, int index, boolean concat) {
        if (current > answer)
            return false;
        if (index == valueList.size() - 1) {
            return (
                (current * this.valueList.get(index) == answer) ||
                (current + valueList.get(index) == answer) ||
                (concat && (Long.parseLong(String.valueOf(current) + String.valueOf(valueList.get(index))) == answer)));
        } else {
            return (
                evaluatePart(current * valueList.get(index), index + 1, concat) ||
                evaluatePart(current + valueList.get(index), index + 1, concat) ||
                (concat && (evaluatePart(Long.parseLong(String.valueOf(current) + String.valueOf(valueList.get(index))), index + 1, concat))));
        }
    }

    public boolean evaluate(boolean concat) {
        return evaluatePart(valueList.get(0), 1, concat);
    }

    public long getAnswer() {
        return this.answer;
    }

    @Override
    public String toString() {
        return this.answer + ": " + this.valueList.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
