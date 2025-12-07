package year_2025.day_4;

import common.utils.InputLoader;

public class Main4 {

    public static void main(String[] args) {

        String testData = """
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.""";

        Day4Solver testSolver = new Day4Solver(testData.lines().toList());
        System.out.println("Test 1 : " + testSolver.solvePart1());
        System.out.println("Test 2 : " + testSolver.solvePart2());


        Day4Solver solver = new Day4Solver(InputLoader.load(2025,4));
        System.out.println("Part 1: " + solver.solvePart1());
        System.out.println("Part 2: " + solver.solvePart2());
    }
}
