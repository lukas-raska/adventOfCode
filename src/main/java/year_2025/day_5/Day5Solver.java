package year_2025.day_5;

import java.util.Arrays;
import java.util.Comparator;

public class Day5Solver {
    private final long[][] ranges;
    private final long[] ids;

    Day5Solver(String puzzleInput) {
        ranges = puzzleInput.lines()
                .filter(s -> s.matches("\\d+-\\d+"))
                .map(Day5Solver::parseRange)
                .toArray(long[][]::new);
        ids = puzzleInput.lines()
                .filter(s -> s.matches("\\d+"))
                .mapToLong(Day5Solver::parseId)
                .toArray();
    }

    long part1() {
        return Arrays.stream(ids)
                .filter(this::fitInSomeRange)
                .count();
    }

    long part2() {
        long[][] sortedRanges = Arrays.stream(ranges)
                .sorted(Comparator.comparingLong(r -> r[0]))
                .toArray(long[][]::new);

        long start = sortedRanges[0][0];
        long end = sortedRanges[0][1];
        long cnt = end - start + 1;
        long previousEnd = end;
        for (int i = 1; i < sortedRanges.length; i++) {
            start = sortedRanges[i][0];
            end = sortedRanges[i][1];
            if (start <= previousEnd) {
                start = previousEnd + 1;
            }
            if (end < previousEnd){
                continue;
            }

            cnt += (end - start + 1);
            previousEnd = end;

        }
        return cnt;

    }


    private boolean fitInSomeRange(long id) {
        for (var range : ranges) {
            long start = range[0];
            long end = range[1];
            if (id >= start && id <= end) {
                return true;
            }
        }
        return false;
    }

    private static long[] parseRange(String data) {
        String[] split = data.split("-");
        long start = Long.parseLong(split[0].trim());
        long end = Long.parseLong(split[1].trim());
        return new long[]{start, end};
    }

    private static long parseId(String data) {
        return Long.parseLong(data.trim());
    }
}