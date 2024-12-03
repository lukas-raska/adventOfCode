package year_2024.day_1;

import utils.InputLoader;

import java.util.List;

public class Main1 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2024, 1);

        Day1Solver day1Solver = new Day1Solver(puzzleInput);
        System.out.println("Part 1: " + day1Solver.part1Solution()); //765748
        System.out.println("Part 2: " + day1Solver.part2Solution()); //27732508
    }
}
