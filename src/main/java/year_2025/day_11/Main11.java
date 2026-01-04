package year_2025.day_11;

import common.utils.InputLoader;

public class Main11 {

    public static void main(String[] args) {

        String testData = """
                aaa: you hhh
                you: bbb ccc
                bbb: ddd eee
                ccc: ddd eee fff
                ddd: ggg
                eee: out
                fff: out
                ggg: out
                hhh: ccc fff iii
                iii: out""";

        Day11Solver testSolver = new Day11Solver(testData.lines().toList());
        testSolver.printSolution();

        Day11Solver solver = new Day11Solver(InputLoader.load(2025,11));
        solver.printSolution();
    }
}
