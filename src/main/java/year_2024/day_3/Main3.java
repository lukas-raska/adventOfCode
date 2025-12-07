package year_2024.day_3;

import common.utils.InputLoader;

import java.util.stream.Collectors;

public class Main3 {

    public static void main(String[] args) {

        String puzzleInput = InputLoader
                .load(2024, 3)
                .stream()
                .collect(Collectors.joining("\n"));


        Day3Solver day3Solver = new Day3Solver(puzzleInput);

        System.out.println("Part 1: " + day3Solver.solvePart1()); //185797128
        System.out.println("Part 2: " + day3Solver.solvePart2()); //89798695
    }
}
