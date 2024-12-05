package au.id.foxy.aoc2024.day5;

public class SafetyManualRule {
    private int[] rules = new int[2];

    public SafetyManualRule(String ruleSpec) {
        String[] parts = ruleSpec.split("\\|");
        this.rules[0] = Integer.parseInt(parts[0]);
        this.rules[1] = Integer.parseInt(parts[1]);
    }

    public int getRule(int idx) {
        return rules[idx];
    }

    @Override
    public String toString() {
        return "SafetyManualRule{" + rules[0] + ", " + rules[1] + "}";
    }
}
