package year_2024.day_9;

import utils.InputLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Main9 {

    public static void main(String[] args) throws IOException {

        String sample = "2333133121414131402";
        String sample2 = "12345";
        String sample3 = "90909";
        String puzzleInput = InputLoader.load(2024, 9).getFirst();

        String jirkaInput = Files.readString(Path.of("src/main/resources/2024/aoc24_09.txt"));

        Day9Solver jirkaSolver = new Day9Solver(jirkaInput);
        System.out.println(jirkaSolver.solvePart1());
        System.out.println(jirkaInput.length());
        System.out.println(jirkaSolver.getDiskMap().length());



//        Day9Solver testSolver = new Day9Solver(sample);
//        System.out.println("Test 1: "+testSolver.solvePart1());
//        System.out.println();
//
//        Day9Solver testSolver2 = new Day9Solver(sample2);
//        System.out.println("Test 2: " + testSolver2.solvePart1());
//        System.out.println();
//
//        Day9Solver testSolver3 = new Day9Solver(sample3);
//        System.out.println("Test 3: " + testSolver3.solvePart1());
//        System.out.println();
//
        Day9Solver day9Solver = new Day9Solver(puzzleInput);
        var startTime = System.currentTimeMillis();
        var part1Result = day9Solver.solvePart1();
        var endTime = System.currentTimeMillis();
        var duration = Duration.ofMillis(endTime - startTime).getSeconds();
        System.out.println("Part 1: " + part1Result + " (time: " + duration + " s)");
        //6260050832569 too high
        //6259530434615 too low

        System.out.println("My input: " + puzzleInput.length());
        System.out.println("My input: " + day9Solver.getDiskMap().length());


        //6346871685398


    }
}
