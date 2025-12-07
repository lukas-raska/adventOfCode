package year_2024.day_10;

import common.utils.InputLoader;

import java.util.List;

public class Main10 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2024, 10);
        Day10Solver day10Solver = new Day10Solver(puzzleInput);

        System.out.println("Part 1: " + day10Solver.solvePart1()); //566
        System.out.println("Part 2: " + day10Solver.solvePart2()); //1324

    }
}
