package year_2024.day_3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Solver {

    private final static String MUL_REGEX = "mul\\(\\d+,\\d+\\)";
    private final static Pattern MUL_PATTERN = Pattern.compile(MUL_REGEX);
    private final static Pattern DO_DONT_MUL_PATTERN =
            Pattern.compile("(do\\(\\))|(don't\\(\\))|(" + MUL_REGEX + ")");

    private final String input;

    public Day3Solver(String puzzleInput) {
        this.input = puzzleInput;
    }

    public long solvePart1() {
        long result = 0;
        Matcher mulMatcher = MUL_PATTERN.matcher(this.input);
        while (mulMatcher.find()) {
            result += processMulInstruction(mulMatcher.group());
        }
        return result;
    }

    public long solvePart2() {

        long result = 0;
        boolean doFlag = true;
        Matcher matcher = DO_DONT_MUL_PATTERN.matcher(this.input);

        while (matcher.find()) {
            String next = matcher.group();
            if (next.equals("do()")) {
                doFlag = true;
            } else if (next.equals("don't()")) {
                doFlag = false;
            } else {
                result += (doFlag) ? processMulInstruction(next) : 0;
            }
        }
        return result;
    }

    private long processMulInstruction(String mulInstruction) {
        long result = 1;
        Matcher matcher = Pattern
                .compile("\\d+")
                .matcher(mulInstruction);
        while (matcher.find()) {
            result *= Integer.parseInt(matcher.group());
        }
        return result;
    }

}
