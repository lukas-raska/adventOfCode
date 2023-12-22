package year_2023.day_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //načtení dat ze zdrojového souboru
        Path relativePath = Paths.get("src", "main", "java", "year_2023", "day_11", "input.txt");
        List<StringBuilder> input = new ArrayList<>();
        try {
            input = Files.readAllLines(relativePath.toAbsolutePath())
                    .stream()
                    .map(StringBuilder::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        //prázdný prostor (tj. bez galaxií "#") znásobím daným koeficientem
        List<StringBuilder> expandedSpacePartOne = expandSpace(input, 2);
        List<StringBuilder> expandedSpacePartTwo = expandSpace(input, 1000000);

        //vytvoření seznamu všech galaxií a jejich souřadnic [x,y] x-sloupec, y-řádek
        List<int[]> galaxyCoordinatesPartOne = getGalaxyCoordinates(expandedSpacePartOne);
        List<int[]> galaxyCoordinatesPartTwo = getGalaxyCoordinates(expandedSpacePartTwo);

        //výpočet vzdálenosti mezi galaxiemi
        long totalDistancePartOne = 0;
        long totalDistancePartTwo = 0;
        for (int i = 0; i < galaxyCoordinatesPartOne.size() - 1; i++) {
            for (int j = i + 1; j < galaxyCoordinatesPartOne.size(); j++) {
                totalDistancePartOne += countDistance(galaxyCoordinatesPartOne.get(i), galaxyCoordinatesPartOne.get(j));
            }
        }
        for (int i = 0; i < galaxyCoordinatesPartTwo.size() - 1; i++) {
            for (int j = i + 1; j < galaxyCoordinatesPartTwo.size(); j++) {
                totalDistancePartTwo += countDistance(galaxyCoordinatesPartTwo.get(i), galaxyCoordinatesPartTwo.get(j));
            }
        }

        System.out.println("The answer of the day 11: ");
        System.out.println("Part one: " + totalDistancePartOne);
        System.out.println("Part Two: " + totalDistancePartTwo);


    }

    public static List<int[]> getGalaxyCoordinates(List<StringBuilder> galaxyMap) {
        List<int[]> coordinatesList = new ArrayList<>();
        for (int i = 0; i < galaxyMap.size(); i++) {
            for (int j = 0; j < galaxyMap.get(i).length(); j++) {
                if (galaxyMap.get(i).charAt(j) == '#') {
                    coordinatesList.add(new int[]{j, i});
                }
            }
        }
        return coordinatesList;
    }

    /**
     * Každý řádek a sloupec, který obsahuje pouze prázdný prostor ".", znásobí zadaným koeficientem
     */
    public static List<StringBuilder> expandSpace(List<StringBuilder> sourceList, int coefficient) {
        //vytvoření nového listu pro výstup
        List<StringBuilder> outputList = new ArrayList<>();
        for (StringBuilder sb : sourceList) {
            outputList.add(new StringBuilder(sb.toString()));
        }
        //testuji sloupce
        String toInsert = ".".repeat(coefficient);
        for (int i = 0; i < outputList.get(0).length(); i++) {
            int offset = 0;
            int columnIndex = i + offset;
            if (outputList.stream().allMatch(stringBuilder -> stringBuilder.charAt(columnIndex) == '.')) {
                outputList.stream()
                        .forEach(stringBuilder -> {
                            stringBuilder.deleteCharAt(columnIndex);
                            stringBuilder.insert(columnIndex, toInsert);
                        });
                i += toInsert.length();
            }
        }
        //testuji řádky
        for (int i = 0; i < outputList.size(); i++) {
            if (outputList.get(i).indexOf("#") == -1) {
                outputList.add(i, outputList.get(i));
                i += (coefficient - 1);
            }
        }
        return outputList;
    }

    private static long countDistance(int[] startCoordinates, int[] targetCoordinates) {
        long xDist = Math.abs(targetCoordinates[0] - startCoordinates[0]);
        long yDist = Math.abs(targetCoordinates[1] - startCoordinates[1]);
        return xDist + yDist;
    }


}




