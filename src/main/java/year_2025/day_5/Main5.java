package year_2025.day_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main5 {

    public static void main(String[] args) {


        String testInput = """
                3-5
                10-14
                16-20
                12-18
                
                1
                5
                8
                11
                17
                32""";

        Day5Solver testDay5Solver = new Day5Solver(testInput);
        System.out.println("Test 1 : " + testDay5Solver.part1());
        System.out.println("Test 2 : " + testDay5Solver.part2());


        String input = "";
        try {
            input = String.join("\n", Files.readAllLines(Path.of("src/main/resources/2025/input_2025_5.txt")));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        Day5Solver solver = new Day5Solver(input);
        System.out.println("Part 1: " + solver.part1());
        System.out.println("Part 2: " + solver.part2());
    }
}
