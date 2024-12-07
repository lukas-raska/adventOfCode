package year_2024.day_6;

import java.util.Arrays;
import java.util.List;

public class Day6Solver {

    private char[][] board;

    private Guard guard;

    public Day6Solver(List<String> puzzleInput) {
        initializeData(puzzleInput);
    }

    public int solvePart1() {
        var visited = markVisited();
        int result = 0;
        for (var booleans : visited) {
            for (var wasVisited : booleans) {
                result += wasVisited ? 1 : 0;
            }
        }
        return result;
    }

    public int solvePart2() {
        int rows = this.board.length;
        int cols = this.board[0].length;
        int options = 0;

        int[] startingPosition = new int[]{this.guard.getRow(), this.guard.getCol()};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!(r == startingPosition[0] && c == startingPosition[1])) {
                    char[][] modifiedBoard = copy2DArray(this.board);
                    modifiedBoard[r][c] = '#';
                    if (isInfinityCircuit(modifiedBoard)) {
                        options++;
                    }
                }
            }
        }
        return options;
    }

    private boolean[][] markVisited() {
        //board data
        int rows = this.board.length;
        int cols = this.board[0].length;
        //starting guard data
        int startingRow = this.guard.getRow();
        int startingCol = this.guard.getCol();
        Direction startingDirection = this.guard.getDirection();

        boolean[][] visited = new boolean[rows][cols];
        visited[startingRow][startingCol] = true;

        int currentRow = startingRow;
        int currentCol = startingCol;
        boolean isOnTheBoard = true;

        while (isOnTheBoard) {
            try {
                int nextRow = currentRow + this.guard.getDirection().getDy();
                int nextCol = currentCol + this.guard.getDirection().getDx();
                if (this.board[nextRow][nextCol] == '#') {
                    this.guard.changeDirection();
                } else {
                    this.guard.move();
                    currentRow = nextRow;
                    currentCol = nextCol;
                }
                visited[currentRow][currentCol] = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                isOnTheBoard = false;
            }
        }

        this.guard.reset(startingRow, startingCol, startingDirection);

        return visited;
    }

    private boolean isInfinityCircuit(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        int startingRow = this.guard.getRow();
        int startingCol = this.guard.getCol();
        Direction startingDirection = this.guard.getDirection();

        boolean[][] visited = new boolean[rows][cols];

        int currentRow = startingRow;
        int currentCol = startingCol;
        visited[currentRow][currentCol] = true;
        int maxPossibleSteps = rows * cols;
        int step = 0;
        int nextCol, nextRow;

        while (true) {
            try {
                nextRow = currentRow + this.guard.getDirection().getDy();
                nextCol = currentCol + this.guard.getDirection().getDx();
                if (board[nextRow][nextCol] == '#') {
                    this.guard.changeDirection();
                } else {
                    this.guard.move();
                    currentRow = nextRow;
                    currentCol = nextCol;
                    visited[currentRow][currentCol] = true;
                    step++;
                }
                if (step >= maxPossibleSteps) {
                    this.guard.reset(startingRow, startingCol, startingDirection);
                    return true;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                this.guard.reset(startingRow, startingCol, startingDirection);
                return false;
            }
        }
    }


    private char[][] copy2DArray(char[][] sourceArray) {
        int rows = sourceArray.length;
        int cols = sourceArray[0].length;
        char[][] resultArray = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            resultArray[r] = Arrays.copyOf(sourceArray[r], cols);
        }
        return resultArray;
    }


    public void visualize(char[][] board,
                          boolean[][] visited) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                char printed = visited[r][c] ? 'X' : board[r][c];
                System.out.print(printed);
            }
            System.out.println();
        }

    }

    public void initializeData(List<String> puzzleInput) {
        int rows = puzzleInput.size();
        int cols = puzzleInput.getFirst().length();
        this.board = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char current = puzzleInput.get(r).charAt(c);
                boolean guardFound = Arrays
                        .stream(Direction.getPossibleIcons())
                        .anyMatch(icon -> icon == current);
                this.board[r][c] = current;
                if (guardFound) {
                    this.guard = new Guard(c, r, current);
                }
            }
        }
    }
}
