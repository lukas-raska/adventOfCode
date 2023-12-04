package year_2023.day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final Path relativePath = Paths.get("src", "main", "java", "year_2023", "day_3", "input.txt");

    public static void main(String[] args) {

        int resultPartOne = 0;
        int resultPartTwo = 0;

        try {
            resultPartOne = getTheSumOfEnginePartNumbers(Files.readAllLines(relativePath.toAbsolutePath()));
            resultPartTwo = getGearRatio(Files.readAllLines(relativePath.toAbsolutePath()));

        } catch (IOException exception) {
            System.err.println("File reading error: " + exception.getMessage());
        }

        System.out.println("The answer of Day 3: ");
        System.out.println("Part one: " + resultPartOne);
        System.out.println("Part two: " + resultPartTwo);
    }

    public static int getGearRatio(List<String> engineSchema) {

        int output = 0;

        //přidám znaky na začátek a konec řádku, abych nenarážel na hranice pole při následném procházení
        List<String> engineSchemaModified = new ArrayList<>();
        for (String line : engineSchema) {
            engineSchemaModified.add("XXX" + line + "XXX");
        }

        //cyklus pro procházení jednotlivých řádků
        for (int line = 0; line < engineSchema.size(); line++) {

            String previousLine = (line == 0) ? null : engineSchemaModified.get(line - 1);
            String currentLine = engineSchemaModified.get(line);
            String nextLine = (line == engineSchema.size() - 1) ? null : engineSchemaModified.get(line + 1);

            for (int charPositionIndex = 3; charPositionIndex < currentLine.length() - 3; charPositionIndex++) {
                char currentChar = currentLine.charAt(charPositionIndex);
                if (currentChar == '*') { //pokud naleznu *, prohledám její okolí (indexy -3 a +3)

                    //v případě nalezení hvězdičky definuji její okolí, ve kterém budu ověřovat přítomnost čísel
                    String toCheckFromPreviousLine = (previousLine == null) ? "" : previousLine.substring(charPositionIndex - 3, charPositionIndex + 4);
                    String toCheckFromCurrentLine = currentLine.substring(charPositionIndex - 3, charPositionIndex + 4);
                    String toCheckFromNextLine = (nextLine == null) ? "" : nextLine.substring(charPositionIndex - 3, charPositionIndex + 4);

                    StringBuilder currentNumber = new StringBuilder();
                    List<Integer> indexesOfNumber = new ArrayList<>();
                    List<Integer> foundedNumbers = new ArrayList<>();
                    boolean isNumber;
                    boolean isNearTheStar;

                    //kontrola okolí nad hvězdičkou
                    for (int i = 0; i < toCheckFromPreviousLine.length(); i++) {
                        char current = toCheckFromPreviousLine.charAt(i);
                        //pokud narazím na číslici, zaznamenám si její pozici a hodnotu
                        if (Character.isDigit(current)) {
                            isNumber = true;
                            indexesOfNumber.add(i);
                            currentNumber.append(current);
                        } else {
                            isNumber = false;
                        }
                        //pokud jsem na konci testovaného řádku
                        if (i == toCheckFromPreviousLine.length() - 1) {
                            isNumber = false;
                        }
                        //otestuji poslední číslo, zda se nachází v blízkosti hvězdičky
                        if (!isNumber && !currentNumber.isEmpty()) {
                            isNearTheStar = indexesOfNumber.contains(2) || indexesOfNumber.contains(3) || indexesOfNumber.contains(4);
                            if (isNearTheStar) {
                                foundedNumbers.add(Integer.parseInt(currentNumber.toString())); //pokud ano, uložím
                            }
                            //a vynuluju data posledního čísla
                            indexesOfNumber = new ArrayList<>();
                            currentNumber = new StringBuilder();
                        }
                    }

                    //kontrola okolí vedle hvězdičky
                    for (int i = 0; i < toCheckFromCurrentLine.length(); i++) {
                        char currentCharOnCurrentLine = toCheckFromCurrentLine.charAt(i);
                        if (Character.isDigit(currentCharOnCurrentLine)) {
                            isNumber = true;
                            indexesOfNumber.add(i);
                            currentNumber.append(currentCharOnCurrentLine);
                        } else {
                            isNumber = false;
                        }
                        if (i == toCheckFromCurrentLine.length() - 1) {
                            isNumber = false;
                        }
                        if (!isNumber && !currentNumber.isEmpty()) {
                            isNearTheStar = indexesOfNumber.contains(2) || indexesOfNumber.contains(3) || indexesOfNumber.contains(4);
                            if (isNearTheStar) {
                                foundedNumbers.add(Integer.parseInt(currentNumber.toString()));
                            }
                            indexesOfNumber = new ArrayList<>();
                            currentNumber = new StringBuilder();
                        }
                    }

                    //kontrola okolí pod hvězdičkou
                    for (int i = 0; i < toCheckFromNextLine.length(); i++) {
                        char currentCharOnNextLine = toCheckFromNextLine.charAt(i);
                        if (Character.isDigit(currentCharOnNextLine)) {
                            isNumber = true;
                            indexesOfNumber.add(i);
                            currentNumber.append(currentCharOnNextLine);
                        } else {
                            isNumber = false;
                        }
                        if (i == toCheckFromNextLine.length() - 1) {
                            isNumber = false;
                        }
                        if (!isNumber && !currentNumber.isEmpty()) {
                            isNearTheStar = indexesOfNumber.contains(2) || indexesOfNumber.contains(3) || indexesOfNumber.contains(4);
                            if (isNearTheStar) {
                                foundedNumbers.add(Integer.parseInt(currentNumber.toString()));
                            }
                            indexesOfNumber = new ArrayList<>();
                            currentNumber = new StringBuilder();
                        }
                    }

                    if (foundedNumbers.size() == 2) {
                        System.out.println("Započítávám čísla: " + foundedNumbers.get(0) + " a " + foundedNumbers.get(1));
                        System.out.println();
                        output += (foundedNumbers.get(0) * foundedNumbers.get(1));
                    } else {
                        System.out.println("Počet okolních čísel je: " + foundedNumbers.size() + " - Nezapočítávám nic.");
                        System.out.println();
                    }
                }
            }
        }
        return output;
    }

    public static int getTheSumOfEnginePartNumbers(List<String> engineSchema) {

        int output = 0;

        //cyklus pro procházení jednotlivých řádků
        for (int line = 0; line < engineSchema.size(); line++) {

            String previousLine = (line == 0) ? null : engineSchema.get(line - 1);
            String currentLine = engineSchema.get(line);
            String nextLine = (line == engineSchema.size() - 1) ? null : engineSchema.get(line + 1);

            int uncheckedNumber, validNumber;
            boolean isNumber;

            List<Integer> indexesOfCurrentNumber = new ArrayList<>();
            StringBuilder currentNumber = new StringBuilder();

            //pomocí tohoto cyklu procházím jednotlivé řádky a hledám na nich čísla
            for (int charPositionIndex = 0; charPositionIndex < currentLine.length(); charPositionIndex++) {

                if (Character.isDigit(currentLine.charAt(charPositionIndex))) {
                    isNumber = true;
                    indexesOfCurrentNumber.add(charPositionIndex);
                    currentNumber.append(currentLine.charAt(charPositionIndex));
                } else {
                    isNumber = false;
                }
                if (charPositionIndex == currentLine.length() - 1)
                    isNumber = false;

                if (!currentNumber.isEmpty() && !isNumber) {
                    uncheckedNumber = Integer.parseInt(currentNumber.toString());

                    int indexBeforeNumber = (indexesOfCurrentNumber.get(0) == 0) ?
                            0 : indexesOfCurrentNumber.get(0) - 1;
                    int indexAfterNumber = (indexesOfCurrentNumber.get(indexesOfCurrentNumber.size() - 1) == currentLine.length() - 1) ?
                            currentLine.length() - 1 : indexesOfCurrentNumber.get(indexesOfCurrentNumber.size() - 1) + 1;

                    //řetězce, které budu prohledávat na výskyt znaků jiných než čísla, nebo tečky
                    String toCheckFromPreviousLine = (previousLine == null) ? "" : previousLine.substring(indexBeforeNumber, indexAfterNumber + 1);
                    String toCheckFromCurrentLine = currentLine.substring(indexBeforeNumber, indexAfterNumber + 1);
                    String toCheckFromNextLine = (nextLine == null) ? "" : nextLine.substring(indexBeforeNumber, indexAfterNumber + 1);

                    boolean symbolsInPreviosLine = false;
                    boolean symbolsInCurrentLine = false;
                    boolean symbolsInNextLine = false;

                    //ověření, zda okolí čísla neobsahuje jiné znaky než čísla, nebo tečky
                    for (char c : toCheckFromPreviousLine.toCharArray()) {
                        if (!Character.isDigit(c) && c != '.') {
                            symbolsInPreviosLine = true;
                            break;
                        }
                    }
                    for (char c : toCheckFromCurrentLine.toCharArray()) {
                        if (!Character.isDigit(c) && c != '.') {
                            symbolsInCurrentLine = true;
                            break;
                        }
                    }
                    for (char c : toCheckFromNextLine.toCharArray()) {
                        if (!Character.isDigit(c) && c != '.') {
                            symbolsInNextLine = true;
                            break;
                        }
                    }

                    boolean isPartOfEngine = symbolsInPreviosLine || symbolsInCurrentLine || symbolsInNextLine;

                    validNumber = isPartOfEngine ? uncheckedNumber : 0;
                    output += validNumber;

                    //vynulování proměnných pro další číslo
                    indexesOfCurrentNumber = new ArrayList<>();
                    currentNumber = new StringBuilder();
                }
            }
        }
        return output;
    }
}

