package year_2015.day_2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final Path relativePath = Paths.get("src", "main", "java", "year_2015", "day_2", "input.txt");

    public static void main(String[] args) {

        int totalPaperArea = 0;
        int totalNeededRibbon = 0;

        try {
            List<String> lines = Files.readAllLines(relativePath.toAbsolutePath());
            for (String line : lines) {
                totalPaperArea += getNeededPaperAreaForPresent(line);
                totalNeededRibbon += getNeedRibbonForPresent(line);
            }
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        System.out.println("The answer of day 2: ");
        System.out.println("Part one: " + totalPaperArea);
        System.out.println("Part two: " + totalNeededRibbon);

    }

    public static int getNeededPaperAreaForPresent(String presentDimensions) {

        String[] fetchedDimensions = presentDimensions.split("x");
        if (fetchedDimensions.length != 3) {
            return -1; //wrong input
        }
        int length = Integer.parseInt(fetchedDimensions[0]);
        int width = Integer.parseInt(fetchedDimensions[1]);
        int heigth = Integer.parseInt(fetchedDimensions[2]);

        List<Integer> sideAreas = new ArrayList<>();
        sideAreas.add(length * width);
        sideAreas.add(length * heigth);
        sideAreas.add(heigth * width);

        int reserve = Collections.min(sideAreas);
        int sumOfAreas = sideAreas.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int neededPaperArea = 2 * sumOfAreas + reserve;

        return neededPaperArea;
    }

    public static int getNeedRibbonForPresent (String presentDimensions){
        String[] fetchedDimensions = presentDimensions.split("x");
        if (fetchedDimensions.length != 3) {
            return -1; //wrong input
        }
        int length = Integer.parseInt(fetchedDimensions[0]);
        int width = Integer.parseInt(fetchedDimensions[1]);
        int heigth = Integer.parseInt(fetchedDimensions[2]);
        List<Integer>dimensions = new ArrayList<>();
        dimensions.add(length);
        dimensions.add(width);
        dimensions.add(heigth);
        Collections.sort(dimensions);

        int smallestSide = dimensions.get(0);
        int secondSmallest = dimensions.get(1);
        int volume = dimensions.get(0)* dimensions.get(1)*dimensions.get(2); //reserve for the bow

        int neededRibbon = 2*(smallestSide+secondSmallest)+volume;

        return neededRibbon;
    }
}
