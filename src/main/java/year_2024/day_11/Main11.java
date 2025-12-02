package year_2024.day_11;

public class Main11 {

    public static void main(String[] args) {

        String puzzleInput = "77 515 6779622 6 91370 959685 0 9861";
        String sample = "125 17";

        Day11Solver day11Solver = new Day11Solver(puzzleInput);
        Day11Solver testSolver = new Day11Solver(sample);

      //  testSolver.modify2024();


      // System.out.println("Part 1: " + day11Solver.solvePart1()); //187738
       System.out.println("Test part 1: " + testSolver.solvePart1());

        //System.out.println(testSolver.solvePart1());

    }
}
