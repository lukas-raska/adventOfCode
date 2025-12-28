package year_2025.day_9;

import common.Solver;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day9Solver implements Solver<Long, Long> {

    private final List<Point> corners;
    private List<Point> edges;

    private Set<Point> edgesSet;

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
        long width = a.getCol() - b.getCol() + 1;
        long height = a.getRow() - b.getRow() + 1;
        return Math.abs(width * height);
    }

    @Override
    public Long part2() {
        this.edges = getDefinedEdges();
        long maxArea = 0;
        for (Point corner : corners) {
            maxArea = Math.max(maxArea,getLocalMaxArea(corner));
        }
        return maxArea;
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

    private long getLocalMaxArea(Point corner) {

        long maxArea = 0L;

        for (var direction : possibleCornerExpansions(corner)) {

            Predicate<Point> isOppositePredicate = switch (direction) {
                case UP_LEFT -> point -> point.getRow() <= corner.getRow() && point.getCol() <= corner.getCol();
                case UP_RIGHT -> point -> point.getRow() <= corner.getRow() && point.getCol() >= corner.getCol();
                case DOWN_LEFT -> point -> point.getRow() >= corner.getRow() && point.getCol() <= corner.getCol();
                case DOWN_RIGHT -> point -> point.getRow() >= corner.getRow() && point.getCol() >= corner.getCol();
                default -> throw new IllegalArgumentException("Invalid corner direction: " + direction);
            };

            for (var testedCorner : corners) {
                if (testedCorner.equals(corner)) {
                    continue;
                }

                if (isOppositePredicate.test(testedCorner) && isValidArea(corner, testedCorner)) {
                    long width = 1 + Math.abs(testedCorner.getCol() - corner.getCol());
                    long height = 1 + Math.abs(testedCorner.getRow() - corner.getRow());
                    maxArea = Math.max(maxArea, width * height);
                }
            }
        }

        return maxArea;
    }

    private boolean isValidArea(Point start,
                                Point opposite) {

        if (start.getRow() == opposite.getRow()) {
            return start.getCol() != opposite.getCol();
        }
        if (start.getCol() == opposite.getCol()) {
            return start.getRow() != opposite.getRow();
        }
        int leftBottomCol = Math.min(start.getCol(), opposite.getCol());
        int leftBottomRow = Math.max(start.getRow(), opposite.getRow());
        int rightTopCol = Math.max(start.getCol(), opposite.getCol());
        int rightTopRow = Math.min(start.getRow(), opposite.getRow());
        Predicate<Point> invalidAreaPredicate = p -> {
            boolean isCrossedHorizontally =
                    (p.getCol() == leftBottomCol || p.getCol() == rightTopCol) && p.getRow() < leftBottomRow && p.getRow() >rightTopRow;
            boolean isCrossedVertically =
                    (p.getRow() == leftBottomRow || p.getRow() == rightTopRow) && p.getCol() > leftBottomCol && p.getCol() < rightTopCol;
            boolean anyEdgeInside =
                    p.getCol() > leftBottomCol && p.getCol() < rightTopCol && p.getRow() < leftBottomRow && p.getRow() > rightTopRow;
            return (p.isVerticalEdge() && isCrossedVertically) || (p.isHorizontalEdge() && isCrossedHorizontally) || anyEdgeInside;
        };


        List<Point> crossingEdges = edges.stream().filter(invalidAreaPredicate).toList();
        return crossingEdges.isEmpty();
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
        UP_LEFT(-1, -1);

        private final int dRow;
        private final int dCol;

        Direction(int dRow,
                  int dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
        }

    }


}
