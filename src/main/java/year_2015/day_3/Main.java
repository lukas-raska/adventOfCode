package year_2015.day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static final Path relativePath = Paths.get("src", "main", "java", "year_2015", "day_3", "input.txt");

    public static void main(String[] args) {

        int result = 0;
        int length = 0;

        try {
            List<String> lines = Files.readAllLines(relativePath.toAbsolutePath());
            for (String line : lines) {
                result += countHousesWithPresent(line);
                length += line.length();
            }

        } catch (IOException e) {
            System.err.println("Chyba při čtení souboru: " + e.getMessage());
        }

        System.out.println("The answer of day 3: " + result);
        System.out.println("Délka vstupu: " + length);

    }

    // ^ - sever - y++
    // v - jih - y--
    // > - východ x++
    // < - západ x--

    public static int countHousesWithPresent(String line) {
        int visitedHouses = 1;
        int x = 0;
        int y = 0;
        String startingLocation = String.format("[%s,%s]", x, y);
        String currentLocation = startingLocation;
        List<String> visitedCoordinates = new ArrayList<>();
        visitedCoordinates.add(currentLocation);
        boolean isVisitedNewPlace;
        for (char c : line.toCharArray()) {
            System.out.println("Navštívené souřadnice: " + visitedCoordinates.size());
            isVisitedNewPlace = false;
            switch (c) {
                case '^':
                    currentLocation = String.format("[%s,%s]", x, y++);
                    if (!visitedCoordinates.contains(currentLocation)) {
                        visitedHouses++;
                        visitedCoordinates.add(new String(String.format("[%s,%s]", x, y)));
                    }
                    break;
                case 'V':
                    currentLocation = String.format("[%s,%s]", x, y--);
                    if (!visitedCoordinates.contains(currentLocation)) {
                        visitedHouses++;
                        visitedCoordinates.add(new String(String.format("[%s,%s]", x, y)));
                    }
                    break;
                case '>':
                    currentLocation = String.format("[%s,%s]", x++, y);
                    if (!visitedCoordinates.contains(currentLocation)) {
                        visitedHouses++;
                        visitedCoordinates.add(new String(String.format("[%s,%s]", x, y)));
                    }
                    break;
                case '<':
                    currentLocation = String.format("[%s,%s]", x--, y);
                    if (!visitedCoordinates.contains(currentLocation)) {
                        visitedHouses++;
                        visitedCoordinates.add(new String(String.format("[%s,%s]", x, y)));
                    }
                    break;
            }
        }
        return visitedHouses;
    }
}
