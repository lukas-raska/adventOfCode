package year_2024.day_7;

import utils.InputLoader;

public class Main7 {

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2024, 7);

        Day7Solver day7Solver = new Day7Solver(puzzleInput);

        long startTime = System.currentTimeMillis();
        long part1Result = day7Solver.solvePart1();
        long endTime = System.currentTimeMillis();
        long part1Runtime = endTime - startTime;

        System.out.println("Part 1: " + part1Result + " (runtime: " + part1Runtime + " ms)"); //1260333054159 min 36 ms


        startTime = System.currentTimeMillis();
        long part2Result = day7Solver.solvePart2();
        endTime = System.currentTimeMillis();
        long part2Runtime = endTime - startTime;

        System.out.println("Part 1: " + part2Result + " (runtime: " + part2Runtime + " ms)"); //162042343638683 min
        // 1213 ms
    }


}
