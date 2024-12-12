package au.id.foxy.aoc2024.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import au.id.foxy.aoc2024.AdventOfCode;
import au.id.foxy.aoc2024.AdventOfCodePart;

public class Day9 implements AdventOfCode {
    private List<Integer> blocks;
    private List<Integer> freeSpaceIndexes;
    private Map<Integer, Integer> idTofileSizeMap;
    private Map<Integer, List<Integer>> fileSizeToIdsMap;
    private Map<Integer, Integer> fileLocationMap;
    private List<Integer> blockIdList;

    public Day9(Path filePath) throws IOException {
        var inputChars = Files.readString(filePath).stripTrailing().toCharArray();
        blocks = new ArrayList<>();
        freeSpaceIndexes = new LinkedList<>();
        idTofileSizeMap = new HashMap<>();
        fileSizeToIdsMap = new HashMap<>();
        fileLocationMap = new HashMap<>();
        blockIdList = new ArrayList<>();

        int blockId = 0;
        for (int x = 0; x < inputChars.length; x++) {
            if (x % 2 == 0) {
                int fileSize = Character.getNumericValue(inputChars[x]);
                for (int y = 1; y <= fileSize; y++) {
                    blocks.add(blockId);
                    if (y == 1)
                        fileLocationMap.put(blockId, blocks.size() - 1);
                }
                idTofileSizeMap.put(blockId, fileSize);
                if (!fileSizeToIdsMap.containsKey(fileSize))
                    fileSizeToIdsMap.put(fileSize, new ArrayList<>());
                blockIdList.add(blockId);
                blockId++;
            } else {
                for (int y = 1; y <= Character.getNumericValue(inputChars[x]); y++) {
                    blocks.add(null);
                    freeSpaceIndexes.add(blocks.size() - 1);
                }
            }
        }
        // System.out.println(this.blocksString());
    }

    static String blocksString(List<Integer> blocks) {
        var s = new StringBuilder();
        for (var x : blocks) {
            if (x == null) {
                s.append(".");
            } else {
                s.append(String.valueOf(x));
            }
            s.append(" ");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    static long checksum(List<Integer> blocks) {
        long checksumValue = 0;
        for (var x = 0; x < blocks.size(); x++) {
            if (blocks.get(x) == null)
                continue;
            checksumValue += x * blocks.get(x);
        }
        return checksumValue;
    }

    static int checkFreeSpace(List<Integer> blocks, int index) {
        int freeSpace = 0;
        for (int x = index; x < blocks.size() && blocks.get(x) == null; x++)
            freeSpace++;
        return freeSpace;
    }

    public AdventOfCodePart part1() {
        var part = new AdventOfCodePart();
        var blocks = new ArrayList<>(this.blocks);
        var freeSpaceIndexes = new LinkedList<>(this.freeSpaceIndexes);
        for (int x = blocks.size() - 1; x > 0; x--) {
            if (freeSpaceIndexes.get(0) > x)
                break;
            if (blocks.get(x) != null) {
                blocks.set(freeSpaceIndexes.get(0), blocks.get(x));
                freeSpaceIndexes.remove(0);
                freeSpaceIndexes.add(x);
                blocks.set(x, null);
            }
        }

        // System.out.println(blocksString(blocks));
        part.setOutput(String.valueOf(checksum(blocks)));
        return part;
    }

    public AdventOfCodePart part2() {
        var part = new AdventOfCodePart();
        var filesToMove = new ArrayList<>(this.blockIdList);
        Collections.reverse(filesToMove);

        MainLoop:
        for (int fileIdIndex = 0; fileIdIndex < filesToMove.size(); fileIdIndex++) {
            var fileToMove = filesToMove.get(fileIdIndex);
            var fileLocation = fileLocationMap.get(fileToMove);
            for (int x = 0; x < fileLocation; x++) {
                var freeSpace = checkFreeSpace(this.blocks, x);
                if (freeSpace == 0)
                    continue;
                if (freeSpace < idTofileSizeMap.get(fileToMove)) {
                    x += freeSpace - 1;
                    continue;
                }
                for (int y = 0; y < idTofileSizeMap.get(fileToMove); y++) {
                    this.blocks.set(x + y, fileToMove);
                    this.blocks.set(fileLocationMap.get(fileToMove) + y, null);
                }
                continue MainLoop;
            }
        }

        // System.out.println(blocksString(this.blocks));

        part.setOutput(String.valueOf(checksum(this.blocks)));
        return part;
    }
}
