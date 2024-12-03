package year_2023.day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //cesta ke zdrojovému souboru
        Path relativePath = Paths.get("src", "main", "resources", "2023", "input_2023_7.txt");

        //načtení dat ze zdrojového souboru
        List<String> inputList = new ArrayList<>();
        try {
            inputList = Files.readAllLines(relativePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Chyba čtení souboru: " + e.getMessage());
        }

        // zpracování načtených dat a uložení do Listu
        List<Hand> listOfHands = new ArrayList<>();
        for (String line : inputList) {
            String[] splittedLine = line.trim().split(" ");
            listOfHands.add(new Hand(splittedLine[0], Integer.parseInt(splittedLine[1])));
        }

        //part 1
        Collections.sort(listOfHands);
        int totalWinnings = 0;
        int rank = 1;
        for (Hand hand : listOfHands) {
            totalWinnings += rank * hand.getBid();
            rank++;
        }

        //part 2
        Hand.setPartTwo(true);
        Hand.setCardHierarchy("J23456789TQKA");

        int totalWinningsPartTwo = 0;
        rank = 1; //nutno přenastavit
        Collections.sort(listOfHands);
        for (Hand hand : listOfHands) {
            totalWinningsPartTwo += rank * hand.getBid();
            rank++;
        }

        System.out.println("The answer of the day 7: ");
        System.out.println("Part one: " + totalWinnings);
        System.out.println("Part two: " + totalWinningsPartTwo);
    }
}
