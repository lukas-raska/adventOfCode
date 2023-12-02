package day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static final Path relativePath = Paths.get("src", "main", "resources", "input.txt");

    public static void main(String[] args) {

        int sumOfValues = 0;

        try {
            List<String> lines = Files.readAllLines(relativePath.toAbsolutePath());
            for (String line : lines) {
                sumOfValues += getCalibrationValue(line, true);
            }
        } catch (IOException exception) {
            System.out.println("File reading error: " + exception.getMessage());
        }
        System.out.println("Answer of 1st day: " + sumOfValues);
    }

    public static int getCalibrationValue(String input, boolean isPartTwo) {
        //part two also takes into account numbers in text form
        if (isPartTwo) {
            String[] numbersAsString = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
            //first and last character retained due to interleaving of some numbers
            String[] numberSubstitutes = {"z0o", "o1e", "t2o", "t3e", "f4r", "f5e", "s6x", "s7n", "e8t", "n9e"}; //initial and final character retained due to interleaving of some numbers
            for (int i = 0; i < numbersAsString.length; i++) {
                if (input.contains(numbersAsString[i])) {
                    input = input.replace(numbersAsString[i], numberSubstitutes[i]);
                }
            }
        }
        StringBuilder digits = new StringBuilder();
        for (Character c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }
        if (digits.isEmpty()) {
            return 0;
        }
        String firstDigit = String.valueOf(digits.charAt(0));
        String lastDigit = String.valueOf(digits.charAt(digits.length() - 1));
        return Integer.parseInt(firstDigit + lastDigit);

    }

}

