package year_2025.day_4;

import java.util.Arrays;
import java.util.List;

public class Day4Solver {
    private final char[][] puzzleInput;
    private final int MAX_ROW;
    private final int MAX_COL;

    enum Direction {
        N(-1, 0),
        NE(-1, 1),
        E(0, 1),
        SE(1, 1),
        S(1, 0),
        SW(1, -1),
        W(0, -1),
        NW(-1, -1);

        private final int dRow;
        private final int dCol;

        Direction(int dRow,
                  int dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
        }
    }

    Day4Solver(List<String> puzzleInput) {
        this.puzzleInput = puzzleInput.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
        MAX_ROW = this.puzzleInput.length;
        MAX_COL = this.puzzleInput[0].length;
    }

    int solvePart1() {
        int count = 0;
        for (int r = 0; r < MAX_ROW; r++) {
            for (int c = 0; c < MAX_COL; c++) {
                if (puzzleInput[r][c] == '@' && isAccessible(r, c)) {
                    count++;
                }
            }
        }
        return count;
    }

    int solvePart2() {
        boolean somethingRemoved;
        int count = 0;
        do {
            somethingRemoved = false;
            for (int r = 0; r < MAX_ROW; r++) {
                for (int c = 0; c < MAX_COL; c++) {
                    if (puzzleInput[r][c] == '@' && isAccessible(r, c)) {
                        count++;
                        puzzleInput[r][c] = 'X';
                        somethingRemoved = true;
                    }
                }
            }

        }
        while (somethingRemoved);
        return count;
    }

    private boolean isAccessible(int row,
                                 int col) {
        int count = 0;
        for (var dir : Direction.values()) {
            int nextRow = row + dir.dRow;
            int nextCol = col + dir.dCol;
            boolean isValidRange = nextRow >= 0 && nextRow < MAX_ROW && nextCol >= 0 && nextCol < MAX_COL;
            if (isValidRange && puzzleInput[nextRow][nextCol] == '@') {
                count++;
            }
            if (count > 3) {
                return false;
            }
        }
        return true;
    }

    private void printDiagram() {
        Arrays.stream(puzzleInput).forEach(System.out::println);
    }

}
