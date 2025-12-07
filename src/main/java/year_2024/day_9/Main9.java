package year_2024.day_9;

import common.utils.InputLoader;

import java.io.IOException;

public class Main9 {

    public static void main(String[] args) throws IOException {

        String puzzleInput = InputLoader.load(2024, 9).getFirst();

        Day9Solver day9Solver = new Day9Solver(puzzleInput);

        System.out.println("Part 1: " + day9Solver.solvePart1() ); //6259790630969
        System.out.println("Part 2: " + day9Solver.solvePart2());
    }
}
