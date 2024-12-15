package year_2024.day_10;

import java.util.*;
import java.util.stream.Collectors;

public class Day10Solver {

    private final Point[][] map;

    public Day10Solver(List<String> puzzleInput) {
        this.map = parse(puzzleInput);
    }

    public int solvePart1() {
        return findPathStarts().stream()
                .mapToInt(this::score)
                .sum();
    }

    public int solvePart2() {
        return findPathStarts().stream()
                .mapToInt(this::rating)
                .sum();
    }

    public Set<Point> findPathStarts() {
        return Arrays.stream(this.map)
                .flatMap(Arrays::stream)
                .filter(point -> point.getHeight() == 0)
                .collect(Collectors.toSet());
    }

    public int score(Point start) {
        Deque<Point> traversed = new LinkedList<>();
        traversed.add(start);
        while (!traversed.isEmpty()) {
            Point current = traversed.pop();
            current.setReached(true);
            for (var dir : Compass.values()) {
                int nextRow = current.getRow() + dir.getdRow();
                int nextCol = current.getCol() + dir.getdCol();
                if (nextRow < 0 || nextRow >= this.map.length || nextCol < 0 || nextCol >= this.map[0].length) {
                    continue;
                }
                Point next = this.map[nextRow][nextCol];
                if (next.getHeight() == current.getHeight() + 1) {
                    traversed.push(next);
                }
            }
        }
        int score = (int) Arrays.stream(this.map)
                .flatMap(Arrays::stream)
                .filter(point -> point.isReached() && point.getHeight() == 9)
                .count();

        resetMap();

        return score;
    }

    public int rating(Point start) {
        int rating = 0;
        Deque<Point> traversed = new LinkedList<>();
        traversed.add(start);
        while (!traversed.isEmpty()) {
            Point current = traversed.poll();
            for (var dir : Compass.values()) {
                int nextRow = current.getRow() + dir.getdRow();
                int nextCol = current.getCol() + dir.getdCol();
                if (nextRow < 0 || nextRow >= this.map.length || nextCol < 0 || nextCol >= this.map[0].length) {
                    continue;
                }
                Point next = this.map[nextRow][nextCol];
                if (next.getHeight() == current.getHeight() + 1) {
                    traversed.push(next);
                }
                if (current.getHeight() == 8 && next.getHeight() == 9) {
                    rating++;
                }
            }
        }
        return rating;
    }


    private void resetMap() {
        Arrays.stream(this.map)
                .flatMap(Arrays::stream)
                .forEach(point -> point.setReached(false));
    }


    private Point[][] parse(List<String> puzzleInput) {
        int colMax = puzzleInput.getFirst().length();
        int rowMax = puzzleInput.size();
        Point[][] map = new Point[rowMax][colMax];
        for (int r = 0; r < rowMax; r++) {
            for (int c = 0; c < colMax; c++) {
                int h = Character.getNumericValue(puzzleInput.get(r).charAt(c));
                map[r][c] = new Point(r, c, h);
            }
        }
        return map;
    }
}
