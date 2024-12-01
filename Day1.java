import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Day1 {
    public static void main(String[] args) {
        List<List<Integer>> locationIds = new ArrayList<>();
        locationIds.add(new ArrayList<>());
        locationIds.add(new ArrayList<>());
        
        var filePath = "day1_input.txt";
        int inputLength = 0;
        int score = 0;

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
        }

        System.out.println("Part 1: " + score);

    }
}
