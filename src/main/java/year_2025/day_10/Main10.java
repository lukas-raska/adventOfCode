package year_2025.day_10;

import common.utils.InputLoader;

import java.util.Scanner;

public class Main10 {

    public static void main(String[] args) throws InterruptedException {


        Scanner scanner = new Scanner(System.in);
        String testData = """
                [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
                [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
                [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}""";


//                Day10Solver testSolver = new Day10Solver(testData.lines().toList());
//                testSolver.printSolution();

                Day10Solver solver = new Day10Solver(InputLoader.load(2025,10));
                solver.printSolution();


    }
}
