package year_2015.day_16;

import utils.InputLoader;

import java.util.Map;
import java.util.stream.Collectors;

public class Main16 {

    private static final Map<String, Integer> GIFT_CLUES = Map.of(
            "children", 3,
            "cats", 7,
            "samoyeds", 2,
            "pomeranians", 3,
            "akitas", 0,
            "vizslas", 0,
            "goldfish", 5,
            "trees", 3,
            "cars", 2,
            "perfumes", 1
    );

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2015, 16, "input_2015_16.txt");

        var auntSueClues = puzzleInput.stream().map(AuntSue::parse).toList();

        var differencesMap = auntSueClues.stream()
                .collect(Collectors.toMap(
                        AuntSue::id,
                        Main16::countDifferenceForPart1)
                );

        int minDiff = differencesMap.values().stream().min(Integer::compareTo).orElseThrow();
        int searchedSueId = differencesMap.entrySet().stream()
                .filter(entry -> entry.getValue() == minDiff)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        //part 2
        var pointsMap = auntSueClues.stream()
                .collect(Collectors.toMap(
                        AuntSue::id,
                        Main16::countPointsForPart2)
                );


        int maxPoints = pointsMap.values().stream().max(Integer::compareTo).orElseThrow();
        int searchedSueId2 = pointsMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxPoints)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        System.out.println("The answer of the day 16:");
        System.out.println("Part 1: " + searchedSueId); //373
        System.out.println("Part 2: " + searchedSueId2); //260

    }

    public static int countDifferenceForPart1(AuntSue sueData) {

        int totalDifference = 0;

        for (var entry : GIFT_CLUES.entrySet()) {
            String giftClueKey = entry.getKey();
            int giftClueValue = entry.getValue();

            if (sueData.clues().containsKey(giftClueKey)) {
                int currentDiff = Math.abs(giftClueValue - sueData.clues().get(giftClueKey));
                totalDifference += currentDiff;
            }
        }
        return totalDifference;
    }

    public static int countPointsForPart2(AuntSue sueData) {
        int points = 0;

        for (var entry : GIFT_CLUES.entrySet()) {

            String giftClueKey = entry.getKey();
            int giftClueValue = entry.getValue();

            if (sueData.clues().containsKey(giftClueKey)) {

                int sueClueValue = sueData.clues().get(giftClueKey);

                if (giftClueKey.equals("cats") || giftClueKey.equals("trees")) {
                    if (sueClueValue > giftClueValue) {
                        points++;
                    }

                } else if (giftClueKey.equals("pomeranians") || giftClueKey.equals("goldfish")) {
                    if (sueClueValue < giftClueValue) {
                        points++;
                    }

                } else {
                    if (sueClueValue == giftClueValue) {
                        points++;
                    }
                }
            }

        }
        return points;
    }
}
