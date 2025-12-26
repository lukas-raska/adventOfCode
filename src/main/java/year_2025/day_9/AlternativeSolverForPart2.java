//package year_2025.day_9;
//
//import common.Solver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlternativeSolverForPart2 implements Solver<Long, Long> {
//
//
//
//    record Edge(double distance, Point start, Point end) {
//
//        static Edge MIN = new Edge(0d, new Point(0, 0), new Point(0, 0));
//    }
//
//    private final List<Point> points;
//
//    AlternativeSolverForPart2(List<String> puzzleInput) {
//        this.points = puzzleInput.stream()
//                .map(Point::parse)
//                .toList();
//    }
//
//
//    List<Edge> getLongestEdges() {
//        Edge longest = Edge.MIN;
//        Edge secondLongest = Edge.MIN;
//
//        int index = 0;
//        int size = points.size();
//        final Point start = points.getFirst();
//        Point current, next;
//        do {
//            current = points.get(index % size);
//            next = points.get((index + 1) % size);
//            double distance = Math.sqrt(Math.pow(current.row() - next.row(), 2) + Math.pow(current.col() - next.col(),
//                    2));
//            if (distance > longest.distance()) {
//                secondLongest = longest;
//                longest = new Edge(distance, current, next);
//            } else if (distance > secondLongest.distance()) {
//                secondLongest = new Edge(distance, current, next);
//            }
//            index++;
//        }
//        while (!next.equals(start));
//
//        return List.of(longest, secondLongest);
//    }
//
//
//    @Override
//    public Long part1() {
//        return 0L;
//    }
//
//    @Override
//    public Long part2() {
//        //find two long edges
//        var twoLongest = getLongestEdges();
//        System.out.println("Two longest distances (edges): " + twoLongest);
//
//        // find rows of Points with this distances
//        int cuttingRow1 = twoLongest.getFirst().start().row();
//        int cuttingRow2 = twoLongest.getLast().start().row();
//        System.out.println("Cutting row1: " + cuttingRow1);
//        System.out.println("Cutting row2: " + cuttingRow2);
//
//        // najdi horní a spodní část obrazce - tvořeno seznamem bodů
//        List<Point> upper = new ArrayList<>();
//        List<Point> lower = new ArrayList<>();
//        points.stream().forEach(point -> {
//            if (point.row() <= cuttingRow1) {
//                upper.add(point);
//            }
//            if (point.row() >= cuttingRow2) {
//                lower.add(point);
//            }
//        });
//        System.out.println("Upper: ");
//        upper.stream().forEach(System.out::println);
//        System.out.println("Lower: ");
//        lower.stream().forEach(System.out::println);
//
//        int minRow = upper.stream().mapToInt(Point::row).min().orElseThrow();
//        int maxRow = lower.stream().mapToInt(Point::row).max().orElseThrow();
//        System.out.println("Min row: " + minRow);
//        System.out.println("Max row: " + maxRow);
//        Point top = upper.stream().filter(p -> p.row() == minRow).findAny().orElseThrow();
//        Point bottom = lower.stream().filter(p -> p.row() == maxRow).findAny().orElseThrow();
//        /*
//        projdi každý bod a pro ten najdi protlehlý takový, že aby col protilehlého byl mezi row+ a row -
//         */
//
//        long maxArea = 0L;
//        //1 .kvadrant
//        int col = 0;
//        for (int i = 0; i < upper.size() / 2; i++) {
//            long area = 0;
//            int row = upper.get(i).row();
//            col = Math.max(col, upper.get(i).col());
//            //najdi opozici
//            int colOppMin = Integer.MAX_VALUE;
//            for (int j = upper.size() - 2; j > upper.size() / 2; j--) {
//                Point oppFirst = upper.get(j);
//                Point oppSec = upper.get(j - 1);
//                colOppMin = Math.min(colOppMin, oppFirst.col());
//                if (oppFirst.row() == row || oppSec.row() == row) {
//                    int colOp = Math.max(oppFirst.col(), oppSec.col());
//                    colOppMin = Math.min(colOppMin, colOp);
//                    int height = cuttingRow1 - row;
//                    int width = colOppMin - col;
//                    area = (long) height * width;
//                    maxArea = Math.max(maxArea, area);
//                }
//                if (oppFirst.row() > row && oppSec.row() < row) {
//                    //System.out.println("First col: " + first.col() + " - Second col: " + sec.col());
//                    int colOp = Math.max(oppFirst.col(), oppSec.col());
//                    colOppMin = Math.min(colOppMin, colOp);
//                    int height = cuttingRow1 - row;
//                    int width = colOppMin - col;
//                    area = (long) height * width;
//                    maxArea = Math.max(maxArea, area);
//                }
//            }
//        }
//
//        //2. kvadrant
//        col = Integer.MAX_VALUE;
//        for (int i = upper.size()-1; i > upper.size() / 2; i--) {
//            long area = 0;
//            int row = upper.get(i).row();
//            col = Math.min(col, upper.get(i).col());
//            //najdi opozici
//            int colOppMax = 0;
//            for (int j = 1; j < upper.size() / 2; j++) {
//                Point oppFirst = upper.get(j);
//                Point oppSec = upper.get(j - 1);
//                colOppMax = Math.min(colOppMax, oppFirst.col());
//                if (oppFirst.row() == row || oppSec.row() == row) {
//                    int colOp = Math.min(oppFirst.col(), oppSec.col());
//                    colOppMax = Math.min(colOppMax, colOp);
//                    int height = cuttingRow1 - row;
//                    int width = col - colOppMax;
//                    area = (long) height * width;
//                    maxArea = Math.max(maxArea, area);
//                }
//                if (oppFirst.row() > row && oppSec.row() < row) {
//                    //System.out.println("First col: " + first.col() + " - Second col: " + sec.col());
//                    int colOp = Math.max(oppFirst.col(), oppSec.col());
//                    colOppMax = Math.max(colOppMax, colOp);
//                    int height = cuttingRow1 - row;
//                    int width = col - colOppMax;
//                    area = (long) height * width;
//                    maxArea = Math.max(maxArea, area);
//                }
//            }
//        }
//
//        return maxArea;
//        //2 229 239 624 too high
//        //16472913 too low
//        //1097422599
//
//
//    }
//    }
