package year_2025.day_9;

import common.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Day9Solver implements Solver<Long, Long> {

    private final List<Point> corners;
    private List<Point> edges;

    public Day9Solver(List<String> puzzleInput) {
        this.corners = puzzleInput.stream()
                .map(Point::parse)
                .map(point -> {
                    point.setType(Point.Type.CORNER);
                    return point;
                })
                .toList();
    }

    @Override
    public Long part1() {
        long maxArea = 0;
        for (int i = 0; i < corners.size() - 1; i++) {
            for (int j = i + 1; j < corners.size(); j++) {
                long area = getArea(corners.get(i), corners.get(j));
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    private long getArea(Point a,
                         Point b) {
        long width = 1 + Math.abs(a.getCol() - b.getCol());
        long height = 1 + Math.abs(a.getRow() - b.getRow());
        return width * height;
    }

    @Override
    public Long part2() {
        this.edges = getDefinedEdges();
        long maxArea = 0;
        for (Point first : corners) {
            for (Point second : corners) {
                long width = 1 + Math.abs(second.getCol() - first.getCol());
                long height = 1 + Math.abs(second.getRow()) - first.getRow();
                long currentArea = width * height;
                if (currentArea > maxArea) {
                    int minRow = Math.min(first.getRow(), second.getRow());
                    int maxRow = Math.max(first.getRow(), second.getRow());
                    int minCol = Math.min(first.getCol(), second.getCol());
                    int maxCol = Math.max(first.getCol(), second.getCol());

                    if (
                            noCornersInArea(minRow, maxRow, minCol, maxCol) &&
                                    noEdgesInArea(minRow, maxRow, minCol, maxCol) &&
                                    expandInRightDirection(first, second)
                    ) {
                        maxArea = currentArea;
                    }
                }
            }
        }

        return maxArea;
    }

    private boolean noCornersInArea(int minRow,
                                    int maxRow,
                                    int minCol,
                                    int maxCol) {

        for (Point tested : corners) {
            if (tested.getRow() > minRow && tested.getRow() < maxRow && tested.getCol() > minCol && tested.getCol() < maxCol) {
                return false;
            }
        }
        return true;
    }

    private boolean expandInRightDirection(Point first,
                                           Point second) {

        int dRow = (int) Math.signum(second.getRow() - first.getRow());
        int dCol = (int) Math.signum(second.getCol() - first.getCol());
        Direction firstToSecond = Direction.of(dRow, dCol);

        return possibleCornerExpansions(first).contains(firstToSecond);
    }

    private boolean noEdgesInArea(int minRow,
                                  int maxRow,
                                  int minCol,
                                  int maxCol) {
        if (edges == null) {
            this.edges = getDefinedEdges();
        }
        for (Point testedEdge : edges) {
            if (testedEdge.getRow() > minRow && testedEdge.getRow() < maxRow && testedEdge.getCol() > minCol && testedEdge.getCol() < maxCol) {
                return false;
            }
        }
        return true;
    }

    private Set<Direction> possibleCornerExpansions(Point corner) {

        Map<Direction, Point> adjacentEdges = new HashMap<>();
        Set<Direction> possibleDirections = new HashSet<>();

        for (Direction direction : EnumSet.of(
                Direction.UP,
                Direction.RIGHT,
                Direction.DOWN,
                Direction.LEFT
        )) {
            int nextRow = corner.getRow() + direction.dRow;
            int nextCol = corner.getCol() + direction.dCol;

            for (Point edge : edges) {
                if (edge.getRow() == nextRow && edge.getCol() == nextCol) {
                    adjacentEdges.put(direction, edge);
                }
                if (adjacentEdges.size() == 2) {
                    break;
                }
            }

            Set<Point.Location> adjacentEdgeLocations = adjacentEdges.values().stream()
                    .map(Point::getLocation)
                    .collect(Collectors.toSet());


            //TOP,LEFT
            if (adjacentEdgeLocations.contains(Point.Location.UP) && adjacentEdgeLocations.contains(Point.Location.LEFT)) {
                possibleDirections.add(Direction.DOWN_RIGHT);
                if (adjacentEdges.containsKey(Direction.UP) && adjacentEdges.containsKey(Direction.LEFT)) {
                    possibleDirections.add(Direction.DOWN_LEFT);
                    possibleDirections.add(Direction.UP_RIGHT);
                }
            }
            //TOP,RIGHT
            if (adjacentEdgeLocations.contains(Point.Location.UP) && adjacentEdgeLocations.contains(Point.Location.RIGHT)) {
                possibleDirections.add(Direction.DOWN_LEFT);
                if (adjacentEdges.containsKey(Direction.UP) && adjacentEdges.containsKey(Direction.RIGHT)) {
                    possibleDirections.add(Direction.DOWN_RIGHT);
                    possibleDirections.add(Direction.UP_LEFT);
                }
            }
            //DOWN, LEFT
            if (adjacentEdgeLocations.contains(Point.Location.DOWN) && adjacentEdgeLocations.contains(Point.Location.LEFT)) {
                possibleDirections.add(Direction.UP_RIGHT);
                if (adjacentEdges.containsKey(Direction.DOWN) && adjacentEdges.containsKey(Direction.LEFT)) {
                    possibleDirections.add(Direction.UP_LEFT);
                    possibleDirections.add(Direction.DOWN_RIGHT);
                }
            }
            //DOWN, RIGHT
            if (adjacentEdgeLocations.contains(Point.Location.DOWN) && adjacentEdgeLocations.contains(Point.Location.RIGHT)) {
                possibleDirections.add(Direction.UP_LEFT);
                if (adjacentEdges.containsKey(Direction.DOWN) && adjacentEdges.containsKey(Direction.RIGHT)) {
                    possibleDirections.add(Direction.UP_RIGHT);
                    possibleDirections.add(Direction.DOWN_LEFT);
                }
            }

        }
        return possibleDirections;
    }


    private List<Point> getDefinedEdges() {
        Point start = corners.getFirst();
        int size = corners.size();
        int index = 0;
        Point current, next;
        List<Point> edges = new ArrayList<>();
        do {
            current = corners.get(index % size);
            next = corners.get((index + 1) % size);
            index++;
            if (current.getCol() == next.getCol()) {
                int startAt = Math.min(current.getRow(), next.getRow()) + 1;
                int endAt = Math.max(current.getRow(), next.getRow()) - 1;
                for (int row = startAt; row <= endAt; row++) {
                    edges.add(
                            new Point(
                                    row,
                                    current.getCol(),
                                    Point.Type.VERTICAL_EDGE,
                                    (next.getRow() - current.getRow()) > 0 ? Point.Location.RIGHT : Point.Location.LEFT)
                    );
                }
            }
            if (current.getRow() == next.getRow()) {
                int startAt = Math.min(current.getCol(), next.getCol()) + 1;
                int endAt = Math.max(current.getCol(), next.getCol()) - 1;
                for (int col = startAt; col <= endAt; col++) {
                    edges.add(
                            new Point(
                                    current.getRow(),
                                    col,
                                    Point.Type.HORIZONTAL_EDGE,
                                    (next.getCol() - current.getCol()) > 0 ? Point.Location.UP : Point.Location.DOWN)
                    );
                }
            }
        }
        while (!next.equals(start));

        return edges;
    }

    enum Direction {
        UP(-1, 0),
        UP_RIGHT(-1, 1),
        RIGHT(0, 1),
        DOWN_RIGHT(1, 1),
        DOWN(1, 0),
        DOWN_LEFT(1, -1),
        LEFT(0, -1),
        UP_LEFT(-1, -1),
        NOWHERE(0, 0);

        private final int dRow;
        private final int dCol;

        Direction(int dRow,
                  int dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
        }

        public static Direction of(int dRow,
                                   int dCol) {
            for (Direction direction : Direction.values()) {
                if (direction.dRow == dRow && direction.dCol == dCol) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Unknown direction for dRow=%d, dCol=%d".formatted(dRow, dCol));
        }
    }
}
