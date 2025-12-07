package year_2024.day_2;

import common.utils.InputLoader;

import java.util.List;

public class Main2 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2024, 2);

        Day2Solver day2Solver = new Day2Solver(puzzleInput);

        System.out.println("Part 1: " + day2Solver.solvePart1()); //463
        System.out.println("Part 2: " + day2Solver.solvePart2()); //514


    }
}
