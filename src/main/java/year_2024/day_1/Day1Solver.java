package year_2024.day_1;

import java.util.Arrays;
import java.util.List;

public class Day1Solver {

    private final int[] leftColumn;
    private final int[] rightColumn;


    public Day1Solver(List<String> puzzleInput) {
        this.leftColumn = new int[puzzleInput.size()];
        this.rightColumn = new int[puzzleInput.size()];
        for (int i = 0; i < puzzleInput.size(); i++) {
            var parsedLine = Arrays
                    .stream(puzzleInput.get(i).split("\\p{javaWhitespace}+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            this.leftColumn[i] = parsedLine[0];
            this.rightColumn[i] = parsedLine[1];
        }
    }


    public int part1Solution() {
        int[] sortedLeftColumn = Arrays.stream(this.leftColumn).sorted().toArray();
        int[] sortedRightColumn = Arrays.stream(this.rightColumn).sorted().toArray();
        int result = 0;
        for (int i = 0; i < sortedLeftColumn.length; i++) {
            result += Math.abs(sortedLeftColumn[i] - sortedRightColumn[i]);
        }
        return result;
    }

    public int part2Solution() {
        int result = 0;
        for (int i = 0; i < this.leftColumn.length; i++) {
            int current = this.leftColumn[i];
            long frequencyInRightColumn = Arrays.stream(this.rightColumn)
                    .filter(n -> n == current)
                    .count();
            result += (int) (current * frequencyInRightColumn);
        }
        return result;
    }


}
