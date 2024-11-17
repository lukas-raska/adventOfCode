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
                        Main16::countDifference)
                );


        int minDiff = differencesMap.values().stream().min(Integer::compareTo).orElseThrow();
        int searchedSueId = differencesMap.entrySet().stream()
                .filter(entry -> entry.getValue() == minDiff)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        System.out.println("The answer of the day 16:");
        System.out.println("Part 1: " + searchedSueId); //373

    }

    public static int countDifference(AuntSue sueData) {

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
}
