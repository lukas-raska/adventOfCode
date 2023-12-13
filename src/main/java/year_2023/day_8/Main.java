package year_2023.day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Path relativePath = Paths.get("src", "main", "java", "year_2023", "day_8", "input.txt");

        //načtení dat ze zdrojového souboru
        List<String> inputList = new ArrayList<>();
        try {
            inputList = Files.readAllLines(relativePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        //zpracování vstupních dat
        String navRules = inputList.get(0);

        Map<String, String[]> nodeMap = new LinkedHashMap<>();

        for (int i = 2; i < inputList.size(); i++) {
            String[] splitInput = inputList.get(i)
                    .replaceAll("[^A-Z0-9\\s]", "")
                    .split("\\s+");
            String currentKey = splitInput[0];
            String[] currentValue = {splitInput[1], splitInput[2]};
            nodeMap.put(currentKey, currentValue);
        }

        //PART ONE - hledání počtu kroků
        String start = "AAA";
        String target = "ZZZ";
        int steps = 0;
        int navIndex = 0;

        String currentNode = start;

        while (!currentNode.equals(target)) {
            navIndex = navIndex % navRules.length();
            currentNode = (navRules.charAt(navIndex) == 'L') ? nodeMap.get(currentNode)[0] : nodeMap.get(currentNode)[1];
            steps++;
            navIndex++;
        }
        System.out.println("The answer of the day 8: ");
        System.out.println("Part one: " + steps);



        //PART TWO


        //procházení cest


        System.out.println("Part two: " + countStepsPartTwo(nodeMap, navRules));

    }

    public static long countStepsPartTwo(Map<String, String[]> nodeMap, String navRules) {
        List<String> currentNodes = findStartingNodes(nodeMap);
        long steps = 0;
        int navIndex = 0;
        int hitCounter = 0;
        long[] hitZetIntervals = new long[currentNodes.size()];

        while (!(allNodesEndsToZet(currentNodes) || hitCounter == currentNodes.size())) {
            navIndex = navIndex % navRules.length();

            for (int i = 0; i < currentNodes.size(); i++) {
                String nextNode = (navRules.charAt(navIndex) == 'L') ?
                        nodeMap.get(currentNodes.get(i))[0] : nodeMap.get(currentNodes.get(i))[1];
                currentNodes.set(i, nextNode);

                if (nextNode.endsWith("Z")) {
                    hitCounter++;
                    hitZetIntervals[i] = steps + 1;
                }
            }
            steps++;
            navIndex++;
        }


        //vzhledem k tomu, že se intervaly trefení Z-uzlů periodicky opakují. výsledný počet krok se získá jako nejmenší společný násobek dílčích intervalů
        long[] dividedHitZetIntervals = Arrays.stream(hitZetIntervals).map(step -> step / navRules.length()).toArray();
        steps = navRules.length();
        for (long n :dividedHitZetIntervals){
            steps*=n;
        }
        return steps;
    }

    private static List<String> findStartingNodes(Map<String, String[]> nodeMap) {
        return nodeMap.keySet().stream()
                .filter(key -> key.endsWith("A"))
                .collect(Collectors.toList());
    }

    private static boolean allNodesEndsToZet(List<String> currentNodes) {
        return currentNodes.stream()
                .allMatch(node -> node.endsWith("Z"));
    }
}