package year_2024.day_7;

import java.util.Arrays;
import java.util.List;

public class Day7Solver {

    private final List<Equation> equations;


    public Day7Solver(List<String> puzzleInput) {
        this.equations = puzzleInput.stream()
                .map(this::parseInput)
                .toList();

    }

    public long solvePart1() {
        return this.equations.stream()
                .filter(equation -> equation.isValid(false))
                .mapToLong(Equation::result)
                .sum();
    }

    public long solvePart2() {
        return this.equations.stream()
                .filter(equation -> equation.isValid(true))
                .mapToLong(Equation::result)
                .sum();
    }


    public Equation parseInput(String inputData) {
        String[] split = inputData.split(":");
        String[] members = split[1].trim().split(" ");
        long eqResult = Long.parseLong(split[0]);
        long[] eqMembers = Arrays.stream(members)
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .toArray();
        return new Equation(eqResult, eqMembers);
    }


}
