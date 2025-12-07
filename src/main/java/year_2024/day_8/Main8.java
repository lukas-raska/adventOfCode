package year_2024.day_8;

import common.utils.InputLoader;

public class Main8 {

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2024,8);

        Day8Solver day8Solver = new Day8Solver(puzzleInput);

        System.out.println("Part 1: " + day8Solver.solvePart1());//214
        System.out.println("Part 2: " + day8Solver.solvePart2());//809
    }
}
