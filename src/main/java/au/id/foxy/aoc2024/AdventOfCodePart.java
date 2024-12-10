package au.id.foxy.aoc2024;

public class AdventOfCodePart {
    private long startTime = 0L;
    private long endTime = 0L;
    private String output = "";

    public AdventOfCodePart() {
        this.startTime = System.nanoTime();
    }

    public void setOutput(String output) {
        this.endTime = System.nanoTime();
        this.output = output;
    }

    public String getOutput() {
        return this.output;
    }

    public Long getDuration() {
        return this.endTime - this.startTime;
    }
}
