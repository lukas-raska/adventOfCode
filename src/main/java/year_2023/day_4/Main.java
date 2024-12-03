package year_2023.day_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static final Path relativePath = Paths.get("src", "main", "resources", "2023", "input_2023_4.txt");

    public static void main(String[] args) {

        int partOneResult = 0;
        int partTwoResult = 0;
        LocalDateTime start = LocalDateTime.now();

        try {
            //načtení souboru
            List<String> fetchedCards = Files.readAllLines(relativePath.toAbsolutePath());
            //part one
            for (String card : fetchedCards) {
                partOneResult += getTotalPoints(card);
            }
            //part two (nutno předělat, dává správný výsledek, ale děsně neefektivně)
            partTwoResult = countCardsWon(fetchedCards);

        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        LocalDateTime end = LocalDateTime.now();

        System.out.println("The answer of day 4:");
        System.out.println("Part one: " + partOneResult);
        System.out.println("Part two: " + partTwoResult);

        Duration duration = Duration.between(start, end);

        System.out.println("Doba běhu: " + duration.getSeconds() + "," + duration.toMillisPart() + " s");

    }

    public static int countCardsWon(List<String> cards) {

        List<String> listOfWonCards = new ArrayList<>(cards);
        long numberOfMatches;

        for (int i = 0; i < listOfWonCards.size(); i++) {

            String currentCard = listOfWonCards.get(i);

            numberOfMatches = countMatches(listOfWonCards.get(i));

            if (numberOfMatches == 0) {
                continue;
            }
            for (int j = 0; j < numberOfMatches; j++) {
                listOfWonCards.add(cards.get(getCardID(currentCard) + j));
            }
        }
        return listOfWonCards.size();
    }

    //ad part one
    public static long getTotalPoints(String input) {
        //zahodit část stringu s id
        String partWithNumbers = input.substring(9);

        //rozdělím dle znaku |
        String[] splitedPartWithNumbers = partWithNumbers.split("\\|");

        //čísla uložím do Listů
        List<Integer> winningNumbersList = splitAndParseToList(splitedPartWithNumbers[0], " ");
        List<Integer> secretNumbersList = splitAndParseToList(splitedPartWithNumbers[1], " ");

        //spočítám počet shod mezi výherními a tajnými čísly
        Long countOfMatchingNumbers = countMatches(winningNumbersList, secretNumbersList);

        //spočítám skóre (1. shoda -> 1 bod, za každou další shodu se skóre zdvojnásobí)
        int score = (countOfMatchingNumbers < 1) ? 0 : 1;
        for (int i = 1; i < countOfMatchingNumbers; i++) {
            score *= 2;
        }
        return score;
    }

    public static List<Integer> splitAndParseToList(String input, String delimiter) {
        List<Integer> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(input, delimiter);
        while (stringTokenizer.hasMoreElements()) {
            list.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        return list;
    }

    public static Long countMatches(List<Integer> winningNumbers, List<Integer> secretNumbers) {
        return secretNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public static Long countMatches(String card) {
        String[] splitedCard = card
                .substring(9)
                .split("\\|");
        List<Integer> winningNumbers = splitAndParseToList(splitedCard[0], " ");
        List<Integer> secretNumbers = splitAndParseToList(splitedCard[1], " ");
        return secretNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public static int getCardID(String card) {
        return Integer.parseInt(card.substring(5, 8).trim());
    }
}
