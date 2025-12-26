package year_2025.day_9;

import common.Solver;

public class Main9 {

    public static void main(String[] args) {

String testData = """
        7,1
        11,1
        11,7
        9,7
        9,5
        2,5
        2,3
        7,3""";

        Solver<Long,Long> testSolver = new Day9Solver(testData.lines().toList());
        testSolver.printSolution();

//        Solver<Long,Long> solver = new AlternativeSolverForPart2(InputLoader.load(2025,9));
//        solver.printSolution();

//        Solver<Long,Long> solver = new Day9Solver(InputLoader.load(2025,9));
//        solver.printSolution();




    }
}
