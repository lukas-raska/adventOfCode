package year_2025.day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main2 {

    public static void main(String[] args) throws IOException {

        String testInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";




        Day2Solver testSolver = new Day2Solver(testInput);
        long res1 = testSolver.solvePart1();
        long res2 = testSolver.solvePart2();
//
//        testSolver.getInvalidIdsInRange(new long[]{1188511880,1188511890}, testSolver::isStillInvalid).forEach(System.out::println);
//        testSolver.isStillInvalid(1010);

        System.out.println("Part 1 (test): " + res1);
        System.out.println("Part 2 (test): " + res2);

        Day2Solver solver = new Day2Solver(Files.readString(Path.of("src/main/resources/2025/input_2025_2.txt")));
        long startTime1 = System.nanoTime();
        long result1 = solver.solvePart1();
        long elapsed1 = Math.round((System.nanoTime() - startTime1)/1_000_000.0);

        long startTime2 = System.nanoTime();
        long result2 = solver.solvePart2();
        long elapsed2 = Math.round((System.nanoTime() - startTime2)/1_000_000.0);





        System.out.println("Part 1: " + result1 + " (elapsed time: "  + elapsed1 + ")");
        System.out.println("Part 2: " + result2 + " (elapsed time: "  + elapsed2 + ")");



    }
}
