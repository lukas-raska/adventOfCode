package year_2024.day_4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4Solver {

    private final static String SEARCH_WORD_PART_1 = "XMAS";
    private final List<String> input;

    public Day4Solver(List<String> puzzleInput) {
        this.input = puzzleInput;
    }


    public int solvePart1() {
        int count = 0;
        for (int row = 0; row < this.input.size(); row++) {
            for (int col = 0; col < this.input.getFirst().length(); col++) {
                char current = this.input.get(row).charAt(col);
                if (current == SEARCH_WORD_PART_1.charAt(0)) {
                    for (Direction direction : Direction.values()) {
                        count += countOccurrencesInDirection(direction, row, col);
                    }
                }
            }
        }
        return count;
    }

    public int solvePart2() {
        int count = 0;
        for (int row = 1; row < this.input.size() - 1; row++) {
            for (int col = 1; col < this.input.getFirst().length() - 1; col++) {
                char current = this.input.get(row).charAt(col);
                if (current == 'A') {
                    char upLeft = this.input.get(row - 1).charAt(col - 1);
                    char upRight = this.input.get(row - 1).charAt(col + 1);
                    char downLeft = this.input.get(row + 1).charAt(col - 1);
                    char downRight = this.input.get(row + 1).charAt(col + 1);
                    String firstDiagonal = "" + upLeft + 'A' + downRight;
                    String secondDiagonal = "" + upRight + 'A' + downLeft;
                    Set<String> possibleShapes = new HashSet<>(Set.of("MAS", "SAM"));
                    if (possibleShapes.contains(firstDiagonal) && possibleShapes.contains(secondDiagonal)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private int countOccurrencesInDirection(Direction direction,
                                            int fromRow,
                                            int fromCol) {
        int count = 0;
        int dx = direction.getDx();
        int dy = direction.getDy();
        char next;

        StringBuilder found = new StringBuilder();
        found.append(this.input.get(fromRow).charAt(fromCol));

        while (found.length() < SEARCH_WORD_PART_1.length() && !isHittingBorder(direction, fromRow, fromCol)) {
            fromRow += dy;
            fromCol += dx;
            next = this.input.get(fromRow).charAt(fromCol);
            found.append(next);
        }
        if (found.toString().equals(SEARCH_WORD_PART_1)) {
            count++;
        }

        return count;
    }

    private boolean isHittingBorder(Direction direction,
                                    int row,
                                    int col) {
        int dx = direction.getDx();
        int dy = direction.getDy();
        return (col + dx) < 0 || (col + dx) >= this.input.getFirst().length() ||
                (row + dy) < 0 || (row + dy) >= this.input.size();
    }


}
