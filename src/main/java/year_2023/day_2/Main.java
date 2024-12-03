package year_2023.day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static final int redMax = 12;
    public static final int greenMax = 13;
    public static final int blueMax = 14;

    public static final Path relativePath = Paths.get("src", "main", "resources","2023", "input_2023_2.txt");

    public static void main(String[] args) {

        int sumOfIndexes = 0; //part one
        int sumOfPowers = 0; //part two

        try {
            List<String> lines = Files.readAllLines(relativePath.toAbsolutePath());
            for (String line : lines) {
                sumOfIndexes += getIndexOfPossibleGame(line, redMax, greenMax, blueMax);
                List<Integer> minPossibleCubes = getListOfPossibleMinimums(line);
                sumOfPowers+=getPowerOfPossibleSet(minPossibleCubes);
            }
        } catch (IOException exception) {
            System.err.println("File reading error: " + exception.getMessage());
        }

        System.out.println("The answer of day 2: ");
        System.out.println("part one: " + sumOfIndexes);
        System.out.println("part two:  " + sumOfPowers);
    }


    public static Map<String, List<Integer>> analyzeGame(String inputLine) {

        List<Integer> indexesList = new ArrayList<>();
        List<Integer> redCubes = new ArrayList<>();
        List<Integer> greenCubes = new ArrayList<>();
        List<Integer> blueCubes = new ArrayList<>();

        StringTokenizer splittedInput = new StringTokenizer(inputLine, ":,;");
        String token;
        while (splittedInput.hasMoreElements()) {
            token = splittedInput.nextToken();
            if (token.contains("Game")) {
                token = token.replace("Game", "").trim();
                indexesList.add(Integer.parseInt(token));
            }
            if (token.contains("red")) {
                token = token.replace("red", "").trim();
                redCubes.add(Integer.parseInt(token));
            }
            if (token.contains("green")) {
                token = token.replace("green", "").trim();
                greenCubes.add(Integer.parseInt(token));
            }
            if (token.contains("blue")) {
                token = token.replace("blue", "").trim();
                blueCubes.add(Integer.parseInt(token));
            }
        }

        Map<String, List<Integer>> output = new HashMap<>();
        output.put("index", indexesList);
        output.put("reds", redCubes);
        output.put("greens", greenCubes);
        output.put("blues", blueCubes);

        return output;
    }

    public static int getIndexOfPossibleGame(String inputLine, int redMax, int greenMax, int blueMax) {
        Map<String, List<Integer>> analyzedGame = analyzeGame(inputLine);
        //pokud některá hodnota překročí mez, hra není možná
        if (
                Collections.max(analyzedGame.get("reds")) > redMax ||
                        Collections.max(analyzedGame.get("greens")) > greenMax ||
                        Collections.max(analyzedGame.get("blues")) > blueMax
        ) {
            return 0;
        }
        return analyzedGame.get("index").get(0);
    }

    public static List<Integer> getListOfPossibleMinimums(String inputLine) {
        Map<String, List<Integer>> analyzedGame = analyzeGame(inputLine);
        List<Integer> output = new ArrayList<>();
        output.add(Collections.max(analyzedGame.get("reds")));
        output.add(Collections.max(analyzedGame.get("greens")));
        output.add(Collections.max(analyzedGame.get("blues")));
        return output;
    }

    public static int getPowerOfPossibleSet(List<Integer> possibleSet) {
        int power = 1;
        for (Integer number : possibleSet) {
            power *= number;
        }
        return power;
    }

}





