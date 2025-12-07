package year_2025.day_1;

import common.utils.InputLoader;

import java.util.List;

public class Main1 {

    public static void main(String[] args) {

        List<String> testData = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82""".lines().toList();

        Day1Solver testSolver = new Day1Solver(testData);
        System.out.println("Test part1: " + testSolver.solvePart1());
        System.out.println("Test part2: " + testSolver.solvePart2());


        Day1Solver solver = new Day1Solver(InputLoader.load(2025,1));
        System.out.println("Part 1: " + solver.solvePart1());
        System.out.println("Part 2: " + solver.solvePart2()); //7754 to hhigh, 6885 too low
    }
}
