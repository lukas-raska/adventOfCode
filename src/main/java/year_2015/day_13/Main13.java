package year_2015.day_13;

import utils.InputLoader;

public class Main13 {

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2015, 13, "input_2015_13.txt");

        TableManager tableManager = new TableManager (new Table(puzzleInput));

        int resultPart1 = tableManager.countOptimalHappiness();

        System.out.println("The answer of the day 13: ");
        System.out.println("Part 1: " + resultPart1);

    }


}
