package year_2025.day_12;

import common.utils.InputLoader;

public class Main12 {

    public static void main(String[] args) {

        String testData = """
                0:
                ###
                ##.
                ##.
                
                1:
                ###
                ##.
                .##
                
                2:
                .##
                ###
                ##.
                
                3:
                ##.
                ###
                ##.
                
                4:
                ###
                #..
                ###
                
                5:
                ###
                .#.
                ###
                
                4x4: 0 0 0 0 2 0
                12x5: 1 0 1 0 2 2
                12x5: 1 0 1 0 3 2""";

        Day12Solver testSolver = new Day12Solver(testData.lines().toList());
        testSolver.printSolution();

        Day12Solver solver = new Day12Solver(InputLoader.load(2025,12));
        solver.printSolution();
    }
}
