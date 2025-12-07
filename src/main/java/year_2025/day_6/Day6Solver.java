package year_2025.day_6;

import common.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Solver implements Solver<Long, Long> {

    private final List<String> puzzleInput;

    Day6Solver(List<String> puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    record Equation(char operator, List<Long> members) {

        Equation(char operator) {
            this(operator, new ArrayList<>());
        }

        private BinaryOperator<Long> getOperation(char operator) {
            return switch (operator) {
                case '+' -> Long::sum;
                case '*' -> (a, b) -> a * b;
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            };
        }

        long solve() {
            return members.stream()
                    .reduce(getOperation(this.operator))
                    .orElseThrow();
        }
    }

    private List<Equation> parseToEquations(List<String> puzzleInput) {

        String[] foundOperators = puzzleInput.getLast().split("[ \\t]+");
        List<Equation> equations = Arrays.stream(foundOperators)
                .map(s -> new Equation(s.charAt(0)))
                .toList();

        Pattern numsPattern = Pattern.compile("(\\d+)");
        for (int i = 0; i < puzzleInput.size() - 1; i++) {
            Matcher matcher = numsPattern.matcher(puzzleInput.get(i));
            int indexOfEquation = 0;
            while (matcher.find()) {
                long foundNumber = Long.parseLong(matcher.group());
                equations.get(indexOfEquation++).members().add(foundNumber);
            }
        }
        return equations;
    }


    public Long part1() {
        return parseToEquations(puzzleInput).stream()
                .mapToLong(Equation::solve)
                .sum();

    }

    public Long part2() {

        long grandTotal = 0;
        char operator = puzzleInput.getLast().charAt(0);
        List<Long> equationMembers = new ArrayList<>(puzzleInput.size() - 1);
        for (int col = 0; col < puzzleInput.getFirst().length(); col++) {

            if (!Character.isWhitespace(puzzleInput.getLast().charAt(col))) {
                operator = puzzleInput.getLast().charAt(col);
            }
            StringBuilder number = new StringBuilder();
            for (int row = 0; row < puzzleInput.size() - 1; row++) {
                char curr = puzzleInput.get(row).charAt(col);
                if (Character.isDigit(curr)) {
                    number.append(curr);
                }
            }
            if (number.isEmpty()) {
                grandTotal += new Equation(operator, equationMembers).solve();
                equationMembers.clear();
            } else {
                equationMembers.add(Long.parseLong(number.toString()));
            }
            if (col == puzzleInput.getFirst().length() - 1) {
                grandTotal += new Equation(operator, equationMembers).solve();
            }
        }
        return grandTotal;
    }

}
