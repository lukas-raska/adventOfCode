package year_2023.day_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Path relativePath = Paths.get("src", "main", "java", "year_2023", "day_9", "input.txt");

        //načtení dat ze zdrojového souboru
        List<String> fetchedInput = new ArrayList<>();
        try {
            fetchedInput = Files.readAllLines(relativePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        //vstup převedu na seznam integer polí představujících jednotlivé řady
        List<long[]> parsedInput = new ArrayList<>();
        for (String s : fetchedInput) {
            String[] splitInputLine = s.trim().split(" ");
            long[] parsedInputLine = new long[splitInputLine.length];
            for (int i = 0; i < splitInputLine.length; i++) {
                parsedInputLine[i] = Long.parseLong(splitInputLine[i]);
            }
            parsedInput.add(parsedInputLine);
        }

        //pro hledání extrapolace na začátku polí každé z nich reverzuji


        //sem budou ukládány požadované extrapolované hodnoty
        long outputPartOne = 0;
        long outputPartTwo = 0;

        //cyklus pro každý řádek ze zadaného vstupu
        for (long[] numbers : parsedInput) {
            //pro extrapolaci na konci pole (part one)
            outputPartOne += extrapolate(numbers);

            //pro extrapolaci na začátku pole jednoduše otočím
            long[] reversedNumbers = new long[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                reversedNumbers[i] = numbers[numbers.length - 1 - i];
            }
            outputPartTwo += extrapolate(reversedNumbers);
        }

        System.out.println("The answer of day 9:");
        System.out.println("Part one: " + outputPartOne);
        System.out.println("Part two: " + outputPartTwo);

    }

    public static long extrapolate(long[] numbers) {
        //pro uložení výsledku, extrapolovaného čísla
        long extrapolated = 0;
        //pomocný seznam řad, do kterého budou ukládány mezivýpočty
        List<long[]> processedLines = new ArrayList<>();
        processedLines.add(numbers);
        long[] currentLine = processedLines.get(0);

        //do pomocného listu vložím nové pole (vždy o prvek kratší) a do něj rozdíly prvků z pole předcházejícího)
        //to se opakuje, dokud v posledním řádku nezbudou pouze nulové prvky
        while (!(Arrays.stream(processedLines.get(processedLines.size() - 1)).allMatch(n -> n == 0))) {
            long[] newLine = new long[currentLine.length - 1];
            for (int i = 0; i < newLine.length; i++) {
                newLine[i] = currentLine[i + 1] - currentLine[i];
            }
            processedLines.add(newLine);
            currentLine = newLine;
        }
        //sečtením posledních čísel z každého řádku tohoto pomocného seznamu poté získám extrapolované číslo z původní řady
        for (int j = processedLines.size() - 1; j >= 0; j--) {
            extrapolated += processedLines.get(j)[processedLines.get(j).length - 1];
        }
        return extrapolated;
    }
}
