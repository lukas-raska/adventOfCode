package year_2024.day_6;

import utils.InputLoader;

public class Main6 {

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2024, 6);

        Day6Solver day6Solver = new Day6Solver(puzzleInput);
        long part1startTime = System.currentTimeMillis();
        int part1result = day6Solver.solvePart1();
        long part1endTime = System.currentTimeMillis();
        long part1Duration = part1endTime - part1startTime;

        long part2startTime = System.currentTimeMillis();
        int part2Result = day6Solver.solvePart2();
        long part2endTime = System.currentTimeMillis();
        long part2Duration = part2endTime - part2startTime;


        System.out.println("Part 1: " + part1result + " (runtime: " + part1Duration + " ms)"); //5131
        System.out.println("Part 2: " + part2Result + " (runtime: " + part2Duration + " ms)"); //1784
    }
}
