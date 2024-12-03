package year_2015.day_17;

import utils.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main17 {


    public static void main(String[] args) {

        int totalEggnog = 150;

        var containers = InputLoader.load(2015, 17)
                .stream()
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        //part 1 - number of possible container combinations to handle given amount of eggnog
        var possibleCombinations = getAllPossibleContainerCombinations(containers, totalEggnog);
        int resultPart1 = possibleCombinations.size();

        //part 2 -
        //find min used amount of containers in found combinations
        int minPossibleAmount = containers.length;
        for (var combination : possibleCombinations) {
            int usedContainer = Arrays.stream(combination)
                    .filter(containerVolume -> containerVolume != 0)
                    .sum();
            if (usedContainer < minPossibleAmount) {
                minPossibleAmount = usedContainer;
            }
        }

        //result is size of combinations with found minimum amount of containers used
        int finalMinPossibleAmount = minPossibleAmount;
        var resultPart2 = possibleCombinations.stream()
                .filter(combination -> Arrays.stream(combination)
                        .filter(container -> container != 0)
                        .sum() == finalMinPossibleAmount)
                .toList()
                .size();


        System.out.println("The answer of the day 17:");
        System.out.println("Part 1: " + resultPart1); //1638
        System.out.println("Part 2: " + resultPart2); //17


    }

    public static List<int[]> getAllPossibleContainerCombinations(int[] containers,
                                                                  int totalEggnog) {

        int length = containers.length;
        List<int[]> allCombinations = new ArrayList<>();

        generateContainerPossibilities(
                containers,
                new boolean[length],
                new int[length],
                allCombinations,
                totalEggnog,
                0);

        return allCombinations;
    }


    public static void generateContainerPossibilities(int[] containers,
                                                      boolean[] containerUsed,
                                                      int[] currentCombination,
                                                      List<int[]> allCombinations,
                                                      int remainingEggnog,
                                                      int indexOfContainer) {
        if (remainingEggnog == 0) {
            allCombinations.add(Arrays.copyOf(currentCombination, currentCombination.length));
            return;
        }
        for (int i = indexOfContainer; i < containers.length; i++) {
            if (!containerUsed[i] && remainingEggnog >= containers[i]) {
                currentCombination[i]++;
                containerUsed[i] = true;
                generateContainerPossibilities(
                        containers,
                        containerUsed,
                        currentCombination,
                        allCombinations,
                        remainingEggnog - containers[i],
                        i);
                currentCombination[i]--;
                containerUsed[i] = false;
            }
        }
    }


}
