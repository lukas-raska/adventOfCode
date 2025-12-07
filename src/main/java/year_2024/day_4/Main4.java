package year_2024.day_4;

import common.utils.InputLoader;

public class Main4 {

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2024, 4);

        Day4Solver day4Solver = new Day4Solver(puzzleInput);

        System.out.println("Part 1: " + day4Solver.solvePart1()); //2297
        System.out.println("Part 2: " + day4Solver.solvePart2()); //1745

    }
}
