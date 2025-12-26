package year_2025.day_9;

import common.Solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class Day9Solver implements Solver<Long, Long> {

    private final List<Point> corners;
    private final List<Point> edges;

    public Day9Solver(List<String> puzzleInput) {
        this.corners = puzzleInput.stream()
                .map(Point::parse)
                .toList();
        this.corners.forEach(point -> point.setType(Point.Type.CORNER));
        this.edges = getDefinedEdges();
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
        specifyEdgesType();
        long maxArea = 0;
        for (Point corner : corners) {
            var oppositeCorner = findOppositeCorner(corner);
            long width = 1 + Math.abs(corner.getCol() - oppositeCorner.getCol());
            long height = 1 + Math.abs(corner.getRow() - oppositeCorner.getRow());
            maxArea = Math.max(maxArea, width * height);
        }
        return maxArea;
    }

    private Point findOppositeCorner(Point corner) {
        var dir = getCornerExpansion(corner);
        int startRow = corner.getRow();
        int startCol = corner.getCol();
        long minDistance = Long.MAX_VALUE;
        Point nearest = corner;

        for (var testedCorner : corners) {
            if (testedCorner.equals(corner)) {
                continue;
            }
            int row = testedCorner.getRow();
            int col = testedCorner.getCol();
            Predicate<Point> isOppositePredicate = switch (dir) {
                case UP_LEFT -> point -> point.getRow() < corner.getRow() && point.getCol() < corner.getCol();
                case UP_RIGHT -> point -> point.getRow() < corner.getRow() && point.getCol() > corner.getCol();
                case DOWN_LEFT -> point -> point.getRow() > corner.getRow() && point.getCol() < corner.getCol();
                case DOWN_RIGHT -> point -> point.getRow() > corner.getRow() && point.getCol() > corner.getCol();
                default -> throw new IllegalArgumentException("Invalid corner direction: " + dir);
            };
            if (isOppositePredicate.test(testedCorner)) {
                long distance = (long) (startCol - col) * (startCol - col) + (long) (startRow - row) * (startRow - row);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = testedCorner;
                }
            }
        }
        return nearest;

    }


    private Direction getCornerExpansion(Point corner) {

        StringBuilder adjacent = new StringBuilder();
        for (Direction direction : EnumSet.of(
                Direction.DOWN,
                Direction.UP,
                Direction.LEFT,
                Direction.RIGHT)) {
            int neighborRow = corner.getRow() + direction.dRow;
            int neighborCol = corner.getCol() + direction.dCol;

            var optLocation = edges.stream()
                    .filter(p -> p.getRow() == neighborRow && p.getCol() == neighborCol)
                    .map(Point::getLocation)
                    .findFirst();

            optLocation.ifPresent(adjacent::append);
        }

        return switch (adjacent.toString().toUpperCase()) {
            case "UPLEFT", "LEFTUP" -> Direction.DOWN_RIGHT;
            case "UPRIGHT", "RIGHTUP" -> Direction.DOWN_LEFT;
            case "DOWNLEFT", "LEFTDOWN" -> Direction.UP_RIGHT;
            case "DOWNRIGHT", "RIGHTDOWN" -> Direction.UP_LEFT;
            default -> throw new IllegalArgumentException("Unknown adjacent edges: " + adjacent);
        };

    }

    private void specifyEdgesType() {
        int minCol = corners.stream().mapToInt(Point::getCol).min().orElseThrow();
        int maxCol = corners.stream().mapToInt(Point::getCol).max().orElseThrow();
        int minRow = corners.stream().mapToInt(Point::getRow).min().orElseThrow();
        int maxRow = corners.stream().mapToInt(Point::getRow).max().orElseThrow();
        //specify vertical edge location (LEFT,RIGHT)
        for (int row = minRow; row <= maxRow; row++) {
            List<Point> pointsInCurrentRow = getCornersAndEdgesInRow(row);
            pointsInCurrentRow.sort(Comparator.comparing(Point::getCol));
            boolean inside = false;
            boolean touchHorizontalEdge = false;
            for (Point point : pointsInCurrentRow) {
                if (point.isEdge()) {
                    point.setLocation(inside ? Point.Location.RIGHT : Point.Location.LEFT);
                    inside = !inside;
                }
                if (point.isCorner()) {
                    if (!touchHorizontalEdge) {
                        touchHorizontalEdge = true;
                    } else {
                        inside = true;
                        touchHorizontalEdge = false;
                    }
                }
            }
        }
        //specify horizontal edge location (UP,DOWN)
        for (int col = minCol; col <= maxCol; col++) {
            List<Point> pointsInCurrentCol = getCornersAndEdgesInCol(col);
            pointsInCurrentCol.sort(Comparator.comparing(Point::getRow));
            boolean inside = false;
            boolean touchVerticalEdge = false;
            for (Point point : pointsInCurrentCol) {
                if (point.isEdge()) {
                    point.setLocation(inside ? Point.Location.DOWN : Point.Location.UP);
                    inside = !inside;
                }
                if (point.isCorner()) {
                    if (!touchVerticalEdge) {
                        touchVerticalEdge = true;
                    } else {
                        inside = true;
                        touchVerticalEdge = false;
                    }
                }
            }

        }
    }

    private List<Point> getCornersAndEdgesInRow(int row) {

        var filteredCorners = corners.stream()
                .filter(point -> point.getRow() == row)
                .toList();
        var filteredEdges = edges.stream()
                .filter(point -> point.getRow() == row)
                .filter(point -> point.getOrientation().equals(Point.Orientation.VERTICAL))
                .toList();

        List<Point> points = new ArrayList<>(filteredCorners);
        points.addAll(filteredEdges);

        return points;
    }

    private List<Point> getCornersAndEdgesInCol(int col) {

        var filteredCorners = corners.stream()
                .filter(point -> point.getCol() == col)
                .toList();
        var filteredEdges = edges.stream()
                .filter(point -> point.getCol() == col)
                .filter(point -> point.getOrientation().equals(Point.Orientation.HORIZONTAL))
                .toList();

        List<Point> points = new ArrayList<>(filteredCorners);
        points.addAll(filteredEdges);

        return points;
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
            if (hasSameColumn(current, next)) {
                int startAt = Math.min(current.getRow(), next.getRow()) + 1;
                int endAt = Math.max(current.getRow(), next.getRow()) - 1;
                for (int row = startAt; row <= endAt; row++) {
                    edges.add(
                            new Point(
                                    row,
                                    current.getCol(),
                                    Point.Type.EDGE,
                                    Point.Orientation.VERTICAL)
                    );
                }
            }
            if (hasSameRow(current, next)) {
                int startAt = Math.min(current.getCol(), next.getCol()) + 1;
                int endAt = Math.max(current.getCol(), next.getCol()) - 1;
                for (int col = startAt; col <= endAt; col++) {
                    edges.add(
                            new Point(
                                    current.getRow(),
                                    col,
                                    Point.Type.EDGE,
                                    Point.Orientation.HORIZONTAL)
                    );
                }
            }
        }
        while (!next.equals(start));

        return edges;
    }


    private boolean hasSameRow(Point a,
                               Point b) {
        return a.getRow() == b.getRow();
    }

    private boolean hasSameColumn(Point a,
                                  Point b) {
        return a.getCol() == b.getCol();
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
