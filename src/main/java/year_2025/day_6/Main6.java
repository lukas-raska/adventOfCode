package year_2025.day_6;

import common.utils.InputLoader;

import java.io.IOException;

public class Main6 {


    public static void main(String[] args) throws IOException {

        String testData = """
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s""";

        new Day6Solver(testData.lines().toList()).printSolution();
        new Day6Solver(InputLoader.load(2025, 6)).printSolution();
    }
}
