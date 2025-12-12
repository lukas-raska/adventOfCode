package year_2025.day_8;

import common.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Day8Solver implements Solver<Long, Long> {

    record Point(int x, int y, int z) {

        static Point parse(String csvData) {
            String[] split = csvData.split(",");
            return new Point(
                    Integer.parseInt(split[0]),
                    Integer.parseInt(split[1]),
                    Integer.parseInt(split[2])
            );
        }
    }

    record Distance(double distance, Point start, Point end) {}

    record Circuit(Set<Point> points) {

        Circuit(Point point) {
            this(new HashSet<>(Set.of(point)));
        }

        boolean sharesPoints(Point a,
                             Point b) {
            return this.points.contains(a) && this.points.contains(b);
        }
    }


    private final List<Point> points;
    private final List<Distance> distances;
    private final List<Circuit> circuits;

    public Day8Solver(List<String> puzzleInput) {
        this.points = puzzleInput.stream()
                .map(Point::parse)
                .toList();
        this.distances = getSortedDistances();
        this.circuits = points.stream()
                .map(Circuit::new)
                .collect(Collectors.toList());

    }

    private long getSquareDistance(Point a,
                                   Point b) {
        return (long) (Math.pow((a.x() - b.x()), 2) + Math.pow((a.y() - b.y()), 2) + Math.pow((a.z() - b.z()), 2));
    }

    private List<Distance> getSortedDistances() {

        List<Distance> distances = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            Point start = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point end = points.get(j);
                distances.add(new Distance(getSquareDistance(start, end), start, end));
            }
        }
        distances.sort(Comparator.comparing(Distance::distance));
        return distances;
    }

    private Circuit findByPoint(Point point) {
        return this.circuits.stream()
                .filter(circuit -> circuit.points().contains(point))
                .findFirst()
                .orElseThrow();
    }

    private void connect(Point a,
                         Point b) {
        Circuit first = findByPoint(a);
        Circuit second = findByPoint(b);
        first.points().addAll(second.points());
        circuits.remove(second);
    }

    @Override
    public Long part1() {
        int possibleConnections = 1000;
        Set<Distance> usedConnections = new HashSet<>();
        int distanceIndex = 0;
        while (usedConnections.size() != possibleConnections) {

            Distance current = distances.get(distanceIndex++);
            Point a = current.start();
            Point b = current.end();
            usedConnections.add(current);
            boolean notConnectedYet = circuits.stream()
                    .noneMatch(circuit -> circuit.sharesPoints(a, b));
            if (notConnectedYet) {
                connect(a, b);
            }
        }
        long result = circuits.stream()
                .map(circuit -> circuit.points().size())
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (n1, n2) -> n1 * n2);

        return result;
    }

    @Override
    public Long part2() {
        resetCircuits();
        int distanceIndex = 0;
        Distance last = distances.getFirst();
        while (this.circuits.size() > 1) {
            Distance current = distances.get(distanceIndex++);
            last = current;
            Point a = current.start();
            Point b = current.end();
            boolean notConnectedYet = circuits.stream()
                    .noneMatch(circuit -> circuit.sharesPoints(a, b));
            if (notConnectedYet) {
                connect(a, b);
            }
        }

        return (long) last.start().x() * last.end().x();
    }

    private void resetCircuits() {
        this.circuits.clear();
        this.circuits.addAll(
                points.stream()
                        .map(Circuit::new)
                        .collect(Collectors.toList()));

    }
}
