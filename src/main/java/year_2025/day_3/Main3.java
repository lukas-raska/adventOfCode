package year_2025.day_3;

import utils.InputLoader;

public class Main3 {

    public static void main(String[] args) {

        String testData = """
                987654321111111
                811111111111119
                234234234234278
                818181911112111""";

        Day3Solver testSolver = new Day3Solver(testData.lines().toList());
        System.out.println("Test 1 = " + testSolver.solvePart1());
        System.out.println("Test 2 = " + testSolver.solvePart2());

        Day3Solver solver = new Day3Solver(InputLoader.load(2025,3));
        System.out.println("Part 1: " + solver.solvePart1());
        System.out.println("Part 2: " + solver.solvePart2());


    }
}
