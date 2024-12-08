package year_2024.day_8;

import utils.InputLoader;

import java.util.Arrays;

public class Main8 {

    public static void main(String[] args) {

        String sample = """
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............""";

        Day8Solver testSolver = new Day8Solver(Arrays.stream(sample.split("\n")).toList());

        var puzzleInput = InputLoader.load(2024,8);

        System.out.println( testSolver.solvePart1());
       // testSolver.visualize();

        Day8Solver day8Solver = new Day8Solver(puzzleInput);

        System.out.println(day8Solver.solvePart1());//214
        System.out.println(day8Solver.solvePart2());//214

    }
}
