package year_2025.day_8;

import common.Solver;
import common.utils.InputLoader;

public class Main8 {

    public static void main(String[] args) {

        Solver<Long, Long> solver = new Day8Solver(InputLoader.load(2025, 8));
        solver.printSolution();
    }
}
