import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

class Day1 {
    public static void main(String[] args) {
        List<List<Integer>> locationIds = new ArrayList<>();
        locationIds.add(new ArrayList<>());
        locationIds.add(new ArrayList<>());

        HashMap<Integer, Integer> locFrequency = new HashMap<>();
        
        var filePath = "day1_input.txt";
        int inputLength = 0;
        int score = 0;
        int similarityScore = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                locationIds.get(0).add(Integer.parseInt(parts[0]));
                locationIds.get(1).add(Integer.parseInt(parts[1]));
                inputLength++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        locationIds.get(0).sort(Integer::compareTo);
        locationIds.get(1).sort(Integer::compareTo);

        for (int x = 0; x < inputLength; x++) {
            score += Math.abs(locationIds.get(0).get(x) - locationIds.get(1).get(x));
            locFrequency.put(locationIds.get(1).get(x), locFrequency.getOrDefault(locationIds.get(1).get(x), 0) + 1);
        }

        for (Integer x : locationIds.get(0)) {
            similarityScore += x * locFrequency.getOrDefault(x, 0);
        }

        System.out.println("Part 1: " + score);
        System.out.println("Part 2: " + similarityScore);

    }
}
