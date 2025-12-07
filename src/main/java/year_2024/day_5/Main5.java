package year_2024.day_5;

import common.utils.InputLoader;

import java.util.List;

public class Main5 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2024, 5);

        Day5Solver day5Solver = new Day5Solver(puzzleInput);

        System.out.println("Part 1: " + day5Solver.solvePart1()); //5108
        System.out.println("Part 2: " + day5Solver.solvePart2()); //7380
    }
}
